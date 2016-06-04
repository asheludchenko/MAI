package oleg.osipenko.mai.domain.interactors;

import oleg.osipenko.mai.data.dto.MainScreenDto;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 29.09.15.
 */
public class GetImageInteractor extends Interactor<Void, MainScreenDto> {
    public GetImageInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
    }

    @Override
    protected Observable<MainScreenDto> buildObservable() {
        return repository.getImages();
    }
}
