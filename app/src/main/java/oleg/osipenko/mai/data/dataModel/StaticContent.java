package oleg.osipenko.mai.data.dataModel;

import android.text.Spanned;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import oleg.osipenko.mai.presentation.utils.Map;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticContent {
    private String title;
    private String image;
    private String text;
    private String facTitle;
    private String facPhoto;
    private String facText;
    private Spanned newsText;
    private Map map;
    private Spanned author;
    private String button;

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

    public String getFacTitle() {
        return facTitle;
    }

    public String getFacPhoto() {
        return facPhoto;
    }

    public String getFacText() {
        return facText;
    }

    public Spanned getNewsText() {
        return newsText;
    }

    public Map getMap() {
        return map;
    }

    public Spanned getAuthor() {
        return author;
    }

    public String getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "StaticContent{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", facTitle='" + facTitle + '\'' +
                ", facPhoto='" + facPhoto + '\'' +
                ", facText='" + facText + '\'' +
                ", newsText=" + newsText +
                ", map=" + map +
                ", author=" + author +
                ", button='" + button + '\'' +
                '}';
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

        public Builder setFacTitile(String facTitile) {
            staticContent.facTitle = facTitile;
            return this;
        }

        public Builder setFacPhoto(String facPhoto) {
            staticContent.facPhoto = facPhoto;
            return this;
        }

        public Builder setFacText(String facText) {
            staticContent.facText = facText;
            return this;
        }

        public Builder setNewsText(Spanned spannable) {
            staticContent.newsText = spannable;
            return this;
        }

        public Builder setMap(Map map) {
            staticContent.map = map;
            return this;
        }

        public Builder setButton(String url) {
            staticContent.button = url;
            return this;
        }

        public StaticContent build() {
            return staticContent;
        }

        @NotNull
        public Builder setAuthor(@Nullable Spanned author) {
            staticContent.author = author;
            return this;
        }
    }
}
