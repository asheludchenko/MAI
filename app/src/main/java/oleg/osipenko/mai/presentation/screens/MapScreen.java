package oleg.osipenko.mai.presentation.screens;

import android.graphics.Bitmap;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetMapInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.MaiPresenter;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.MapView;
import rx.Subscriber;
import rx.observers.Subscribers;

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
            getView().showMap();
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }
    }
}
