package ru.mai.app.presentation.screens;

import android.net.Uri;
import android.os.Bundle;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetImageInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.MainView;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_main)
@WithModule(MainScreen.Module.class)
public class MainScreen extends Path {

    @dagger.Module(
            injects = MainView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {
        @Provides
        Interactor<String, String> provideImage(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor)  {
            return new GetImageInteractor(repository, postExecutionThread, threadExecutor);
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {
        @Inject
        Interactor<String, String> interactor;

        private Subscriber<String> subscriber;

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;

            subscriber = new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    subscriber.unsubscribe();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                }

                @Override
                public void onNext(String image) {
                    if (!hasView()) return;
                    getView().showImage(Uri.parse(image));
                }
            };
            interactor.execute(subscriber);
        }
    }


}
