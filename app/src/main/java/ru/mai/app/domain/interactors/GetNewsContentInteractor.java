package ru.mai.app.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 25.09.15.
 */
public class GetNewsContentInteractor extends Interactor<NewsContentSpecification, List<StaticContent>> {

    @Inject
    public GetNewsContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<StaticContent>> buildObservable() {
        return repository.getSingleNews(parameter);
    }
}
