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
    public static final String SS01 = "Айкидо";
    public static final String SS02 = "Академическая гребля";
    public static final String SS03 = "Атлетическая гимнастика\n(корпус № 12 «Черепаха»)";
    public static final String SS04 = "Аэробика";
    public static final String SS05 = "Бадминтон";
    public static final String SS06 = "Волейбол";
    public static final String SS07 = "Греко-римская борьба";
    public static final String SS08 = "Настольный теннис";
    public static final String SS09 = "Скалолазание";
    public static final String SS10 = "Атлетическая гимнастика\n(ул. К. Царёва, д. 12а)";
    public static final String SS11 = "Йога";
    public static final String SS12 = "Каратэ";
    public static final String SS13 = "Лыжные гонки";
    public static final String SS14 = "Мини-футбол (м)";
    public static final String SS15 = "Мини-футбол (ж)";
    public static final String SS16 = "Рукопашный бой";
    public static final String SS17 = "Теннис\n(ул. К. Царёва, д. 12а)";
    public static final String SS18 = "Тхэквондо";
    public static final String SS19 = "Фитнес";
    public static final String SS20 = "Футбол";
    public static final String SS21 = "Армрестлинг";
    public static final String SS22 = "Бокс";
    public static final String SS23 = "Вольная борьба";
    public static final String SS24 = "Дзюдо";
    public static final String SS25 = "Дуэльная стрельба";
    public static final String SS26 = "Йога\n(корпус № 3 МАИ)";
    public static final String SS27 = "Пулевая стрельба";
    public static final String SS28 = "Гиревой спорт\n(ул. Дубосековская, д. 5)";
    public static final String SS29 = "Пауэрлифтинг";
    public static final String SS30 = "Тяжёлая атлетика\n(ул. Дубосековская, д. 5)";
    public static final String SS31 = "Атлетическая гимнастика\n(Тушино)";
    public static final String SS32 = "Баскетбол";
    public static final String SS33 = "Гиревой спорт\n(«Тушино»)";
    public static final String SS34 = "Теннис\n(«Тушино»)";
    public static final String SS35 = "Спортивные танцы";
    public static final String SS36 = "Альпинизм";
    public static final String SS37 = "Спортивный туризм";
    public static final String SS38 = "Артистическое фехтование";
    public static final String SS39 = "Боевые искусства (капоэйра, окинава-каратэ, тайский бокс)";
    public static final String SS40 = "Гольф";
    public static final String SS41 = "Горный велосипед";
    public static final String SS42 = "Лёгкая атлетика";
    public static final String SS43 = "Парусный спорт";
    public static final String SS44 = "Плавание";
    public static final String SS45 = "Подводное плавание";
    public static final String SS46 = "Регби";
    public static final String SS47 = "Сноуборд, горные лыжи";
    public static final String SS48 = "Спортивное ориентирование";
    public static final String SS49 = "Шахматы";
    public static final String POD1 = "Подготовительные курсы";
    public static final String POD2 = "Школа-экстернат при МАИ";
    public static final String POD3 = "Физико-математическая школа";
    public static final String POD4 = "Центр тестирования и подготовки «ЕГЭ-МАИ»";
    public static final String POD5 = "Базовые школы";
    public static final String COU1 = "Курсы ФДП";
    public static final String COU2 = "МАИНО";
    public static final String COU3 = "ИНПО";
    public static final String COU4 = "ЭНТОС";
    public static final String COU5 = "Абитуриент";
    public static final String SS1 = "Центр технологической поддержки образования МАИ";
    public static final String SS2 = "Центр авиамодельного творчества учащихся";
    public static final String A1 = "Дни открытых дверей";
    public static final String A2 = "День науки в МАИ";
    public static final String A3 = "Университетские субботы";
    public static final String DOSUG1 = "Коллективы ДКиТ МАИ";
    public static final String DOSUG2 = "Базы отдыха";

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
            case PODGOTOVKA + DELIM + POD1:
            case PODGOTOVKA + DELIM + POD5:
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
            case DOSUG + DELIM + DOSUG2:
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
            case SPORT_SECTIONS + DELIM + SS01:
            case SPORT_SECTIONS + DELIM + SS02:
            case SPORT_SECTIONS + DELIM + SS03:
            case SPORT_SECTIONS + DELIM + SS04:
            case SPORT_SECTIONS + DELIM + SS05:
            case SPORT_SECTIONS + DELIM + SS06:
            case SPORT_SECTIONS + DELIM + SS07:
            case SPORT_SECTIONS + DELIM + SS08:
            case SPORT_SECTIONS + DELIM + SS09:
            case SPORT_SECTIONS + DELIM + SS10:
            case SPORT_SECTIONS + DELIM + SS11:
            case SPORT_SECTIONS + DELIM + SS12:
            case SPORT_SECTIONS + DELIM + SS13:
            case SPORT_SECTIONS + DELIM + SS14:
            case SPORT_SECTIONS + DELIM + SS15:
            case SPORT_SECTIONS + DELIM + SS16:
            case SPORT_SECTIONS + DELIM + SS17:
            case SPORT_SECTIONS + DELIM + SS18:
            case SPORT_SECTIONS + DELIM + SS19:
            case SPORT_SECTIONS + DELIM + SS20:
            case SPORT_SECTIONS + DELIM + SS21:
            case SPORT_SECTIONS + DELIM + SS22:
            case SPORT_SECTIONS + DELIM + SS23:
            case SPORT_SECTIONS + DELIM + SS24:
            case SPORT_SECTIONS + DELIM + SS25:
            case SPORT_SECTIONS + DELIM + SS26:
            case SPORT_SECTIONS + DELIM + SS27:
            case SPORT_SECTIONS + DELIM + SS28:
            case SPORT_SECTIONS + DELIM + SS29:
            case SPORT_SECTIONS + DELIM + SS30:
            case SPORT_SECTIONS + DELIM + SS31:
            case SPORT_SECTIONS + DELIM + SS32:
            case SPORT_SECTIONS + DELIM + SS33:
            case SPORT_SECTIONS + DELIM + SS34:
            case SPORT_SECTIONS + DELIM + SS35:
            case SPORT_SECTIONS + DELIM + SS36:
            case SPORT_SECTIONS + DELIM + SS37:
            case SPORT_SECTIONS + DELIM + SS38:
            case SPORT_SECTIONS + DELIM + SS39:
            case SPORT_SECTIONS + DELIM + SS40:
            case SPORT_SECTIONS + DELIM + SS41:
            case SPORT_SECTIONS + DELIM + SS42:
            case SPORT_SECTIONS + DELIM + SS43:
            case SPORT_SECTIONS + DELIM + SS44:
            case SPORT_SECTIONS + DELIM + SS45:
            case SPORT_SECTIONS + DELIM + SS46:
            case SPORT_SECTIONS + DELIM + SS47:
            case SPORT_SECTIONS + DELIM + SS48:
            case SPORT_SECTIONS + DELIM + SS49:
            case PODGOTOVKA + DELIM + POD2:
            case PODGOTOVKA + DELIM + POD3:
            case PODGOTOVKA + DELIM + POD4:
            case PODGOTOVKA + DELIM + POD1 + DELIM + COU1:
            case PODGOTOVKA + DELIM + POD1 + DELIM + COU2:
            case PODGOTOVKA + DELIM + POD1 + DELIM + COU3:
            case PODGOTOVKA + DELIM + POD1 + DELIM + COU4:
            case PODGOTOVKA + DELIM + POD1 + DELIM + COU5:
            case SCHOOL_CENTERS + DELIM + SS1:
            case SCHOOL_CENTERS + DELIM + SS2:
            case SCHOOL_ACTIVITY + DELIM + A1:
            case SCHOOL_ACTIVITY + DELIM + A2:
            case SCHOOL_ACTIVITY + DELIM + A3:
            case DOSUG + DELIM + DOSUG1:
            case DOSUG + DELIM + DOSUG2 + DELIM + REC1:
            case DOSUG + DELIM + DOSUG2 + DELIM + REC2:
                return new StaticContentScreen(item);
            default:
                return new MainScreen();
        }
    }
}
