package ru.mai.app.presentation.screens;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import ru.mai.app.R;
import ru.mai.app.Router;
import ru.mai.app.presentation.MaiPresenter;
import ru.mai.app.presentation.mf_boilerplate.Layout;
import ru.mai.app.presentation.mf_boilerplate.WithModule;
import ru.mai.app.presentation.views.ViewWeb;

import static ru.mai.app.Router.COURSES;
import static ru.mai.app.Router.DELIM;
import static ru.mai.app.Router.PRESENTATIONS;

/**
 * Created by olegosipenko on 25.09.15.
 */
@Layout(R.layout.view_web)
@WithModule(WebViewScreen.Module.class)
public class WebViewScreen extends Path {

    public static String sTitle;

    public WebViewScreen(String title) {
        sTitle = title;
    }

    @dagger.Module(
            injects = ViewWeb.class,
            addsTo = App.AppModule.class
    )
    public class Module {
        @Provides
        Chooser provideChooser() {
            return new Chooser();
        }
    }

    @Singleton
    public static class Presenter extends MaiPresenter<ViewWeb, Object> {

        @Inject
        Chooser chooser;

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            getView().loadContent(chooser.getUrl());
        }

        @Override
        protected void unsubscribe() {

        }
    }

    public static class Chooser {

        public static final String[] P01 = {"http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_gos_politika_kadry.pdf"};
        public static final String[] P02 = {"http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_podgotovka_kadrov.pdf"};
        public static final String[] P03 = {"http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(short).pdf"};
        public static final String[] P04 = {"http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(full).pdf"};
        public static final String[] P05 = {"http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_school.pdf"};
        public static final String[] C01 = {"http://inter.mai.ru/"};
        public static final String[] C02 = {"http://klc103.mai.ru/"};
        public static final String[] C03 = {"http://klc103.mai.ru/"};
        public static final String[] PS = {"http://priem.mai.ru/index.php"};
        public static final String[] SCH01_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f_3.JPG"
        };
        public static final String[] SCH01_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f_3.JPG"
        };
        public static final String[] SCH01_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/1f/3k_1f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/3k_1f_2.JPG"
        };
        public static final String[] SCH01_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/1f/4k_1f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/4k_1f_2.JPG"
        };
        public static final String[] SCH01_5 = {
                "http://files.mai.ru/site//schedule/2015_autumn/1f/5k_1f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/1f/5k_1f_2.JPG"
        };
        public static final String[] SCH02_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f_3.JPG"
        };
        public static final String[] SCH02_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f_4.JPG"
        };
        public static final String SCH02_3 = "http://files.mai.ru/site//schedule/2015_autumn/2f/3k_2f.JPG";
        public static final String[] SCH03_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/3f/1-2k_3f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/1k_3f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/1k_3f_2.JPG"
        };
        public static final String[] SCH03_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f_6.JPG"
        };
        public static final String[] SCH03_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f_4.JPG"
        };
        public static final String[] SCH03_5 = {
                "http://files.mai.ru/site//schedule/2015_autumn/3f/5k_3f.JPG"
        };
        public static final String[] SCH04_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_4.JPG"
        };
        public static final String[] SCH04_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/4f/2k_4f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/4f/2k_4f_2.JPG"
        };
        public static final String[] SCH04_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/4f/3k_4f.JPG"
        };
        public static final String[] SCH04_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/4f/4k_4f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/4f/4k_4f_2.JPG"
        };
        public static final String[] SCH05_1E = {"http://files.mai.ru/site//schedule/2015_autumn/5f/1k_5f.JPG"};
        public static final String[] SCH05_2E = {"http://files.mai.ru/site//schedule/2015_autumn/5f/2k_5f.JPG"};
        public static final String[] SCH05_3E = {"http://files.mai.ru/site//schedule/2015_autumn/5f/3-4k_5f.JPG"};
        public static final String[] SCH05_5E = {"http://files.mai.ru/site//schedule/2015_autumn/5f/5k_5f.JPG"};
        public static final String[] SCH06_1E = {
                "http://files.mai.ru/site//schedule/2015_autumn/6f/1k_6f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/1k_6f_2.JPG"
        };
        public static final String[] SCH06_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_4.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_5.JPG"
        };
        public static final String[] SCH06_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f_3.JPG"
        };
        public static final String[] SCH06_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_2.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_3.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_4.JPG"
        };
        public static final String[] SCH06_5 = {
                "http://files.mai.ru/site//schedule/2015_autumn/6f/5k_6f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/6f/5k_6f_2.JPG"
        };
        public static final String[] SCH07_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/7f/1k_7f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/7f/1k_7f_2.JPG"
        };
        public static final String[] SCH07_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/7f/2k_7f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/7f/2k_7f_2.JPG"
        };
        public static final String[] SCH07_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/7f/3k_7f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/7f/3k_7f_2.JPG"
        };
        public static final String[] SCH07_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/7f/4k_7f.JPG",
                "http://files.mai.ru/site//schedule/2015_autumn/7f/4k_7f_2.JPG"
        };
        public static final String[] SCH07_5 = {
                "http://files.mai.ru/site//schedule/2015_autumn/7f/5k_7f.JPG"
        };
        public static final String[] SCH08_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/8f/1k_8f.JPG"
        };
        public static final String[] SCH08_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/8f/2k_8f.JPG"
        };
        public static final String[] SCH08_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/8f/3k_8f.JPG"
        };
        public static final String[] SCH08_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/8f/4k_8f.JPG"
        };
        public static final String[] SCH09_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/9f/1k_9f.JPG"
        };
        public static final String[] SCH09_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/9f/3k_9f.JPG"
        };
        public static final String[] SCH09_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/9f/4k_9f.JPG"
        };
        public static final String[] SCH09_5 = {
                "http://files.mai.ru/site//schedule/2015_autumn/9f/5k_9f.JPG"
        };
        public static final String[] SCH10_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/10f/1k_10f.JPG"
        };
        public static final String[] SCH10_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/10f/2k_10f.JPG"
        };
        public static final String[] SCH10_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/10f/3k_10f.JPG"
        };
        public static final String[] SCH10_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/10f/4k_10f.JPG"
        };
        public static final String[] SCH10_E = {
                "http://files.mai.ru/site//schedule/2015_autumn/10f/4-5k_10f.JPG"
        };
        public static final String[] SCH10_M = {
                "http://files.mai.ru/site//schedule/2015_autumn/mag_10f.JPG"
        };
        public static final String[] SCH_IN_1 = {
                "http://files.mai.ru/site//schedule/2015_autumn/lingv/1k_lingv.JPG"
        };
        public static final String[] SCH_IN_2 = {
                "http://files.mai.ru/site//schedule/2015_autumn/lingv/2k_lingv.JPG"
        };
        public static final String[] SCH_IN_3 = {
                "http://files.mai.ru/site//schedule/2015_autumn/lingv/3k_lingv.JPG"
        };
        public static final String[] SCH_IN_4 = {
                "http://files.mai.ru/site//schedule/2015_autumn/lingv/4k_lingv.JPG"
        };
        public static final String[] SCH_IN_R = {
            "http://files.mai.ru/site//schedule/2015_autumn/rek/1-4k_rek.JPG",
            "http://files.mai.ru/site//schedule/2015_autumn/rek/1-4k_rek2.JPG"
        };
        public static final String[] SCH_IN_M = {
                "http://files.mai.ru/site//schedule/2015_autumn/mag_avia.JPG"
        };



        private static String[] items = {
                "О государственной политике в сфере подготовки инженерно-технических кадров в Российской Федерации — выступление в Совете Федерации",
                "Подготовка высококвалифицированных кадров для оборонно-промышленного комплекса в России в МАИ",
                "Выступление Геращенко А.Н. на круглом столе с Рогозиным Д.О. (сокращенное)",
                "Выступление Геращенко А.Н. на круглом столе с Рогозиным Д.О. (полное)",
                "Презентация МАИ для школьников и абитуриентов (PDF)",
                "Курсы иностранных языков",
                "Курсы КЛЦ-103",
                "Курсы C-COURSES"
        };

        @Inject
        public Chooser() {
        }

        public String[] getUrl() {
            if (sTitle.equals(PRESENTATIONS + DELIM + items[0])) {
                return P01;
            } else if (sTitle.equals(PRESENTATIONS + DELIM + items[1])) {
                return P02;
            } else if (sTitle.equals(PRESENTATIONS + DELIM + items[2])) {
                return P03;
            } else if (sTitle.equals(PRESENTATIONS + DELIM + items[3])) {
                return P04;
            } else if (sTitle.equals(PRESENTATIONS + DELIM + items[4])) {
                return P05;
            } else if (sTitle.equals(COURSES + DELIM + items[5])) {
                return C01;
            } else if (sTitle.equals(COURSES + DELIM + items[6])) {
                return C02;
            } else if (sTitle.equals(COURSES + DELIM + items[7])) {
                return C03;
            } else if ((sTitle.equals(Router.PRIEM + DELIM + Router.PRIEM_SCHEDULE))) {
                return PS;
            } else {
                return null;
            }
        }

    }
}
