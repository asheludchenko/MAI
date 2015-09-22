package oleg.osipenko.mai.data.dataModel;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticListContent {
    private String title;
    private String image;
    private String text;
    private String listTitle;
    private String listImage;
    private boolean listWithImage;
    private boolean listClickable;

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

    public String getListTitle() {
        return listTitle;
    }

    public String getListImage() {
        return listImage;
    }

    public boolean isListWithImage() {
        return listWithImage;
    }

    public boolean isListClickable() {
        return listClickable;
    }

    @Override
    public String toString() {
        return "StaticListContent{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", listTitle='" + listTitle + '\'' +
                ", listImage='" + listImage + '\'' +
                ", listWithImage=" + listWithImage +
                ", listClickable=" + listClickable +
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

        public Builder setListTitle(String listTitle) {
            content.listTitle= listTitle;
            return this;
        }

        public Builder setListImage(String listImage) {
            content.listImage = listImage;
            return this;
        }

        public Builder setWithImage(boolean withImage) {
            content.listWithImage = withImage;
            return this;
        }

        public Builder setListClickable() {
            content.listClickable = true;
            return this;
        }

        public StaticListContent build() {
            return content;
        }
    }
}
