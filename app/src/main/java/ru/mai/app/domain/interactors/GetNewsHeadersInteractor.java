package ru.mai.app.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import ru.mai.app.data.dataModel.NewsHeadersContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 20.04.16.
 */
public class GetNewsHeadersInteractor extends Interactor<Void, List<NewsHeadersContent>> {
    @Inject
    public GetNewsHeadersInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<NewsHeadersContent>> buildObservable() {
        return repository.getNews();
    }
}
