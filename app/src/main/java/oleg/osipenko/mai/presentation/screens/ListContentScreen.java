package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

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
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetListContentInteractor;
import oleg.osipenko.mai.domain.interactors.Interactor;
import oleg.osipenko.mai.presentation.MaiPresenter;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.utils.SimpleSectionListAdapter;
import oleg.osipenko.mai.presentation.views.ListContentView;
import rx.Subscriber;
import rx.observers.Subscribers;
import timber.log.Timber;

/**
 * Created by olegosipenko on 07.09.15.
 */
@Layout(R.layout.view_list_content)
@WithModule(ListContentScreen.Module.class)
public class ListContentScreen extends Path {

    String menuItem;

    public ListContentScreen(String menuItem) {
        this.menuItem = menuItem;
    }

    @dagger.Module(
            injects = ListContentView.class,
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
    public static class Presenter extends MaiPresenter<ListContentView, List<ListContent>> {

        @Inject
        Interactor<ListContentSpecification, List<ListContent>> interactor;

        List<ListContent> contents;

        private Subscriber<List<ListContent>> subscriber = Subscribers.empty();

        public Presenter() {
            contents = Collections.emptyList();
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
                    for (ListContent content : contents) {
                        if (content.isWithSections()) {
                            List<ListContent> blocks = contents;
                            SimpleSectionListAdapter.Section[] sections = content.getSections();
                            blocks.remove(content);
                            getView().showWithSections(blocks, sections);
                            return;
                        }
                    }
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
