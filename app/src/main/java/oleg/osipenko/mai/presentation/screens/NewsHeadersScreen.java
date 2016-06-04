package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.NewsHeadersContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetNewsHeadersInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.MaiPresenter;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.NewsHeadersView;
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
