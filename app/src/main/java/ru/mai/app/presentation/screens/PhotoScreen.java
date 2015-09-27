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
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetPhotosInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.ListPhotosView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 25.09.15.
 */
@Layout(R.layout.view_list_photos)
@WithModule(PhotoScreen.Module.class)
public class PhotoScreen extends Path {

    String menuItem;

    public PhotoScreen(String menuItem) {
        this.menuItem = menuItem;
    }

    @dagger.Module(
            injects = ListPhotosView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {

        public Module() {
        }

        @Provides
        Interactor<NewsContentSpecification, List<ListContent>> providesListContentInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            GetPhotosInteractor interactor = new GetPhotosInteractor(repository, postExecutionThread, threadExecutor);
            NewsContentSpecification specification = new NewsContentSpecification(menuItem);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<ListPhotosView, List<ListContent>> {

        @Inject
        Interactor<NewsContentSpecification, List<ListContent>> interactor;

        List<ListContent> contents;
        private Handler handler;

        private Subscriber<List<ListContent>> subscriber = Subscribers.empty();

        public Presenter() {
            contents = Collections.emptyList();
            handler = new Handler();
        }

        public String getParameter() {
            return interactor.getParameter().getItem();
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            executeLoad();
        }

        @Override
        public void dropView(ListPhotosView view) {
            super.dropView(view);
            handler.removeCallbacksAndMessages(null);
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }

        public void loadMore() {
            executeLoad();
        }

        private void executeLoad() {
            subscriber = new Subscriber<List<ListContent>>() {
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
                public void onNext(List<ListContent> contents) {
                    if (!hasView()) return;
                    getView().showItems(contents);
                }
            };
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    interactor.execute(subscriber);
                }
            }, 700);
        }
    }
}