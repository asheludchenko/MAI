package oleg.osipenko.mai.domain.interactors;

import java.util.List;

import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class GetStaticListContentInteractor extends Interactor<StaticListContentSpecification, List<StaticListContent>> {

    public GetStaticListContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<StaticListContent>> buildObservable() {
        return repository.getStaticListContent(parameter);
    }
}
