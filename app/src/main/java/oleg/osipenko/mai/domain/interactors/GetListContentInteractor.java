package oleg.osipenko.mai.domain.interactors;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.repository.DataRepository;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.domain.executors.PostExecutionThread;
import oleg.osipenko.mai.domain.executors.ThreadExecutor;
import rx.Observable;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class GetListContentInteractor extends Interactor<ListContentSpecification, List<ListContent>> {

    @Inject
    public GetListContentInteractor(DataRepository repository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(repository, postExecutionThread, threadExecutor);
        Log.d("mai", "interactor constructor");
    }

    @Override
    protected Observable<List<ListContent>> buildObservable() {
        return repository.getListContent(parameter);
    }
}
