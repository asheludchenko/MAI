package ru.mai.app.presentation.views;

import android.content.Context;
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

import butterknife.Bind;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.ConstantsKt;
import ru.mai.app.R;
import ru.mai.app.presentation.screens.PresentationScreen;

/**
 * Created by olegosipenko on 27.09.15.
 */
public class PresView extends FrameLayout{

    @Inject
    PresentationScreen.Presenter presenter;
    @Inject Context context;
    private WebView webView;

    public PresView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        webView = new WebView(context);
        addView(webView);
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
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
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
