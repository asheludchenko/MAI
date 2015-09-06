package oleg.osipenko.mai.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.MaiRepository;
import oleg.osipenko.mai.domain.DomainModule;

/**
 * Created by olegosipenko on 06.09.15.
 */
@Module(injects = DomainModule.class)
public class DataModule {
    @Provides
    @Singleton
    DataRepository providesRepository() {
        return new MaiRepository();
    }
}
