package oleg.osipenko.mai.data.repository;

import android.content.Context;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class MaiRepository implements DataRepository {

    public static final String FACULTIES = "Факультеты";

    List<Integer> facImages = Arrays.asList(
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th,
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th,
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th
    );


    List<Integer> instImages = Arrays.asList(
            R.drawable.f1st,
            R.drawable.f2nd
    );

    List<Integer> filImages = Arrays.asList(
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th
    );

    Context context;

    public MaiRepository(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification) {
        return null;
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(FACULTIES)) {
            String[] faculties = context.getResources().getStringArray(R.array.faculties);
            String[] institutes = context.getResources().getStringArray(R.array.institutes);
            String[] filials = context.getResources().getStringArray(R.array.filials);
            Observable<String> unitedStrings = Observable.from(faculties)
                    .concatWith(Observable.from(institutes))
                    .concatWith(Observable.from(filials));
            Observable<Integer> unitedImages = Observable.from(facImages)
                    .concatWith(Observable.from(instImages))
                    .concatWith(Observable.from(filImages));
            return Observable.zip(unitedStrings, unitedImages, new Func2<String, Integer, ListContent>() {
                @Override
                public ListContent call(String s, Integer integer) {
                    return new ListContent.Builder()
                            .setText(s)
                            .setImage(String.valueOf(integer))
                            .setWithImage(true)
                            .build();
                }
            })
                    .toList()
                    .cache();
        }
        return Observable.from(Collections.EMPTY_LIST);
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return null;
    }
}
