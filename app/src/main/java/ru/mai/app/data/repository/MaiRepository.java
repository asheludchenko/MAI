package ru.mai.app.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import oleg.osipenko.app.R;
import ru.mai.app.Router;
import oleg.osipenko.app.data.api.NetworkProvider;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.dataModel.StaticListContent;
import ru.mai.app.data.repository.specification.ListContentSpecification;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class MaiRepository implements DataRepository {

    Context context;
    StaticContentProvider staticContentProvider;
    ListContentProvider listContentProvider;
    StaticListContentProvider staticListContentProvider;
    NetworkProvider networkProvider;

    public MaiRepository(Context context) {
        this.context = context;
        staticContentProvider = new StaticContentProvider(context);
        listContentProvider = new ListContentProvider(context);
        staticListContentProvider = new StaticListContentProvider(context);
        networkProvider = new NetworkProvider();
    }

    @Override
    public Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification) {
        return staticContentProvider.getStaticContent(specification);
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(Router.NEWS)) {
            return networkProvider.getNews()
                    .flatMap(new Func1<List<? extends ListContent>, Observable<List<ListContent>>>() {
                        @Override
                        public Observable<List<ListContent>> call(List<? extends ListContent> listContents) {
                            List<ListContent> ks = new ArrayList<ListContent>(listContents);
                            return  Observable.just(ks);
                        }
                    });
        }
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

    @Override
    public Observable<List<StaticContent>> getSingleNews(NewsContentSpecification parameter) {
        return networkProvider.getNewsById(parameter)
                .flatMap(new Func1<List<? extends StaticContent>, Observable<List<StaticContent>>>() {
                    @Override
                    public Observable<List<StaticContent>> call(List<? extends StaticContent> staticContents) {
                        List<StaticContent> ks = new ArrayList<StaticContent>(staticContents);
                        return  Observable.just(ks);
                    }
                });
    }

    @Override
    public Observable<List<ListContent>> getPhotos(NewsContentSpecification parameter) {
        return networkProvider.getPhotoAlbums()
                .flatMap(new Func1<List<? extends ListContent>, Observable<List<ListContent>>>() {
                    @Override
                    public Observable<List<ListContent>> call(List<? extends ListContent> listContents) {
                        List<ListContent> als = new ArrayList<ListContent>(listContents);
                        return Observable.just(als);
                    }
                });
    }
}
