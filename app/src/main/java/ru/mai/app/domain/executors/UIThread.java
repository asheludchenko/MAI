package ru.mai.app.domain.executors;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by olegosipenko on 12.08.15.
 */
public class UIThread implements PostExecutionThread {

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
