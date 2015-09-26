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
import ru.mai.app.data.dataModel.StaticListContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticListContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetStaticListContentInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.StaticListContentView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_static_list_content)
@WithModule(StaticListContentScreen.Module.class)
public class StaticListContentScreen extends Path {
    private String item;

    public StaticListContentScreen(String item) {
        this.item = item;
    }

    @dagger.Module(
            injects = StaticListContentView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {
        @Provides
        Interactor<StaticListContentSpecification, List<StaticListContent>> providesStaticListContentInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            GetStaticListContentInteractor interactor = new GetStaticListContentInteractor(
                    repository,
                    postExecutionThread,
                    threadExecutor);
            StaticListContentSpecification specification = new StaticListContentSpecification(item);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<StaticListContentView, List<StaticListContent>> {
        @Inject
        Interactor<StaticListContentSpecification, List<StaticListContent>> interactor;
        List<StaticListContent> contents;
        private Subscriber<List<StaticListContent>> subscriber = Subscribers.empty();

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
            subscriber = new Subscriber<List<StaticListContent>>() {
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
                public void onNext(List<StaticListContent> contents) {
                    if (!hasView()) return;
                    getView().showContent(contents);
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
