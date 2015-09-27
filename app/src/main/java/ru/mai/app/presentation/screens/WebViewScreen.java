package ru.mai.app.presentation.screens;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.path.Path;
import ru.mai.app.App;
import ru.mai.app.R;
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

        public static final String P01 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_gos_politika_kadry.pdf";
        public static final String P02 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_podgotovka_kadrov.pdf";
        public static final String P03 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(short).pdf";
        public static final String P04 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_rector_(full).pdf";
        public static final String P05 = "http://docs.google.com/gview?embedded=true&url=http://files.mai.ru/site/life/brand/presentation/presentation_mai_school.pdf";
        public static final String C01 = "http://inter.mai.ru/";
        public static final String C02 = "http://klc103.mai.ru/";
        public static final String C03 = "http://klc103.mai.ru/";

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
            }  else if (sTitle.equals(COURSES + DELIM + items[7])) {
                return C03;
            }  else {
                return "";
            }
        }

    }
}
