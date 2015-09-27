package ru.mai.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import flow.StateParceler;
import mortar.MortarScope;
import mortar.dagger1support.ObjectGraphService;
import ru.mai.app.data.DataModule;
import ru.mai.app.presentation.MainActivity;
import ru.mai.app.presentation.mf_boilerplate.GsonParceler;
import ru.mai.app.presentation.views.ViewWeb;

/**
 * Created by olegosipenko on 06.09.15.
 */
public class App extends Application {

    public static Bus bus;

    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        LeakCanary.install(this);
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


    @Module(
            injects = {
                    MainActivity.class,
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
