package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import javax.inject.Inject;

import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.screens.WebViewScreen;

/**
 * Created by olegosipenko on 27.09.15.
 */
public class ViewWeb extends FrameLayout{

    @Inject
    WebViewScreen.Presenter presenter;
    @Inject Context context;
    private WebView webView;

    public ViewWeb(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        webView = new WebView(context);
        addView(webView, 0);
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
        removeAllViews();
        webView.destroy();
    }

    public void loadContent(String url) {
        WebSettings settings = webView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                findViewById(R.id.pb).setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                findViewById(R.id.pb).setVisibility(View.GONE);
            }
        });
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            webView.requestFocus(View.FOCUS_DOWN);
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_UP:
                            if (!v.hasFocus()) {
                                v.requestFocus();
                            }
                            break;
                    }
                    return false;
                }
            });
        }
    }
}
