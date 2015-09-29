package ru.mai.app.domain.interactors;

import java.util.Set;

import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 29.09.15.
 */
public class GetImageInteractor extends Interactor<String, String> {
    public GetImageInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<String> buildObservable() {
        return repository.getImages();
    }
}
