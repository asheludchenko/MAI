package oleg.osipenko.mai.data.repository;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import oleg.osipenko.mai.R;
import oleg.osipenko.mai.Router;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.presentation.screens.StaticContentScreen;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static oleg.osipenko.mai.Router.*;


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
        } else if (specification.specified(FACULTIES + DELIM + F1)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F1)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.efremov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F2)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F2)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.agulnik))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F3)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F3)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.sledkov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F4)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F4)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.kird))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac4))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F5)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F5)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.tihonov_ai))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac5))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F6)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F6)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.tushavina))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac6))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F7)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F7)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.tihonov_km))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac7))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F8)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F8)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.krylov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac8))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F9)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F9)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.rabinsky))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac9))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + F10)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(F10)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.mavrodi))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac10))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + INLANG)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(INLANG)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.kalliopin))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_inlang))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + MILIT_INST)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(MILIT_INST)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.goncharenko))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_milit))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + BAYC)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(BAYC)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.prosochkin))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_bayc))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + STRELA)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(STRELA)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.maksimov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_strela))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + AHTUBA)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(AHTUBA)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.toropov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_ahtuba))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + KHIMKI)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(KHIMKI)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.mazurov))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_khimki))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(FACULTIES + DELIM + RADIOVTUZ)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(RADIOVTUZ)
                    .build();
            StaticContent portrait = new StaticContent.Builder()
                    .setFacPhoto(String.valueOf(R.drawable.grubrin))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.fac_radiovtuz))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(portrait)
                    .startWith(title)
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
        } else if (specification.specified(SESSION + DELIM + SESSION_ZACHET)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.session_zach))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(SESSION + DELIM + SESSION_EXAM)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.session_exam))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(SESSION + DELIM + SESSION_OTCH)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.session_otch))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S1)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S2)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S3)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S4)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s4))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S5)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s5))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S6)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s6))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S7)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s7))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S8)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s8))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S9)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s9))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SCHOLARSHIPS + DELIM + S10)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(S1)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.s10))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MILITARY_INSTITUTE + DELIM + MILIT_01)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.uvc))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.uvc))
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
        } else if (specification.specified(MILITARY_INSTITUTE + DELIM + MILIT_02)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.voennaja_kaf))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.voenkaf))
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
        } else if (specification.specified(SECONDARY_EDUCATION + DELIM + SE01)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.se01))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SECONDARY_EDUCATION + DELIM + SE02)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.se02))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SECONDARY_EDUCATION + DELIM + SE03)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.se03))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(SECONDARY_EDUCATION + DELIM + SE04)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.se04))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG01)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG02)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG03)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG04)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag4))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG05)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag5))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG06)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag6))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG07)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag7))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG08)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag8))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(MAGISTRACY + DELIM + MAG09)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile(SE01)
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.mag9))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(ACADEMIC_MOBILITY + DELIM + ACAD01)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.globaleducation))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.acad01))
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
        } else if (specification.specified(ACADEMIC_MOBILITY + DELIM + ACAD02)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.obmen))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.acad02))
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
        } else if (specification.specified(DK + DELIM + DK01) || specification.specified(DOSUG + DELIM + DOSUG1)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.kollektivi))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dk1))
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
        } else if (specification.specified(DK + DELIM + DK02)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.dk_event))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dk2))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS01)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.aviamodelizm))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos1))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS02)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.aerodrom))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos2))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS03)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.aviateh_club))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos3))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS04)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.deltaplan))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos4))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS05)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.divers))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos5))
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
        } else if (specification.specified(DOSAAF + DELIM + DOS06)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.tir))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.dos6))
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
        } else if (specification.specified(LIFE + DELIM + LIF01)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile("Маёвская рекламная\n(\"Только в МАИ\")")
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.lif1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(LIFE + DELIM + LIF02)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.lif2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(LIFE + DELIM + LIF03)) {
            StaticContent title = new StaticContent.Builder()
                    .setFacTitile("Маёвский тост")
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.lif3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
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
        } else if (specification.specified(HELP + DELIM + HELP1)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.help1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(HELP + DELIM + HELP2)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.help2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
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
        } else if (specification.specified(HELP + DELIM + HELP3)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.prilozenie))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.help3))
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
        } else if (specification.specified(RECREATION_CENTERS + DELIM + REC1) || specification.specified(DOSUG + DELIM + DOSUG2 + DELIM + REC1)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.alushta))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.rec1))
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
        } else if (specification.specified(RECREATION_CENTERS + DELIM + REC2) || specification.specified(DOSUG + DELIM + DOSUG2 + DELIM + REC2)) {
            StaticContent image = new StaticContent.Builder()
                    .setImage(String.valueOf(R.drawable.jaropolec))
                    .build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.rec2))
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
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS01)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS01).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.aikido)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss01))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS02)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS02).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.akadem_greb)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss02))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS03)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS03).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.atl_gimn)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss03))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS04)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS04).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.aerob)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss04))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS05)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS05).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.badmin)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss05))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS06)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS06).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.voleibol)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss06))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS07)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS07).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.greko_rim)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss07))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS08)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS08).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.small_tennis)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss08))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS09)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS09).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.skalolaz)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss09))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS10)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS10).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.atl_gimn)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss10))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS11)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS11).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.yoga)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss11))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS12)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS12).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.karate)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss12))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS13)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS13).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ligi)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss13))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS14)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS14).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.minifootball)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss14))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS15)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS15).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.minifootball_girl)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss15))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS16)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS16).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.rukopash)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss16))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS17)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS17).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.tenis)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss17))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS18)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS18).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.tekvondo)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss18))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS19)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS19).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.fitnes)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss19))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS20)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS20).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.football)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss20))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS21)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS21).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.arm)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss21))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS22)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS22).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.box)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss22))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS23)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS23).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.volnaja)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss23))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS24)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS24).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.dzudo)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss24))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS25)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS25).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.dual_strelba)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss25))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS26)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS26).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.yoga)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss26))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS27)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS27).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.pulevaja)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss27))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS28)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS28).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.giri)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss28))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS29)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS29).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.powerlifting)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss29))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS30)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS30).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.tyajolaja)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss30))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS31)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS31).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.atl_gimn)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss31))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS32)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS32).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.basket)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss32))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS33)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS33).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.giri)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss33))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS34)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS34).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.tenis)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss34))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS35)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS35).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.sporttanci)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss35))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS36)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS36).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.alp)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss36))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS37)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS37).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.sporturizm)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss37))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS38)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS38).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.feht)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss38))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS39)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS39).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.boevie)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss39))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS40)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS40).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.golf)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss40))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS41)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS41).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.gornij)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss41))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS42)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS42).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.legk_atletika)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss42))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS43)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS43).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.parus)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss43))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS44)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS44).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.swim)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss44))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS45)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS45).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.podvodnoe)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss45))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS46)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS46).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.regbi)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss46))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS47)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS47).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.snowboard)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss47))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS48)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS48).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.sportorientir)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss48))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SPORT_SECTIONS + DELIM + SS49)) {
            StaticContent title = new StaticContent.Builder().setFacTitile(SS49).build();
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.chess)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss49))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setFacText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .mergeWith(Observable.just(image))
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD2)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.pod2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD3)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.pod3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD4)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.pod4))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1 + DELIM + COU1)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.cou1))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1 + DELIM + COU2)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.cou2))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1 + DELIM + COU3)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.cou3))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1 + DELIM + COU4)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.cou4))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(PODGOTOVKA + DELIM + POD1 + DELIM + COU5)) {
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.cou5))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        } else if (specification.specified(SCHOOL_CENTERS + DELIM + SS1)) {
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ab_ctpo)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss1))
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
            }).cache();
        } else if (specification.specified(SCHOOL_CENTERS + DELIM + SS2)) {
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ab_miraviamodeli)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.ss2))
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
            }).cache();
        } else if (specification.specified(SCHOOL_ACTIVITY + DELIM + A1)) {
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ab_dod)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.a1))
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
            }).cache();
        } else if (specification.specified(SCHOOL_ACTIVITY + DELIM + A2)) {
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ab_dennauki)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.a2))
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
            }).cache();
        } else if (specification.specified(SCHOOL_ACTIVITY + DELIM + A3)) {
            StaticContent image = new StaticContent.Builder().setImage(String.valueOf(R.drawable.ab_saturday)).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(R.array.a3))
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
            }).cache();
        } else if (specification.getItem().length() - specification.getItem().replace(DELIM, "").length() == 3) {
            String[] a = specification.getItem().split(DELIM);
            int res = 0;
            if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW1 + 0)) {
                res = R.array.WW1;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW2 + 1)) {
                res = R.array.WW2;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW3 + 2)) {
                res = R.array.WW3;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW4 + 3)) {
                res = R.array.WW4;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW5 + 4)) {
                res = R.array.WW5;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW6 + 5)) {
                res = R.array.WW6;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW7 + 6)) {
                res = R.array.WW7;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW8 + 7)) {
                res = R.array.WW8;
            } else if (specification.getItem().equals(WAYS + DELIM + WAY1 + DELIM + OCH + DELIM + WW9 + 8)) {
                res = R.array.WW9;
            }
            String screenTitle = a[3].substring(0, a[3].length()-1);
            StaticContent title = new StaticContent.Builder().setFacTitile(screenTitle).build();
            final List<StaticContent> ss = Observable.from(context.getResources().getStringArray(res))
                    .map(new Func1<String, StaticContent>() {
                        @Override
                        public StaticContent call(String s) {
                            return new StaticContent.Builder()
                                    .setText(s)
                                    .build();
                        }
                    })
                    .startWith(title)
                    .toList()
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<StaticContent>>() {
                @Override
                public void call(Subscriber<? super List<StaticContent>> subscriber) {
                    subscriber.onNext(ss);
                    subscriber.onCompleted();
                }
            }).cache();
        }

        return Observable.from(Collections.EMPTY_LIST);
    }
}
