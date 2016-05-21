package ru.mai.app.data.api

import android.os.Build
import android.text.Html
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.parse.ParseQuery
import com.squareup.okhttp.OkHttpClient
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.client.Response
import ru.mai.app.App
import ru.mai.app.BuildConfig
import ru.mai.app.data.dataModel.ListContent
import ru.mai.app.data.dataModel.NewsHeadersContent
import ru.mai.app.data.dataModel.Photo
import ru.mai.app.data.dataModel.StaticContent
import ru.mai.app.data.dto.ScheduleCourses
import ru.mai.app.data.dto.ScheduleFaculties
import ru.mai.app.data.repository.specification.NewsContentSpecification
import rx.Observable
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by olegosipenko on 24.09.15.
 */
class NetworkProvider() {

    private val adapter: RestAdapter
    private lateinit var maiService: MaiService

    init {
        val okClient = OkHttpClient()
        okClient.setConnectTimeout(1, TimeUnit.MINUTES)
        okClient.setReadTimeout(1, TimeUnit.MINUTES)
        val builder = RestAdapter.Builder()
                .setEndpoint("http://mai.ru/")
                .setClient(OkClient(okClient))
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL)
        }
        adapter = builder.build()
        maiService = adapter.create(MaiService::class.java)
    }

    fun getNews(): Observable<List<NewsHeadersContent>> {
        return Observable.defer {
            val response: Response? = maiService.getNewsList(App.getNewsPage(), 8)
                    .retry(3)
                    .timeout(60, TimeUnit.SECONDS, Observable.error(Exception("TimeOut")))
                    .onErrorReturn { e -> null }
                    .toBlocking()
                    .single()
            if (response != null) {
                val json = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, NewsHeadersResponse>>() {}.type
                val headers = gson.fromJson<Map<Int, NewsHeadersResponse>>(reader, itemsMapType)
                Observable.from(headers.values)
                        .map { header -> NewsHeadersContent(header.header, header.detail_photo, header.id) }
                        .toList()
                        .cache()
            } else {
                Observable.empty()
            }
        }
    }

    fun getNewsById(specification: NewsContentSpecification): Observable<List<StaticContent>> {
        val id = specification.getItem()
        return Observable.defer {
            val response: Response? = maiService.getNewsById(id)
                    .retry(3)
                    .timeout(60, TimeUnit.SECONDS, Observable.error(Exception("TimeOut")))
                    .onErrorReturn { e -> null }
                    .toBlocking()
                    .single()
            if (response != null) {
                val json = trimResponse(response)
                val gson = Gson()
                var singleNews: SingleNews? = null
                try {
                    val reader = JsonReader(StringReader(json))
                    reader.isLenient = true
                    singleNews = gson.fromJson(reader, SingleNews::class.java)
                } catch(e: Exception) {
                    Log.e("MAI", e.toString())
                }
                val title: StaticContent = StaticContent.Builder().setFacTitile(singleNews?.header).build()
                val text: StaticContent = StaticContent.Builder()
                        .setNewsText(
                                Html.fromHtml(singleNews?.body?.replace("\t", ""))
                        ).build()
                val image: StaticContent = StaticContent.Builder().setImage(singleNews?.photo).build()
                val date: StaticContent = StaticContent.Builder().setText(singleNews?.date?.split(" ")?.get(0)).build()
                val author: StaticContent = StaticContent.Builder().setAuthor(Html.fromHtml("<b>Автор:</b> ${singleNews?.author}")).build()
                Observable.just(title)
                        .mergeWith(Observable.just(image))
                        .mergeWith(Observable.just(date))
                        .mergeWith(Observable.just(text))
                        .mergeWith(Observable.just(author))
                        .toList()
                        .cache()
            } else {
                Observable.empty()
            }
        }
    }

    fun getPhotoAlbums(): Observable<List<ListContent>> {
        return Observable.defer {
            val response: Response? = maiService.getAlbums(App.getPhotoPage(), 10)
                    .retry(3)
                    .timeout(60, TimeUnit.SECONDS, Observable.error(Exception("TimeOut")))
                    .onErrorReturn { e -> null }
                    .toBlocking()
                    .single()
            if (response != null) {
                val json = trimJson(trimResponse(response))
                val gson = Gson()
                val reader = JsonReader(StringReader(json))
                reader.isLenient = true
                val itemsMapType = object : TypeToken<Map<Int, PhotosListResponse>>() {}.type
                val albums = gson.fromJson<Map<Int, PhotosListResponse>>(reader, itemsMapType)
                Observable.from(albums.values)
                        .map { photoAlbum ->
                            ListContent.Builder()
                                    .setAlbumTitle(Html.fromHtml(photoAlbum.name))
                                    .setPhotos(getPhotos(photoAlbum.id))
                                    .build()
                        }
                        .toList()
                        .cache()
            } else {
                Observable.empty()
            }
        }
    }

    private fun getPhotos(id: String?): List<Photo>? {
        val response: Response? = maiService.getPhotos(id)
                .retry(3)
                .timeout(60, TimeUnit.SECONDS, Observable.error(Exception("TimeOut")))
                .onErrorReturn { e -> null }
                .toBlocking()
                .single()
        if (response != null) {
            val json: String = trimJson(trimResponse(response))
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
        } else {
            return emptyList()
        }
    }

    private fun trimResponse(response: Response?): String {
        val `in` = response?.body?.`in`()
        val n = `in`?.available()
        val bytes = ByteArray(n as Int)
        `in`?.read(bytes, 0, n)
        val s: String?
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

    fun getSchedFaculties(): List<ScheduleFaculties> {
        if (App.getFaculties().isEmpty()) {
            val facultiesQuery: ParseQuery<ScheduleFaculties> = ParseQuery.getQuery("Schedule_faculties")
            val faculties = facultiesQuery.find()
            App.setFaculties(faculties)
            return faculties
        } else {
            return App.getFaculties()
        }
    }

    fun getScheduleCourses(facultyId: String): List<ScheduleCourses> {
        if (App.getCourses().isEmpty()) {
            val coursesQuery: ParseQuery<ScheduleCourses> = ParseQuery.getQuery("Shedule_courses")
            coursesQuery.whereEqualTo("facultyId", facultyId)
            val courses = coursesQuery.find()
            App.setCourses(courses)
            return courses
        } else {
            return App.getCourses()
        }
    }
}
