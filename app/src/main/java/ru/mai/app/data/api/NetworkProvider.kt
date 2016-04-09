package ru.mai.app.data.api

import android.os.Build
import android.text.Html
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import retrofit.RestAdapter
import retrofit.client.Response
import ru.mai.app.App
import ru.mai.app.data.dataModel.ListContent
import ru.mai.app.data.dataModel.Photo
import ru.mai.app.data.dataModel.StaticContent
import ru.mai.app.data.repository.specification.NewsContentSpecification
import rx.Observable
import rx.functions.Func0
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by olegosipenko on 24.09.15.
 */
class NetworkProvider() {

    private val adapter: RestAdapter
    var service: MaiService

    init {
        adapter = RestAdapter.Builder().setEndpoint("http://mai.ru/")
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                 .build()
        service = adapter.create(MaiService::class.java)
    }

    fun getNews(): Observable<List<ListContent>> {
        return Observable.defer(object : Func0<Observable<List<ListContent>>> {
            override fun call(): Observable<List<ListContent>>? {
                val response = service.getNewsList(App.getNewsPage(), 8)
                val json = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, NewsHeadersResponse>>() {}.type
                val headers = gson.fromJson<Map<Int, NewsHeadersResponse>>(reader, itemsMapType)
                return Observable.from(headers.values)
                        .map { header ->
                            ListContent.Builder()
                                    .setLink(header.id).setImage(header.photo).setText(header.header).build()
                        }
                        .toList()
                        .cache()
            }
        })
    }

    fun getNewsById(specification: NewsContentSpecification): Observable<List<StaticContent>> {
        val id = specification.getItem()
        return Observable.defer(object : Func0<Observable<List<StaticContent>>> {
            override fun call(): Observable<List<StaticContent>>? {
                val response = service.getNewsById(id)
                val json = trimResponse(response)
                val gson = Gson()
                val singleNews = gson.fromJson(json, SingleNews::class.java)
                val title: StaticContent = StaticContent.Builder().setFacTitile(singleNews?.header).build()
                val text: StaticContent = StaticContent.Builder()
                        .setNewsText(
                                Html.fromHtml(singleNews?.body?.replace("\t", ""))
                        ).build()
                val image: StaticContent = StaticContent.Builder().setImage(singleNews?.photo).build()
                val date: StaticContent = StaticContent.Builder().setText(singleNews?.date).build()
                val author: StaticContent = StaticContent.Builder().setAuthor(Html.fromHtml("<b>Автор:</b> ${singleNews.author}")).build()
                return Observable.just(title)
                        .mergeWith(Observable.just(image))
                        .mergeWith(Observable.just(date))
                        .mergeWith(Observable.just(text))
                        .mergeWith(Observable.just(author))
                        .toList()
                        .cache()
            }
        })
    }

    fun getPhotoAlbums(): Observable<List<ListContent>> {
        return Observable.defer(object : Func0<Observable<List<ListContent>>> {
            override fun call(): Observable<List<ListContent>>? {
                val response = service.getAlbums(App.getPhotoPage(), 10)
                val json = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, PhotosListResponse>>() {}.type
                val albums = gson.fromJson<Map<Int, PhotosListResponse>>(reader, itemsMapType)
                return Observable.from(albums.values)
                        .map { photoAlbum ->
                            ListContent.Builder()
                                    .setAlbumTitle(Html.fromHtml(photoAlbum.name))
                                    .setPhotos(getPhotos(photoAlbum.id))
                                    .build()
                        }
                        .toList()
                        .cache()
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
            photos = ArrayList<Photo>(gson.fromJson<Map<Int, Photo>>(reader, itemsMapType).values)
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
        val endPos = s.length - 1
        return s.substring(1, endPos)
    }

    private fun trimJson(json: String): String {
        val trimmedJson = json.split("count")[0]
        return trimmedJson.substring(0, trimmedJson.length - 2) + "}"
    }
}
