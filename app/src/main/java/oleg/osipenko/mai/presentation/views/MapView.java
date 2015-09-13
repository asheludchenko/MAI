package oleg.osipenko.mai.presentation.views;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.facebook.common.util.UriUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.screens.MapScreen;
import timber.log.Timber;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MapView extends FrameLayout {
    @Inject
    MapScreen.Presenter presenter;

    @Bind(R.id.zoom)
    ImageViewTouch zoom;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        zoom.setScaleEnabled(true);
        zoom.setDoubleTapEnabled(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        zoom.clear();
        presenter.dropView(this);
    }

    public void showMap() {
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                getResources().getResourcePackageName(R.drawable.map) + '/' +
                getResources().getResourceTypeName(R.drawable.map) + '/' +
                getResources().getResourceEntryName(R.drawable.map) );
        zoom.setImageURI(uri);
    }
}
