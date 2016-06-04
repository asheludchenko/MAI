package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;
import android.os.Handler;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetPhotosInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.MaiPresenter;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.ListPhotosView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 25.09.15.
 */
@Layout(R.layout.view_list_photos)
@WithModule(ListPhotoScreen.Module.class)
public class ListPhotoScreen extends Path {

    String menuItem;

    public ListPhotoScreen(String menuItem) {
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

        @Override
        public void visibilityChanged(boolean visible) {
            super.visibilityChanged(visible);
            if (!visible) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    }
}