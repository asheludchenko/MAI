package ru.mai.app.presentation.events;

/**
 * Created by olegosipenko on 05.04.16.
 */
public class SwipePageEvent {
    private final int position;

    public SwipePageEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "SwipePageEvent{" +
                "position=" + position +
                '}';
    }
}
