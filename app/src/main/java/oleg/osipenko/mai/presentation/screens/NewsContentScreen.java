package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetNewsContentInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.MaiPresenter;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.NewsContentView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

import static oleg.osipenko.mai.Router.DELIM;
import static oleg.osipenko.mai.Router.NEWS;

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
            String id = TextUtils.isDigitsOnly(item) ? item : item.substring(NEWS.length() + DELIM.length());
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
                    if (hasView()) getView().showError(e);
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
