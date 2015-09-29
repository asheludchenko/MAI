package ru.mai.app.presentation.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.R;
import ru.mai.app.presentation.screens.MainScreen;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MainView extends FrameLayout {

    @Inject
    MainScreen.Presenter presenter;

    @Bind(R.id.bkg)
    SimpleDraweeView bkg;

    public MainView(Context context, AttributeSet attrs) {
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

    public void showImage(Uri uri) {
        bkg.setImageURI(uri);
    }
}
