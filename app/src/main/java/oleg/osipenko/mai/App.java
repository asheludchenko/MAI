package oleg.osipenko.mai;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import flow.StateParceler;
import io.fabric.sdk.android.Fabric;
import mortar.MortarScope;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.data.DataModule;
import oleg.osipenko.mai.data.dto.MainScreenDto;
import oleg.osipenko.mai.data.dto.ScheduleCourses;
import oleg.osipenko.mai.data.dto.ScheduleFaculties;
import oleg.osipenko.mai.presentation.MainActivity;
import oleg.osipenko.mai.presentation.PhotoActivity;
import oleg.osipenko.mai.presentation.mf_boilerplate.GsonParceler;
import oleg.osipenko.mai.presentation.views.ViewWeb;

/**
 * Created by olegosipenko on 06.09.15.
 */
public class App extends Application {

    public static Bus bus;
    private static int newsPage = 1;
    private static int photoPage = 1;
    private static List<MainScreenDto> screenDtos;
    private static List<ScheduleFaculties> faculties;
    private static List<ScheduleCourses> courses;
    private static Context sContext;

    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        ParseObject.registerSubclass(MainScreenDto.class);
        ParseObject.registerSubclass(ScheduleFaculties.class);
        ParseObject.registerSubclass(ScheduleCourses.class);
        if (!BuildConfig.DEBUG)
            Fabric.with(this, new Crashlytics());
        HashSet<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setRequestListeners(requestListeners)
                .build();
        Fresco.initialize(this, config);
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        Parse.initialize(this, ConstantsKt.getAPP_ID(), ConstantsKt.getCLIENT());
        ParseInstallation.getCurrentInstallation().saveInBackground();
        bus = new Bus();
        screenDtos = new ArrayList<>();
        faculties = new ArrayList<>();
        courses = new ArrayList<>();
        ParseQuery<ScheduleFaculties> facultiesQuery = ParseQuery.getQuery("Schedule_faculties");
        facultiesQuery.findInBackground(new FindCallback<ScheduleFaculties>() {
            @Override
            public void done(List<ScheduleFaculties> list, ParseException e) {
                if (e == null) {
                    setFaculties(list);
                }
            }
        });
    }

    @Override
    public Object getSystemService(String name) {
        if (null == rootScope) {
            rootScope = MortarScope.buildRootScope()
                    .withService(ObjectGraphService.SERVICE_NAME, ObjectGraph.create(new AppModule(this)))
                    .build("Root");
        }

        if (rootScope.hasService(name)) return rootScope.getService(name);
        return super.getSystemService(name);
    }

    public static int getNewsPage() {
        int current = newsPage;
        newsPage++;
        return current;
    }

    public static int getPhotoPage() {
        int current = photoPage;
        photoPage++;
        return current;
    }

    public static void resetNewsPage() {
        newsPage = 1;
    }

    public static void resetPhotoPage() {
        photoPage = 1;
    }

    public static List<MainScreenDto> getScreenDtos() {
        return screenDtos;
    }

    public static void setScreenDtos(List<MainScreenDto> screenDtos) {
        App.screenDtos.addAll(screenDtos);
    }

    public static List<ScheduleFaculties> getFaculties() {
        return faculties;
    }

    public static void setFaculties(List<ScheduleFaculties> faculties) {
        App.faculties.addAll(faculties);
    }

    public static List<ScheduleCourses> getCourses() {
        return courses;
    }

    public static void setCourses(List<ScheduleCourses> courses) {
        App.courses.addAll(courses);
    }

    public static boolean isStudent() {
        return sContext.getSharedPreferences(ConstantsKt.getSP_KEY(), Context.MODE_PRIVATE).getBoolean(ConstantsKt.getIS_STUDENT_KEY(), true);
    }

    @Module(
            injects = {
                    MainActivity.class,
                    PhotoActivity.class,
                    DataModule.class,
                    ViewWeb.class
            },
            library = true
    )
    public class AppModule {
        Context context;

        AppModule(Context context) {
            this.context = context;
        }

        @Provides
        @Singleton
        Context providesContext() {
            return context;
        }

        @Provides
        @Singleton
        Gson providesGson() {
            return new GsonBuilder().create();
        }

        @Provides
        @Singleton
        StateParceler providesParceler(Gson gson) {
            return new GsonParceler(gson);
        }

        @Provides
        @Singleton
        Router providesRouter() {
            return new Router();
        }
    }
}
