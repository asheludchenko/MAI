package ru.mai.app.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.mai.app.R;
import ru.mai.app.data.api.NetworkProvider;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.dataModel.NewsHeadersContent;
import ru.mai.app.data.dataModel.StaticContent;
import ru.mai.app.data.dataModel.StaticListContent;
import ru.mai.app.data.dto.MainScreenDto;
import ru.mai.app.data.repository.specification.ListContentSpecification;
import ru.mai.app.data.repository.specification.NewsContentSpecification;
import ru.mai.app.data.repository.specification.StaticContentSpecification;
import ru.mai.app.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
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
        return listContentProvider.getListContent(specification);
    }

    @Override
    public Observable<List<NewsHeadersContent>> getNews() {
        return networkProvider.getNews()
                .flatMap(new Func1<List<? extends NewsHeadersContent>, Observable<List<NewsHeadersContent>>>() {
                    @Override
                    public Observable<List<NewsHeadersContent>> call(List<? extends NewsHeadersContent> headersContents) {
                        List<NewsHeadersContent> ks = new ArrayList<NewsHeadersContent>(headersContents);
                        return Observable.just(ks);
                    }
                });
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return staticListContentProvider.getStaticListContent(specification);
    }

    @Override
    public Observable<Bitmap> getMapObservable() {
        return Observable.defer(new Func0<Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inJustDecodeBounds = true;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inDither = true;
                Bitmap map = BitmapFactory.decodeResource(context.getResources(), R.drawable.mai_plan_big_color, options);
                return Observable.just(map);
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
                        return Observable.just(ks);
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

    @Override
    public Observable<MainScreenDto> getImages() {
        return Observable.create(new Observable.OnSubscribe<MainScreenDto>() {
            @Override
            public void call(Subscriber<? super MainScreenDto> subscriber) {
                ParseQuery<MainScreenDto> query = ParseQuery.getQuery(MainScreenDto.class);
                try {
                    List<MainScreenDto> images = query.find();
                    Random random = new Random(System.nanoTime());
                    int randomIndex = random.nextInt(images.size());
                    subscriber.onNext(images.get(randomIndex));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
