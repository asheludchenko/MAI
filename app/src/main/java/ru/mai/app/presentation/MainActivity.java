package ru.mai.app.presentation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;
import com.squareup.otto.Subscribe;
import com.wnafee.vector.compat.ResourcesCompat;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.App;
import ru.mai.app.ConstantsKt;
import ru.mai.app.R;
import ru.mai.app.Router;
import ru.mai.app.presentation.events.ChangeScreenEvent;
import ru.mai.app.presentation.mf_boilerplate.GsonParceler;
import ru.mai.app.presentation.mf_boilerplate.HandlesBack;
import ru.mai.app.presentation.mf_boilerplate.MortarScreenSwitcherFrame;
import ru.mai.app.presentation.screens.MainScreen;

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
    @Bind(R.id.tab_layout)
    TabLayout tabs;

    @Inject
    GsonParceler parceler;
    @Inject
    Router router;

    private MortarScope activityScope;
    private HandlesBack containerAsHandlesBack;
    private FlowDelegate flowDelegate;

    private Drawable hamburger;
    private Drawable arrow;
    boolean isStudent;
    private Deque<String> titleHistory = new LinkedList<>();
    private TabListener tabListener;
    private WeakReference<View> headerref;

    private View.OnClickListener hambgurgerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT);
            } else {
                drawerLayout.closeDrawers();
            }
        }
    };

    private View.OnClickListener arrowListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            toolbar.setTitle(titleHistory.removeFirst());
            containerAsHandlesBack.onBackPressed();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @SuppressWarnings("deprecation")
        FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        mortarStuff(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabListener = new TabListener();

        isStudent = getSharedPreferences(ConstantsKt.getSP_KEY(), MODE_PRIVATE).getBoolean(ConstantsKt.getIS_STUDENT_KEY(), true);

        initIcons();
        initToolbar();
        initMenu();
        initTabs();
        updateWeek();

        containerAsHandlesBack = (HandlesBack) container;
        flowDelegate = FlowDelegate.onCreate(
                nonConfig,
                getIntent(),
                savedInstanceState,
                parceler,
                History.single(new MainScreen()),
                this);

    }

    private void updateWeek() {
        ParseConfig.getInBackground(new ConfigCallback() {
            @Override
            public void done(ParseConfig config, ParseException e) {
                if (null == e) {
                    boolean isEven = config.getBoolean(ConstantsKt.getCONFIG_WEEK_KEY());
                    SharedPreferences.Editor editor = getSharedPreferences(ConstantsKt.getSP_KEY(), MODE_PRIVATE).edit();
                    if (isStudent) {
                        editor.putStringSet(ConstantsKt.getIMAGES_KEY(), new HashSet<String>(config.getList(ConstantsKt.getSTUDENT_IMAGES(), new ArrayList<String>())));
                    } else {
                        editor.putStringSet(ConstantsKt.getIMAGES_KEY(), new HashSet<String>(config.getList(ConstantsKt.getABITUR_IMAGES(), new ArrayList<String>())));
                    }
                    editor.putBoolean(ConstantsKt.getWEEK_KEY(), isEven);
                    editor.apply();
                    inflateMenu();
                }
            }
        });
    }

    private void initTabs() {
        if (tabs.getTabCount() > 0) tabs.removeAllTabs();
        if (isStudent) {
            tabs.addTab(tabs.newTab().setText(R.string.tab_main));
            tabs.addTab(tabs.newTab().setText(R.string.tab_news));
            tabs.addTab(tabs.newTab().setText(R.string.tab_map));
            tabs.addTab(tabs.newTab().setText(R.string.tab_schedule));
        } else {
            tabs.addTab(tabs.newTab().setText(R.string.tab_main));
            tabs.addTab(tabs.newTab().setText(R.string.tab_news));
            tabs.addTab(tabs.newTab().setText(R.string.tab_priem));
            tabs.addTab(tabs.newTab().setText(R.string.tab_media));
        }
        tabs.setOnTabSelectedListener(tabListener);
    }

    private void initIcons() {
        hamburger = ResourcesCompat.getDrawable(this, R.drawable.hamburger);
        arrow = ResourcesCompat.getDrawable(this, R.drawable.arrow);
    }

    private void initToolbar() {
        final SharedPreferences sp = getSharedPreferences(ConstantsKt.getSP_KEY(), MODE_PRIVATE);
        if (!isStudent) {
            toolbar.setTitle(R.string.toolbar_title_abitur);
        } else {
            toolbar.setTitle(R.string.toolbar_title_student);
        }
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp.edit().clear().apply();
                titleHistory.clear();
                finish();
                return true;
            }
        });
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_overflow));
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setHamburger();
    }

    private void setArrow() {
        toolbar.setNavigationIcon(arrow);
        toolbar.setNavigationOnClickListener(arrowListener);
    }

    private void setHamburger() {
        toolbar.setNavigationIcon(hamburger);
        toolbar.setNavigationOnClickListener(hambgurgerListener);
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
        inflateMenu();
        menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                startNewHistory(menuItem.getTitle().toString());
                if (tabs.getSelectedTabPosition() != 0) {
                    tabs.setOnTabSelectedListener(null);
                    tabs.getTabAt(0).select();
                    tabs.setOnTabSelectedListener(tabListener);
                }
                if (menuItem.toString().contains(Router.WEEK)) {
                    setHamburger();
                } else {
                    setArrow();
                }
                drawerLayout.closeDrawers();  // CLOSE DRAWER
                return true;
            }
        });
    }

    private void inflateMenu() {
        SharedPreferences sp = getSharedPreferences(ConstantsKt.getSP_KEY(), MODE_PRIVATE);
        if (!sp.getBoolean(ConstantsKt.getIS_STUDENT_KEY(), true)) {
            menu.getMenu().clear();
            menu.inflateMenu(R.menu.abiturient);
        } else {
            menu.getMenu().clear();
            menu.inflateMenu(R.menu.student);
            boolean isTopEven = sp.getBoolean(ConstantsKt.getWEEK_KEY(), true);
            boolean currentIsEven = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) % 2 == 0;
            TextView header = (TextView) View.inflate(this, R.layout.header, null);
            if ((currentIsEven && isTopEven) || (!currentIsEven && !isTopEven)) {
                header.setText("Верхняя неделя");
            } else {
                header.setText("Нижняя неделя");
            }
            if (null != headerref && headerref.get() != null) menu.removeHeaderView(headerref.get());
            headerref = new WeakReference<View>(header);
            menu.addHeaderView(header);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        flowDelegate.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.bus.register(this);
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.bus.unregister(this);
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
            if (Build.VERSION.SDK_INT >= 16) {
                finishAffinity();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }

        } else {
            toolbar.setTitle(titleHistory.removeFirst());
        }
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
        if (traversal.destination.size() == 1) setHamburger();
        container.dispatch(traversal, callback);
    }

    private void changeScreen(String title) {
        Log.d("mai", title);
        resetCounters(title);
        if (!title.startsWith(Router.NEWS + Router.DELIM)) {
            titleHistory.push(toolbar.getTitle().toString());
            String[] a = title.split("#");
            if (a.length > 1) {
                String[] b = a[1].split("#");
                String screenTitle = b.length > 1 ? b[1] : b[0];
                toolbar.setTitle(screenTitle);
            } else {
                toolbar.setTitle(title);
            }
        } else {
            titleHistory.push(Router.NEWS);
        }
        if (title.equals(Router.VIDEO)) {
            Intent showYoutube = new Intent(this, MaiChannelActivity.class);
            startActivity(showYoutube);
        } else Flow.get(MainActivity.this).set(router.getScreen(title));
    }

    private void resetCounters(String title) {
        if (!titleHistory.isEmpty() && titleHistory.peek().equals(Router.PHOTO))
            App.resetPhotoPage();
        if (!titleHistory.isEmpty() && titleHistory.peek().equals(Router.NEWS) ||
                (!titleHistory.isEmpty() && title.startsWith(Router.NEWS + Router.DELIM)))
            App.resetNewsPage();
    }

    private void startNewHistory(String s) {
        resetCounters(s);
        titleHistory.clear();
        if (!isStudent) {
            titleHistory.push(getString(R.string.toolbar_title_abitur));
        } else {
            titleHistory.push(getString(R.string.toolbar_title_student));
        }
        toolbar.setTitle(s);
        Flow.get(this).setHistory(
                History.emptyBuilder()
                        .push(new MainScreen())
                        .push(router.getScreen(s))
                        .build(),
                Flow.Direction.REPLACE
        );
    }

    @Subscribe
    public void itemClicked(ChangeScreenEvent event) {
        changeScreen(event.getTitle());
    }

    public class TabListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (Flow.get(MainActivity.this) != null) {
                String title = tab.getText().toString();
                startNewHistory(title);
                if (tab.getPosition() != 0) {
                    setArrow();
                } else {
                    setHamburger();
                }
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
