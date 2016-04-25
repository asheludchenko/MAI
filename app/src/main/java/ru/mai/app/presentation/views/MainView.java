package ru.mai.app.presentation.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.R;
import ru.mai.app.data.dto.MainScreenDto;
import ru.mai.app.presentation.screens.MainScreen;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MainView extends FrameLayout {

    @Inject
    MainScreen.Presenter presenter;

    @Bind(R.id.bkg)
    SimpleDraweeView bkg;
    @Bind(R.id.screen_button)
    Button screenButton;
    @OnClick(R.id.screen_button)
    void onButtonClick() {
        if (!TextUtils.isEmpty(link)) {
            Intent clickIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            getContext().startActivity(clickIntent);
        }
    }

    private String link = "";

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

    public void showImage(MainScreenDto image) {
        this.link = image.getLinkUrl();
        bkg.setImageURI(Uri.parse(image.getImageUrl()));
        if (!TextUtils.isEmpty(image.getButtonText())) {
            screenButton.setVisibility(VISIBLE);
            screenButton.setText(image.getButtonText());
        } else {
            screenButton.setVisibility(GONE);
        }

    }

    public void showPlaceholder() {
        bkg.setImageURI(Uri.parse("res://placeholder"));
        bkg.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
    }
}
