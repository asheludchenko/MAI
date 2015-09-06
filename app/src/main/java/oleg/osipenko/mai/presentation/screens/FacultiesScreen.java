package oleg.osipenko.mai.presentation.screens;

import javax.inject.Inject;

import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.PresentationModule;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.FacultiesView;

/**
 * Created by olegosipenko on 07.09.15.
 */
@Layout(R.layout.view_faculties)
@WithModule(FacultiesScreen.Module.class)
public class FacultiesScreen extends Path {

    @dagger.Module (
            injects = FacultiesView.class,
            addsTo = PresentationModule.class
    )
    public class Module {

    }

    public static class Presenter extends ViewPresenter<FacultiesView> {

        @Inject
        public Presenter() {
        }
    }
}
