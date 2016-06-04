package oleg.osipenko.mai.presentation;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.wnafee.vector.compat.ResourcesCompat;

import butterknife.Bind;
import butterknife.ButterKnife;
import oleg.osipenko.mai.ConstantsKt;
import oleg.osipenko.mai.R;

/**
 * Created by olegosipenko on 27.09.15.
 */
public class MaiChannelActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.youtube_container)
    FrameLayout youtube_container;

    private WebView youtube;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_youtube);
        ButterKnife.bind(this);

        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(this, R.drawable.arrow));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        youtube = new WebView(getApplicationContext());
        youtube_container.addView(youtube);

        if (savedState != null) {
            youtube.restoreState(savedState);
        } else {
            WebSettings settings = youtube.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setLoadWithOverviewMode(true);
            youtube.setWebViewClient(new WebViewClient());
            youtube.loadUrl(ConstantsKt.getCHANNEL_URL());
            youtube.setWebChromeClient(new WebChromeClient());
            youtube.setOnKeyListener(new KeyListener());

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
                youtube.requestFocus(View.FOCUS_DOWN);
                youtube.setOnTouchListener(new View.OnTouchListener() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        youtube.saveState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youtube_container.removeAllViews();
        youtube.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private static class KeyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && ((WebView) v).canGoBack()) {
                ((WebView) v).goBack();
                return true;
            } else {
                return false;
            }

        }
    }
}
