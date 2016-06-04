package oleg.osipenko.mai.presentation.events;

/**
 * Created by olegosipenko on 05.04.16.
 */
public class ChangeSelectedTabEvent {
    private final int position;

    public ChangeSelectedTabEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "ChangeSelectedTabEvent{" +
                "position=" + position +
                '}';
    }
}
