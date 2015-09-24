package oleg.osipenko.mai.data.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by olegosipenko on 03.09.15.
 */
public interface MaiService {

    @GET("/events/news/jsonp_detail.php")
    public void getNewsById(@Query(value = "id", encodeValue = false) String id, Callback<Response> callback);

    @GET("/events/news/jsonp_list.php")
    public void getNewsList(@Query(value = "page", encodeValue = false) int page, @Query(value = "pagesize", encodeValue = false) int pagesize, Callback<Response> callback);
}
