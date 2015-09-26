package ru.mai.app.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.ListContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class GetListContentInteractor extends Interactor<ListContentSpecification, List<ListContent>> {

    @Inject
    public GetListContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<ListContent>> buildObservable() {
        return repository.getListContent(parameter);
    }
}
