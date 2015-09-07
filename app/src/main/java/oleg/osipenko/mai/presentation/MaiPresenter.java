package oleg.osipenko.mai.presentation;

import android.view.View;

import mortar.ViewPresenter;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by olegosipenko on 08.09.15.
 */
public abstract class MaiPresenter<V extends View, T> extends ViewPresenter<V> {
    protected Subscriber<T> subscriber;

    @Override
    protected void onExitScope() {
        super.onExitScope();
        unsubscribe();
    }

    public void visibilityChanged(boolean visible) {
        if (!visible) {
            unsubscribe();
        }
    }

    protected void unsubscribe() {
        if (!subscriber.isUnsubscribed()) subscriber.unsubscribe();
    }
}
