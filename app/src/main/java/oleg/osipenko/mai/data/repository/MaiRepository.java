package oleg.osipenko.mai.data.repository;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class MaiRepository implements DataRepository {

    public MaiRepository() {
        Log.d("mai", "constructing repo");
    }

    @Override
    public Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification) {
        return null;
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        ListContent content = new ListContent("факультетики");
        final List<ListContent> list = Arrays.asList(content);
        return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
            @Override
            public void call(Subscriber<? super List<ListContent>> subscriber) {
                Log.d("mai", "send content");
                subscriber.onNext(list);
            }
        });
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return null;
    }
}
