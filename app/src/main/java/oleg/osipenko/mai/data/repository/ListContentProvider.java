package oleg.osipenko.mai.data.repository;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
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
import static oleg.osipenko.mai.Router.DELIM;
import static oleg.osipenko.mai.Router.DOSUG;
import static oleg.osipenko.mai.Router.FACULTIES;
import static oleg.osipenko.mai.Router.HELP;
import static oleg.osipenko.mai.Router.LIBRARIES;
import static oleg.osipenko.mai.Router.LIFE;
import static oleg.osipenko.mai.Router.MEDIA;
import static oleg.osipenko.mai.Router.SCHOLARSHIPS;
import static oleg.osipenko.mai.Router.SCHOOL_ACTIVITY;
import static oleg.osipenko.mai.Router.SCHOOL_CENTERS;
import static oleg.osipenko.mai.Router.SPORT_SECTIONS;
import static oleg.osipenko.mai.Router.WAYS;
import static oleg.osipenko.mai.Router.PODGOTOVKA;
import static oleg.osipenko.mai.Router.POD1;
import static oleg.osipenko.mai.Router.POD5;

/**
 * Created by olegosipenko on 20.09.15.
 */
public class ListContentProvider {

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

    private Context context;

    public ListContentProvider(Context context) {
        this.context = context;
    }

    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(FACULTIES)) {

            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[3];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Факультеты");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(9, "Институты");
            sectionsArray[2] = new SimpleSectionListAdapter.Section(13, "Филиалы");

            Observable<String> unitedStrings = Observable.from(context.getResources().getStringArray(R.array.faculties))
                    .concatWith(Observable.from(context.getResources().getStringArray(R.array.institutes)))
                    .concatWith(Observable.from(context.getResources().getStringArray(R.array.filials)));
            Observable<Integer> unitedImages = Observable.from(facImages)
                    .concatWith(Observable.from(instImages))
                    .concatWith(Observable.from(filImages));

            ListContent sectionBlock = new ListContent.Builder()
                    .setSections(sectionsArray)
                    .setWithSections(true)
                    .build();
            final List<ListContent> contents = new ArrayList<>();
            Observable.zip(unitedStrings, unitedImages, new Func2<String, Integer, ListContent>() {
                @Override
                public ListContent call(String s, Integer integer) {
                    return new ListContent.Builder()
                            .setText(s)
                            .setImage(String.valueOf(integer))
                            .setWithImage(true)
                            .setClickable()
                            .build();
                }
            })
                    .toList()
                    .subscribe(new Action1<List<ListContent>>() {
                        @Override
                        public void call(List<ListContent> listContents) {
                            contents.addAll(listContents);
                        }
                    });
            contents.add(sectionBlock);
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(contents);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCHOLARSHIPS)) {
            final List<ListContent> ss = Stream.of(context.getResources().getStringArray(R.array.scholarships))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            });
        } else if (specification.specified(LIBRARIES)) {
            return Observable.zip(
                    Observable.from(context.getResources().getStringArray(R.array.libraries_names)),
                    Observable.from(context.getResources().getStringArray(R.array.libraries_rooms)),
                    Observable.from(context.getResources().getStringArray(R.array.libraries_phones)),
                    new Func3<String, String, String, ListContent>() {
                        @Override
                        public ListContent call(String name, String address, String phone) {
                            return new ListContent.Builder()
                                    .setTitle(name)
                                    .setSub2(address)
                                    .setSub3(phone)
                                    .build();
                        }
                    }
            )
                    .toList()
                    .cache();
        } else if (specification.specified(CANTEENS)) {

            return Observable.zip(
                    Observable.from(context.getResources().getStringArray(R.array.canteens_names)),
                    Observable.from(context.getResources().getStringArray(R.array.canteen_times)),
                    Observable.from(context.getResources().getStringArray(R.array.canteen_addresses)),
                    new Func3<String, String, String, ListContent>() {
                        @Override
                        public ListContent call(String name, String time, String address) {
                            return new ListContent.Builder()
                                    .setTitle(name)
                                    .setSub2(address)
                                    .setSub4(time)
                                    .build();
                        }
                    }
            )
                    .toList()
                    .cache();
        } else if (specification.specified(COURSES)) {
            final List<ListContent> cc = Stream.of(context.getResources().getStringArray(R.array.courses))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(cc);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(ACADEMIC_MOBILITY)) {
            final List<ListContent> am = Stream.of(context.getResources().getStringArray(R.array.acad_mob))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(am);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SPORT_SECTIONS)) {
            final List<ListContent> ss = Stream.of(context.getResources().getStringArray(R.array.sport))
                    .sorted()
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(MEDIA)) {
            final List<ListContent> ms = Stream.of(context.getResources().getStringArray(R.array.media))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ms);
                    subscriber.onCompleted();
                }
            });
        } else if (specification.specified(LIFE)) {
            final List<ListContent> ls = Stream.of(context.getResources().getStringArray(R.array.life))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ls);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(HELP)) {
            final List<ListContent> hs = Stream.of(context.getResources().getStringArray(R.array.help))
                    .map(new Function<String, ListContent>() {
                        @Override
                        public ListContent apply(String value) {
                            return new ListContent.Builder()
                                    .setText(value)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .collect(Collectors.<ListContent>toList());
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(hs);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(WAYS)) {
            final List<ListContent> ws = Observable.from(context.getResources().getStringArray(R.array.ways))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ws);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCHOOL_ACTIVITY)) {
            final List<ListContent> as = Observable.from(context.getResources().getStringArray(R.array.activity))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(as);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(DOSUG)) {
            final List<ListContent> as = Observable.from(context.getResources().getStringArray(R.array.dosug))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(as);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCHOOL_CENTERS)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.school_centers))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.pod1))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD5)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.pod5))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        }
        return Observable.from(Collections.EMPTY_LIST);
    }
}
