package ru.mai.app.presentation.screens;

import android.graphics.Bitmap;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import oleg.osipenko.app.R;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import oleg.osipenko.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetMapInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.MapView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_map)
@WithModule(MapScreen.Module.class)
public class MapScreen extends Path {

    @dagger.Module(
            injects = MapView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {
        @Provides
        Interactor<StaticContentSpecification, Bitmap> providesMapInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor
        ) {
            return new GetMapInteractor(repository, postExecutionThread, threadExecutor);
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<MapView, Bitmap> {
        @Inject
        Interactor<StaticContentSpecification, Bitmap> interactor;

        private Subscriber<Bitmap> subscriber = Subscribers.empty();

        public Presenter() {
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            subscriber = new Subscriber<Bitmap>() {
                @Override
                public void onCompleted() {
                    subscriber.unsubscribe();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                }

                @Override
                public void onNext(Bitmap bitmap) {
                    if (!hasView()) return;
                    getView().showMap(bitmap);
                }
            };
            interactor.execute(subscriber);
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }
    }
}
