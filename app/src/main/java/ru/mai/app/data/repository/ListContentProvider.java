package ru.mai.app.data.repository;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.mai.app.R;
import ru.mai.app.data.dataModel.ListContent;
import ru.mai.app.data.repository.specification.ListContentSpecification;
import ru.mai.app.presentation.utils.SimpleSectionListAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;

import static ru.mai.app.Router.ACADEMIC_MOBILITY;
import static ru.mai.app.Router.CANTEENS;
import static ru.mai.app.Router.COURSES;
import static ru.mai.app.Router.DELIM;
import static ru.mai.app.Router.DOSUG;
import static ru.mai.app.Router.FACULTIES;
import static ru.mai.app.Router.HELP;
import static ru.mai.app.Router.LIBRARIES;
import static ru.mai.app.Router.LIFE;
import static ru.mai.app.Router.MEDIA;
import static ru.mai.app.Router.OCH;
import static ru.mai.app.Router.OZ;
import static ru.mai.app.Router.POD1;
import static ru.mai.app.Router.POD5;
import static ru.mai.app.Router.PODGOTOVKA;
import static ru.mai.app.Router.PRESENTATIONS;
import static ru.mai.app.Router.SCH1;
import static ru.mai.app.Router.SCH10;
import static ru.mai.app.Router.SCH11;
import static ru.mai.app.Router.SCH12;
import static ru.mai.app.Router.SCH13;
import static ru.mai.app.Router.SCH14;
import static ru.mai.app.Router.SCH15;
import static ru.mai.app.Router.SCH2;
import static ru.mai.app.Router.SCH3;
import static ru.mai.app.Router.SCH4;
import static ru.mai.app.Router.SCH5;
import static ru.mai.app.Router.SCH6;
import static ru.mai.app.Router.SCH7;
import static ru.mai.app.Router.SCH8;
import static ru.mai.app.Router.SCH9;
import static ru.mai.app.Router.SCHEDULE;
import static ru.mai.app.Router.SCHOLARSHIPS;
import static ru.mai.app.Router.SCHOOL_ACTIVITY;
import static ru.mai.app.Router.SCHOOL_CENTERS;
import static ru.mai.app.Router.SPORT_SECTIONS;
import static ru.mai.app.Router.WAY1;
import static ru.mai.app.Router.WAY10;
import static ru.mai.app.Router.WAY11;
import static ru.mai.app.Router.WAY12;
import static ru.mai.app.Router.WAY13;
import static ru.mai.app.Router.WAY14;
import static ru.mai.app.Router.WAY15;
import static ru.mai.app.Router.WAY16;
import static ru.mai.app.Router.WAY2;
import static ru.mai.app.Router.WAY3;
import static ru.mai.app.Router.WAY4;
import static ru.mai.app.Router.WAY5;
import static ru.mai.app.Router.WAY6;
import static ru.mai.app.Router.WAY7;
import static ru.mai.app.Router.WAY8;
import static ru.mai.app.Router.WAY9;
import static ru.mai.app.Router.WAYS;
import static ru.mai.app.Router.ZAOCH;

/**
 * Created by olegosipenko on 20.09.15.
 */
public class ListContentProvider {

    List<Integer> facImages = Arrays.asList(
            R.drawable.fac_1,
            R.drawable.fac_2,
            R.drawable.fac_3,
            R.drawable.fac_4,
            R.drawable.fac_6,
            R.drawable.fac_7,
            R.drawable.fac_8,
            R.drawable.fac_9,
            R.drawable.fac_10,
            R.drawable.fac_inyaz,
            R.drawable.fac_radiovtuz,
            R.drawable.logo
    );

    List<Integer> schedFacImages = Arrays.asList(
            R.drawable.fac_1,
            R.drawable.fac_2,
            R.drawable.fac_3,
            R.drawable.fac_4,
            R.drawable.fac_6,
            R.drawable.fac_7,
            R.drawable.fac_8,
            R.drawable.fac_9,
            R.drawable.fac_10,
            R.drawable.fac_inyaz
    );

    List<Integer> schedInstImages = Arrays.asList(
            R.drawable.fac_ing,
            R.drawable.logo_m,
            R.drawable.logo_m,
            R.drawable.logo_m,
            R.drawable.logo_m
    );


    List<Integer> instImages = Arrays.asList(
            R.drawable.fac_ing,
            R.drawable.fac_voen,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo
    );

    List<Integer> filImages = Arrays.asList(
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo
    );

    private Context context;

