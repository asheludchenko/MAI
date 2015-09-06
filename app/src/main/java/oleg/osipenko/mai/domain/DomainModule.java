package oleg.osipenko.mai.domain;

import javax.inject.Inject;

import dagger.Module;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.presentation.PresentationModule;

/**
 * Created by olegosipenko on 06.09.15.
 */
@Module(injects = PresentationModule.class)
public class DomainModule {
    @Inject
    DataRepository repository;
}
