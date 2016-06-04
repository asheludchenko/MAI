package oleg.osipenko.mai.data.api;

import java.util.List;

/**
 * Created by olegosipenko on 27.09.15.
 */
public class YoutubeApiResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        private Id id;

        public Id getId() {
            return id;
        }
    }

    public static class Id {
        private String videoId;

        public String getVideoId() {
            return videoId;
        }
    }
}
