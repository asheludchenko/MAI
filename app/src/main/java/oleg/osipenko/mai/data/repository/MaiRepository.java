package oleg.osipenko.mai.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

import oleg.osipenko.mai.R;
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

    Context context;
    StaticContentProvider staticContentProvider;
    ListContentProvider listContentProvider;
    StaticListContentProvider staticListContentProvider;

    public MaiRepository(Context context) {
        this.context = context;
        staticContentProvider = new StaticContentProvider(context);
        listContentProvider = new ListContentProvider(context);
        staticListContentProvider = new StaticListContentProvider(context);
    }

    @Override
    public Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification) {
        return staticContentProvider.getStaticContent(specification);
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        return listContentProvider.getListContent(specification);
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return staticListContentProvider.getStaticListContent(specification);
    }

    @Override
    public Observable<Bitmap> getMapObservable() {

        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap map = BitmapFactory.decodeResource(context.getResources(), R.drawable.map);
                subscriber.onNext(map);
            }
        });
    }
}
