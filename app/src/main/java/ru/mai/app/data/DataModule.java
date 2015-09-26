package ru.mai.app.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.MaiRepository;

/**
 * Created by olegosipenko on 06.09.15.
 */
@Module(
        library = true,
        complete = false
)
public class DataModule {

    @Provides
    @Singleton
    DataRepository providesRepository(Context context) {
        return new MaiRepository(context);
    }
}
