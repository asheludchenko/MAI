package oleg.osipenko.mai.presentation.events;

/**
 * Created by olegosipenko on 22.09.15.
 */
public class ChangeScreenEvent {
    private final String title;

    public ChangeScreenEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ChangeScreenEvent{" +
                "title='" + title + '\'' +
                '}';
    }
}
