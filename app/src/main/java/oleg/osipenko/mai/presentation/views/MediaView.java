package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.presentation.events.ChangeScreenEvent;
import oleg.osipenko.mai.presentation.screens.MediaScreen;

/**
 * Created by olegosipenko on 25.09.15.
 */
public class MediaView extends FrameLayout {

    @Bind(R.id.buttons)
    LinearLayout buttonsBlock;

    @Inject
    MediaScreen.Presenter presenter;

    public MediaView(Context context, AttributeSet attrs) {
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

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        presenter.visibilityChanged(visibility == VISIBLE);
    }

    public void showItems(List<ListContent> contents) {
        for (final ListContent item : contents) {
            Button button = (Button) inflate(getContext(), R.layout.media_button, null);
            button.setText(item.getText());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonsBlock.getLayoutParams());
            params.setMargins(0, 20, 0, 20);
            button.setLayoutParams(params);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.bus.post(new ChangeScreenEvent(item.getText()));
                }
            });
            buttonsBlock.addView(button);
        }
    }
}
