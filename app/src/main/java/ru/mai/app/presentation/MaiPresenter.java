package ru.mai.app.presentation;

import android.view.View;

import mortar.ViewPresenter;
import rx.Subscriber;

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
            subscriber = null;
        }
    }

    protected abstract void unsubscribe();
}
