package ru.mai.app.domain.executors

import rx.Scheduler

/**
 * Created by olegosipenko on 05.09.15.
 */
public interface PostExecutionThread {
    public fun getScheduler(): Scheduler
}