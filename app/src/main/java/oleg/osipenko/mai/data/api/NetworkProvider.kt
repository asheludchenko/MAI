package oleg.osipenko.mai.data.api

import android.os.Build
import android.text.Html
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import oleg.osipenko.mai.data.dataModel.ListContent
import oleg.osipenko.mai.data.dataModel.Photo
import oleg.osipenko.mai.data.dataModel.StaticContent
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification
import retrofit.RestAdapter
import retrofit.client.Response
import rx.Observable
import rx.Subscriber
import rx.functions.Func0
import java.io.IOException
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.CountDownLatch

/**
 * Created by olegosipenko on 24.09.15.
 */
public class NetworkProvider() {

    private val adapter: RestAdapter
    var service: MaiService

    init {
        adapter = RestAdapter.Builder().setEndpoint("http://mai.ru/").setLogLevel(RestAdapter.LogLevel.FULL).build()
        service = adapter.create(MaiService::class.java)
    }

    public fun getNews(): Observable<List<ListContent>> {
        val headersList = ArrayList<NewsHeadersResponse>()
        val barrier: CountDownLatch = CountDownLatch(1);
        val thread: Thread = Thread(object : Runnable {
            override fun run() {
                val response = service.getNewsList(1, 30)
                try {
                    var json = trimJson(trimResponse(response))
                    val gson = Gson()
                    val reader = JsonReader(StringReader(json))
                    reader.isLenient = true
                    val itemsMapType = object : TypeToken<Map<Int, NewsHeadersResponse>>() {}.type
                    val headers = gson.fromJson<Map<Int, NewsHeadersResponse>>(reader, itemsMapType)
                    headersList.addAll(headers.values())
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    barrier.countDown()
                }
            }
        })
        thread.start()
        barrier.await()

        if (!headersList.isEmpty()) {
            val ns: List<ListContent> = Observable.from(headersList)
                    .map { header ->
                        ListContent.Builder()
                                .setLink(header.id).setImage(header.photo).setText(header.header).build()
                    }
                    .toList()
                    .toBlocking()
                    .single()
            return Observable.create(object : Observable.OnSubscribe<List<ListContent>> {
                override fun call(t: Subscriber<in List<ListContent>>?) {
                    t?.onNext(ns)
                    t?.onCompleted()
                }
            })
        } else {
            return Observable.empty<List<ListContent>>()
        }

    }

    public fun getNewsById(specification: NewsContentSpecification): Observable<List<StaticContent>> {
        val id = specification.getItem()
        var singleNews: SingleNews? = null
        val barrier: CountDownLatch = CountDownLatch(1)
        val thread: Thread = Thread(object : Runnable {
            override fun run() {
                val response = service.getNewsById(id.toString())
                try {
                    val json = trimResponse(response)
                    val gson = Gson()
                    singleNews = gson.fromJson(json, SingleNews::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    barrier.countDown()
                }
            }
        })
        thread.start()
        barrier.await()
        if (singleNews != null) {
            val title: StaticContent = StaticContent.Builder().setFacTitile(singleNews?.header).build()
            val text: StaticContent = StaticContent.Builder()
                    .setNewsText(
                            Html.fromHtml(singleNews?.getBody()?.replaceAll("\t", ""))
                    ).build()
            val image: StaticContent = StaticContent.Builder().setImage(singleNews?.photo).build()
            val date: StaticContent = StaticContent.Builder().setText(singleNews?.date).build()
            return Observable.just(title)
                    .mergeWith(Observable.just(image))
                    .mergeWith(Observable.just(date))
                    .mergeWith(Observable.just(text))
                    .toList()
        } else {
            return Observable.empty<List<StaticContent>>()
        }
    }

    public fun getPhotoAlbums(): Observable<List<ListContent>> {
        var ps: List<ListContent>? = null
        val barrier: CountDownLatch = CountDownLatch(1)
        var response: Response? = null
        var als: List<ListContent>? = null
        val thread: Thread = Thread(object : Runnable {
            override fun run() {
                response = service.getAlbums(1, 30)
                var json: String = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, PhotosListResponse>>() {}.type
                val albums = gson.fromJson<Map<Int, PhotosListResponse>>(reader, itemsMapType)
                als = Observable.from(albums.values())
                        .map { photoAlbum ->
                            ListContent.Builder()
                                    .setAlbumTitle(Html.fromHtml(photoAlbum.name))
                                    .setPhotos(getPhotos(photoAlbum.id))
                                    .build()
                        }
                        .toList()
                        .toBlocking()
                        .single()
                barrier.countDown()
            }
        })
        thread.start()
        barrier.await()

        return Observable.defer(object : Func0<Observable<List<ListContent>>> {
            override fun call(): Observable<List<ListContent>>? {
                val response = service.getAlbums(1, 10)
                val json = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, PhotosListResponse>>() {}.type
                val albums = gson.fromJson<Map<Int, PhotosListResponse>>(reader, itemsMapType)
                return Observable.from(albums.values())
                        .map { photoAlbum ->
                            ListContent.Builder()
                                    .setAlbumTitle(Html.fromHtml(photoAlbum.name))
                                    .setPhotos(getPhotos(photoAlbum.id))
                                    .build()
                        }
                        .toList()
            }

        })

    }

    private fun getPhotos(id: String?): List<Photo>? {
        var response: Response = service.getPhotos(id)
        var json: String = trimJson(trimResponse(response))
        val gson = Gson()
        val reader = JsonReader(StringReader(json))
        reader.isLenient = true
        val itemsMapType = object : TypeToken<Map<Int, Photo>>() {}.type
        var photos: List<Photo>? = null
        try {
            photos = ArrayList<Photo>(gson.fromJson<Map<Int, Photo>>(reader, itemsMapType).values())
        } catch (e: Exception) {
            photos = Collections.emptyList()
        }
        return photos
    }

    private fun trimResponse(response: Response?): String {
        val `in` = response?.body?.`in`()
        val n = `in`?.available()
        val bytes = ByteArray(n as Int)
        `in`?.read(bytes, 0, n)
        var s: String? = null
        if (Build.VERSION.SDK_INT < 19) {
            s = String(bytes, Charset.forName("UTF-8"))
        } else {
            s = String(bytes, StandardCharsets.UTF_8)
        }
        val endPos = s.length() - 1
        return s.substring(1, endPos)
    }

    private fun trimJson(json: String): String {
        val trimmedJson = json.split("count")[0]
        return trimmedJson.substring(0, trimmedJson.length() - 2) + "}"
    }
}
