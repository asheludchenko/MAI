package oleg.osipenko.mai;

import android.util.Log;

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
    public static final String STUDGORODOK = "Студгородок";
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
    public static final String WAYS = "Направления подготовки";
    public static final String DORMITORIES = "Общежития";
    public static final String ALUMNI = "Выпускники";
    public static final String PODGOTOVKA = "Подготовка к поступлению";
    public static final String DOCS = "Документы для поступления";
    public static final String NABOR = "Целевой набор";
    public static final String SCHOOL_CENTERS = "Центры обучения школьников";
    public static final String SCHOOL_ACTIVITY = "Мероприятия для школьников";
    public static final String DOSUG = "Досуг";
    public static final String CONTACTS = "Контакты";
    public static final String PRIEM = "Приёмная комиссия";
    public static final String DELIM = "#";
    public static final String F1 = "№1 \"Авиационная техника\"";
    public static final String F2 = "№2 \"Двигатели летательных аппаратов\"";
    public static final String F3 = "№3 \"Системы управления, информатика и электроэнергетика\"";
    public static final String F4 = "№4 \"Радиоэлектроника летательных аппаратов\"";
    public static final String F6 = "№6 \"Аэрокосмический\"";
    public static final String F5 = "Инженерно-экономический институт МАИ (ИНЖЭКИН МАИ)";
    public static final String F7 = "№7 \"Робототехнические и интеллектуальные системы\"";
    public static final String F8 = "№8 \"Прикладная математика и физика\"";
    public static final String F9 = "№9 \"Прикладная механика\"";
    public static final String F10 = "№10 \"Социальный инжиниринг\"";
    public static final String RADIOVTUZ = "\"РадиоВТУЗ МАИ\"";
    public static final String INLANG = "Иностранных языков";
    public static final String BAYC = "\"Восход\" МАИ в г. Байконур";
    public static final String AHTUBA = "\"Взлёт\" МАИ в г. Ахтубинске";
    public static final String STRELA = "\"Стрела\" МАИ в г. Жуковском";
    public static final String KHIMKI = "\"Ракетно-космическая техника\" в г. Химки";
    public static final String MILIT_INST = "Военный институт МАИ";
    public static final String SESSION_ZACHET = "Зачётная сессия";
    public static final String SESSION_EXAM = "Экзаменационная сессия";
    public static final String SESSION_OTCH = "Отчисление студентов";
    public static final String S1 = "Государственная академическая стипендия";
    public static final String S2 = "Повышенная государственная академическая стипендия";
    public static final String S3 = "Повышенная стипендия Учёного Совета МАИ";
    public static final String S4 = "Дополнительная повышенная государственная академическая стипендия";
    public static final String S5 = "Государственная стипендия для иностранных студентов, обучающихся по очной форме обучения за счет средств федерального бюджета (по направлениям Минобрнауки России)";
    public static final String S6 = "Государственная социальная стипендия";
    public static final String S7 = "Дополнительная повышенная государственная социальная стипендия нуждающимся студентам 1-го и 2-го курсов";
    public static final String S8 = "Дополнительная стипендия студентам, обучающимся по программам военной подготовки в учебном военном центре при МАИ";
    public static final String S9 = "Государственная стипендия для слушателей и студентов из числа граждан, проходивших военную службу";
    public static final String S10 = "Именные стипендии";
    public static final String MILIT_01 = "Учебный военный центр";
    public static final String MILIT_02 = "Военная кафедра";
    public static final String SE01 = "«Авиационная техника»";
    public static final String SE02 = "Инженерно-экономический институт МАИ";
    public static final String SE03 = "«Прикладная механика»";
    public static final String SE04 = "«Иностранных языков»";
    public static final String MAG01 = "«Авиационная техника»";
    public static final String MAG02 = "«Двигатели летательных аппаратов»";
    public static final String MAG03 = "«Системы управления, информатика и электроэнергетика»";
    public static final String MAG04 = "«Инженерно-экономический институт МАИ»";
    public static final String MAG05 = "«Аэрокосмический»";
    public static final String MAG06 = "«Прикладная математика и физика»";
    public static final String MAG07 = "«Прикладная механика»";
    public static final String MAG08 = "«Социальный инжиниринг»";
    public static final String MAG09 = "«Иностранных языков»";
    public static final String ACAD01 = "Глобальное образование";
    public static final String ACAD02 = "Программы обмена";
    public static final String DK01 = "Коллективы";
    public static final String DK02 = "Мероприятия";
    public static final String DOS01 = "Авиамодельный клуб";
    public static final String DOS02 = "Аэродром МАИ";
    public static final String DOS03 = "Авиа-технический клуб";
    public static final String DOS04 = "Дельтапланерный клуб";
    public static final String DOS05 = "Морской клуб (клуб аквалангистов)";
    public static final String DOS06 = "Стрелковый клуб";
    public static final String LIF01 = "Песни";
    public static final String LIF02 = "Словарь";
    public static final String LIF03 = "Тосты";
    public static final String HELP1 = "Копировальные центры";
    public static final String HELP2 = "Расписание";
    public static final String HELP3 = "Европейское приложение к диплому";
    public static final String REC1 = "ОУЦ МАИ Алушта";
    public static final String REC2 = "ОУЦ Ярополец";

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
            case WAYS:
            case SCHOOL_ACTIVITY:
            case DOSUG:
            case SCHOOL_CENTERS:
                return new ListContentScreen(item);
            case SESSION:
            case MILITARY_INSTITUTE:
            case SECONDARY_EDUCATION:
            case MAGISTRACY:
            case RECREATION_CENTERS:
            case DK:
            case DOSAAF:
            case PODGOTOVKA:
            case PRIEM:
                return new StaticListContentScreen(item);
            case PRACTICS:
            case DOTATIONS:
            case EMPLOYMENT_CENTER:
            case SCIENCE:
            case STUDGORODOK:
            case SANATORIUM:
            case DEBATING_CLUB:
            case MAISKY_VZLET:
            case PROFKOM:
            case SOMOL:
            case PRESS:
            case HISTORY:
            case DORMITORIES:
            case ALUMNI:
            case DOCS:
            case NABOR:
            case CONTACTS:
            case FACULTIES + DELIM + F1:
            case FACULTIES + DELIM + F2:
            case FACULTIES + DELIM + F3:
            case FACULTIES + DELIM + F4:
            case FACULTIES + DELIM + F5:
            case FACULTIES + DELIM + F6:
            case FACULTIES + DELIM + F7:
            case FACULTIES + DELIM + F8:
            case FACULTIES + DELIM + F9:
            case FACULTIES + DELIM + F10:
            case FACULTIES + DELIM + RADIOVTUZ:
            case FACULTIES + DELIM + INLANG:
            case FACULTIES + DELIM + MILIT_INST:
            case FACULTIES + DELIM + BAYC:
            case FACULTIES + DELIM + AHTUBA:
            case FACULTIES + DELIM + STRELA:
            case FACULTIES + DELIM + KHIMKI:
            case SESSION + DELIM + SESSION_ZACHET:
            case SESSION + DELIM + SESSION_EXAM:
            case SESSION + DELIM + SESSION_OTCH:
            case SCHOLARSHIPS + DELIM + S1:
            case SCHOLARSHIPS + DELIM + S2:
            case SCHOLARSHIPS + DELIM + S3:
            case SCHOLARSHIPS + DELIM + S4:
            case SCHOLARSHIPS + DELIM + S5:
            case SCHOLARSHIPS + DELIM + S6:
            case SCHOLARSHIPS + DELIM + S7:
            case SCHOLARSHIPS + DELIM + S8:
            case SCHOLARSHIPS + DELIM + S9:
            case SCHOLARSHIPS + DELIM + S10:
            case MILITARY_INSTITUTE + DELIM + MILIT_01:
            case MILITARY_INSTITUTE + DELIM + MILIT_02:
            case SECONDARY_EDUCATION + DELIM + SE01:
            case SECONDARY_EDUCATION + DELIM + SE02:
            case SECONDARY_EDUCATION + DELIM + SE03:
            case SECONDARY_EDUCATION + DELIM + SE04:
            case MAGISTRACY + DELIM + MAG01:
            case MAGISTRACY + DELIM + MAG02:
            case MAGISTRACY + DELIM + MAG03:
            case MAGISTRACY + DELIM + MAG04:
            case MAGISTRACY + DELIM + MAG05:
            case MAGISTRACY + DELIM + MAG06:
            case MAGISTRACY + DELIM + MAG07:
            case MAGISTRACY + DELIM + MAG08:
            case MAGISTRACY + DELIM + MAG09:
            case ACADEMIC_MOBILITY + DELIM + ACAD01:
            case ACADEMIC_MOBILITY + DELIM + ACAD02:
            case DK + DELIM + DK01:
            case DK + DELIM + DK02:
            case DOSAAF + DELIM + DOS01:
            case DOSAAF + DELIM + DOS02:
            case DOSAAF + DELIM + DOS03:
            case DOSAAF + DELIM + DOS04:
            case DOSAAF + DELIM + DOS05:
            case DOSAAF + DELIM + DOS06:
            case LIFE + DELIM + LIF01:
            case LIFE + DELIM + LIF02:
            case LIFE + DELIM + LIF03:
            case HELP + DELIM + HELP1:
            case HELP + DELIM + HELP2:
            case HELP + DELIM + HELP3:
            case RECREATION_CENTERS + DELIM + REC1:
            case RECREATION_CENTERS + DELIM + REC2:
                return new StaticContentScreen(item);
            default:
                return new MainScreen();
        }
    }
}
