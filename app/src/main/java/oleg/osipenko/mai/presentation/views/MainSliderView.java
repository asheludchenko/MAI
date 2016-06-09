package oleg.osipenko.mai.presentation.views;

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
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.ConstantsKt;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.Router;
import oleg.osipenko.mai.presentation.events.ChangeSelectedTabEvent;
import oleg.osipenko.mai.presentation.events.HamburgerEvent;
import oleg.osipenko.mai.presentation.mf_boilerplate.HandlesBack;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;
import oleg.osipenko.mai.presentation.screens.MainScreen;
import oleg.osipenko.mai.presentation.screens.MainSliderScreen;
import oleg.osipenko.mai.presentation.screens.MediaScreen;
import oleg.osipenko.mai.presentation.screens.NewsHeadersScreen;
import oleg.osipenko.mai.presentation.screens.PhotoScreen;
import oleg.osipenko.mai.presentation.screens.StaticListContentScreen;

/**
 * Created by olegosipenko on 05.04.16.
 */
public class MainSliderView extends LinearLayout implements HandlesBack {

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

    @Override
    public boolean onBackPressed() {
        pager.setCurrentItem(0);
        App.bus.post(new HamburgerEvent());
        return true;
    }

    public void showScreens(int startPosition) {
        boolean isStudent = getContext().getSharedPreferences(ConstantsKt.getSP_KEY(), Context.MODE_PRIVATE).getBoolean(ConstantsKt.getIS_STUDENT_KEY(), true);
        List<Path> mainScreens;
        if (isStudent) {
            mainScreens = Arrays.asList(new MainScreen(), new NewsHeadersScreen(), new PhotoScreen(String.valueOf(R.drawable.mai_plan_big_color)), new ListContentScreen(Router.SCHEDULE));
        } else {
            mainScreens = Arrays.asList(new MainScreen(), new NewsHeadersScreen(), new StaticListContentScreen(Router.PRIEM), new MediaScreen(Router.MEDIA));
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
                presenter.pageSwipe(position);
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
