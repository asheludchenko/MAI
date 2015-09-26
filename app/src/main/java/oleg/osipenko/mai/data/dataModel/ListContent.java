package oleg.osipenko.mai.data.dataModel;

import android.text.Spanned;

import java.util.Arrays;
import java.util.List;

import oleg.osipenko.mai.presentation.utils.SimpleSectionListAdapter;

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
    private boolean withSections;
    private SimpleSectionListAdapter.Section[] sections;
    private boolean clickable;
    private boolean dialogable;
    private String link;
    private Spanned albumTitle;
    private List<Photo> photos;

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

    public SimpleSectionListAdapter.Section[] getSections() {
        return sections;
    }

    public boolean isWithSections() {
        return withSections;
    }

    public boolean isClickable() {
        return clickable;
    }

    public boolean isDialogable() {
        return dialogable;
    }

    public String getLink() {
        return link;
    }

    public Spanned getAlbumTitle() {
        return albumTitle;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "ListContent{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", sub2='" + sub2 + '\'' +
                ", sub3='" + sub3 + '\'' +
                ", sub4='" + sub4 + '\'' +
                ", withImage=" + withImage +
                ", withSections=" + withSections +
                ", sections=" + Arrays.toString(sections) +
                ", clickable=" + clickable +
                ", dialogable=" + dialogable +
                ", link='" + link + '\'' +
                ", albumTitle=" + albumTitle +
                ", photos=" + photos +
                '}';
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

        public Builder setWithSections(boolean withSections) {
            content.withSections = withSections;
            return this;
        }

        public Builder setSections(SimpleSectionListAdapter.Section[] sections) {
            content.sections = sections;
            return this;
        }

        public Builder setClickable() {
            content.clickable = true;
            return this;
        }

        public Builder setDialogable() {
            content.dialogable = true;
            return this;
        }

        public Builder setLink(String link) {
            content.link = link;
            return this;
        }

        public Builder setAlbumTitle(Spanned title) {
            content.albumTitle = title;
            return this;
        }

        public Builder setPhotos(List<Photo> photos) {
            content.photos = photos;
            return this;
        }

        public ListContent build() {
            return content;
        }
    }
}
