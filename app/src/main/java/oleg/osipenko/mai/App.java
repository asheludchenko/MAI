package oleg.osipenko.mai;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import flow.StateParceler;
import mortar.MortarScope;
import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.presentation.MainActivity;
import oleg.osipenko.mai.presentation.mf_boilerplate.GsonParceler;

/**
 * Created by olegosipenko on 06.09.15.
 */
public class App extends Application {

    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
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
            injects = MainActivity.class,
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
        Gson provideGson() {
            return new GsonBuilder().create();
        }

        @Provides
        @Singleton
        StateParceler provideParceler(Gson gson) {
            return new GsonParceler(gson);
        }
    }
}
