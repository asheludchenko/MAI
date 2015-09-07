package oleg.osipenko.mai.data.repository;

import android.content.Context;

import java.util.List;

import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class MaiRepository implements DataRepository {

    public static final String FACULTIES = "menu_faculties";

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
            return Observable.from(faculties)
                    .concatWith(Observable.from(institutes))
                    .concatWith(Observable.from(filials))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent(s);
                        }
                    })
                    .toList()
                    .cache();
        }
        return null;
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return null;
    }
}
