package ru.mai.app.presentation.views;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
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
import ru.mai.app.presentation.screens.WebViewScreen;

/**
 * Created by olegosipenko on 27.09.15.
 */
public class ViewWeb extends NestedScrollView{

    @Inject
    WebViewScreen.Presenter presenter;
    @Inject Context context;

    public ViewWeb(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
        removeAllViews();
    }

    public void loadContent(String[] url) {
        for (String string : url) {
            WebView webView = new WebView(context);
            addView(webView);
            WebSettings settings = webView.getSettings();
            settings.setDisplayZoomControls(true);
            settings.setJavaScriptEnabled(true);
            settings.setLoadWithOverviewMode(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(string);
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
}
