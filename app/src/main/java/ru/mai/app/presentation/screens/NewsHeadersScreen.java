package ru.mai.app.presentation.screens;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.data.dataModel.NewsHeadersContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetNewsHeadersInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.NewsHeadersView;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by olegosipenko on 20.04.16.
 */
@Layout(R.layout.view_news_headers)
@WithModule(NewsHeadersScreen.Module.class)
public class NewsHeadersScreen extends Path {

    public NewsHeadersScreen() {
    }

    @dagger.Module(
            injects = NewsHeadersView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {

        public Module() {
        }

        @Provides
        Interactor<Void, List<NewsHeadersContent>> provideNewsHeadersInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            return new GetNewsHeadersInteractor(repository, postExecutionThread, threadExecutor);
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<NewsHeadersView, List<NewsHeadersContent>> {

        @Inject
        Interactor<Void, List<NewsHeadersContent>> newsHeadersInteractor;

        public Presenter() {
        }


        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            executeLoad();
        }

        @Override
        protected void unsubscribe() {
            newsHeadersInteractor.unsubscribe();
        }

        public void loadMore() {
            executeLoad();
        }

        public void executeLoad() {
            newsHeadersInteractor.execute(new Subscriber<List<NewsHeadersContent>>() {
                @Override
                public void onCompleted() {
                    newsHeadersInteractor.unsubscribe();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                }

                @Override
                public void onNext(List<NewsHeadersContent> newsHeadersContents) {
                    if (!hasView()) return;
                    getView().showHeaders(newsHeadersContents);
                }
            });
        }
    }
}
