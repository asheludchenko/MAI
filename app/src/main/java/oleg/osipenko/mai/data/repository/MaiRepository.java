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

    List<Integer> magImages = Arrays.asList(
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th,
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th,
            R.drawable.f1st
    );

    List<Integer> secImages = Arrays.asList(
            R.drawable.f1st,
            R.drawable.f2nd,
            R.drawable.f4th,
            R.drawable.f6th
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
            final List<StaticContent> contents = Stream.of(context.getResources().getStringArray(R.array.practics))
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
                    subscriber.onCompleted();
                }
            });
        } else if (specification.specified(DOTATIONS)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.dotations))
                    .build();
            final List<StaticContent> ds = Observable.from(context.getResources().getStringArray(R.array.dotations))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ds);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(EMPLOYMENT_CENTER)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.employ))
                    .build();
            final List<StaticContent> es = Observable.from(context.getResources().getStringArray(R.array.employment))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(es);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SCIENCE)) {
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.polyanski))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.science))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(STUDGORODOK)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.studgorodok))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.studgorodok))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SANATORIUM)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.profilak))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.sanatorium))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            })
                    .cache();
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
        }
        return Observable.from(Collections.EMPTY_LIST);
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        if (specification.specified(SESSION)) {
            List<StaticListContent> sessionList = Stream.of(context.getResources().getStringArray(R.array.sessions_list))
                    .map(new Function<String, StaticListContent>() {
                        @Override
                        public StaticListContent apply(String value) {
                            return new StaticListContent.Builder()
                                    .setListTitle(value)
                                    .build();
                        }
                    })
                    .collect(Collectors.<StaticListContent>toList());
            List<StaticListContent> blocks = Stream.of(context.getResources().getStringArray(R.array.sessions))
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
            blocks.addAll(sessionList);
            final List<StaticListContent> contents = new ArrayList<>(blocks);
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(contents);
                    subscriber.onCompleted();
                }
            });
        } else if (specification.specified(MILITARY_INSTITUTE)) {
            StaticListContent textBlock = new StaticListContent.Builder()
                    .setText(context.getString(R.string.militar))
                    .build();
            List<StaticListContent> ms = Stream.of(context.getResources().getStringArray(R.array.military_list))
                    .map(new Function<String, StaticListContent>() {
                        @Override
                        public StaticListContent apply(String value) {
                            return new StaticListContent.Builder()
                                    .setListTitle(value)
                                    .build();
                        }
                    })
                    .collect(Collectors.<StaticListContent>toList());
            ms.add(0, textBlock);
            final List<StaticListContent> m = new ArrayList<>(ms);
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(m);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(MAGISTRACY)) {
            final StaticListContent textBlock = new StaticListContent.Builder()
                    .setText(context.getString(R.string.magistrat))
                    .build();
            final List<StaticListContent> m = Observable.zip(
                    Observable.from(context.getResources().getStringArray(R.array.magistr_ways)),
                    Observable.from(magImages),
                    new Func2<String, Integer, StaticListContent>() {
                        @Override
                        public StaticListContent call(String s, Integer integer) {
                            return new StaticListContent.Builder()
                                    .setListTitle(s)
                                    .setListImage(String.valueOf(integer))
                                    .build();
                        }
                    }
            )
                    .startWith(textBlock)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(m);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SECONDARY_EDUCATION)) {
            Observable<StaticListContent> texts = Observable.from(context.getResources().getStringArray(R.array.second))
                    .map(new Func1<String, StaticListContent>() {
                        @Override
                        public StaticListContent call(String s) {
                            return new StaticListContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    });
            final List<StaticListContent> ss = Observable.zip(
                    Observable.from(context.getResources().getStringArray(R.array.second_ways)),
                    Observable.from(secImages),
                    new Func2<String, Integer, StaticListContent>() {
                        @Override
                        public StaticListContent call(String s, Integer integer) {
                            return new StaticListContent.Builder()
                                    .setListTitle(s)
                                    .setListImage(String.valueOf(integer))
                                    .build();
                        }
                    }
            )
                    .startWith(texts)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            });
        } else if (specification.specified(RECREATION_CENTERS)) {
            StaticListContent image = new StaticListContent.Builder()
                    .setImage(String.valueOf(R.drawable.recreation))
                    .build();
            StaticListContent textBlock = new StaticListContent.Builder()
                    .setText(context.getString(R.string.recreation))
                    .build();
            final List<StaticListContent> rs = Observable.from(context.getResources().getStringArray(R.array.recreations))
                    .map(new Func1<String, StaticListContent>() {
                        @Override
                        public StaticListContent call(String s) {
                            return new StaticListContent.Builder()
                                    .setListTitle(s)
                                    .build();
                        }
                    })
                    .startWith(textBlock)
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(rs);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(DK)) {
            StaticListContent title = new StaticListContent.Builder()
                    .setTitle(context.getString(R.string.dk_title))
                    .build();
            final List<StaticListContent> dks = Observable.concat(
                    Observable.merge(
                            Observable.from(context.getResources().getStringArray(R.array.dk))
                                    .map(new Func1<String, StaticListContent>() {
                                        @Override
                                        public StaticListContent call(String s) {
                                            return new StaticListContent.Builder()
                                                    .setText(s)
                                                    .build();
                                        }
                                    }),
                            Observable.just(title)
                    ),
                    Observable.from(context.getResources().getStringArray(R.array.dks))
                            .map(new Func1<String, StaticListContent>() {
                                @Override
                                public StaticListContent call(String s) {
                                    return new StaticListContent.Builder()
                                            .setListTitle(s)
                                            .build();

                                }
                            })
            )
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(dks);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(DOSAAF)) {
            final List<StaticListContent> ds = Observable.merge(
                    Observable.from(context.getResources().getStringArray(R.array.dossaaf))
                            .map(new Func1<String, StaticListContent>() {
                                @Override
                                public StaticListContent call(String s) {
                                    return new StaticListContent.Builder()
                                            .setText(s)
                                            .build();
                                }
                            }),
                    Observable.from(context.getResources().getStringArray(R.array.dosaafs))
                            .map(new Func1<String, StaticListContent>() {
                                @Override
                                public StaticListContent call(String s) {
                                    return new StaticListContent.Builder()
                                            .setListTitle(s)
                                            .build();
                                }
                            })
            )
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(ds);
                    subscriber.onCompleted();
                }
            })
                    .cache();
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
