package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.widget.NestedScrollView;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.presentation.events.ChangeScreenEvent;
import oleg.osipenko.mai.presentation.screens.StaticListContentScreen;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticListContentView extends NestedScrollView {

    @Inject
    StaticListContentScreen.Presenter presenter;

    @Bind(R.id.root)
    LinearLayout root;
    ListContentView.ClickListener listener;
    String screenName;

    public StaticListContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
        screenName = presenter.getParameter();
        listener = new ListContentView.ClickListener() {
            @Override
            public void itemClicked(String value) {
                App.bus.post(new ChangeScreenEvent(value));
            }
        };
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

    public void showContent(List<StaticListContent> contents) {
        for (StaticListContent content : contents) {
            View view = null;
            if (content.getImage() != null) {
                view = getImageView(content.getImage());
            } else if (content.getText() != null) {
                view = getTextView(content.getText());
            } else if (content.getListTitle() != null) {
                view = getListView(content);
            } else if (content.getTitle() != null) {
                view = getTitleView(content.getTitle());
            }
            root.addView(view);
        }
    }

    private View getImageView(String image) {
        SimpleDraweeView imageView = new SimpleDraweeView(getContext());
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(image)
                .build();
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
        textView.setAutoLinkMask(Linkify.ALL);
        textView.setText(text);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        int padding = Math.round(
                getResources().getDisplayMetrics().density * 16
        );
        textView.setPadding(padding, padding / 2, padding, padding / 2);
        setParams(textView);

        return textView;
    }

    private View getTitleView(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setAutoLinkMask(Linkify.ALL);
        Typeface tf = textView.getTypeface();
        textView.setTypeface(tf, 1);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        int padding = Math.round(
                getResources().getDisplayMetrics().density * 16
        );
        textView.setPadding(padding, padding/2, padding, padding/2);
        setParams(textView);
        return textView;
    }

    private View getListView(final StaticListContent content) {
        View listContent = View.inflate(getContext(), R.layout.item_static_list, null);
        TextView textView = (TextView) listContent.findViewById(R.id.text);
        textView.setText(content.getListTitle());
        if (content.getListImage() != null) {
            SimpleDraweeView image = (SimpleDraweeView) listContent.findViewById(R.id.image);
            image.setVisibility(VISIBLE);
            Uri uri = null;
            if (content.isListWithImage()) {
                uri = Uri.parse(content.getListImage());
            } else {
                uri = new Uri.Builder()
                        .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                        .path(content.getListImage())
                        .build();
            }
            image.setImageURI(uri);
        }
        if (content.isListClickable()) {
            listContent.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemClicked(content.getListTitle());
                }
            });
        }
        return listContent;
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
