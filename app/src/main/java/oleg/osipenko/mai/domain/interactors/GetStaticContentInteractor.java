package oleg.osipenko.mai.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class GetStaticContentInteractor extends Interactor<StaticContentSpecification, List<StaticContent>> {
    @Inject
    public GetStaticContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<StaticContent>> buildObservable() {
        return repository.getStaticContent(parameter);
    }
}
