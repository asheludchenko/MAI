package oleg.osipenko.mai.data.repository;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import oleg.osipenko.mai.R;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static oleg.osipenko.mai.Router.AHTUBA;
import static oleg.osipenko.mai.Router.ALUMNI;
import static oleg.osipenko.mai.Router.BAYC;
import static oleg.osipenko.mai.Router.CONTACTS;
import static oleg.osipenko.mai.Router.DEBATING_CLUB;
import static oleg.osipenko.mai.Router.DELIM;
import static oleg.osipenko.mai.Router.DOCS;
import static oleg.osipenko.mai.Router.DORMITORIES;
import static oleg.osipenko.mai.Router.DOTATIONS;
import static oleg.osipenko.mai.Router.EMPLOYMENT_CENTER;
import static oleg.osipenko.mai.Router.F1;
import static oleg.osipenko.mai.Router.F10;
import static oleg.osipenko.mai.Router.F2;
import static oleg.osipenko.mai.Router.F3;
import static oleg.osipenko.mai.Router.F4;
import static oleg.osipenko.mai.Router.F5;
import static oleg.osipenko.mai.Router.F6;
import static oleg.osipenko.mai.Router.F7;
import static oleg.osipenko.mai.Router.F8;
import static oleg.osipenko.mai.Router.F9;
import static oleg.osipenko.mai.Router.FACULTIES;
import static oleg.osipenko.mai.Router.HISTORY;
import static oleg.osipenko.mai.Router.INLANG;
import static oleg.osipenko.mai.Router.KHIMKI;
import static oleg.osipenko.mai.Router.MAISKY_VZLET;
import static oleg.osipenko.mai.Router.MILITARY_INSTITUTE;
import static oleg.osipenko.mai.Router.MILIT_01;
import static oleg.osipenko.mai.Router.MILIT_02;
import static oleg.osipenko.mai.Router.MILIT_INST;
import static oleg.osipenko.mai.Router.NABOR;
import static oleg.osipenko.mai.Router.PRACTICS;
import static oleg.osipenko.mai.Router.PRESS;
import static oleg.osipenko.mai.Router.PROFKOM;
import static oleg.osipenko.mai.Router.RADIOVTUZ;
import static oleg.osipenko.mai.Router.S1;
import static oleg.osipenko.mai.Router.S10;
import static oleg.osipenko.mai.Router.S2;
import static oleg.osipenko.mai.Router.S3;
import static oleg.osipenko.mai.Router.S4;
import static oleg.osipenko.mai.Router.S5;
import static oleg.osipenko.mai.Router.S6;
import static oleg.osipenko.mai.Router.S7;
import static oleg.osipenko.mai.Router.S8;
import static oleg.osipenko.mai.Router.S9;
import static oleg.osipenko.mai.Router.SANATORIUM;
import static oleg.osipenko.mai.Router.SCHOLARSHIPS;
import static oleg.osipenko.mai.Router.SCIENCE;
import static oleg.osipenko.mai.Router.SECONDARY_EDUCATION;
import static oleg.osipenko.mai.Router.SESSION;
import static oleg.osipenko.mai.Router.SESSION_EXAM;
import static oleg.osipenko.mai.Router.SESSION_OTCH;
import static oleg.osipenko.mai.Router.SESSION_ZACHET;
import static oleg.osipenko.mai.Router.SOMOL;
import static oleg.osipenko.mai.Router.STRELA;
import static oleg.osipenko.mai.Router.STUDGORODOK;
import static oleg.osipenko.mai.Router.SE01;
import static oleg.osipenko.mai.Router.SE02;
import static oleg.osipenko.mai.Router.SE03;
import static oleg.osipenko.mai.Router.SE04;
import static oleg.osipenko.mai.Router.MAGISTRACY;
import static oleg.osipenko.mai.Router.MAG01;
import static oleg.osipenko.mai.Router.MAG02;
import static oleg.osipenko.mai.Router.MAG03;
import static oleg.osipenko.mai.Router.MAG04;
import static oleg.osipenko.mai.Router.MAG05;
import static oleg.osipenko.mai.Router.MAG06;
import static oleg.osipenko.mai.Router.MAG07;
import static oleg.osipenko.mai.Router.MAG08;
import static oleg.osipenko.mai.Router.MAG09;
import static oleg.osipenko.mai.Router.ACADEMIC_MOBILITY;
import static oleg.osipenko.mai.Router.ACAD01;
import static oleg.osipenko.mai.Router.ACAD02;
import static oleg.osipenko.mai.Router.DK;
import static oleg.osipenko.mai.Router.DK01;
import static oleg.osipenko.mai.Router.DK02;
import static oleg.osipenko.mai.Router.DOSAAF;
import static oleg.osipenko.mai.Router.DOS01;
import static oleg.osipenko.mai.Router.DOS02;
import static oleg.osipenko.mai.Router.DOS03;
import static oleg.osipenko.mai.Router.DOS04;
import static oleg.osipenko.mai.Router.DOS05;
import static oleg.osipenko.mai.Router.DOS06;
import static oleg.osipenko.mai.Router.LIFE;
import static oleg.osipenko.mai.Router.LIF01;
import static oleg.osipenko.mai.Router.LIF02;
import static oleg.osipenko.mai.Router.LIF03;
import static oleg.osipenko.mai.Router.HELP;
import static oleg.osipenko.mai.Router.HELP1;
import static oleg.osipenko.mai.Router.HELP2;
import static oleg.osipenko.mai.Router.HELP3;
import static oleg.osipenko.mai.Router.RECREATION_CENTERS;
import static oleg.osipenko.mai.Router.REC1;
import static oleg.osipenko.mai.Router.REC2;


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
        } else if (specification.specified(DK + DELIM + DK01)) {
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
        } else if (specification.specified(RECREATION_CENTERS + DELIM + REC1)) {
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
        } else if (specification.specified(RECREATION_CENTERS + DELIM + REC2)) {
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
        }

        return Observable.from(Collections.EMPTY_LIST);
    }
}
