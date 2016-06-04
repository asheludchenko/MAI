package oleg.osipenko.mai.data.repository;

import android.graphics.Bitmap;

import java.util.List;

import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.NewsHeadersContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.dto.MainScreenDto;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import rx.Observable;

/**
 * Created by olegosipenko on 07.09.15.
 */
public interface DataRepository {
    Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification);

    Observable<List<ListContent>> getListContent(ListContentSpecification specification);

    Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification);

    Observable<Bitmap> getMapObservable();

    Observable<List<StaticContent>> getSingleNews(NewsContentSpecification parameter);

    Observable<List<ListContent>> getPhotos(NewsContentSpecification parameter);

    Observable<MainScreenDto> getImages();

    Observable<List<NewsHeadersContent>> getNews();
}
