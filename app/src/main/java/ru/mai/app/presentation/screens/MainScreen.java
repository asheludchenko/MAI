package ru.mai.app.presentation.screens;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;
import ru.mai.app.R;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.MainView;

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
