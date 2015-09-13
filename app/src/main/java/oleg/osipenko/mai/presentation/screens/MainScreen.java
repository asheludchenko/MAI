package oleg.osipenko.mai.presentation.screens;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.domain.DomainModule;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.ListContentView;
import oleg.osipenko.mai.presentation.views.MainView;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_main)
@WithModule(MainScreen.Module.class)
public class MainScreen extends Path {

    @dagger.Module(
            injects = MainView.class
    )
    public class Module {

    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {
        @Inject
        public Presenter() {
        }
    }
}
