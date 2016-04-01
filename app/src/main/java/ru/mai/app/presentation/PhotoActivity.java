package ru.mai.app.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.R;
import ru.mai.app.data.dataModel.Photo;
import ru.mai.app.presentation.mf_boilerplate.GsonParceler;
import ru.mai.app.presentation.mf_boilerplate.HandlesBack;
import ru.mai.app.presentation.mf_boilerplate.MortarScreenSwitcherFrame;
import ru.mai.app.presentation.screens.PhotoSliderScreen;

import static mortar.bundler.BundleServiceRunner.getBundleServiceRunner;

/**
 * Created by olegosipenko on 26.09.15.
 */
public class PhotoActivity extends Activity implements Flow.Dispatcher {

    public static final String POSITION = "uri to open";
    public static final String PHOTOS = "photos";

    private MortarScope activityScope;
    private HandlesBack containerAsHandlesBack;
    private FlowDelegate flowDelegate;

    @Bind(R.id.container)
    MortarScreenSwitcherFrame container;
    @Inject
    GsonParceler parceler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @SuppressWarnings("deprecation")
        FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        mortarStuff(savedInstanceState);

        setContentView(R.layout.photo_activity);
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra(POSITION, 0);
        List<Photo> photos = getIntent().getParcelableArrayListExtra(PHOTOS);

        containerAsHandlesBack = (HandlesBack) container;
        flowDelegate = FlowDelegate.onCreate(
                nonConfig,
                getIntent(),
                savedInstanceState,
                parceler,
                History.single(new PhotoSliderScreen(photos, position)),
                this);
    }

    private void mortarStuff(Bundle savedInstanceState) {
        MortarScope parentScope = MortarScope.getScope(getApplication());
        String scopeName = getLocalClassName() + "-task-" + getTaskId();

        activityScope = parentScope.findChild(scopeName);
        if (null == activityScope) {
            activityScope = parentScope.buildChild()
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .build(scopeName);
        }
        ObjectGraphService.inject(this, this);

        getBundleServiceRunner(activityScope).onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        flowDelegate.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flowDelegate.onPause();
    }

    @SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
    @Override
    public Object onRetainNonConfigurationInstance() {
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    public Object getSystemService(String name) {
        if (null != flowDelegate) {
            Object flowService = flowDelegate.getSystemService(name);
            if (null != flowService) return flowService;
        }
        return activityScope != null && activityScope.hasService(name) ? activityScope.getService(name)
                : super.getSystemService(name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
        getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    protected void onDestroy() {
        // activityScope may be null in case isWrongInstance() returned true in onCreate()
        if (isFinishing() && activityScope != null) {
            activityScope.destroy();
            activityScope = null;
        }

        super.onDestroy();
    }

    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
        container.dispatch(traversal, callback);
    }
}
