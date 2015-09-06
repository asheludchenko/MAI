package oleg.osipenko.mai;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import oleg.osipenko.mai.presentation.MainActivity;
import oleg.osipenko.mai.presentation.PresentationModule;

/**
 * Created by olegosipenko on 06.09.15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        ObjectGraph.create(new AppModule(this));
    }

    @Module(
            includes = PresentationModule.class,
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
    }
}
