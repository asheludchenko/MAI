package oleg.osipenko.mai.presentation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import javax.inject.Inject;

import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.presentation.screens.FacultiesScreen;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class FacultiesView extends LinearLayout {

    @Inject
    FacultiesScreen.Presenter presenter;

    public FacultiesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectGraphService.inject(context, this);
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
}
