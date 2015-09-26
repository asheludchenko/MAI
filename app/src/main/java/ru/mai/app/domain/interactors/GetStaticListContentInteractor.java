package ru.mai.app.domain.interactors;

import java.util.List;

import ru.mai.app.data.dataModel.StaticListContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticListContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
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
