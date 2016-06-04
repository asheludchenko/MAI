package oleg.osipenko.mai.data.api;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by olegosipenko on 27.09.15.
 */
public interface YoutubeService {
    @GET("/youtube/v3/search")
    YoutubeApiResponse getVideos(
            @Query(value = "key", encodeValue = false) String id,
            @Query(value = "channelId", encodeValue = false) String channelId,
            @Query(value = "part", encodeValue = false) String part,
            @Query(value = "order", encodeValue = false) String order,
            @Query(value = "maxResults", encodeValue = false) int max
    );

}
