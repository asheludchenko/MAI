package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dto.MainScreenDto;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetImageInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.MainView;
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
        public Module() {
        }

        @Provides
        Interactor<Void, MainScreenDto> provideImage(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor)  {
            return new GetImageInteractor(repository, postExecutionThread, threadExecutor);
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {
        @Inject
        Interactor<Void, MainScreenDto> interactor;

        private Subscriber<MainScreenDto> subscriber;

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;

            subscriber = new Subscriber<MainScreenDto>() {
                @Override
                public void onCompleted() {
                    subscriber.unsubscribe();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                    getView().showPlaceholder();
                }

                @Override
                public void onNext(MainScreenDto image) {
                    if (!hasView()) return;
                    getView().showImage(image);
                }
            };
            interactor.execute(subscriber);
        }
    }


}
