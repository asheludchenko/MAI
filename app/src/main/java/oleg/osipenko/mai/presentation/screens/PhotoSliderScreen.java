package oleg.osipenko.mai.presentation.screens;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import flow.path.Path;
import mortar.ViewPresenter;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.Photo;
import oleg.osipenko.mai.presentation.mf_boilerplate.Layout;
import oleg.osipenko.mai.presentation.mf_boilerplate.WithModule;
import oleg.osipenko.mai.presentation.views.PhotoSliderView;

/**
 * Created by olegosipenko on 01.04.16.
 */
@Layout(R.layout.view_photo_slider)
@WithModule(PhotoSliderScreen.Module.class)
public class PhotoSliderScreen extends Path {

    private static List<Photo> sPhotos = new ArrayList<>();
    private static int startPosition = 0;

    public PhotoSliderScreen(List<Photo> photos, int startPosition) {
        sPhotos.clear();
        sPhotos.addAll(photos);
        PhotoSliderScreen.startPosition = startPosition;
    }

    @dagger.Module(
            injects = PhotoSliderView.class,
            addsTo = App.AppModule.class
    )
    public class Module {
        public Module() {
        }
    }

    public static class Presenter extends ViewPresenter<PhotoSliderView> {

        @Inject
        public Presenter() {
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            getView().showPhotos(sPhotos, startPosition);
        }
    }
}
