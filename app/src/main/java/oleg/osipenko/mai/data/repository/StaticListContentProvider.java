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
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

import static oleg.osipenko.mai.Router.DK;
import static oleg.osipenko.mai.Router.DOSAAF;
import static oleg.osipenko.mai.Router.MAGISTRACY;
import static oleg.osipenko.mai.Router.MILITARY_INSTITUTE;
import static oleg.osipenko.mai.Router.RECREATION_CENTERS;
import static oleg.osipenko.mai.Router.SECONDARY_EDUCATION;
import static oleg.osipenko.mai.Router.SESSION;
import static oleg.osipenko.mai.Router.PODGOTOVKA;

/**
 * Created by olegosipenko on 20.09.15.
 */
public class StaticListContentProvider {

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


    private Context context;

    public StaticListContentProvider(Context context) {
        this.context = context;
    }

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
        } else if (specification.specified(PODGOTOVKA)) {
            StaticListContent image = new StaticListContent.Builder()
                    .setImage(String.valueOf(R.drawable.podgotovka))
                    .build();
            StaticListContent text = new StaticListContent.Builder()
                    .setText(context.getString(R.string.podgot))
                    .build();
            final List<StaticListContent> ps = Observable.from(context.getResources().getStringArray(R.array.podgotovka))
                    .map(new Func1<String, StaticListContent>() {
                        @Override
                        public StaticListContent call(String s) {
                            return new StaticListContent.Builder()
                                    .setListTitle(s)
                                    .build();
                        }
                    })
                    .startWith(text)
                    .startWith(image)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticListContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticListContent>> subscriber) {
                    subscriber.onNext(ps);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        }
        return Observable.from(Collections.EMPTY_LIST);
    }
}
