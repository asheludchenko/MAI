package ru.mai.app.presentation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.R;
import ru.mai.app.presentation.screens.MapScreen;
import ru.mai.app.presentation.utils.ScaleImageView;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MapView extends FrameLayout {
    @Inject
    MapScreen.Presenter presenter;

    @Bind(R.id.zoom)
    WebView zoom;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        WebSettings settings = zoom.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
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

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        presenter.visibilityChanged(visibility == VISIBLE);
    }

    public void showMap() {
        progressBar.setVisibility(GONE);
        zoom.loadUrl("file:///android_asset/map.html");
    }
}
