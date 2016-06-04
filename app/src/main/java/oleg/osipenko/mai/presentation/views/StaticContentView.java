package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.presentation.screens.StaticContentScreen;
import oleg.osipenko.mai.presentation.utils.Map;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class StaticContentView extends NestedScrollView {

    @Inject
    StaticContentScreen.Presenter presenter;
    @Bind(R.id.root)
    LinearLayout root;

    public StaticContentView(Context context, AttributeSet attrs) {
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
        for (StaticContent content : contents) {
            View view = null;
            if (content.getImage() != null) {
                view = getImageView(content.getImage());
            } else if (content.getText() != null) {
                view = getTextView(content.getText());
            } else if (content.getFacTitle() != null) {
                view = getFacTitleView(content.getFacTitle());
            } else if (content.getFacPhoto() != null) {
                view = getFacPhoto(content.getFacPhoto());
            } else if (content.getFacText() != null) {
                view = getFacText(content.getFacText());
            } else if (content.getTitle() != null) {
                view = getFacTitleView(content.getTitle());
                ((TextView) view).setGravity(Gravity.LEFT);
            } else if (content.getMap() != null) {
                view = getMapImageView(content.getMap());
            } else if (content.getButton() != null) {
                view = getButton(content.getButton());
                //view.getLayoutParams().width = DrawerLayout.LayoutParams.WRAP_CONTENT;
            }
            root.addView(view);
        }
    }

    private View getFacText(String facText) {
        TextView text = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.fac_text, null);
        text.setText(Html.fromHtml(facText));
        text.setMovementMethod(LinkMovementMethod.getInstance());
        return text;
    }

    private View getFacPhoto(String facPhoto) {
        View photo = LayoutInflater.from(getContext()).inflate(R.layout.fac_image, null);
        SimpleDraweeView view = (SimpleDraweeView) photo.findViewById(R.id.portrait);
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(facPhoto)
                .build();
        view.setImageURI(uri);
        return photo;
    }

    private View getFacTitleView(String facTitle) {
        TextView title = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.fac_title, null);
        title.setText(facTitle);
        return title;
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

    private View getMapImageView(final Map image) {
        SimpleDraweeView imageView = new SimpleDraweeView(getContext());
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(image.getMapImage()))
                .build();
        imageView.setImageURI(uri);
        imageView.setAdjustViewBounds(true);
        int height = Math.round(getWidth() / 1.79f);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                height
        ));
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yaIntent = new Intent(Intent.ACTION_VIEW, image.getYandexUri());
                PackageManager pm = getContext().getPackageManager();
                if (yaIntent.resolveActivity(pm) != null) {
                    getContext().startActivity(yaIntent);
                } else {
                    Intent gmIntent = new Intent(Intent.ACTION_VIEW, image.getGmUri());
                    gmIntent.setPackage("com.google.android.apps.maps");
                    if (gmIntent.resolveActivity(pm) != null) getContext().startActivity(gmIntent);
                }
            }
        });
        return imageView;
    }

    private View getTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setAutoLinkMask(Linkify.ALL);
        textView.setText(Html.fromHtml(text));
        //textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextColor(getResources().getColor(android.R.color.black));
        int padding = Math.round(
                getResources().getDisplayMetrics().density * 16
        );
        textView.setPadding(padding, padding / 2, padding, padding / 2);
        setParams(textView);
        return textView;
    }

    private Button getButton(final String url) {
        Button button = (Button) LayoutInflater.from(getContext()).inflate(R.layout.description_button, null, false);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(intent);
            }
        });
        return button;
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
