package oleg.osipenko.mai;

import flow.path.Path;
import oleg.osipenko.mai.presentation.screens.ListContentScreen;
import oleg.osipenko.mai.presentation.screens.MainScreen;
import oleg.osipenko.mai.presentation.screens.MapScreen;
import oleg.osipenko.mai.presentation.screens.StaticContentScreen;
import oleg.osipenko.mai.presentation.screens.StaticListContentScreen;

/**
 * Created by olegosipenko on 13.09.15.
 */
public class Router {
    public static final String WEEK = "week";
    public static final String FACULTIES = "Факультеты";
    public static final String SESSION = "Сессия";
    public static final String PRACTICS = "Практика";
    public static final String SCHOLARSHIPS = "Стипендии";
    public static final String LIBRARIES = "Библиотеки";
    public static final String CANTEENS = "Столовые";
    public static final String DOTATIONS = "Дотации и материальная помощь";
    public static final String MILITARY_INSTITUTE = "Военный институт";
    public static final String SECONDARY_EDUCATION = "Второе высшее образование";
    public static final String MAGISTRACY = "Магистратура";
    public static final String COURSES = "Курсы";
    public static final String ACADEMIC_MOBILITY = "Академическая мобильность";
    public static final String EMPLOYMENT_CENTER = "Центр трудоустройства МАИ";
    public static final String SCIENCE = "Научная деятельность";
    public static final String DORMITORIES = "Студгородок";
    public static final String SANATORIUM = "Санаторий-профилакторий";
    public static final String RECREATION_CENTERS = "Базы отдыха";
    public static final String SPORT_SECTIONS = "Спортивные секции";
    public static final String DK = "Дворец культуры и техники МАИ";
    public static final String DOSAAF = "Спортивно-технические клубы (ДОСААФ МАИ)";
    public static final String DEBATING_CLUB = "Клуб Дебаты МАИ";
    public static final String MAISKY_VZLET = "Фестиваль \"МАЙский взлёт\"";
    public static final String PROFKOM = "Профком студентов и аспирантов МАИ";
    public static final String SOMOL = "Союз молодёжи";
    public static final String PRESS = "Маёвские СМИ и интернет-ресурсы";
    public static final String MEDIA = "Медиа";
    public static final String HISTORY = "История МАИ";
    public static final String LIFE = "Жизнь маёвца";
    public static final String HELP = "В помощь студенту";
    public static final String MAIN = "Главная";
    public static final String NEWS = "Новости";
    public static final String MAP = "Карта";
    public static final String SCHEDULE = "Расписание";

    public Path getScreen(String item) {
        if (item.contains(WEEK) || item.equals(MAIN)) return new MainScreen();
        if (item.equals(MAP)) return new MapScreen();
        switch (item) {
            case FACULTIES:
            case SCHOLARSHIPS:
            case LIBRARIES:
            case CANTEENS:
            case COURSES:
            case ACADEMIC_MOBILITY:
            case SPORT_SECTIONS:
            case MEDIA:
            case LIFE:
            case HELP:
            case NEWS:
            case SCHEDULE:
                return new ListContentScreen(item);
            case SESSION:
            case MILITARY_INSTITUTE:
            case SECONDARY_EDUCATION:
            case MAGISTRACY:
            case RECREATION_CENTERS:
            case DK:
            case DOSAAF:
                return new StaticListContentScreen(item);
            case PRACTICS:
            case DOTATIONS:
            case EMPLOYMENT_CENTER:
            case SCIENCE:
            case DORMITORIES:
            case SANATORIUM:
            case DEBATING_CLUB:
            case MAISKY_VZLET:
            case PROFKOM:
            case SOMOL:
            case PRESS:
            case HISTORY:
                return new StaticContentScreen(item);
            default:
                return new MainScreen();
        }
    }
}
