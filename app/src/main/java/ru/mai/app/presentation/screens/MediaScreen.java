package ru.mai.app.presentation.screens;

import android.os.Bundle;

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
import ru.mai.app.data.repository.specification.ListContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetListContentInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.MediaView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 25.09.15.
 */
@Layout(R.layout.view_media)
@WithModule(MediaScreen.Module.class)
public class MediaScreen extends Path {

    String menuItem;

    public MediaScreen(String menuItem) {
        this.menuItem = menuItem;
    }

    @dagger.Module(
            injects = MediaView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {

        public Module() {
        }

        @Provides
        Interactor<ListContentSpecification, List<ListContent>> providesListContentInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            GetListContentInteractor interactor = new GetListContentInteractor(repository, postExecutionThread, threadExecutor);
            ListContentSpecification specification = new ListContentSpecification(menuItem);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<MediaView, List<ListContent>> {

        @Inject
        Interactor<ListContentSpecification, List<ListContent>> interactor;

        List<ListContent> contents;

        private Subscriber<List<ListContent>> subscriber = Subscribers.empty();

        public Presenter() {
            contents = Collections.emptyList();
        }

        public String getParameter() {
            return interactor.getParameter().getItem();
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
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
            interactor.execute(subscriber);
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }
    }
}