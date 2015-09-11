package oleg.osipenko.mai.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wnafee.vector.compat.ResourcesCompat;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.presentation.mf_boilerplate.GsonParceler;
import oleg.osipenko.mai.presentation.mf_boilerplate.HandlesBack;
import oleg.osipenko.mai.presentation.mf_boilerplate.MortarScreenSwitcherFrame;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;

import static mortar.bundler.BundleServiceRunner.getBundleServiceRunner;

public class MainActivity extends Activity implements Flow.Dispatcher {

    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    MortarScreenSwitcherFrame container;
    @Bind(R.id.nav_menu)
    NavigationView menu;

    @Inject
    GsonParceler parceler;

    private MortarScope activityScope;
    private HandlesBack containerAsHandlesBack;
    private FlowDelegate flowDelegate;

    private Drawable hamburger;
    private Drawable arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIcons();

        @SuppressWarnings("deprecation")
        FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        mortarStuff(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initHamburger();

        initMenu();

        containerAsHandlesBack = (HandlesBack) container;
        flowDelegate = FlowDelegate.onCreate(
                nonConfig,
                getIntent(),
                savedInstanceState,
                parceler,
                History.single(new ListContentScreen("Факультеты")),
                this);
    }

    private void initIcons() {
        hamburger = ResourcesCompat.getDrawable(this, R.drawable.hamburger);
        arrow = ResourcesCompat.getDrawable(this, R.drawable.arrow);
    }

    private void initHamburger() {

        toolbar.setNavigationIcon(hamburger);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.closeDrawers();
                }
            }
        });
        toolbar.setTitle(R.string.toolbar_title_student);
        toolbar.setTitleTextColor(0xFFFFFFFF);
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

    private void initMenu() {
        menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Flow.get(MainActivity.this).setHistory(
                        History.single(new ListContentScreen(menuItem.getTitle().toString())),
                        Flow.Direction.REPLACE
                );
                toolbar.setNavigationIcon(arrow);
                drawerLayout.closeDrawers();  // CLOSE DRAWER
                toolbar.setTitle(menuItem.getTitle());
                return true;
            }
        });
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
        if (!containerAsHandlesBack.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return containerAsHandlesBack.onBackPressed();
        }
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
