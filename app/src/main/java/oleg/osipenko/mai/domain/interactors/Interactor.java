package oleg.osipenko.mai.domain.interactors;

import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by olegosipenko on 07.09.15.
 */
public abstract class Interactor<T, R> {
    public static final String STATIC_CONTENT = "getStaticContentInteractor";
    public static final String LIST_CONTENT = "getListContentInteractor";
    public static final String STATIC_LIST_CONTENT = "getStaticListContentInteractor";

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    protected final DataRepository repository;

    protected T parameter;

    protected Interactor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.repository = repository;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    private Subscription subscription = Subscriptions.empty();


    protected abstract Observable<R> buildObservable();

    public void execute(Subscriber<R> subscriber) {
        subscription = buildObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void updateParameter(T parameter) {
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }

    public boolean isUnSubscribed() {
        return this.subscription.isUnsubscribed();
    }
}
