package ru.mai.app.presentation.screens;

import android.os.Bundle;

import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.PhotoView;

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
    class Module {
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
