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
import static ru.mai.app.Router.PRIEM;
import static ru.mai.app.Router.PRIEM_SCHEDULE;
import static ru.mai.app.Router.SCHEDULE;
import static ru.mai.app.Router.SCH1;
import static ru.mai.app.Router.SCH2;
import static ru.mai.app.Router.SCH3;
import static ru.mai.app.Router.SCH4;
import static ru.mai.app.Router.SCH5;
import static ru.mai.app.Router.SCH6;
import static ru.mai.app.Router.SCH7;
import static ru.mai.app.Router.SCH8;
import static ru.mai.app.Router.SCH9;
import static ru.mai.app.Router.SCH10;
import static ru.mai.app.Router.SCH11;
import static ru.mai.app.Router.SCH12;
import static ru.mai.app.Router.SCH13;
import static ru.mai.app.Router.SCH14;
import static ru.mai.app.Router.SCH15;

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

        public static final String P01 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_gos_politika_kadry.pdf";
        public static final String P02 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_podgotovka_kadrov.pdf";
        public static final String P03 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(short).pdf";
        public static final String P04 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(full).pdf";
        public static final String P05 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_school.pdf";
        public static final String C01 = "http://inter.mai.ru/";
        public static final String C02 = "http://klc103.mai.ru/";
        public static final String C03 = "http://klc103.mai.ru/";
        public static final String PS = "http://priem.mai.ru/index.php";
        public static final String S01_1 = "1 курс — очное";
        public static final String S01_2 = "2 курс — очное";
        public static final String S01_3 = "3 курс — очное";
        public static final String S01_4 = "4 курс — очное";
        public static final String S01_5 = "5 курс — очное";
        public static final String S01_6 = "Магистратура";
        public static final String S02_1 = "1 курс — очное";
        public static final String S02_2 = "2 курс — очное";
        public static final String S02_3 = "3 курс — очное";
        public static final String S02_4 = "4 курс — очное";
        public static final String S02_5 = "5 курс — очное";
        public static final String S02_6 = "Очно-заочное";
        public static final String S02_7 = "Магистратура";
        public static final String S03_1 = "1 курс — очное";
        public static final String S03_2 = "2 курс — очное";
        public static final String S03_5 = "5 курс — очное";
        public static final String S04_1 = "1 курс — очное";
        public static final String S04_2 = "2 курс — очное";
        public static final String S04_3 = "3 курс — очное";
        public static final String S04_4 = "4 курс — очное";
        public static final String S04_5 = "5 курс — очное";
        public static final String S04_6 = "Магистратура";
        public static final String S06_1 = "1 курс — очное";
        public static final String S06_2 = "2 курс — очное";
        public static final String S06_3 = "3 курс — очное";
        public static final String S06_4 = "4 курс — очное";
        public static final String S06_5 = "5 курс — очное";
        public static final String S06_6 = "Очно-заочное";
        public static final String S06_7 = "Магистратура";
        public static final String S07_1 = "1 курс — очное";
        public static final String S07_2 = "2 курс — очное";
        public static final String S07_3 = "3 курс — очное";
        public static final String S07_4 = "4 курс — очное";
        public static final String S07_5 = "5 курс — очное";
        public static final String S07_6 = "Очно-заочное";
        public static final String S08_1 = "1 курс — очное";
        public static final String S08_2 = "2 курс — очное";
        public static final String S08_3 = "3 курс — очное";
        public static final String S08_4 = "4 курс — очное";
        public static final String S08_6 = "Очно-заочное";
        public static final String S08_7 = "Магистратура";
        public static final String S09_1 = "1 курс — очное";
        public static final String S09_2 = "2 курс — очное";
        public static final String S09_3 = "3 курс — очное";
        public static final String S09_4 = "4 курс — очное";
        public static final String S09_5 = "5 курс — очное";
        public static final String S10_1 = "Очное, очно-заочное, магистратура";
        public static final String S11_1 = "Очное, магистратура (реклама и связи с общественностью)";
        public static final String S05_1 = "1 курс — очное";
        public static final String S05_2 = "2 курс — очное";
        public static final String S05_3 = "3 курс — очное";
        public static final String S05_4 = "4 курс — очное";
        public static final String S05_5 = "5 курс — очное";
        public static final String S05_6 = "Очно-заочное";
        public static final String S05_7 = "Магистратура";
        public static final String S12_1 = "1 курс — очное";
        public static final String S12_2 = "2 курс — очное";
        public static final String S12_3 = "3 курс — очное";
        public static final String S12_4 = "4 курс — очное";
        public static final String S12_6 = "Очно-заочное";
        public static final String S12_7 = "Магистратура";
        public static final String S12_8 = "Аспирантура, 1 год обучения";
        public static final String S12_9 = "Аспирантура, 2 год обучения";
        public static final String S13_1 = "1 курс — очное";
        public static final String S13_2 = "2 курс — очное";
        public static final String S13_3 = "3 курс — очное";
        public static final String S13_4 = "4 курс — очное";
        public static final String S13_7 = "Магистратура";
        public static final String S13_8 = "Аспирантура, 1 год обучения";
        public static final String S13_9 = "Аспирантура, 2 год обучения";
        public static final String S14_1 = "1 курс — очное";
        public static final String S14_2 = "2 курс — очное";
        public static final String S14_3 = "3 курс — очное";
        public static final String S14_4 = "4 курс — очное";
        public static final String S14_6 = "Очно-заочное";
        public static final String S14_7 = "Магистратура";
        public static final String S14_8 = "Аспирантура, 1 год обучения";
        public static final String S14_9 = "Аспирантура, 2 год обучения";
        public static final String S15_1 = "1 курс — очное";
        public static final String S15_2 = "2 курс — очное";
        public static final String S15_3 = "3 курс — очное";
        public static final String S15_4 = "4 курс — очное";
        public static final String S15_6 = "Очно-заочное";
        public static final String S15_7 = "Магистратура";
        public static final String S15_8 = "Аспирантура, 1 год обучения";
        public static final String S15_9 = "Аспирантура, 2 год обучения";


        public static final String SCH01_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_1k.pdf";
        public static final String SCH01_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_2k.pdf";
        public static final String SCH01_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_3k.pdf";
        public static final String SCH01_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_4k.pdf";
        public static final String SCH01_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_5.jpg";
        public static final String SCH01_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1_master.jpg";
        public static final String SCH02_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_1.pdf";
        public static final String SCH02_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_2.pdf";
        public static final String SCH02_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_3.pdf";
        public static final String SCH02_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_4.pdf";
        public static final String SCH02_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_5.pdf";
        public static final String SCH02_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_vecher.jpg";
        public static final String SCH02_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2_master.jpg";
        public static final String SCH03_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3_1.pdf";
        public static final String SCH03_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3_2.pdf";
        public static final String SCH03_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3_5.pdf";
        public static final String SCH04_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_1.pdf";
        public static final String SCH04_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_2.pdf";
        public static final String SCH04_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_3.pdf";
        public static final String SCH04_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_4.pdf";
        public static final String SCH04_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_5.pdf";
        public static final String SCH04_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4_master.pdf";
        public static final String SCH06_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_1.pdf";
        public static final String SCH06_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_2.pdf";
        public static final String SCH06_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_3.pdf";
        public static final String SCH06_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_4.pdf";
        public static final String SCH06_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_5.pdf";
        public static final String SCH06_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_vecher.pdf";
        public static final String SCH06_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/6_master.pdf";
        public static final String SCH07_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_1.JPG";
        public static final String SCH07_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_2.JPG";
        public static final String SCH07_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_3.JPG";
        public static final String SCH07_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_4.JPG";
        public static final String SCH07_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_5.JPG";
        public static final String SCH07_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/7_vecher.JPG";
        public static final String SCH08_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_1.JPG";
        public static final String SCH08_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_2.JPG";
        public static final String SCH08_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_3.JPG";
        public static final String SCH08_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_4.JPG";
        public static final String SCH08_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_vecher.JPG";
        public static final String SCH08_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/8_master.jpg";
        public static final String SCH09_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/9_1.JPG";
        public static final String SCH09_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/9_2.JPG";
        public static final String SCH09_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/9_3.JPG";
        public static final String SCH09_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/9_4.JPG";
        public static final String SCH09_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/9_5.JPG";
        public static final String SCH10_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/10.xlsx";
        public static final String SCH11_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/In_yaz.xlsx";
        public static final String SCH05_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_1_k.pdf";
        public static final String SCH05_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_2_k.pdf";
        public static final String SCH05_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_3_k.pdf";
        public static final String SCH05_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_4_k.pdf";
        public static final String SCH05_5 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_5_k.pdf";
        public static final String SCH05_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_vecher.pdf";
        public static final String SCH05_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/5_master.pdf";
        public static final String SCH12_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_1_k.xlsx";
        public static final String SCH12_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_2_k.xlsx";
        public static final String SCH12_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_3_k.xlsx";
        public static final String SCH12_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_4_k.xlsx";
        public static final String SCH12_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_vecher.xlsx";
        public static final String SCH12_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_master.xlsx";
        public static final String SCH12_8 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_post1.xlsx";
        public static final String SCH12_9 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/1MATI_post2.xlsx";
        public static final String SCH13_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_1.xls";
        public static final String SCH13_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_2.xlsx";
        public static final String SCH13_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_3.xlsx";
        public static final String SCH13_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_4.xlsx";
        public static final String SCH13_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_master.xlsx";
        public static final String SCH13_8 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_post1.xlsx";
        public static final String SCH13_9 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/2MATI_post2.xlsx";
        public static final String SCH14_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_1_k.xlsx";
        public static final String SCH14_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_2_k.xlsx";
        public static final String SCH14_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_3_k.xlsx";
        public static final String SCH14_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_4_k.xlsx";
        public static final String SCH14_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_vecher.xlsx";
        public static final String SCH14_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_master_1.xlsx";
        public static final String SCH14_8 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_post_1.xlsx";
        public static final String SCH14_9 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/3MATI_post_2.xlsx";
        public static final String SCH15_1 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_1_k.xlsx";
        public static final String SCH15_2 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_2_k.xlsx";
        public static final String SCH15_3 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_3_k.xlsx";
        public static final String SCH15_4 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_4_k.xlsx";
        public static final String SCH15_6 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_vecher.xls";
        public static final String SCH15_7 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_master.xlsx";
        public static final String SCH15_8 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_post_1.xlsx";
        public static final String SCH15_9 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/schedule/2015_2016_spring/4MATI_post_2.xlsx";


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

        public String getUrl() {
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
            } else if ((sTitle.equals(PRIEM + DELIM + PRIEM_SCHEDULE))) {
                return PS;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_1))) {
                return SCH01_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_2))) {
                return SCH01_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_3))) {
                return SCH01_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_4))) {
                return SCH01_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_5))) {
                return SCH01_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_6))) {
                return SCH01_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_1))) {
                return SCH02_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_2))) {
                return SCH02_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_3))) {
                return SCH02_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_4))) {
                return SCH02_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_5))) {
                return SCH02_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_6))) {
                return SCH02_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_7))) {
                return SCH02_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_1))) {
                return SCH03_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_2))) {
                return SCH03_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_5))) {
                return SCH03_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_1))) {
                return SCH04_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_2))) {
                return SCH04_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_3))) {
                return SCH04_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_4))) {
                return SCH04_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_5))) {
                return SCH04_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_6))) {
                return SCH04_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_1))) {
                return SCH06_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_2))) {
                return SCH06_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_3))) {
                return SCH06_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_4))) {
                return SCH06_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_5))) {
                return SCH06_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_6))) {
                return SCH06_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_7))) {
                return SCH06_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_1))) {
                return SCH07_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_2))) {
                return SCH07_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_3))) {
                return SCH07_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_4))) {
                return SCH07_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_5))) {
                return SCH07_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_6))) {
                return SCH07_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_1))) {
                return SCH08_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_2))) {
                return SCH08_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_3))) {
                return SCH08_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_4))) {
                return SCH08_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_6))) {
                return SCH08_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_7))) {
                return SCH08_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_1))) {
                return SCH09_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_2))) {
                return SCH09_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_3))) {
                return SCH09_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_4))) {
                return SCH09_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_5))) {
                return SCH09_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_1))) {
                return SCH10;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_1))) {
                return SCH11;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_1))) {
                return SCH05_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_2))) {
                return SCH05_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_3))) {
                return SCH05_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_4))) {
                return SCH05_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_5))) {
                return SCH05_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_6))) {
                return SCH05_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_7))) {
                return SCH05_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_1))) {
                return SCH12_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_2))) {
                return SCH12_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_3))) {
                return SCH12_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_4))) {
                return SCH12_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_6))) {
                return SCH12_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_7))) {
                return SCH12_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_8))) {
                return SCH12_8;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH12 + DELIM + S12_9))) {
                return SCH12_9;
            }  else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S12_1))) {
                return SCH13_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_2))) {
                return SCH13_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_3))) {
                return SCH13_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_4))) {
                return SCH13_4;
            }  else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_7))) {
                return SCH13_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_8))) {
                return SCH13_8;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH13 + DELIM + S13_9))) {
                return SCH13_9;
            }  else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_1))) {
                return SCH14_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_2))) {
                return SCH14_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_3))) {
                return SCH14_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_4))) {
                return SCH14_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_6))) {
                return SCH14_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_7))) {
                return SCH14_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_8))) {
                return SCH14_8;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH14 + DELIM + S14_9))) {
                return SCH14_9;
            }  else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_1))) {
                return SCH15_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_2))) {
                return SCH15_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_3))) {
                return SCH15_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_4))) {
                return SCH15_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_6))) {
                return SCH15_6;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_7))) {
                return SCH15_7;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_8))) {
                return SCH15_8;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH15 + DELIM + S15_9))) {
                return SCH15_9;
            } else {
                return "http://mai.ru";
            }
        }

    }
}
