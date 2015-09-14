package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.screens.MapScreen;
import oleg.osipenko.mai.presentation.utils.ScaleImageView;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MapView extends FrameLayout {
    @Inject
    MapScreen.Presenter presenter;

    @Bind(R.id.zoom)
    ScaleImageView zoom;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    public void showMap(Bitmap bitmap) {
        zoom.setImageBitmap(bitmap);
    }
}
