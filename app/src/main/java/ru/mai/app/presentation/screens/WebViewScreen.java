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
        public static final String S01_1 = "1О-101С, 1О-102С, 1О-103С, 1О-104С, 1О-110С, 1О-111С, 1О-112С";
        public static final String S01_2 = "1О-105С, 1О-106С, 1О-107С, 1О-108С, 1О-109С";
        public static final String S01_3 = "1О-101Б, 1О-103Б, 1О-104Б, 1О-105Бки, 1О-106Б, 1О-107Б";
        public static final String S01_4 = "1О-201Б, 1О-203Б, 1О-204Б, 1О-205Бки, 1О-206Б, 1О-207Б";
        public static final String S01_5 = "1О-205С, 1О-206С, 1О-207С, 1О-208С, 1О-209С";
        public static final String S01_6 = "1О-201С, 1О-202С, 1О-203С, 1О-204С, 1О-210С, 1О-211С, 1О-212С";
        public static final String S01_7 = "1О-305С, 1О-306С, 1О-307С, 1О-308С, 1О-309С";
        public static final String S01_8 = "1О-301С, 1О-302С, 1О-303С, 1О-304С, 1О-310С, 1О-311С, 1О-312С";
        public static final String S01_9 = "1О-401Б, 1О-103Б, 1О-405С, 1О-406С, 1О-407С, 1О-408С, 1О-409С";
        public static final String S01_10 = "1О-401С, 1О-402С, 1О-303С, 1О-404С, 1О-104Б, 1О-410С";
        public static final String S01_11 = "1О-505С, 1О-506С, 1О-507С, 1О-509С";
        public static final String S01_12 = "1О-501С, 1О-502С, 1О-503С, 1О-504С, 1О-510С";
        public static final String S02_1 = "2О-101М, 2О-102М, 2О-103М, 2О-109М";
        public static final String S02_2 = "2О-101С, 2О-109С, 2О-110С, 2О-104С, 2О-106С, 2О-107С";
        public static final String S02_3 = "2О-101Б, 2О-102Б, 2О-104Б, 2О-105Б, 2О-106Б";
        public static final String S02_4 = "2О-201М, 2О-202М, 2О-203М";
        public static final String S02_5 = "2О-201С, 2О-209С, 2О-210С, 2О-204С, 2О-206С, 2О-207С";
        public static final String S02_6 = "2О-201Б, 2О-204Б, 2О-205Б, 2О-207Б, 2О-206Б, 2О-211Бки";
        public static final String S02_7 = "2О-301Б, 2О-302Б, 2О-304Б, 2О-305Б, 2О-306Б, 2О-311Бки, 2О-312Бки";
        public static final String S03_1 = "3О-102М, 2О-103М, 3О-104М, 3О-107М, 3О-202М, 3О-203М, 3О-204М";
        public static final String S03_2 = "3О-107Б, 3О-109Б, 3О-110Б, 3О-111Б, 3О-112Б, 3О-121Б, 3О-114Б, 3О-115Б";
        public static final String S03_3 = "3О-101Б, 3О-102Б, 3О-103Б, 3О-104Б, 3О-105Б, 3О-106Б, 3О-119Б, 3О-115Б";
        public static final String S03_4 = "3О-207Б, 3О-209Б, 3О-210Б, 3О-211Б, 3О-212Б, 3О-221Б, 3О-214Б";
        public static final String S03_5 = "3О-201Б, 3О-202Б, 3О-220Бки, 3О-203Б, 3О-204Б, 3О-205Б, 3О-206Б";
        public static final String S03_6 = "3О-215Б, 3О-216Б, 3О-217Бк";
        public static final String S03_7 = "3О-312Б, 3О-321Б, 3О-314Б, 3О-315Б, 3О-316Б, 3О-317Бк, 3О-114Б, 3О-115Б";
        public static final String S03_8 = "3О-307Б, 3О-308Б, 3О-309Б, 3О-119Бки, 3О-110Б, 3О-311Б, 3О-323Б";
        public static final String S03_9 = "3О-301Б, 3О-302Б, 3О-320Бки, 3О-303Б, 3О-304Б, 3О-305Б, 3О-306Б, 3О-322Б";
        public static final String S03_10 = "7О-510С, 3О-501С, 3О-502С, 3О-503С, 3О-504С, 3О-505С, 3О-506С";
        public static final String S04_1 = "4О-101М, 4О-103М, 4О-104М, 4О-107М, 4О-202М, 4О-203М, 3О-204М";
        public static final String S04_2 = "4О-110Б, 4О-111Б, 4О-114Б";
        public static final String S04_3 = "4О-101Б, 4О-103Б, 4О-106Б, 4О-107Б, 4О-109Б";
        public static final String S04_4 = "4О-101С, 4О-102С, 4О-103С, 4О-105С, 4О-106С, 4О-107С, 4О-104С";
        public static final String S04_5 = "4О-210Б, 4О-211Б, 4О-214Б";
        public static final String S04_6 = "4О-201Б, 4О-203Б, 4О-206Б, 4О-207Б, 4О-209Б";
        public static final String S04_7 = "4О-301Б, 4О-302Б, 4О-303Б, 4О-304Б, 4О-306Б, 4О-307Б";
        public static final String S04_8 = "4О-408Б, 4О-409Б, 4О-410Б, 4О-411Б";
        public static final String S04_9 = "4О-401С, 4О-402С, 4О-403С, 4О-405С, 4О-406С, 4О-407С, 4О-404С";
        public static final String S05_1 = "5В-101Бк, 5В-102Бк, 5В-103Бк, 5В-104Бк, 5В-105Бк";
        public static final String S05_2 = "5В-201Бк, 5В-202Бк, 5В-203Бк, 5В-205Бк";
        public static final String S05_3 = "5В-301Бк, 5В-302Бк, 5В-303Бк, 5В-305Бк, 5В-401Бк, 5В-402Бк, 5В-403Бк";
        public static final String S05_4 = "5В-501Бк, 5В-502Бк, 5В-503Бк";
        public static final String S06_1 = "6О-101С, 6О-102С, 6О-103С, 6О-105С, 6О-106С, 6О-111С";
        public static final String S06_2 = "6О-105Б, 6О-106Б, 6О-107Б, 6О-109Б, 6О-108Б, 6О-101Б";
        public static final String S06_3 = "6О-203М, 6О-205М, 6О-207Мки, 6О-208Мки";
        public static final String S06_4 = "6О-215Ски, 6О-202С, 6О-203С, 6О-205С, 6О-205С, 6О-204Б";
        public static final String S06_5 = "6О-207С, 6О-210С, 6О-213С, 6О-211С, 6О-201С, 6О-201Б";
        public static final String S06_6 = "6О-205Б, 6О-206Б, 6О-210Б, 6О-213Бки, 6О-207Б, 6О-209Б, 6О-208Б";
        public static final String S06_7 = "6О-301С, 6О-302С, 6О-303С, 6О-304С, 6О-305С, 6О-306С";
        public static final String S06_8 = "6О-305Б, 6О-306Б, 6О-310Б, 6О-313Б, 6О-307Б, 6О-309Б, 6О-308Б";
        public static final String S06_9 = "6О-301Б, 6О-314Б, 6О-304Б";
        public static final String S06_10 = "6О-407С, 6О-408С, 6О-410С, 6О-412С, 6О-409С";
        public static final String S06_11 = "6О-401С, 6О-402С, 6О-403С, 6О-404С, 6О-405С, 6О-406С, 6О-411С";
        public static final String S06_12 = "6О-401Б, 6О-414Б, 6О-404Б";
        public static final String S06_13 = "6О-405Б, 6О-406Б, 6О-412Б, 6О-407Бки, 6О-409Б, 6О-408Б, 6О-402Б";
        public static final String S06_14 = "6О-507С, 6О-512С, 6О-508С, 6О-509С, 6О-510С, 6О-511С";
        public static final String S06_15 = "6О-501С, 6О-502С, 6О-503С, 6О-504С, 6О-505С, 6О-506С";
        public static final String S07_1 = "7О-101С, 7О-102С, 7О-103С, 7О-104С";
        public static final String S07_2 = "7О-106С, 7О-107С, 7О-108С, 7О-109С, 7О-110С, 3О-106С";
        public static final String S07_3 = "7О-201С, 7О-202С, 7О-203С, 7О-204С";
        public static final String S07_4 = "7О-206С, 7О-207С, 7О-208С, 7О-209С, 7О-210С, 3О-206СС";
        public static final String S07_5 = "7О-301С, 7О-302С, 7О-303С, 7О-304С";
        public static final String S07_6 = "7О-306С, 7О-307С, 7О-308С, 7О-309С, 7О-310С, 3О-306С";
        public static final String S07_7 = "7О-406С, 7О-407С, 7О-408С, 7О-410С, 3О-406С";
        public static final String S07_8 = "7О-401С, 7О-402С, 7О-403С, 7О-404С";
        public static final String S07_9 = "7О-501С, 7О-502,6С, 7О-503С, 7О-504,5С, 7О-508,9С, 7О-510С, 3О-506С";
        public static final String S08_1 = "8О-101Б, 8О-107Б, 8О-106Б, 8О-108Б, 8О-102Б, 8О-103Б, 8О-104Б, 8О-105Б";
        public static final String S08_2 = "8О-201Б, 8О-207Б, 8О-206Б, 8О-208Б, 8О-202Б, 8О-203Б, 8О-204Б, 8О-205Б";
        public static final String S08_3 = "8О-301Б, 8О-307Б, 8О-306Б, 8О-308Б, 8О-302Б, 8О-303Б, 8О-304Б, 8О-305Б";
        public static final String S08_4 = "8О-401Б, 8О-407Б, 8О-406Б, 8О-408Б, 8О-402Б, 8О-403Б, 8О-404Б, 8О-405Б";
        public static final String S09_1 = "9О-101С, 9О-102С, 9О-101Б, 9О-102Б, 9О-104Б, 9О-106Б";
        public static final String S09_2 = "9О-301С, 9О-302С, 9О-301Б, 9О-302Б, 9О-304Б, 9О-306Б";
        public static final String S09_3 = "9О-401С, 9О-402С, 9О-401Б, 9О-402Б, 9О-404Б";
        public static final String S09_4 = "9О-501С, 9О-502С";
        public static final String S10_1 = "ГО-101Б, ГО-102Б, ГО-103Бк";
        public static final String S10_2 = "ГО-201Б, ГО-202Б, ГО-203Бк, ГО-204Бк";
        public static final String S10_3 = "ГО-301Б, ГО-302Б, ГО-303Бк, ГО-304Бк";
        public static final String S10_4 = "ГО-401Б, ГО-402Б, ГО-403Бк, ГО-404Бк";
        public static final String S10_5 = "ГВ-401Б, ГВ-501Б";
        public static final String S10_6 = "ГО-101м, ГО-201м";
        public static final String S11_1 = "ИО-101Бк, ИО-102Бк, ИО-103Бк, ИО-104Бк";
        public static final String S11_2 = "ИО-201Бк, ИО-202Бк, ИО-203Бк";
        public static final String S11_3 = "ИО-301Бк, ИО-302Бк, ИО-303Бк";
        public static final String S11_4 = "ИО-401Бк, ИО-402Бк";
        public static final String S11_5 = "ИО-105Бк, ИО-106Бк, ИО-107Бк, ИО-204Бк, ИО-205Бк, ИО-304Бк, ИО-305Бк, ИО-403Бк, ИО-404Бк";
        public static final String S11_6 = "ИО-105Бк, ИО-106Бк, ИО-107Бк, ИО-204Бк, ИО-205Бк, ИО-304Бк, ИО-305Бк, ИО-403Бк, ИО-404Бк";
        public static final String S11_7 = "ИО-101М, ИО-102М, ИД-105М, ИО-201М, ИО-204М";

        public static final String SCH01_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f.JPG";
        public static final String SCH01_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f_2.JPG";
        public static final String SCH01_1_3 = "http://files.mai.ru/site//schedule/2015_autumn/1f/1k_1f_3.JPG";
        public static final String SCH01_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f.JPG";
        public static final String SCH01_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f_2.JPG";
        public static final String SCH01_2_3 = "http://files.mai.ru/site//schedule/2015_autumn/1f/2k_1f_3.JPG";
        public static final String SCH01_3_1 = "http://files.mai.ru/site//schedule/2015_autumn/1f/3k_1f.JPG";
        public static final String SCH01_3_2 = "http://files.mai.ru/site//schedule/2015_autumn/1f/3k_1f_2.JPG";
        public static final String SCH01_4_1 = "http://files.mai.ru/site//schedule/2015_autumn/1f/4k_1f.JPG";
        public static final String SCH01_4_2 = "http://files.mai.ru/site//schedule/2015_autumn/1f/4k_1f_2.JPG";
        public static final String SCH01_5_1 = "http://files.mai.ru/site//schedule/2015_autumn/1f/5k_1f.JPG";
        public static final String SCH01_5_2 = "http://files.mai.ru/site//schedule/2015_autumn/1f/5k_1f_2.JPG";
        public static final String SCH02_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f.JPG";
        public static final String SCH02_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f_2.JPG";
        public static final String SCH02_1_3 = "http://files.mai.ru/site//schedule/2015_autumn/2f/1k_2f_3.JPG";
        public static final String SCH02_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f.JPG";
        public static final String SCH02_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f_3.JPG";
        public static final String SCH02_2_3 = "http://files.mai.ru/site//schedule/2015_autumn/2f/2k_2f_4.JPG";
        public static final String SCH02_3 = "http://files.mai.ru/site//schedule/2015_autumn/2f/3k_2f.JPG";
        public static final String SCH03_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/3f/1-2k_3f.JPG";
        public static final String SCH03_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/3f/1k_3f.JPG";
        public static final String SCH03_1_3 = "http://files.mai.ru/site//schedule/2015_autumn/3f/1k_3f_2.JPG";
        public static final String SCH03_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f.JPG";
        public static final String SCH03_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f_3.JPG";
        public static final String SCH03_2_3 = "http://files.mai.ru/site//schedule/2015_autumn/3f/2k_3f_6.JPG";
        public static final String SCH03_3_1 = "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f.JPG";
        public static final String SCH03_3_2 = "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f_3.JPG";
        public static final String SCH03_3_3 = "http://files.mai.ru/site//schedule/2015_autumn/3f/3k_3f_4.JPG";
        public static final String SCH03_5_1 = "http://files.mai.ru/site//schedule/2015_autumn/3f/5k_3f.JPG";
        public static final String SCH04_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f.JPG";
        public static final String SCH04_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_2.JPG";
        public static final String SCH04_1_3 = "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_3.JPG";
        public static final String SCH04_1_4 = "http://files.mai.ru/site//schedule/2015_autumn/4f/1k_4f_4.JPG";
        public static final String SCH04_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/4f/2k_4f.JPG";
        public static final String SCH04_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/4f/2k_4f_2.JPG";
        public static final String SCH04_3_1 = "http://files.mai.ru/site//schedule/2015_autumn/4f/3k_4f.JPG";
        public static final String SCH04_4_1 = "http://files.mai.ru/site//schedule/2015_autumn/4f/4k_4f.JPG";
        public static final String SCH04_4_2 = "http://files.mai.ru/site//schedule/2015_autumn/4f/4k_4f_2.JPG";
        public static final String SCH05_1E = "http://files.mai.ru/site//schedule/2015_autumn/5f/1k_5f.JPG";
        public static final String SCH05_2E = "http://files.mai.ru/site//schedule/2015_autumn/5f/2k_5f.JPG";
        public static final String SCH05_3E = "http://files.mai.ru/site//schedule/2015_autumn/5f/3-4k_5f.JPG";
        public static final String SCH05_5E = "http://files.mai.ru/site//schedule/2015_autumn/5f/5k_5f.JPG";
        public static final String SCH06_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/6f/1k_6f.JPG";
        public static final String SCH06_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/6f/1k_6f_2.JPG";
        public static final String SCH06_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f.JPG";
        public static final String SCH06_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_3.JPG";
        public static final String SCH06_2_3 = "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_4.JPG";
        public static final String SCH06_2_4 = "http://files.mai.ru/site//schedule/2015_autumn/6f/2k_6f_5.JPG";
        public static final String SCH06_3_1 = "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f.JPG";
        public static final String SCH06_3_2 = "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f_2.JPG";
        public static final String SCH06_3_3 = "http://files.mai.ru/site//schedule/2015_autumn/6f/3k_6f_3.JPG";
        public static final String SCH06_4_1 = "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f.JPG";
        public static final String SCH06_4_2 = "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_2.JPG";
        public static final String SCH06_4_3 = "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_3.JPG";
        public static final String SCH06_4_4 = "http://files.mai.ru/site//schedule/2015_autumn/6f/4k_6f_4.JPG";
        public static final String SCH06_5_1 = "http://files.mai.ru/site//schedule/2015_autumn/6f/5k_6f.JPG";
        public static final String SCH06_5_2 = "http://files.mai.ru/site//schedule/2015_autumn/6f/5k_6f_2.JPG";
        public static final String SCH07_1_1 = "http://files.mai.ru/site//schedule/2015_autumn/7f/1k_7f.JPG";
        public static final String SCH07_1_2 = "http://files.mai.ru/site//schedule/2015_autumn/7f/1k_7f_2.JPG";
        public static final String SCH07_2_1 = "http://files.mai.ru/site//schedule/2015_autumn/7f/2k_7f.JPG";
        public static final String SCH07_2_2 = "http://files.mai.ru/site//schedule/2015_autumn/7f/2k_7f_2.JPG";
        public static final String SCH07_3_1 = "http://files.mai.ru/site//schedule/2015_autumn/7f/3k_7f.JPG";
        public static final String SCH07_3_2 = "http://files.mai.ru/site//schedule/2015_autumn/7f/3k_7f_2.JPG";
        public static final String SCH07_4_1 = "http://files.mai.ru/site//schedule/2015_autumn/7f/4k_7f.JPG";
        public static final String SCH07_4_2 = "http://files.mai.ru/site//schedule/2015_autumn/7f/4k_7f_2.JPG";
        public static final String SCH07_5_1 = "http://files.mai.ru/site//schedule/2015_autumn/7f/5k_7f.JPG";
        public static final String SCH08_1 = "http://files.mai.ru/site//schedule/2015_autumn/8f/1k_8f.JPG";
        public static final String SCH08_2 = "http://files.mai.ru/site//schedule/2015_autumn/8f/2k_8f.JPG";
        public static final String SCH08_3 = "http://files.mai.ru/site//schedule/2015_autumn/8f/3k_8f.JPG";
        public static final String SCH08_4 = "http://files.mai.ru/site//schedule/2015_autumn/8f/4k_8f.JPG";
        public static final String SCH09_1 = "http://files.mai.ru/site//schedule/2015_autumn/9f/1k_9f.JPG";
        public static final String SCH09_3 = "http://files.mai.ru/site//schedule/2015_autumn/9f/3k_9f.JPG";
        public static final String SCH09_4 = "http://files.mai.ru/site//schedule/2015_autumn/9f/4k_9f.JPG";
        public static final String SCH09_5 = "http://files.mai.ru/site//schedule/2015_autumn/9f/5k_9f.JPG";
        public static final String SCH10_1 = "http://files.mai.ru/site//schedule/2015_autumn/10f/1k_10f.JPG";
        public static final String SCH10_2 = "http://files.mai.ru/site//schedule/2015_autumn/10f/2k_10f.JPG";
        public static final String SCH10_3 = "http://files.mai.ru/site//schedule/2015_autumn/10f/3k_10f.JPG";
        public static final String SCH10_4 = "http://files.mai.ru/site//schedule/2015_autumn/10f/4k_10f.JPG";
        public static final String SCH10_E = "http://files.mai.ru/site//schedule/2015_autumn/10f/4-5k_10f.JPG";
        public static final String SCH10_M = "http://files.mai.ru/site//schedule/2015_autumn/mag_10f.JPG";
        public static final String SCH_IN_1 = "http://files.mai.ru/site//schedule/2015_autumn/lingv/1k_lingv.JPG";
        public static final String SCH_IN_2 = "http://files.mai.ru/site//schedule/2015_autumn/lingv/2k_lingv.JPG";
        public static final String SCH_IN_3 = "http://files.mai.ru/site//schedule/2015_autumn/lingv/3k_lingv.JPG";
        public static final String SCH_IN_4 = "http://files.mai.ru/site//schedule/2015_autumn/lingv/4k_lingv.JPG";
        public static final String SCH_IN_R_1 = "http://files.mai.ru/site//schedule/2015_autumn/rek/1-4k_rek.JPG";
        public static final String SCH_IN_R_2 = "http://files.mai.ru/site//schedule/2015_autumn/rek/1-4k_rek2.JPG";
        public static final String SCH_IN_M = "http://files.mai.ru/site//schedule/2015_autumn/mag_avia.JPG";


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
                return SCH01_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_2))) {
                return SCH01_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_3))) {
                return SCH01_1_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_4))) {
                return SCH01_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_5))) {
                return SCH01_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_6))) {
                return SCH01_2_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_7))) {
                return SCH01_3_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_8))) {
                return SCH01_3_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_9))) {
                return SCH01_4_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_10))) {
                return SCH01_4_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_11))) {
                return SCH01_5_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH1 + DELIM + S01_12))) {
                return SCH01_5_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_1))) {
                return SCH02_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_2))) {
                return SCH02_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_3))) {
                return SCH02_1_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_4))) {
                return SCH02_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_5))) {
                return SCH02_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_6))) {
                return SCH02_2_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH2 + DELIM + S02_7))) {
                return SCH02_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_1))) {
                return SCH03_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_2))) {
                return SCH03_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_3))) {
                return SCH03_1_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_4))) {
                return SCH03_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_5))) {
                return SCH03_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_6))) {
                return SCH03_2_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_7))) {
                return SCH03_3_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_8))) {
                return SCH03_3_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_9))) {
                return SCH03_3_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH3 + DELIM + S03_10))) {
                return SCH03_5_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_1))) {
                return SCH04_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_2))) {
                return SCH04_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_3))) {
                return SCH04_1_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_4))) {
                return SCH04_1_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_5))) {
                return SCH04_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_6))) {
                return SCH04_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_7))) {
                return SCH04_3_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_8))) {
                return SCH04_4_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH4 + DELIM + S04_9))) {
                return SCH04_4_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_1))) {
                return SCH05_1E;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_2))) {
                return SCH05_2E;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_3))) {
                return SCH05_3E;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH5 + DELIM + S05_4))) {
                return SCH05_5E;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_1))) {
                return SCH06_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_2))) {
                return SCH06_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_3))) {
                return SCH06_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_4))) {
                return SCH06_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_5))) {
                return SCH06_2_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_6))) {
                return SCH06_2_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_7))) {
                return SCH06_3_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_8))) {
                return SCH06_3_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_9))) {
                return SCH06_3_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_10))) {
                return SCH06_4_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_11))) {
                return SCH06_4_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_12))) {
                return SCH06_4_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_13))) {
                return SCH06_4_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_14))) {
                return SCH06_5_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH6 + DELIM + S06_15))) {
                return SCH06_5_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_1))) {
                return SCH07_1_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_2))) {
                return SCH07_1_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_3))) {
                return SCH07_2_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_4))) {
                return SCH07_2_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_5))) {
                return SCH07_3_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_6))) {
                return SCH07_3_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_7))) {
                return SCH07_4_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_8))) {
                return SCH07_4_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH7 + DELIM + S07_9))) {
                return SCH07_5_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_1))) {
                return SCH08_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_2))) {
                return SCH08_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_3))) {
                return SCH08_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH8 + DELIM + S08_4))) {
                return SCH08_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_1))) {
                return SCH09_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_2))) {
                return SCH09_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_3))) {
                return SCH09_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH9 + DELIM + S09_4))) {
                return SCH09_5;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_1))) {
                return SCH10_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_2))) {
                return SCH10_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_3))) {
                return SCH10_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_4))) {
                return SCH10_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_5))) {
                return SCH10_E;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH10 + DELIM + S10_6))) {
                return SCH10_M;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_1))) {
                return SCH_IN_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_2))) {
                return SCH_IN_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_3))) {
                return SCH_IN_3;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_4))) {
                return SCH_IN_4;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_5))) {
                return SCH_IN_R_1;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_6))) {
                return SCH_IN_R_2;
            } else if ((sTitle.equals(SCHEDULE + DELIM + SCH11 + DELIM + S11_7))) {
                return SCH_IN_M;
            } else {
                return "http://mai.ru";
            }
        }

    }
}
