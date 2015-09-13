package oleg.osipenko.mai.data.dataModel;

import java.util.List;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticListContent {
    private String title;
    private String image;
    private String text;
    private List<ListContent> lists;

    public StaticListContent() {
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public List<ListContent> getLists() {
        return lists;
    }

    @Override
    public String toString() {
        return "StaticListContent{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", lists=" + lists +
                '}';
    }

    public static class Builder {
        private StaticListContent content;

        public Builder() {
            content = new StaticListContent();
        }

        public Builder setTitle(String title) {
            content.title = title;
            return this;
        }

        public Builder setImage(String image) {
            content.image = image;
            return this;
        }

        public Builder setText(String text) {
            content.text = text;
            return this;
        }

        public Builder setList(List<ListContent> list) {
            content.lists = list;
            return this;
        }

        public StaticListContent build() {
            return content;
        }
    }
}
