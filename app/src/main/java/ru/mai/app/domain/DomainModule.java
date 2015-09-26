package ru.mai.app.domain;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.mai.app.data.DataModule;
import ru.mai.app.domain.executors.JobExecutor;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import ru.mai.app.domain.executors.UIThread;

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
