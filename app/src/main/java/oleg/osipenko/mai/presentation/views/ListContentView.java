package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class ListContentView extends LinearLayout {

    @Bind(R.id.text)
    TextView textView;

    @Inject
    ListContentScreen.Presenter presenter;

    public ListContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("mai", "view constructor");
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        textView.setText("Жопа");

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("mai", "view " + (presenter == null));
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    public void showText(List<ListContent> contents) {
        textView.setText(contents.get(0).getText());
    }
}
