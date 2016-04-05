package ru.mai.app.presentation.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.path.Path;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.App;
import ru.mai.app.ConstantsKt;
import ru.mai.app.R;
import ru.mai.app.Router;
import ru.mai.app.presentation.events.ChangeSelectedTabEvent;
import ru.mai.app.presentation.screens.ListContentScreen;
import ru.mai.app.presentation.screens.MainScreen;
import ru.mai.app.presentation.screens.MainSliderScreen;
import ru.mai.app.presentation.screens.MapScreen;
import ru.mai.app.presentation.screens.MediaScreen;
import ru.mai.app.presentation.screens.StaticListContentScreen;

/**
 * Created by olegosipenko on 05.04.16.
 */
public class MainSliderView extends LinearLayout {

    @Inject
    MainSliderScreen.Presenter presenter;
    @Bind(R.id.pager)
    ViewPager pager;

    public MainSliderView(Context context, AttributeSet attrs) {
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

    public void showScreens(int startPosition) {
        boolean isStudent = getContext().getSharedPreferences(ConstantsKt.getSP_KEY(), Context.MODE_PRIVATE).getBoolean(ConstantsKt.getIS_STUDENT_KEY(), true);
        List<Path> mainScreens;
        if (isStudent) {
            mainScreens = Arrays.asList(new MainScreen(), new ListContentScreen(Router.NEWS), new MapScreen(), new ListContentScreen(Router.SCHEDULE));
        } else {
            mainScreens = Arrays.asList(new MainScreen(), new ListContentScreen(Router.NEWS), new StaticListContentScreen(Router.PRIEM), new MediaScreen(Router.MEDIA));
        }
        SlidePagerAdapter<Path> adapter = new SlidePagerAdapter<>(getContext(), mainScreens);
        pager.setAdapter(adapter);
        pager.setCurrentItem(startPosition);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                App.bus.post(new ChangeSelectedTabEvent(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void swipePage(int position) {
        pager.setCurrentItem(position);
    }
}
