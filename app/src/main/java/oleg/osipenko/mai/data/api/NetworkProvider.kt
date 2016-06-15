package oleg.osipenko.mai.data.api

import android.os.Build
import android.text.Html
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.parse.ParseQuery
import com.squareup.okhttp.OkHttpClient
import oleg.osipenko.mai.App
import oleg.osipenko.mai.BuildConfig
import oleg.osipenko.mai.PAGE_LIM
import oleg.osipenko.mai.data.dataModel.ListContent
import oleg.osipenko.mai.data.dataModel.NewsHeadersContent
import oleg.osipenko.mai.data.dataModel.Photo
import oleg.osipenko.mai.data.dto.ScheduleCourses
import oleg.osipenko.mai.data.dto.ScheduleFaculties
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.client.Response
import rx.Observable
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by olegosipenko on 24.09.15.
 */
class NetworkProvider() {

    private val adapter: RestAdapter
    private lateinit var maiService: MaiService

    init {
        val okClient = OkHttpClient()
        val builder = RestAdapter.Builder()
                .setEndpoint("http://mai.ru/")
                .setClient(OkClient(okClient))
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL)
        }
        adapter = builder.build()
        maiService = adapter.create(MaiService::class.java)
    }

    fun getNews(page: Int): Observable<List<NewsHeadersContent>> {
        return Observable.defer {
            val response: Response? = maiService.getNewsList(page, PAGE_LIM)
                    .retry(3)
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
                Observable.error(Exception("Error loading news"))
            }
        }
    }

    fun getNewsById(specification: NewsContentSpecification): Observable<List<oleg.osipenko.mai.data.dataModel.StaticContent>> {
        val id = specification.getItem()
        return Observable.defer {
            val response: Response? = maiService.getNewsById(id)
                    .retry(3)
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
                val title: oleg.osipenko.mai.data.dataModel.StaticContent = oleg.osipenko.mai.data.dataModel.StaticContent.Builder().setFacTitile(singleNews?.header).build()
                val text: oleg.osipenko.mai.data.dataModel.StaticContent = oleg.osipenko.mai.data.dataModel.StaticContent.Builder()
                        .setNewsText(
                                Html.fromHtml(singleNews?.body?.replace("\t", ""))
                        ).build()
                val image: oleg.osipenko.mai.data.dataModel.StaticContent = oleg.osipenko.mai.data.dataModel.StaticContent.Builder().setImage(singleNews?.photo).build()
                val date: oleg.osipenko.mai.data.dataModel.StaticContent = oleg.osipenko.mai.data.dataModel.StaticContent.Builder().setText(singleNews?.date?.split(" ")?.get(0)).build()
                val author: oleg.osipenko.mai.data.dataModel.StaticContent = oleg.osipenko.mai.data.dataModel.StaticContent.Builder().setAuthor(Html.fromHtml("<b>Автор:</b> ${singleNews?.author}")).build()
                Observable.just(title)
                        .mergeWith(Observable.just(image))
                        .mergeWith(Observable.just(date))
                        .mergeWith(Observable.just(text))
                        .mergeWith(Observable.just(author))
                        .toList()
                        .cache()
            } else {
                Observable.error(Exception("Error loading news"))
            }
        }
    }

    fun getPhotoAlbums(): Observable<List<ListContent>> {
        return Observable.defer {
            val response: Response? = maiService.getAlbums(App.getPhotoPage(), 10)
                    .retry(3)
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
