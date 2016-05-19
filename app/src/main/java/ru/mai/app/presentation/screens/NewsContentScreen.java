package ru.mai.app.presentation.screens;

import android.os.Bundle;
import android.os.Handler;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetNewsContentInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.NewsContentView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

import static ru.mai.app.Router.DELIM;
import static ru.mai.app.Router.NEWS;

/**
 * Created by olegosipenko on 25.09.15.
 */
@Layout(R.layout.view_news_content)
@WithModule(NewsContentScreen.Module.class)
public class NewsContentScreen extends Path {
    String item;

    public NewsContentScreen(String item) {
        this.item = item;
    }

    @dagger.Module(
            injects = NewsContentView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {

        public Module() {
        }

        @Provides
        Interactor<NewsContentSpecification, List<StaticContent>> providesNewsContentInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            GetNewsContentInteractor interactor = new GetNewsContentInteractor(repository, postExecutionThread, threadExecutor);
            String id = item.substring(NEWS.length() + DELIM.length());
            NewsContentSpecification specification = new NewsContentSpecification(id);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<NewsContentView, List<StaticContent>> {
        @Inject
        Interactor<NewsContentSpecification, List<StaticContent>> interactor;
        List<StaticContent> contents;
        private Subscriber<List<StaticContent>> subscriber = Subscribers.empty();
        private Handler handler;

        public Presenter() {
            contents = Collections.emptyList();
            handler = new Handler();
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            subscriber = new Subscriber<List<StaticContent>>() {
                @Override
                public void onCompleted() {
                    subscriber = Subscribers.empty();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e.getMessage());
                    subscriber = Subscribers.empty();
                }

                @Override
                public void onNext(List<StaticContent> contents) {
                    if (!hasView()) return;
                    getView().showContent(contents);
                }
            };
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    interactor.execute(subscriber);
                }
            }, 700);
        }

        @Override
        public void visibilityChanged(boolean visible) {
            super.visibilityChanged(visible);
            if (!visible) {
                handler.removeCallbacksAndMessages(null);
            }
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }
    }
}
