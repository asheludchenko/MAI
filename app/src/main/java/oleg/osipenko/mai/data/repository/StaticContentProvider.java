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
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static oleg.osipenko.mai.Router.ALUMNI;
import static oleg.osipenko.mai.Router.CONTACTS;
import static oleg.osipenko.mai.Router.DEBATING_CLUB;
import static oleg.osipenko.mai.Router.DOCS;
import static oleg.osipenko.mai.Router.DORMITORIES;
import static oleg.osipenko.mai.Router.DOTATIONS;
import static oleg.osipenko.mai.Router.EMPLOYMENT_CENTER;
import static oleg.osipenko.mai.Router.HISTORY;
import static oleg.osipenko.mai.Router.MAISKY_VZLET;
import static oleg.osipenko.mai.Router.NABOR;
import static oleg.osipenko.mai.Router.PRACTICS;
import static oleg.osipenko.mai.Router.PRESS;
import static oleg.osipenko.mai.Router.PROFKOM;
import static oleg.osipenko.mai.Router.SANATORIUM;
import static oleg.osipenko.mai.Router.SCIENCE;
import static oleg.osipenko.mai.Router.SOMOL;
import static oleg.osipenko.mai.Router.STUDGORODOK;

/**
 * Created by olegosipenko on 20.09.15.
 */
public class StaticContentProvider {

    private Context context;

    public StaticContentProvider(Context context) {
        this.context = context;
    }

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
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.debat))
                    .build();
            final List<StaticContent> ds = Observable.from(context.getResources().getStringArray(R.array.debating))
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
        } else if (specification.specified(MAISKY_VZLET)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.vzlet))
                    .build();
            final List<StaticContent> ms = Observable.from(context.getResources().getStringArray(R.array.vzlet_fest))
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
                    subscriber.onNext(ms);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(PROFKOM)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.profkom))
                    .build();
            final List<StaticContent> ps = Observable.from(context.getResources().getStringArray(R.array.prof))
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
                    subscriber.onNext(ps);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(SOMOL)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.somol))
                    .build();
            final List<StaticContent> ps = Observable.from(context.getResources().getStringArray(R.array.somol))
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
                    subscriber.onNext(ps);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(PRESS)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.press))
                    .build();
            StaticContent smiTitle = new StaticContent.Builder()
                    .setTitle(context.getString(R.string.mai_smi_title))
                    .build();
            StaticContent smis = new StaticContent.Builder()
                    .setText(context.getString(R.string.mai_smis))
                    .build();
            StaticContent internetTitle = new StaticContent.Builder()
                    .setTitle(context.getString(R.string.internet_title))
                    .build();
            StaticContent internets = new StaticContent.Builder()
                    .setText(context.getString(R.string.inets))
                    .build();
            final List<StaticContent> ps = Observable.merge(
                    Observable.just(image),
                    Observable.just(smiTitle),
                    Observable.just(smis),
                    Observable.just(internetTitle),
                    Observable.just(internets)
            ).toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ps);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(HISTORY)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.hist))
                    .build();
            final List<StaticContent> hs = Observable.from(context.getResources().getStringArray(R.array.history))
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
                    subscriber.onNext(hs);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(DORMITORIES)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.dormitories))
                    .build();
            final List<StaticContent> ds = Observable.from(context.getResources().getStringArray(R.array.dormitories))
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
        } else if (specification.specified(ALUMNI)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.alumni))
                    .build();
            final List<StaticContent> as = Observable.from(context.getResources().getStringArray(R.array.alumnis))
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
                    subscriber.onNext(as);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        } else if (specification.specified(DOCS)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.docs))
                    .build();
            final List<StaticContent> ds = Observable.from(context.getResources().getStringArray(R.array.docss))
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
        } else if (specification.specified(NABOR)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.nabor))
                    .build();
            final List<StaticContent> ds = Observable.from(context.getResources().getStringArray(R.array.nabors))
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
        } else if (specification.specified(CONTACTS)) {
            StaticContent address = new StaticContent.Builder()
                    .setFacText(context.getString(R.string.address))
                    .build();
            StaticContent phone = new StaticContent.Builder()
                    .setFacText(context.getString(R.string.phone))
                    .build();
            StaticContent email = new StaticContent.Builder()
                    .setFacText(context.getString(R.string.email))
                    .build();
            StaticContent site = new StaticContent.Builder()
                    .setFacText(context.getString(R.string.site))
                    .build();
            final List<StaticContent> cs = Arrays.asList(address, phone, email, site);
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(cs);
                    subscriber.onCompleted();
                }
            })
                    .cache();
        }
        return Observable.from(Collections.EMPTY_LIST);
    }
}
