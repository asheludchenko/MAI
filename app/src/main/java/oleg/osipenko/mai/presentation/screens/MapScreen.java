package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.MapView;

/**
 * Created by olegosipenko on 13.09.15.
 */
@Layout(R.layout.view_map)
@WithModule(MapScreen.Module.class)
public class MapScreen extends Path {

    @dagger.Module(
            injects = MapView.class
    )
    public class Module {

    }

    @Singleton
    public static class Presenter extends ViewPresenter<MapView> {
        @Inject
        public Presenter() {
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            getView().showMap();
        }
    }
}
