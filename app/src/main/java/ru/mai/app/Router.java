package ru.mai.app;


import flow.path.Path;
import ru.mai.app.presentation.screens.ListContentScreen;
import ru.mai.app.presentation.screens.MainScreen;
import ru.mai.app.presentation.screens.MapScreen;
import ru.mai.app.presentation.screens.MediaScreen;
import ru.mai.app.presentation.screens.NewsContentScreen;
import ru.mai.app.presentation.screens.PhotoScreen;
import ru.mai.app.presentation.screens.PresentationScreen;
import ru.mai.app.presentation.screens.StaticContentScreen;
import ru.mai.app.presentation.screens.StaticListContentScreen;

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
    public static final String WAY1 = "Факультет №1 — \"Авиационная техника\"";
    public static final String WAY2 = "Факультет №2 — \"Двигатели летательных аппаратов\"";
    public static final String WAY3 = "Факультет №3 — \"Системы управления, информатика и электроэнергетика";
    public static final String WAY4 = "Факультет №4 — \"Радиоэлектроника летательных аппаратов\"";
    public static final String WAY5 = "Инженерно-экономический институт МАИ (ИНЖЭКИН МАИ)";
    public static final String WAY6 = "Факультет №6 — \"Аэрокосмический\"";
    public static final String WAY7 = "Факультет №7 — \"Робототехнические и интеллектуальные системы\"";
    public static final String WAY8 = "Факультет №8 — \"Прикладная математика и физика\"";
    public static final String WAY9 = "Факультет №9 — \"Прикладная механика\"";
    public static final String WAY10 = "Факультет №10 — \"Социальный инжиниринг\"";
    public static final String WAY11 = "Факультет \"РадиоВТУЗ МАИ\"";
    public static final String WAY12 = "Факультет Иностранных языков";
    public static final String WAY14 = "Филиал \"Восход\" МАИ в г. Байконур";
    public static final String WAY13 = "Филиал \"Взлёт\" МАИ в г. Ахтубинске";
    public static final String WAY15 = "Филиал \"Стрела\" МАИ в г. Жуковском";
    public static final String WAY16 = "Филиал \"Ракетно-космическая техника\" в г. Химки";
    public static final String OCH = "Очная";
    public static final String ZAOCH = "Заочная";
    public static final String OZ = "Очно-Заочная";
    public static final String WW1 = "Баллистика и гидроаэродинамика";
    public static final String WW2 = "Авиастроение";
    public static final String WW3 = "Управление качеством";
    public static final String WW4 = "Сервис";
    public static final String WW5 = "Системный анализ и управление";
    public static final String WW6 = "Самолето- и вертолетостроение";
    public static final String WW7 = "Авиастроение";
    public static final String WW8 = "Баллистика и гидроаэродинамика";
    public static final String WW9 = "Специальные организационно-технические системы";
    public static final String WW10 = "Сервис";
    public static final String WW11 = "Авиастроение";
    public static final String WW12 = "Самолето- и вертолетостроение";
    public static final String WW13 = "Авиастроение";
    public static final String WW14 = "Двигатели летательных аппаратов";
    public static final String WW15 = "Проектирование авиационных и ракетных двигателей";
    public static final String WW16 = "Стандартизация и метрология";
    public static final String WW17 = "Двигатели летательных аппаратов";
    public static final String WW18 = "Техносферная безопасность";
    public static final String WW19 = "Двигатели летательных аппаратов";
    public static final String WW20 = "Информационные системы и технологии";
    public static final String WW21 = "Прикладаная информатика";
    public static final String WW22 = "Программная инженерия";
    public static final String WW23 = "Биотехнические системы и технологии";
    public static final String WW24 = "Электроэнергетика и электротехника";
    public static final String WW25 = "Системы управления движением и навигация";
    public static final String WW26 = "Управление в технических системах";
    public static final String WW27 = "Интегрированные системы летательных аппаратов";
    public static final String WW28 = "Системы управления летательными аппаратами";
    public static final String WW29 = "Информатика и вычислительная техника";
    public static final String WW30 = "Программная инженерия";
    public static final String WW31 = "Авиастроение";
    public static final String WW32 = "управление в технических системах";
    public static final String WW33 = "Информатика и вычислительная техника";
    public static final String WW34 = "Прикладная информатика";
    public static final String WW35 = "Информационная безопасность";
    public static final String WW36 = "Радиотехника";
    public static final String WW37 = "Инфокоммуникационные технологии и системы связи";
    public static final String WW38= "Конструирование и технология электронных средств";
    public static final String WW39 = "Безопасность информационных технологий в правоохранительной сфере";
    public static final String WW40 = "Радиоэлектронные системы и комплексы";
    public static final String WW41 = "Радиотехника";
    public static final String WW42 = "Инфокоммуникационные технологии и системы связи";
    public static final String WW43 = "Радиотехника";
    public static final String WW44 = "Управление персоналом";
    public static final String WW45 = "Менеджмент";
    public static final String WW46 = "Информатика и вычислительная техника";
    public static final String WW47 = "Прикладная информатика";
    public static final String WW48 = "Экология и природопользование";
    public static final String WW49 = "Экономика";
    public static final String WW50 = "Проектирование, производство и эксплуатация ракет и ракетно-космических комплексов";
    public static final String WW51 = "Менеджмент";
    public static final String WW52 = "Информатика и вычислительная техника";
    public static final String WW53 = "Экономика";
    public static final String WW54 = "Прикладная информатика";
    public static final String WW55 = "Менеджмент";
    public static final String WW56 = "Управление персоналом";
    public static final String WW57 = "Менеджмент";
    public static final String WW58 = "Экономика";
    public static final String WW59 = "Прикладная информатика";
    public static final String WW60 = "Менеджмент";
    public static final String WW61 = "Информатика и вычислительная техника";
    public static final String WW62 = "Менеджмент";
    public static final String WW63 = "Баллистика и гидроаэродинамика";
    public static final String WW64 = "Ракетные комплексы и космонавтика";
    public static final String WW65 = "Информатика и вычислительная техника";
    public static final String WW66 = "Специальные организационно-технические системы";
    public static final String WW67 = "Испытание летательных аппаратов";
    public static final String WW68 = "Проектирование, производство и эксплуатация ракет и ракетно-космических комплексов";
    public static final String WW69 = "Системный анализ и управление";
    public static final String WW70 = "Баллистика и гидроаэродинамика";
    public static final String WW71 = "Ракетные комплексы, и космонавтика";
    public static final String WW72 = "Биотехнические системы и технологии";
    public static final String WW73 = "Информатика и вычислительная техника";
    public static final String WW74 = "Экология и природопользование";
    public static final String WW75 = "Интегрированные системы летательных аппаратов";
    public static final String WW76 = "Системы управления летательными аппаратами";
    public static final String WW77 = "Интегрированные системы летательных аппаратов";
    public static final String WW78 = "Прикладная математика и информатика";
    public static final String WW79 = "Прикладная математика";
    public static final String WW80 = "Прикладная математика";
    public static final String WW81 = "Прикладная математика";
    public static final String WW82 = "Авиастроение";
    public static final String WW83 = "Проектирование, производство и эксплуатация ракет и ракетно-космических комплексов";
    public static final String WW84 = "Авиастроение";
    public static final String WW85 = "Прикладная механика";
    public static final String WW86 = "Биотехнические системы и технологии";
    public static final String WW87 = "Прикладная информатика";
    public static final String WW88 = "Прикладная информатика";
    public static final String WW89 = "Менеджмент";
    public static final String WW90 = "Менеджмент";
    public static final String WW91 = "Информатика и вычислительная техника";
    public static final String WW92 = "Радиоэлектронные системы и комплексы";
    public static final String WW93 = "Информатика и вычислительная техника";
    public static final String WW94 = "Конструирование";
    public static final String WW95 = "Конструирование и технология электронных средств";
    public static final String WW96 = "Радиотехника";
    public static final String WW97 = "Лингвистика";
    public static final String WW98 = "Реклама и связи с общественностью";
    public static final String WW99 = "Авиастроение";
    public static final String WW100 = "Реклама и связи с общественностью";
    public static final String WW101 = "Лингвистика";
    public static final String WW102 = "Авиастроение";
    public static final String WW103 = "Реклама и связи с общественностью";
    public static final String WW104 = "Менеджмент";
    public static final String WW105 = "Радиоэлектронные системы и комплексы";
    public static final String WW106 = "Испытание летательных аппаратов";
    public static final String WW107 = "Менеджмент";
    public static final String WW108 = "Радиоэлектронные системы и комплексы";
    public static final String WW109 = "Менеджмент";
    public static final String WW110 = "Испытание летательных аппаратов";
    public static final String WW111 = "Прикладная математика";
    public static final String WW112 = "Информатика и вычислительная техника";
    public static final String WW113 = "Менеджмент";
    public static final String WW114 = "Испытание летательных аппаратов";
    public static final String WW115 = "Информатика и вычислительная техника";
    public static final String WW116 = "Менеджмент";
    public static final String WW117 = "Информатика и вычислительная техника";
    public static final String WW118 = "Баллистика и гидроаэродинамика";
    public static final String WW119 = "Менеджмент";
    public static final String WW120 = "Радиоэлектронные системы и комплексы";
    public static final String WW121 = "Испытание летательных аппаратов";
    public static final String WW122 = "Системы управления летательными аппаратами";
    public static final String WW123 = "Самолёто- и вертолётостроение";
    public static final String WW124 = "Менеджмент";
    public static final String WW125 = "Испытание летательных аппаратов";
    public static final String WW126 = "Специальные машины и устройства";
    public static final String WW127 = "Автоматические системы управления";
    public static final String WW128 = "Компьютерные системы и комплексы";
    public static final String WW129 = "Программирование в компьютерных системах";
    public static final String WW130 = "Экономика и бухгалтерский учёт";
    public static final String VIDEO = "Видео";
    public static final String PHOTO = "Фото";
    public static final String PRESENTATIONS = "Презентации";


    public Path getScreen(String item) {
        if (item.contains(WEEK) || item.equals(MAIN)) return new MainScreen();
        if (item.equals(MAP)) return new MapScreen();
        if (item.length() - item.replace(DELIM, "").length() == 3) return new StaticContentScreen(item);
        if (item.startsWith(NEWS + DELIM)) return new NewsContentScreen(item);
        if (item.equals(PHOTO)) return new PhotoScreen(item);
        if (item.equals(PRESENTATIONS)) return new PresentationScreen();
        switch (item) {
            case FACULTIES:
            case SCHOLARSHIPS:
            case LIBRARIES:
            case CANTEENS:
            case COURSES:
            case ACADEMIC_MOBILITY:
            case SPORT_SECTIONS:
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
            case WAYS + DELIM + WAY1 + DELIM + OCH:
            case WAYS + DELIM + WAY1 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY1 + DELIM + OZ:
            case WAYS + DELIM + WAY2 + DELIM + OCH:
            case WAYS + DELIM + WAY2 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY2 + DELIM + OZ:
            case WAYS + DELIM + WAY3 + DELIM + OCH:
            case WAYS + DELIM + WAY3 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY3 + DELIM + OZ:
            case WAYS + DELIM + WAY4 + DELIM + OCH:
            case WAYS + DELIM + WAY4 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY4 + DELIM + OZ:
            case WAYS + DELIM + WAY5 + DELIM + OCH:
            case WAYS + DELIM + WAY5 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY5 + DELIM + OZ:
            case WAYS + DELIM + WAY6 + DELIM + OCH:
            case WAYS + DELIM + WAY6 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY6 + DELIM + OZ:
            case WAYS + DELIM + WAY7 + DELIM + OCH:
            case WAYS + DELIM + WAY7 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY7 + DELIM + OZ:
            case WAYS + DELIM + WAY8 + DELIM + OCH:
            case WAYS + DELIM + WAY8 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY8 + DELIM + OZ:
            case WAYS + DELIM + WAY9 + DELIM + OCH:
            case WAYS + DELIM + WAY9 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY9 + DELIM + OZ:
            case WAYS + DELIM + WAY10 + DELIM + OCH:
            case WAYS + DELIM + WAY10 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY10 + DELIM + OZ:
            case WAYS + DELIM + WAY11 + DELIM + OCH:
            case WAYS + DELIM + WAY11 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY11 + DELIM + OZ:
            case WAYS + DELIM + WAY12 + DELIM + OCH:
            case WAYS + DELIM + WAY12 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY12 + DELIM + OZ:
            case WAYS + DELIM + WAY13 + DELIM + OCH:
            case WAYS + DELIM + WAY13 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY13 + DELIM + OZ:
            case WAYS + DELIM + WAY14 + DELIM + OCH:
            case WAYS + DELIM + WAY14 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY14 + DELIM + OZ:
            case WAYS + DELIM + WAY15 + DELIM + OCH:
            case WAYS + DELIM + WAY15 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY15 + DELIM + OZ:
            case WAYS + DELIM + WAY16 + DELIM + OCH:
            case WAYS + DELIM + WAY16 + DELIM + ZAOCH:
            case WAYS + DELIM + WAY16 + DELIM + OZ:
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
            case MEDIA:
                return new MediaScreen(item);
            default:
                return new MainScreen();
        }
    }

    public static CharSequence[] getDialogItems(String text) {
        CharSequence[] all = {OCH, ZAOCH, OZ};
        CharSequence[] ochOz = {OCH, OZ};
        CharSequence[] ochZaoch = {OCH, ZAOCH};
        CharSequence[] och = {OCH};
        switch (text) {
            case WAY2:
            case WAY4:
            case WAY7:
            case WAY8:
            case WAY9:
            case WAY14:
                return ochOz;
            case WAY3:
            case WAY11:
                return ochZaoch;
            case WAY6:
            case WAY10:
            case WAY16:
                return och;
            default:
                return all;
        }
    }
}
