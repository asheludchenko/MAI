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
import rx.functions.Func2;

import static oleg.osipenko.mai.Router.ACADEMIC_MOBILITY;
import static oleg.osipenko.mai.Router.CANTEENS;
import static oleg.osipenko.mai.Router.COURSES;
import static oleg.osipenko.mai.Router.DEBATING_CLUB;
import static oleg.osipenko.mai.Router.DK;
import static oleg.osipenko.mai.Router.DORMITORIES;
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
        if (specification.specified(PRACTICS)) {
            String[] practics = context.getResources().getStringArray(R.array.practics);
            final List<StaticContent> contents = Stream.of(practics)
                    .map(new Function<String, StaticContent>() {
                        @Override
                        public StaticContent apply(String value) {
                            return new StaticContent.Builder()
                                    .setText(value)
                                    .build();
                        }
                    })
                    .collect(Collectors.<StaticContent>toList());
            contents.add(0, new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.praktika))
                    .build());
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(contents);
                }
            });
        } else if (specification.specified(DOTATIONS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(EMPLOYMENT_CENTER)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(SCIENCE)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(DORMITORIES)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(SANATORIUM)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(DEBATING_CLUB)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(MAISKY_VZLET)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(PROFKOM)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(SOMOL)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(PRESS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(HISTORY)) {
            return Observable.from(Collections.EMPTY_LIST);
        }
        return Observable.from(Collections.EMPTY_LIST);
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(FACULTIES)) {
            String[] facultiesArray = context.getResources().getStringArray(R.array.faculties);
            String[] institutesArray = context.getResources().getStringArray(R.array.institutes);
            String[] filialsArray = context.getResources().getStringArray(R.array.filials);

            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[3];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Факультеты");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(9, "Институты");
            sectionsArray[2] = new SimpleSectionListAdapter.Section(13, "Филиалы");

            Observable<String> unitedStrings = Observable.from(facultiesArray)
                    .concatWith(Observable.from(institutesArray))
                    .concatWith(Observable.from(filialsArray));
            Observable<Integer> unitedImages = Observable.from(facImages)
                    .concatWith(Observable.from(instImages))
                    .concatWith(Observable.from(filImages));



            Observable<ListContent> sections = Observable.create(new Observable.OnSubscribe<ListContent>() {
                @Override
                public void call(Subscriber<? super ListContent> subscriber) {
                    ListContent sectionBlock = new ListContent.Builder()
                            .setSections(sectionsArray)
                            .setWithSections(true)
                            .build();
                    subscriber.onNext(sectionBlock);
                }
            });
            Observable<ListContent> contents = Observable.zip(unitedStrings, unitedImages, new Func2<String, Integer, ListContent>() {
                @Override
                public ListContent call(String s, Integer integer) {
                    return new ListContent.Builder()
                            .setText(s)
                            .setImage(String.valueOf(integer))
                            .setWithImage(true)
                            .build();
                }
            });
            return Observable.merge(sections,
                    contents)
                    .toList()
                    .cache();
        } else if (specification.specified(SCHOLARSHIPS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(LIBRARIES)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(CANTEENS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(COURSES)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(ACADEMIC_MOBILITY)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(SPORT_SECTIONS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(MEDIA)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(LIFE)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(HELP)) {
            return Observable.from(Collections.EMPTY_LIST);
        }
        return Observable.from(Collections.EMPTY_LIST);
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        if (specification.specified(SESSION)) {
            String[] session = context.getResources().getStringArray(R.array.sessions);
            String[] sessions = context.getResources().getStringArray(R.array.sessions_list);
            List<ListContent> sessionList = Stream.of(sessions)
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setTitle(value)
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            StaticListContent sessionsBlock = new StaticListContent.Builder()
                    .setList(sessionList)
                    .build();
            List<StaticListContent> blocks = Stream.of(session)
                    .map(new Function<String, StaticListContent>() {
                        @Override
                        public StaticListContent apply(String value) {
                            return new StaticListContent.Builder()
                                    .setText(value)
                                    .build();
                        }
                    })
                    .collect(Collectors.<StaticListContent>toList());
            StaticListContent image = new StaticListContent.Builder()
                    .setImage(String.valueOf(R.drawable.sessia))
                    .build();
            blocks.add(0, image);
            blocks.add(sessionsBlock);
            final List<StaticListContent> contents = new ArrayList<>(blocks);
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(contents);
                }
            });
        } else if (specification.specified(MILITARY_INSTITUTE)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(MAGISTRACY)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(SECONDARY_EDUCATION)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(RECREATION_CENTERS)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(DK)) {
            return Observable.from(Collections.EMPTY_LIST);
        } else if (specification.specified(DOSAAF)) {
            return Observable.from(Collections.EMPTY_LIST);
        }
        return Observable.from(Collections.EMPTY_LIST);
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