    public ListContentProvider(Context context) {
        this.context = context;
    }

    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(FACULTIES)) {

            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[3];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Факультеты");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(12, "Институты");
            sectionsArray[2] = new SimpleSectionListAdapter.Section(20, "Филиалы");

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
            final List<ListContent> contents = Observable.zip(unitedStrings, unitedImages, new Func2<String, Integer, ListContent>() {
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
                    .toBlocking()
                    .single();
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
            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[4];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Научно-техническая библиотека");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(11, "Берниковская наб., д. 14");
            sectionsArray[2] = new SimpleSectionListAdapter.Section(18, "Оршанская ул., д. 3");
            sectionsArray[3] = new SimpleSectionListAdapter.Section(20, "Фестивальная ул., д. 4, корп. 3");

            final List<ListContent> contents = Observable.zip(
                    Observable.from(context.getResources().getStringArray(R.array.libraries_names)),
                    Observable.from(context.getResources().getStringArray(R.array.libraries_rooms)),
                    new Func2<String, String, ListContent>() {
                        @Override
                        public ListContent call(String name, String room) {
                            return new ListContent.Builder()
                                    .setTitle(name)
                                    .setSub2(room).build();
                        }
                    }
            )
                    .toList()
                    .toBlocking()
                    .single();
            ListContent sectionBlock = new ListContent.Builder()
                    .setSections(sectionsArray)
                    .setWithSections(true)
                    .build();

            contents.add(sectionBlock);
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(contents);
                    subscriber.onCompleted();
                }
            })
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
            ListContent title = new ListContent.Builder()
                    .setFacTitle(ACADEMIC_MOBILITY)
                    .build();
            final List<ListContent> am = Observable.from(context.getResources().getStringArray(R.array.acad_mob))
                    .map(new Func1<String, ListContent>() {
                        @Override
                        public ListContent call(String s) {
                            return new ListContent.Builder()
                                    .setText(s)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .startWith(title)
                    .toList()
                    .toBlocking()
                    .single();
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
                                    .setDialogable()
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
        } else if (specification.specified(WAYS + DELIM + WAY1 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way1o))
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
        } else if (specification.specified(WAYS + DELIM + WAY1 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way1z))
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
        } else if (specification.specified(WAYS + DELIM + WAY1 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way1oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY2 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way2o))
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
        } else if (specification.specified(WAYS + DELIM + WAY2 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way2oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY3 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way3o))
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
        } else if (specification.specified(WAYS + DELIM + WAY3 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way3z))
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
        } else if (specification.specified(WAYS + DELIM + WAY4 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way4o))
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
        } else if (specification.specified(WAYS + DELIM + WAY4 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way4oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY5 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way5o))
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
        } else if (specification.specified(WAYS + DELIM + WAY5 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way5z))
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
        } else if (specification.specified(WAYS + DELIM + WAY5 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way5oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY6 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way6o))
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
        } else if (specification.specified(WAYS + DELIM + WAY7 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way7o))
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
        } else if (specification.specified(WAYS + DELIM + WAY7 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way7oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY8 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way8o))
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
        } else if (specification.specified(WAYS + DELIM + WAY8 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way8oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY9 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way9o))
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
        } else if (specification.specified(WAYS + DELIM + WAY9 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way9oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY10 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way10o))
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
        } else if (specification.specified(WAYS + DELIM + WAY11 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way11o))
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
        } else if (specification.specified(WAYS + DELIM + WAY11 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way11z))
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
        } else if (specification.specified(WAYS + DELIM + WAY12 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way12o))
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
        } else if (specification.specified(WAYS + DELIM + WAY12 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way12z))
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
        } else if (specification.specified(WAYS + DELIM + WAY12 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way12oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY13 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way13o))
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
        } else if (specification.specified(WAYS + DELIM + WAY13 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way13z))
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
        } else if (specification.specified(WAYS + DELIM + WAY13 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way13oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY14 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way14o))
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
        } else if (specification.specified(WAYS + DELIM + WAY14 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way14oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY15 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way15o))
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
        } else if (specification.specified(WAYS + DELIM + WAY15 + DELIM + ZAOCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way15z))
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
        } else if (specification.specified(WAYS + DELIM + WAY15 + DELIM + OZ)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way15oz))
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
        } else if (specification.specified(WAYS + DELIM + WAY16 + DELIM + OCH)) {
            final List<ListContent> ss = Observable.from(context.getResources().getStringArray(R.array.way16o))
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
        } else if (specification.specified(PRESENTATIONS)) {
            final List<ListContent> ps = Observable.from(context.getResources().getStringArray(R.array.presentations))
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
                    subscriber.onNext(ps);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCHEDULE)) {
            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[2];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Факультеты");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(10, "Институты");

            Observable<String> unitedStrings = Observable.from(context.getResources().getStringArray(R.array.sched_facs))
                    .concatWith(Observable.from(context.getResources().getStringArray(R.array.sched_insts)));
            Observable<Integer> unitedImages = Observable.from(schedFacImages)
                    .concatWith(Observable.from(schedInstImages));

            ListContent sectionBlock = new ListContent.Builder()
                    .setSections(sectionsArray)
                    .setWithSections(true)
                    .build();
            final List<ListContent> contents = Observable.zip(unitedStrings, unitedImages, new Func2<String, Integer, ListContent>() {
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
                    .toBlocking()
                    .single();
            contents.add(sectionBlock);
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(contents);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCHEDULE + DELIM + SCH1)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch1))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH2)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch2))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH3)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch3))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH4)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch4))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH5)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch5))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH6)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch6))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH7)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch7))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH8)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch8))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH9)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch9))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH10)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch10))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH11)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch11))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH12)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch12))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH13)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch13))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH14)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch14))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        } else if (specification.specified(SCHEDULE + DELIM + SCH15)) {
            return Observable.defer(new Func0<Observable<List<ListContent>>>() {
                @Override
                public Observable<List<ListContent>> call() {
                    return Observable.from(context.getResources().getStringArray(R.array.sch15))
                            .map(new Func1<String, ListContent>() {
                                @Override
                                public ListContent call(String s) {
                                    return new ListContent.Builder().setText(s).setClickable().build();
                                }
                            })
                            .toList();
                }
            });
        }
        return Observable.from(Collections.EMPTY_LIST);
    }
}
