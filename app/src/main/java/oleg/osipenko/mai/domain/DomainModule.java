package oleg.osipenko.mai.domain;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import oleg.osipenko.mai.data.DataModule;
import oleg.osipenko.mai.domain.executors.JobExecutor;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import oleg.osipenko.mai.domain.executors.UIThread;

/**
 * Created by olegosipenko on 06.09.15.
 */
@Module(
        library = true,
        includes = DataModule.class,
        complete = false
)
public class DomainModule {


    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }


    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
