package ru.mai.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.Parse;
import com.parse.ParseObject;
import com.squareup.otto.Bus;

import java.util.HashSet;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import flow.StateParceler;
import io.fabric.sdk.android.Fabric;
import mortar.MortarScope;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.data.DataModule;
import ru.mai.app.data.dto.MainScreenDto;
import ru.mai.app.presentation.MainActivity;
import ru.mai.app.presentation.PhotoActivity;
import ru.mai.app.presentation.mf_boilerplate.GsonParceler;
import ru.mai.app.presentation.views.ViewWeb;

/**
 * Created by olegosipenko on 06.09.15.
 */
public class App extends Application {

    public static Bus bus;
    private static int newsPage = 1;
    private static int photoPage = 1;

    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(MainScreenDto.class);
        if (!BuildConfig.DEBUG) Fabric.with(this, new Crashlytics());
        HashSet<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setRequestListeners(requestListeners)
                .build();
        Fresco.initialize(this, config);
        Parse.initialize(this, ConstantsKt.getAPP_ID(), ConstantsKt.getCLIENT());
        bus = new Bus();
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
            return new Router(context);
        }
    }
}
