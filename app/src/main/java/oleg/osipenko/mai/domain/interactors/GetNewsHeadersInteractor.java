package oleg.osipenko.mai.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import oleg.osipenko.mai.data.dataModel.NewsHeadersContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 20.04.16.
 */
public class GetNewsHeadersInteractor extends Interactor<Integer, List<NewsHeadersContent>> {
    @Inject
    public GetNewsHeadersInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<NewsHeadersContent>> buildObservable() {
        return repository.getNews(parameter);
    }
}
