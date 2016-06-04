package oleg.osipenko.mai.data.api;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by olegosipenko on 03.09.15.
 */
public interface MaiService {

    @GET("/events/news/jsonp_detail.php")
    Observable<Response> getNewsById(@Query(value = "id", encodeValue = false) String id);

    @GET("/events/news/jsonp_list.php")
    Observable<Response> getNewsList(@Query(value = "page", encodeValue = false) int page, @Query(value = "pagesize", encodeValue = false) int pagesize);

    @GET("/events/news/jsonp_albums.php")
    Observable<Response> getAlbums(@Query(value = "page", encodeValue = false) int page, @Query(value = "pagesize", encodeValue = false) int pagesize);

    @GET("/events/news/jsonp_album.php")
    Observable<Response> getPhotos(@Query(value = "id", encodeValue = false) String id);
}
