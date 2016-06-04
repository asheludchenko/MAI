package oleg.osipenko.mai.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import oleg.osipenko.mai.App;
import oleg.osipenko.mai.R;
import oleg.osipenko.mai.Router;
import oleg.osipenko.mai.data.api.NetworkProvider;
import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.NewsHeadersContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.dataModel.StaticListContent;
import oleg.osipenko.mai.data.dto.MainScreenDto;
import oleg.osipenko.mai.data.dto.ScheduleCourses;
import oleg.osipenko.mai.data.dto.ScheduleFaculties;
import oleg.osipenko.mai.data.repository.specification.ListContentSpecification;
import oleg.osipenko.mai.data.repository.specification.NewsContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.data.repository.specification.StaticListContentSpecification;
import oleg.osipenko.mai.presentation.utils.SimpleSectionListAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by olegosipenko on 07.09.15.
 */
public class MaiRepository implements oleg.osipenko.mai.data.repository.DataRepository {

    Context context;
    oleg.osipenko.mai.data.repository.StaticContentProvider staticContentProvider;
    ListContentProvider listContentProvider;
    oleg.osipenko.mai.data.repository.StaticListContentProvider staticListContentProvider;
    NetworkProvider networkProvider;

    public MaiRepository(Context context) {
        this.context = context;
        staticContentProvider = new oleg.osipenko.mai.data.repository.StaticContentProvider(context);
        listContentProvider = new ListContentProvider(context);
        staticListContentProvider = new oleg.osipenko.mai.data.repository.StaticListContentProvider(context);
        networkProvider = new NetworkProvider();
    }

    @Override
    public Observable<List<StaticContent>> getStaticContent(StaticContentSpecification specification) {
        return staticContentProvider.getStaticContent(specification);
    }

    @Override
    public Observable<List<ListContent>> getListContent(ListContentSpecification specification) {
        if (specification.specified(Router.SCHEDULE)) {
            List<ScheduleFaculties> all = networkProvider.getSchedFaculties();
            List<ScheduleFaculties> faculties = Observable.from(all).filter(new Func1<ScheduleFaculties, Boolean>() {
                @Override
                public Boolean call(ScheduleFaculties scheduleFaculties) {
                    return scheduleFaculties.isFaculty();
                }
            }).toList().toBlocking().single();
            final SimpleSectionListAdapter.Section[] sectionsArray = new SimpleSectionListAdapter.Section[2];
            sectionsArray[0] = new SimpleSectionListAdapter.Section(0, "Факультеты");
            sectionsArray[1] = new SimpleSectionListAdapter.Section(faculties.size(), "Институты");
            ListContent sectionBlock = new ListContent.Builder()
                    .setSections(sectionsArray)
                    .setWithSections(true)
                    .build();
            final List<ListContent> contents = Observable.from(all)
                    .map(new Func1<ScheduleFaculties, ListContent>() {
                        @Override
                        public ListContent call(ScheduleFaculties scheduleFaculties) {
                            return new ListContent.Builder()
                                    .setText(scheduleFaculties.getFacultyName())
                                    .setLink(scheduleFaculties.getName())
                                    .setImage(scheduleFaculties.logo())
                                    .setWithImage(true)
                                    .setClickable()
                                    .build();
                        }
                    })
                    .toList()
                    .onErrorReturn(new Func1<Throwable, List<ListContent>>() {
                        @Override
                        public List<ListContent> call(Throwable throwable) {
                            return new ArrayList<ListContent>();
                        }
                    })
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
        } else if (specification.getItem().contains(Router.SCHEDULE + Router.DELIM)) {
            String facultyId = specification.getItem().split(Router.DELIM)[1];
            List<ScheduleCourses> courses = networkProvider.getScheduleCourses(facultyId);
            final List<ListContent> contents = Observable.from(courses)
                    .map(new Func1<ScheduleCourses, ListContent>() {
                        @Override
                        public ListContent call(ScheduleCourses scheduleCourse) {
                            return new ListContent.Builder()
                                    .setText(scheduleCourse.getName())
                                    .setClickable()
                                    .setLink(scheduleCourse.getUrl())
                                    .build();
                        }
                    })
                    .toList()
                    .onErrorReturn(new Func1<Throwable, List<ListContent>>() {
                        @Override
                        public List<ListContent> call(Throwable throwable) {
                            return new ArrayList<ListContent>();
                        }
                    })
                    .toBlocking()
                    .single();
            return Observable.create(new Observable.OnSubscribe<List<ListContent>>() {
                @Override
                public void call(Subscriber<? super List<ListContent>> subscriber) {
                    subscriber.onNext(contents);
                    subscriber.onCompleted();
                }
            }).cache();
        } else {
            return listContentProvider.getListContent(specification);
        }
    }

