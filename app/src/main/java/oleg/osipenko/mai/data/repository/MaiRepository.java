package oleg.osipenko.mai.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import mortar.dagger1support.ObjectGraphService;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import oleg.osipenko.mai.presentation.utils.SimpleSectionListAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;

import static oleg.osipenko.mai.Router.ACADEMIC_MOBILITY;
import static oleg.osipenko.mai.Router.CANTEENS;
import static oleg.osipenko.mai.Router.COURSES;
import static oleg.osipenko.mai.Router.DEBATING_CLUB;
import static oleg.osipenko.mai.Router.DK;
import static oleg.osipenko.mai.Router.STUDGORODOK;
import static oleg.osipenko.mai.Router.DOSAAF;
import static oleg.osipenko.mai.Router.DOTATIONS;
import static oleg.osipenko.mai.Router.EMPLOYMENT_CENTER;
import static oleg.osipenko.mai.Router.FACULTIES;
import static oleg.osipenko.mai.Router.HELP;
import static oleg.osipenko.mai.Router.HISTORY;
import static oleg.osipenko.mai.Router.LIBRARIES;
import static oleg.osipenko.mai.Router.LIFE;
import static oleg.osipenko.mai.Router.MAGISTRACY;
import static oleg.osipenko.mai.Router.MAISKY_VZLET;
import static oleg.osipenko.mai.Router.MEDIA;
import static oleg.osipenko.mai.Router.MILITARY_INSTITUTE;
import static oleg.osipenko.mai.Router.PRACTICS;
import static oleg.osipenko.mai.Router.PRESS;
import static oleg.osipenko.mai.Router.PROFKOM;
import static oleg.osipenko.mai.Router.RECREATION_CENTERS;
import static oleg.osipenko.mai.Router.SANATORIUM;
import static oleg.osipenko.mai.Router.SCHOLARSHIPS;
import static oleg.osipenko.mai.Router.SCIENCE;
import static oleg.osipenko.mai.Router.SECONDARY_EDUCATION;
import static oleg.osipenko.mai.Router.SESSION;
import static oleg.osipenko.mai.Router.SOMOL;
import static oleg.osipenko.mai.Router.SPORT_SECTIONS;

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
