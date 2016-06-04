package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.PhotoView;

/**
 * Created by olegosipenko on 01.04.16.
 */
@Layout(R.layout.view_photo)
@WithModule(PhotoScreen.Module.class)
public class PhotoScreen extends Path {

    String url;

    public PhotoScreen(String url) {
        this.url = url;
    }

    @dagger.Module(
            injects = PhotoView.class,
            addsTo = App.AppModule.class
    )
    public class Module {
        public Module() {
        }
        @Provides
        public Presenter providePresenter() {
            return new Presenter(url);
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<PhotoView> {
        private String url;

        public Presenter(String url) {
            this.url = url;
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            getView().loadImage(url);
        }
    }
}
