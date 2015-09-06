package oleg.osipenko.mai.domain

import oleg.osipenko.mai.domain.executors.PostExecutionThread
import oleg.osipenko.mai.domain.executors.ThreadExecutor
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

/**
 * Created by olegosipenko on 05.09.15.
 */
public abstract class Interactor protected constructor(private val threadExecutor: ThreadExecutor, private val postExecutionThread: PostExecutionThread) {

    private var subscription = Subscriptions.empty()

    protected abstract fun buildObservable(): Observable<Any>

    public fun execute(UseCaseSubscriber: Subscriber<Any>) {
        this.subscription = this.buildObservable().subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler()).subscribe(UseCaseSubscriber)
    }

    public fun unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe()
        }
    }
}
