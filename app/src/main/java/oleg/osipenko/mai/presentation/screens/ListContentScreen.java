package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.interactors.GetListContentInteractor;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.ListContentView;
import rx.Subscriber;

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
            Log.d("mai", "module constructor " + menuItem);
        }

        @Provides
        GetListContentInteractor providesListContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
            GetListContentInteractor interactor = new GetListContentInteractor(repository, postExecutionThread, threadExecutor);
            ListContentSpecification specification = new ListContentSpecification(menuItem);
            interactor.updateParameter(specification);
            return interactor;
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ListContentView> {

        GetListContentInteractor interactor;

        @Inject
        public Presenter(GetListContentInteractor interactor) {
            this.interactor = interactor;
            Log.d("mai", "constructor");
        }


        private Subscriber<List<ListContent>> subscriber = new Subscriber<List<ListContent>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ListContent> contents) {
                getView().showText(contents);
            }
        };

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            Log.d("mai", "presenter on load");
            if (!hasView()) return;
            interactor.execute(subscriber);
        }
    }
}
