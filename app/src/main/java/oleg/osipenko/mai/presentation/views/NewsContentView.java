package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.net.Uri;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.presentation.screens.NewsContentScreen;

/**
 * Created by olegosipenko on 25.09.15.
 */
public class NewsContentView extends NestedScrollView {

    @Inject
    NewsContentScreen.Presenter presenter;
    @Bind(R.id.pb)
    ProgressBar progressBar;
    @Bind(R.id.root)
    LinearLayout root;

    public NewsContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
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
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        presenter.visibilityChanged(visibility == VISIBLE);
    }

    public void showContent(List<StaticContent> contents) {
        progressBar.setVisibility(GONE);
        for (StaticContent content : contents) {
            View view = null;
            if (content.getImage() != null) {
                view = getImageView(content.getImage());
            } else if (content.getNewsText() != null) {
                view = getTextView(content.getNewsText());
            } else if (content.getFacTitle() != null) {
                view = getFacTitleView(content.getFacTitle());
            } else if (content.getText() != null) {
                view = getTextView(content.getText());
            } else if (content.getAuthor() != null) {
                view = getTextView(content.getAuthor());
            }
            root.addView(view);
        }
    }

    public void showError(Throwable e) {
        progressBar.setVisibility(GONE);
        if (e != null && e.getMessage() != null) Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

    }

    private View getFacTitleView(String facTitle) {
        TextView title = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.fac_title, null);
        title.setText(Html.fromHtml(facTitle));
        return title;
    }

    private View getImageView(String image) {
        SimpleDraweeView imageView = new SimpleDraweeView(getContext());
        Uri uri = Uri.parse(image);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .build();
        imageView.setHierarchy(hierarchy);
        imageView.setImageURI(uri);
        imageView.setAdjustViewBounds(true);
        int height = Math.round(getWidth() / 1.79f);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                height
        ));
        return imageView;
    }

    private View getTextView(String text) {
        TextView textView = new TextView(getContext());

        textView.setText(text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextColor(getResources().getColor(android.R.color.black));
        int padding = Math.round(
                getResources().getDisplayMetrics().density * 16
        );
        textView.setPadding(padding, padding/2, padding, padding/2);
        setParams(textView);
        return textView;
    }


    private View getTextView(Spanned text) {
        TextView textView = new TextView(getContext());

        textView.setText(text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextColor(getResources().getColor(android.R.color.black));
        int padding = Math.round(
                getResources().getDisplayMetrics().density * 16
        );
        textView.setPadding(padding, padding/2, padding, padding/2);
        setParams(textView);
        return textView;
    }

    private void setParams(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.START
        );
        view.setLayoutParams(params);
    }
}