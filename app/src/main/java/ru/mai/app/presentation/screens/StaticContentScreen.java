package ru.mai.app.presentation.screens;

import android.os.Bundle;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import oleg.osipenko.app.R;
import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.domain.DomainModule;
import ru.mai.app.domain.executors.PostExecutionThread;
import oleg.osipenko.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.interactors.GetStaticContentInteractor;
import ru.mai.app.domain.interactors.Interactor;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.StaticContentView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_static_content)
@WithModule(StaticContentScreen.Module.class)
public class StaticContentScreen extends Path {
    String item;

    public StaticContentScreen(String item) {
        this.item = item;
    }

    @dagger.Module(
            injects = StaticContentView.class,
            addsTo = App.AppModule.class,
            includes = DomainModule.class
    )
    public class Module {
        @Provides
        Interactor<StaticContentSpecification, List<StaticContent>> providesStaticContentInteractor(
                DataRepository repository,
                PostExecutionThread postExecutionThread,
                ThreadExecutor threadExecutor) {
            GetStaticContentInteractor interactor = new GetStaticContentInteractor(repository, postExecutionThread, threadExecutor);
            StaticContentSpecification specification = new StaticContentSpecification(item);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<StaticContentView, List<StaticContent>> {
        @Inject
        Interactor<StaticContentSpecification, List<StaticContent>> interactor;
        List<StaticContent> contents;
        private Subscriber<List<StaticContent>> subscriber = Subscribers.empty();

        public Presenter() {
            contents = Collections.emptyList();
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
            interactor.execute(subscriber);
        }

        @Override
        protected void unsubscribe() {
            if (!interactor.isUnSubscribed()) interactor.unsubscribe();
        }
    }
}
