package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.Photo;
import oleg.osipenko.mai.presentation.screens.PhotoScreen;
import oleg.osipenko.mai.presentation.screens.PhotoSliderScreen;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by olegosipenko on 01.04.16.
 */
public class PhotoSliderView extends LinearLayout {

    @Inject
    PhotoSliderScreen.Presenter presenter;
    @Bind(R.id.pager)
    ViewPager pager;

    public PhotoSliderView(Context context, AttributeSet attrs) {
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

    public void showPhotos(List<Photo> photos, int startPosition) {
        List<PhotoScreen> photoScreens = Observable.from(photos)
                .map(new Func1<Photo, PhotoScreen>() {
                    @Override
                    public PhotoScreen call(Photo photo) {
                        return new PhotoScreen(photo.getOriginal());
                    }
                })
                .toList()
                .toBlocking()
                .single();
        SlidePagerAdapter<PhotoScreen> adapter = new SlidePagerAdapter<>(getContext(), photoScreens);
        pager.setAdapter(adapter);
        pager.setCurrentItem(startPosition);
    }
}
