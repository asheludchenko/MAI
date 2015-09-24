package oleg.osipenko.mai.data.api;

/**
 * Created by olegosipenko on 03.09.15.
 */
public class SingleNews {
    private String header;
    private String photo;
    private String date;
    private String author;
    private String body;

    public String getHeader() {
        return header;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "SingleNews{" +
                "header='" + header + '\'' +
                ", photo='" + photo + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
