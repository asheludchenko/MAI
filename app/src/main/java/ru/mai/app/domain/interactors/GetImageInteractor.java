package ru.mai.app.domain.interactors;

import java.util.Set;

import ru.mai.app.data.dto.MainScreenDto;
import ru.mai.app.data.repository.DataRepository;
import ru.mai.app.domain.executors.PostExecutionThread;
import ru.mai.app.domain.executors.ThreadExecutor;
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