    @Override
    public Observable<List<NewsHeadersContent>> getNews() {
        return networkProvider.getNews()
                .flatMap(new Func1<List<? extends NewsHeadersContent>, Observable<List<NewsHeadersContent>>>() {
                    @Override
                    public Observable<List<NewsHeadersContent>> call(List<? extends NewsHeadersContent> headersContents) {
                        List<NewsHeadersContent> ks = new ArrayList<NewsHeadersContent>(headersContents);
                        return Observable.just(ks);
                    }
                });
    }

    @Override
    public Observable<List<StaticListContent>> getStaticListContent(StaticListContentSpecification specification) {
        return staticListContentProvider.getStaticListContent(specification);
    }

    @Override
    public Observable<Bitmap> getMapObservable() {
        return Observable.defer(new Func0<Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inJustDecodeBounds = true;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inDither = true;
                Bitmap map = BitmapFactory.decodeResource(context.getResources(), R.drawable.mai_plan_big_color, options);
                return Observable.just(map);
            }
        });
    }

    @Override
    public Observable<List<StaticContent>> getSingleNews(NewsContentSpecification parameter) {
        return networkProvider.getNewsById(parameter)
                .flatMap(new Func1<List<? extends StaticContent>, Observable<List<StaticContent>>>() {
                    @Override
                    public Observable<List<StaticContent>> call(List<? extends StaticContent> staticContents) {
                        List<StaticContent> ks = new ArrayList<StaticContent>(staticContents);
                        return Observable.just(ks);
                    }
                });
    }

    @Override
    public Observable<List<ListContent>> getPhotos(NewsContentSpecification parameter) {
        return networkProvider.getPhotoAlbums()
                .flatMap(new Func1<List<? extends ListContent>, Observable<List<ListContent>>>() {
                    @Override
                    public Observable<List<ListContent>> call(List<? extends ListContent> listContents) {
                        List<ListContent> als = new ArrayList<ListContent>(listContents);
                        return Observable.just(als);
                    }
                });
    }

    @Override
    public Observable<MainScreenDto> getImages() {
        return Observable.create(new Observable.OnSubscribe<MainScreenDto>() {
            @Override
            public void call(Subscriber<? super MainScreenDto> subscriber) {
                if (App.getScreenDtos().isEmpty()) {
                    ParseQuery<MainScreenDto> query = ParseQuery.getQuery(MainScreenDto.class);
                    try {
                        List<MainScreenDto> images = query.find();
                        App.setScreenDtos(images);
                        Random random = new Random(System.nanoTime());
                        List<MainScreenDto> filtered;
                        if (Router.isStudent) {
                            filtered = Observable.from(App.getScreenDtos())
                                    .filter(new Func1<MainScreenDto, Boolean>() {
                                        @Override
                                        public Boolean call(MainScreenDto mainScreenDto) {
                                            return mainScreenDto.isStudent();
                                        }
                                    })
                                    .toList()
                                    .toBlocking()
                                    .single();
                        } else {
                            filtered = Observable.from(App.getScreenDtos())
                                    .filter(new Func1<MainScreenDto, Boolean>() {
                                        @Override
                                        public Boolean call(MainScreenDto mainScreenDto) {
                                            return !mainScreenDto.isStudent();
                                        }
                                    })
                                    .toList()
                                    .toBlocking()
                                    .single();
                        }
                        int randomIndex = random.nextInt(filtered.size());
                        subscriber.onNext(filtered.get(randomIndex));
                        subscriber.onCompleted();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } finally {
                        subscriber.onCompleted();
                    }
                } else {
                    Random random = new Random(System.nanoTime());
                    List<MainScreenDto> filtered;
                    if (Router.isStudent) {
                        filtered = Observable.from(App.getScreenDtos())
                                .filter(new Func1<MainScreenDto, Boolean>() {
                                    @Override
                                    public Boolean call(MainScreenDto mainScreenDto) {
                                        return mainScreenDto.isStudent();
                                    }
                                })
                                .toList()
                                .toBlocking()
                                .single();
                    } else {
                        filtered = Observable.from(App.getScreenDtos())
                                .filter(new Func1<MainScreenDto, Boolean>() {
                                    @Override
                                    public Boolean call(MainScreenDto mainScreenDto) {
                                        return !mainScreenDto.isStudent();
                                    }
                                })
                                .toList()
                                .toBlocking()
                                .single();
                    }
                    int randomIndex = random.nextInt(filtered.size());
                    subscriber.onNext(filtered.get(randomIndex));
                    subscriber.onCompleted();
                }
            }
        });
    }
}
