package oleg.osipenko.mai.domain.interactors;

import android.graphics.Bitmap;

import javax.inject.Inject;

import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
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
