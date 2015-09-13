package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import javax.inject.Inject;

import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.presentation.screens.MainScreen;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class MainView extends FrameLayout {

    @Inject
    MainScreen.Presenter presenter;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
    }
}
