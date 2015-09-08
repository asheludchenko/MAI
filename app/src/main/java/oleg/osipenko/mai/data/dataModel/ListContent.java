package oleg.osipenko.mai.data.dataModel;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListContent {
    private String text;
    private String image;
    private String title;
    private String sub2;
    private String sub3;
    private String sub4;
    private boolean withImage;

    public ListContent() {
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSub2() {
        return sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public String getSub4() {
        return sub4;
    }

    public boolean isWithImage() {
        return withImage;
    }

    public static class Builder {
        private ListContent content;

        public Builder() {
            content = new ListContent();
        }

        public Builder setText(String text) {
            content.text = text;
            return this;
        }

        public Builder setImage(String image) {
            content.image = image;
            return this;
        }

        public Builder setTitle(String title) {
            content.title = title;
            return this;
        }

        public Builder setSub2(String sub2) {
            content.sub2 = sub2;
            return this;
        }

        public Builder setSub3(String sub3) {
            content.sub3 = sub3;
            return this;
        }

        public Builder setSub4(String sub4) {
            content.sub4 = sub4;
            return this;
        }

        public Builder setWithImage(boolean withImage) {
            content.withImage = withImage;
            return this;
        }

        public ListContent build() {
            return content;
        }
    }
}
