package oleg.osipenko.mai.domain.interactors;

import java.util.List;

import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 25.09.15.
 */
public class GetPhotosInteractor extends Interactor<NewsContentSpecification, List<ListContent>> {
    public GetPhotosInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<List<ListContent>> buildObservable() {
        return repository.getPhotos(parameter);
    }
}
