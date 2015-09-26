package ru.mai.app.domain.interactors;

import java.util.List;

import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
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
