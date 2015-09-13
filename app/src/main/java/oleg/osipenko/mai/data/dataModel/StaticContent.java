package oleg.osipenko.mai.data.dataModel;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticContent {
    private String title;
    private String image;
    private String text;

    public StaticContent() {
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

    public static class Builder {
        private StaticContent staticContent;

        public Builder() {
            staticContent = new StaticContent();
        }

        public Builder setTitle(String title) {
            staticContent.title = title;
            return this;
        }

        public Builder setImage(String image) {
            staticContent.image = image;
            return this;
        }

        public Builder setText(String text) {
            staticContent.text = text;
            return this;
        }

        public StaticContent build() {
            return staticContent;
        }
    }
}
