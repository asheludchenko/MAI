package oleg.osipenko.mai.data.api;

import java.util.List;

/**
 * Created by olegosipenko on 24.09.15.
 */
public class NewsHeadersResponse {

    List<Content> headers;

    public static class Content {
        private String id;
        private String header;
        private String photo;
        private String date;
    }
}
