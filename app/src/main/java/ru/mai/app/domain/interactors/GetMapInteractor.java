package ru.mai.app.domain.interactors;

import android.graphics.Bitmap;

import javax.inject.Inject;

import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 14.09.15.
 */
public class GetMapInteractor extends Interactor<StaticContentSpecification, Bitmap> {
    @Inject
    public GetMapInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<Bitmap> buildObservable() {
        return repository.getMapObservable();
    }
}
