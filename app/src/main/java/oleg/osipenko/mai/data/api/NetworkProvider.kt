package oleg.osipenko.mai.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import oleg.osipenko.mai.data.dataModel.ListContent
import oleg.osipenko.mai.data.dataModel.StaticContent
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import rx.Observable
import rx.Subscriber
import java.io.IOException
import java.io.StringReader
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
        val headersList = ArrayList<NewsHeadersResponse.Content>()
        val barrier : CountDownLatch = CountDownLatch(1);
        val thread : Thread = Thread(object : Runnable {
            override fun run() {
                val response = service.getNewsList(1, 30)
                try {
                    val `in` = response.body.`in`()
                    val n = `in`.available()
                    val bytes = ByteArray(n)
                    `in`.read(bytes, 0, n)
                    val s = String(bytes, StandardCharsets.UTF_8)
                    val endPos = s.length() - 1
                    var json = s.substring(1, endPos)
                    val trimmedJson = json.split("count")[0]
                    json = trimmedJson.substring(0, trimmedJson.length() - 2) + "}"
                    val gson = Gson()
                    val reader = JsonReader(StringReader(json))
                    reader.isLenient = true
                    val itemsMapType = object : TypeToken<Map<Int, NewsHeadersResponse.Content>>() {

                    }.type
                    val headers = gson.fromJson<Map<Int, NewsHeadersResponse.Content>>(reader, itemsMapType)
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
                    .map { header -> ListContent.Builder().setImage(header.getPhoto()).setText(header.getHeader()).build() }
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

    public fun getNewsById(specification: StaticContentSpecification): Observable<List<StaticContent>> {
        val id = specification.item
        service.getNewsById(id, object : Callback<Response> {
            override fun success(response1: Response, response: Response) {
                try {
                    val `in` = response1.body.`in`()
                    val n = `in`.available()
                    val bytes = ByteArray(n)
                    `in`.read(bytes, 0, n)
                    val s = String(bytes, StandardCharsets.UTF_8)
                    val endPos = s.length() - 1
                    val json = s.substring(1, endPos)
                    val gson = Gson()
                    val singleNews = gson.fromJson(json, SingleNews::class.java)
                    Log.d("aaa", singleNews.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun failure(error: RetrofitError) {
                Log.d("aaa", error.getMessage())
            }
        })
        return Observable.empty<List<StaticContent>>()
    }
}
