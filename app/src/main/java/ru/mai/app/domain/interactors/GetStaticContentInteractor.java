package ru.mai.app.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
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
