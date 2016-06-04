package oleg.osipenko.mai.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.MaiRepository;

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
