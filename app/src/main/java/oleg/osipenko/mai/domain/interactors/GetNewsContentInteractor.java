package oleg.osipenko.mai.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
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
