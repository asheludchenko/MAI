package ru.mai.app.data.repository;

import android.graphics.Bitmap;

import java.util.List;

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
