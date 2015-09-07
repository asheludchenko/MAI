package oleg.osipenko.mai.data;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.MaiRepository;

/**
 * Created by olegosipenko on 06.09.15.
 */
@Module(library = true)
public class DataModule {

    public DataModule() {
        Log.d("mai", "data constructor");
    }

    @Provides
    @Singleton
    DataRepository providesRepository() {
        return new MaiRepository();
    }
}
