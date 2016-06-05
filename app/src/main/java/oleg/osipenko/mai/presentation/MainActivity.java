package oleg.osipenko.mai.presentation;

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
import flow.path.Path;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.App;
import oleg.osipenko.mai.ConstantsKt;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.Router;
import oleg.osipenko.mai.presentation.events.ChangeScreenEvent;
import oleg.osipenko.mai.presentation.events.ChangeSelectedTabEvent;
import oleg.osipenko.mai.presentation.events.OpenCourseScheduleEvent;
import oleg.osipenko.mai.presentation.events.OpenScheduleEvent;
import oleg.osipenko.mai.presentation.events.SwipePageEvent;
import oleg.osipenko.mai.presentation.mf_boilerplate.GsonParceler;
import oleg.osipenko.mai.presentation.mf_boilerplate.HandlesBack;
import oleg.osipenko.mai.presentation.mf_boilerplate.MortarScreenSwitcherFrame;
import oleg.osipenko.mai.presentation.screens.MainScreen;
import oleg.osipenko.mai.presentation.screens.MainSliderScreen;
import oleg.osipenko.mai.presentation.screens.NewsContentScreen;

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
            setupTitleOnBack();
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
        if (getIntent() != null && getIntent().hasExtra(ConstantsKt.EXTRA_ID)) {
            flowDelegate = FlowDelegate.onCreate(
                    nonConfig,
                    getIntent(),
                    savedInstanceState,
                    parceler,
                    History.single(new NewsContentScreen(getIntent().getExtras().get(ConstantsKt.EXTRA_ID).toString())),
                    this);
        } else {
            flowDelegate = FlowDelegate.onCreate(
                    nonConfig,
                    getIntent(),
                    savedInstanceState,
                    parceler,
                    History.single(new MainScreen()),
                    this);
        }

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
            if (null != headerref && headerref.get() != null)
                menu.removeHeaderView(headerref.get());
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
            setupTitleOnBack();
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
        if (title.equals(Router.VIDEO)) {
            Intent showYoutube = new Intent(this, MaiChannelActivity.class);
            startActivity(showYoutube);
            //titleHistory.push(title);
        } else {
            Path newScreen = router.getScreen(title);
            Flow.get(MainActivity.this).set(newScreen);
            //titleHistory.push(title);
        }
    }

    private void setToolbarTitle(String title) {
        if (title.equals(Router.VIDEO)) return;
        int delimCount = title.length() - title.replaceAll(Router.DELIM, "").length();
        if (delimCount == 1 && !title.startsWith(Router.SCHEDULE) || title.equals(Router.PHOTO) || title.equals(Router.PRESENTATIONS)) {
            titleHistory.push(toolbar.getTitle().toString());
            String[] parts = title.split(Router.DELIM);
            if (parts.length == 1) {
                 if (parts[0].equals(Router.COURSES)) {
                    toolbar.setTitle(Router.COURSES);
                } else {
                    toolbar.setTitle(parts[0]);
                }
            } else {
                if (parts[0].equals(Router.ACADEMIC_MOBILITY)) {
                    toolbar.setTitle("");
                } else if (parts[0].equals(Router.SCHOOL_ACTIVITY)) {
                    toolbar.setTitle("Мероприятия");
                } else if (parts[0].equals(Router.SCHOOL_CENTERS)) {
                    toolbar.setTitle("Центры обучения");
                } else if (parts[0].equals(Router.PODGOTOVKA)) {
                    toolbar.setTitle("Подготовка");
                } else if (parts[1].equals(Router.PODGOTOVKA)) {
                    toolbar.setTitle("Подготовка");
                } else if (parts[0].equals(Router.DOSAAF)) {
                    toolbar.setTitle("ДОСААФ");
                } else if (parts[0].equals(Router.DK)) {
                    toolbar.setTitle("ДКиТ МАИ");
                } else if (parts[0].equals(Router.SECONDARY_EDUCATION)) {
                    toolbar.setTitle("Второе высшее");
                } else if (parts[1].equals(Router.F5) || parts[1].equals(Router.MILIT_INST)) {
                    toolbar.setTitle("Институты");
                } else if (parts[0].equals(Router.MAGISTRACY)) {
                    toolbar.setTitle(Router.MAGISTRACY);
                } else if (parts[1].equals(Router.BAYC) || parts[1].equals(Router.AHTUBA) || parts[1].equals(Router.STRELA) || parts[1].equals(Router.KHIMKI)) {
                    toolbar.setTitle("Филиалы");
                } else if (parts[1].equals(Router.MILIT_01)) {
                    toolbar.setTitle("УВЦ");
                } else if (parts[1].equals(Router.MILIT_02)) {
                    toolbar.setTitle(Router.MILIT_02);
                } else {
                    toolbar.setTitle(parts[0]);
                }
            }
        } else {
            String[] parts = title.split(Router.DELIM);
            if (parts.length > 1) {
                if (parts[0].equals(Router.PODGOTOVKA)) {
                    toolbar.setTitle("Подготовка");
                } else if (parts[0].equals(Router.WAYS)) {
                    toolbar.setTitle("Направления");
                    titleHistory.push("Направления");
                } else if (title.startsWith(Router.SCHEDULE)) {
                    String titleToSet = parts[1].split(" ")[0];
                    toolbar.setTitle(titleToSet);
                    titleHistory.push(title);
                } else {
                    toolbar.setTitle(parts[0]);
                    titleHistory.push(title);
                }

            } else {
                toolbar.setTitle(title);
                titleHistory.push(title);
            }
        }
    }

    private void resetCounters(String title) {
        //if (!titleHistory.isEmpty() && titleHistory.peek().equals(Router.PHOTO))
            App.resetPhotoPage();
       // if (!titleHistory.isEmpty() && titleHistory.peek().equals(Router.NEWS) ||
         //       (!titleHistory.isEmpty() && title.startsWith(Router.NEWS + Router.DELIM)))
            App.resetNewsPage();
    }

    private void startNewHistory(String title) {
        resetCounters(title);
        titleHistory.clear();
        if (!isStudent) {
            titleHistory.push(getString(R.string.toolbar_title_abitur));
        } else {
            titleHistory.push(getString(R.string.toolbar_title_student));
        }
        if (title.equals(Router.ACADEMIC_MOBILITY)) {
            toolbar.setTitle("");
        } else if (title.equals(Router.SCHOOL_CENTERS)) {
            toolbar.setTitle("Центры обучения");
        } else if (title.equals(Router.SCHOOL_ACTIVITY)) {
            toolbar.setTitle("Мероприятия");
        } else if (title.equals(Router.WAYS)) {
            toolbar.setTitle("Направления");
        } else if (title.equals(Router.SECONDARY_EDUCATION)) {
            toolbar.setTitle("Второе высшее");
        } else if (title.equals(Router.MAGISTRACY)) {
            toolbar.setTitle(Router.MAGISTRACY);
        } else if (title.equals(Router.DK)) {
            toolbar.setTitle("ДКиТ МАИ");
        } else if (title.equals(Router.SCIENCE)) {
            toolbar.setTitle("Наука");
        } else if (title.equals(Router.SANATORIUM)) {
            toolbar.setTitle("Санаторий");
        } else if (title.equals(Router.DOSAAF)) {
            toolbar.setTitle("ДОСААФ");
        } else if (title.equals(Router.PROFKOM)) {
            toolbar.setTitle("Профком");
        } else if (title.equals(Router.PRESS)) {
            toolbar.setTitle("СМИ и интернет");
        } else if (title.equals(Router.MAISKY_VZLET)) {
            toolbar.setTitle("Майский взлёт");
        } else if (title.equals(Router.EMPLOYMENT_CENTER)) {
            toolbar.setTitle("");
        } else if (title.equals(Router.DOTATIONS)) {
            toolbar.setTitle("Дотации");
        } else if (title.equals(Router.PODGOTOVKA)) {
            toolbar.setTitle("Подготовка");
        } else if (title.equals(Router.DOCS)) {
            toolbar.setTitle("Документы");
        } else {
            toolbar.setTitle(title);
        }
        Path newScreen = router.getScreen(title);
        Flow.get(this).setHistory(
                History.emptyBuilder()
                        .push(new MainScreen())
                        .push(newScreen)
                        .build(),
                Flow.Direction.REPLACE
        );
    }

    private void setupTitleOnBack() {
        if (!titleHistory.isEmpty()) {
            String titleFromHistory = titleHistory.removeFirst();
            int delimCount = titleFromHistory.length() - titleFromHistory.replaceAll(Router.DELIM, "").length();
            if (delimCount == 1 && !titleFromHistory.startsWith(Router.SCHEDULE)) {
                String[] parts = titleFromHistory.split(Router.DELIM);
                toolbar.setTitle(parts[0]);
            } else {
                String[] parts = titleFromHistory.split(Router.DELIM);
                if (parts.length > 1) {
                    toolbar.setTitle(parts[0]);
                } else {
                    toolbar.setTitle(titleFromHistory);
                }
            }
        }
        if (titleHistory.isEmpty()) drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Subscribe
    public void itemClicked(ChangeScreenEvent event) {
        resetCounters(event.getTitle());
        setToolbarTitle(event.getTitle());
        changeScreen(event.getTitle());
    }

    @Subscribe
    public void schedItemClicked(OpenCourseScheduleEvent event) {
        resetCounters(event.getTitle());
        setToolbarTitle(event.getTitle());
        changeScreen(event.getName());
    }

    @Subscribe
    public void swipeScreen(ChangeSelectedTabEvent event) {
        tabs.setOnTabSelectedListener(null);
        tabs.getTabAt(event.getPosition()).select();
        setToolbarTitle(tabs.getTabAt(event.getPosition()).getText().toString());
        tabs.setOnTabSelectedListener(tabListener);
    }

    @Subscribe
    public void openSchedule(OpenScheduleEvent event) {
        resetCounters(event.getTitle());
        setToolbarTitle(event.getTitle());
        changeScreen(event.getUrl());
    }

    public class TabListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (Flow.get(MainActivity.this).getHistory().top() instanceof MainSliderScreen) {
                App.bus.post(new SwipePageEvent(tab.getPosition()));
                if (tab.getPosition() != 0) {
                    setArrow();
                } else {
                    setHamburger();
                }
            } else if (Flow.get(MainActivity.this) != null) {
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
            if (Flow.get(MainActivity.this).getHistory().top() instanceof MainSliderScreen) {
                App.bus.post(new SwipePageEvent(tab.getPosition()));
                if (tab.getPosition() != 0) {
                    setArrow();
                } else {
                    setHamburger();
                }
            } else if (Flow.get(MainActivity.this) != null) {
                String title = tab.getText().toString();
                startNewHistory(title);
                if (tab.getPosition() != 0) {
                    setArrow();
                } else {
                    setHamburger();
                }
            }
        }
    }
}
