DROP TABLE IF EXISTS lecture CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS student_group CASCADE;
DROP TABLE IF EXISTS study_type CASCADE;
DROP TABLE IF EXISTS speciality CASCADE;
DROP TABLE IF EXISTS discipline CASCADE;
DROP TABLE IF EXISTS faculty_department CASCADE;
DROP TABLE IF EXISTS faculty CASCADE;
DROP TABLE IF EXISTS educator CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS lecture_hall CASCADE;
DROP TABLE IF EXISTS campus CASCADE;

DROP SEQUENCE IF EXISTS global_sequence;
CREATE SEQUENCE global_sequence START WITH 100000;

CREATE TABLE campus
(
    id          INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    number      VARCHAR(8)   NOT NULL,
    address     VARCHAR(100) NOT NULL,
    description VARCHAR(1200)
);
CREATE UNIQUE INDEX campus_number_idx ON campus (number);

CREATE TABLE lecture_hall
(
    id          INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    number      VARCHAR(8)  NOT NULL,
    campus_id   INTEGER     NOT NULL,
    type        VARCHAR(20) NOT NULL,
    capacity    INTEGER     NOT NULL,
    description VARCHAR(1200),
    CONSTRAINT campus_id_idx UNIQUE (campus_id, number),
    FOREIGN KEY (campus_id) REFERENCES campus ON DELETE CASCADE
);

CREATE TABLE person
(
    id           INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    first_name   VARCHAR(25)  NOT NULL,
    last_name    VARCHAR(25)  NOT NULL,
    middle_name  VARCHAR(25)  NOT NULL,
    birth_date   DATE         NOT NULL,
    city         VARCHAR(25)  NOT NULL,
    address      VARCHAR(100) NOT NULL,
    passport     VARCHAR(20)  NOT NULL,
    mobile_phone VARCHAR(20)  NOT NULL,
    email        VARCHAR(40)  NOT NULL
);
CREATE UNIQUE INDEX person_passport_idx ON person (passport);

CREATE TABLE faculty
(
    id                INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name              VARCHAR(100) NOT NULL,
    dean_address      VARCHAR(100) NOT NULL,
    dean_email        VARCHAR(40)  NOT NULL,
    dean_phone_number VARCHAR(20)  NOT NULL,
    description       VARCHAR(1200),
    CONSTRAINT faculty_name_idx UNIQUE (name)
);

CREATE TABLE faculty_department
(
    id           INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    address      VARCHAR(100) NOT NULL,
    email        VARCHAR(40)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    description  VARCHAR(1200),
    faculty_id   INTEGER      NOT NULL,
    CONSTRAINT faculty_department_name_idx UNIQUE (name),
    FOREIGN KEY (faculty_id) REFERENCES faculty ON DELETE CASCADE
);

CREATE TABLE educator
(
    id                    INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    person_id             INTEGER     NOT NULL,
    position              VARCHAR(50) NOT NULL,
    lecture_hall_id       INTEGER,
    faculty_department_id INTEGER,
    academic_degree       VARCHAR(50) NOT NULL,
    phone_number          VARCHAR(20),
    CONSTRAINT educator_person_idx UNIQUE (person_id),
    FOREIGN KEY (person_id) REFERENCES person ON DELETE CASCADE,
    FOREIGN KEY (lecture_hall_id) REFERENCES lecture_hall ON DELETE SET NULL,
    FOREIGN KEY (faculty_department_id) REFERENCES faculty_department ON DELETE SET NULL
);

CREATE TABLE discipline
(
    id                    INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name                  VARCHAR(100) NOT NULL,
    short_name            VARCHAR(20)  NOT NULL,
    faculty_department_id INTEGER,
    CONSTRAINT discipline_name_idx UNIQUE (name),
    FOREIGN KEY (faculty_department_id) REFERENCES faculty_department ON DELETE SET NULL
);

CREATE TABLE speciality
(
    id         INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    code       VARCHAR(15)  NOT NULL,
    short_name VARCHAR(10)  NOT NULL,
    faculty_id INTEGER      NOT NULL,
    CONSTRAINT speciality_code_idx UNIQUE (code),
    FOREIGN KEY (faculty_id) REFERENCES faculty ON DELETE CASCADE
);

CREATE TABLE student_group
(
    id                     INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name                   VARCHAR(100) NOT NULL,
    speciality_id          INTEGER,
    study_type             VARCHAR(20)  NOT NULL,
    current_education_year INTEGER      NOT NULL,
    admission              DATE         NOT NULL,
    curator_id             INTEGER,
    CHECK (current_education_year BETWEEN 1 AND 8),
    FOREIGN KEY (speciality_id) REFERENCES speciality ON DELETE SET NULL,
    FOREIGN KEY (curator_id) REFERENCES educator ON DELETE SET NULL
);

CREATE TABLE student
(
    id                 INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    record_book_number VARCHAR(15) NOT NULL,
    person_id          INTEGER     NOT NULL,
    student_group_id   INTEGER,
    leader             BOOLEAN DEFAULT FALSE,
    CONSTRAINT student_person_idx UNIQUE (person_id),
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE,
    FOREIGN KEY (student_group_id) REFERENCES student_group (id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX student_record_book_number_idx ON student (record_book_number);

CREATE TABLE lecture
(
    id               INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    discipline_id    INTEGER     NOT NULL,
    lecture_type     VARCHAR(20) NOT NULL,
    lecture_hall_id  INTEGER,
    educator_id      INTEGER,
    student_group_id INTEGER     NOT NULL,
    date             DATE    DEFAULT NOW(),
    FOREIGN KEY (discipline_id) REFERENCES discipline ON DELETE CASCADE,
    FOREIGN KEY (lecture_hall_id) REFERENCES lecture_hall ON DELETE SET NULL,
    FOREIGN KEY (educator_id) REFERENCES educator ON DELETE SET NULL,
    FOREIGN KEY (student_group_id) REFERENCES student_group ON DELETE CASCADE
);

CREATE TABLE users
(
    id         INTEGER NOT NULL PRIMARY KEY,
    login      VARCHAR(100),
    password   VARCHAR(100),
    enabled    BOOLEAN   DEFAULT TRUE,
    registered TIMESTAMP DEFAULT NOW()
);
CREATE UNIQUE INDEX user_login_idx ON users (login);

CREATE TABLE user_roles
(
    id      INTEGER PRIMARY KEY DEFAULT NEXTVAL('global_sequence'),
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- 100000 -- 100001
INSERT INTO campus(name, number, address, description)
VALUES ('ФКП', '1', 'П.Бровки ул., 4', 'Факультет компьютерного проектирования'),
       ('ФКСиС', '4', 'Гикало ул., 9', 'Факультет компьютерных систем и сетей');

-- 100002 - 100009
INSERT INTO lecture_hall(number, campus_id, type, capacity, description)
VALUES ('101', 100000, 'LECTURE', 200, 'Описание 1'),
       ('102', 100000, 'LECTURE', 200, 'Описание 2'),
       ('103', 100000, 'LABORATORY', 40, 'Описание 3'),
       ('104', 100000, 'LABORATORY', 40, 'Описание 4'),
       ('201', 100001, 'LECTURE', 200, 'Описание 5'),
       ('202', 100001, 'LECTURE', 200, 'Описание 6'),
       ('203', 100001, 'LABORATORY', 40, 'Описание 7'),
       ('204', 100001, 'LABORATORY', 40, 'Описание 8');

-- 100010 -- 100109
INSERT INTO person(first_name, last_name, middle_name, birth_date, city, address, passport, mobile_phone, email)
VALUES ('Оливер', 'Желиба', 'Евгеньевич', '1996-04-23T06:30:45Z', 'Минск', 'Автозаводская ул. 42', 'KH3531356',
        '+375 29 116-66-76', 'zheliba@gmail.com'),
       ('Ярослав', 'Пономарёв', 'Данилович', '1993-08-16T20:56:30Z', 'Минск', 'Айвазовского ул. 61', 'MP8707734',
        '+375 25 465-34-69', 'ponomarev@protonmail.com'),
       ('Казбек', 'Архипов', 'Фёдорович', '1982-09-12T05:42:31Z', 'Минск', 'Герасименко ул. 37', 'HB6750870',
        '+375 29 642-26-98', 'arhipov@protonmail.com'),
       ('Устин', 'Батейко', 'Владимирович', '1996-11-21T14:20:08Z', 'Минск', 'Илимская ул. 57', 'MC0994913',
        '+375 29 673-31-86', 'bateyko@protonmail.com'),
       ('Филипп', 'Филиппов', 'Викторович', '1984-05-16T07:45:42Z', 'Минск', 'Нестерова ул. 76', 'KH2904557',
        '+375 29 296-25-27', 'filippov@yandex.ru'),
       ('Ярослав', 'Давыдов', 'Андреевич', '1988-02-07T13:34:38Z', 'Минск', 'Фучика ул. 14', 'BM6673737',
        '+375 29 227-16-65', 'davidov@protonmail.com'),
       ('Чарльз', 'Шубин', 'Петрович', '1991-04-07T09:49:36Z', 'Минск', 'Автодоровская ул. 94', 'AB6198888',
        '+375 25 674-94-29', 'shubin@yandex.ru'),
       ('Юрий', 'Корнейчук', 'Алексеевич', '1993-08-02T06:36:47Z', 'Минск', 'Азовская ул. 28', 'AB4936611',
        '+375 25 834-52-28', 'korneychuk@protonmail.com'),
       ('Аркадий', 'Сидоров', 'Леонидович', '1984-10-14T01:59:32Z', 'Минск', 'Алибегова ул. 75', 'MC1132938',
        '+375 29 727-94-44', 'sidorov@gmail.com'),
       ('Чарльз', 'Тимофеев', 'Виталиевич', '1984-08-12T00:29:41Z', 'Минск', 'Космонавтов ул. 27', 'BM1111309',
        '+375 29 242-76-73', 'timofeev@gmail.com'),
       ('Остап', 'Повалий', 'Данилович', '1988-11-16T19:30:16Z', 'Минск', 'Рафиева ул. 16', 'AB5400570',
        '+375 44 479-67-13', 'povaliy@protonmail.com'),
       ('Шарль', 'Дзюба', 'Богданович', '1987-09-16T11:44:01Z', 'Минск', 'Слободская ул. 24', 'KH7228101',
        '+375 29 393-42-33', 'dzjuba@protonmail.com'),
       ('Святослав', 'Гришин', 'Алексеевич', '1992-04-24T20:53:36Z', 'Минск', 'Любимова просп. просп. 52', 'KH8093426',
        '+375 44 166-65-87', 'grishin@protonmail.com'),
       ('Орест', 'Игнатов', 'Владимирович', '1988-10-03T11:47:36Z', 'Минск', 'Авакяна ул. 42', 'AB5971801',
        '+375 25 494-42-51', 'ignatov@protonmail.com'),
       ('Евдоким', 'Худобяк', 'Сергеевич', '1984-02-11T15:43:29Z', 'Минск', 'Артиллеристов ул. 19', 'BM6821845',
        '+375 25 419-49-28', 'hudobjak@protonmail.com'),
       ('Данила', 'Жданов', 'Максимович', '1996-04-22T11:10:03Z', 'Минск', 'Асаналиева ул. 80', 'HB6263775',
        '+375 44 893-97-64', 'zhdanov@gmail.com'),
       ('Эдуард', 'Навальный', 'Петрович', '1986-12-30T19:43:22Z', 'Минск', 'Аэродромная ул. 34', 'HB9045226',
        '+375 44 376-13-35', 'navalniy@yandex.ru'),
       ('Эдуард', 'Белов', 'Васильевич', '1981-05-10T10:10:25Z', 'Минск', 'Аэрофлотская ул. 50', 'BM6507058',
        '+375 25 352-53-28', 'belov@protonmail.com'),
       ('Зураб', 'Павленко', 'Петрович', '1988-06-13T20:53:21Z', 'Минск', 'Быховская ул. 72', 'BM9561769',
        '+375 29 835-59-59', 'pavlenko@yandex.ru'),
       ('Огюст', 'Кондратьев', 'Борисович', '1990-06-14T04:06:44Z', 'Минск', 'Воронянского ул. 87', 'MC3305044',
        '+375 29 461-22-85', 'kondratev@gmail.com'),
       ('Казбек', 'Милославский', 'Алексеевич', '1983-08-20T03:06:00Z', 'Минск', 'Володько ул. 82', 'BM0977378',
        '+375 25 612-73-76', 'miloslavskiy@yandex.ru'),
       ('Иван', 'Мазайло', 'Сергеевич', '1998-03-31T23:40:50Z', 'Минск', 'Жуковского ул. 75', 'AB5831490',
        '+375 29 576-86-34', 'mazaylo@gmail.com'),
       ('Чеслав', 'Горобчук', 'Андреевич', '1988-01-14T21:39:07Z', 'Минск', 'Чкалова ул. 3', 'BM5602120',
        '+375 29 597-24-66', 'gorobchuk@gmail.com'),
       ('Феликс', 'Петровский', 'Борисович', '1994-01-15T09:44:04Z', 'Минск', 'Авангардная ул. 97', 'MP0898257',
        '+375 25 772-91-97', 'petrovskiy@yandex.ru'),
       ('Макар', 'Анисимов', 'Викторович', '1989-02-03T20:23:49Z', 'Минск', 'Азгура ул. 39', 'MC2569041',
        '+375 44 164-28-61', 'anisimov@protonmail.com'),
       ('Вениамин', 'Ситников', 'Иванович', '1991-06-13T03:18:41Z', 'Минск', 'Академическая ул. 52', 'KH4018378',
        '+375 44 661-45-88', 'sitnikov@yandex.ru'),
       ('Йозеф', 'Герасимов', 'Виталиевич', '1998-10-22T10:41:36Z', 'Минск', 'Аллейная ул. 68', 'AB4473622',
        '+375 25 613-12-98', 'gerasimov@yandex.ru'),
       ('Назар', 'Стрелков', 'Данилович', '1988-09-18T04:49:29Z', 'Минск', 'Гуртьева ул. 87', 'BM2674650',
        '+375 25 523-96-99', 'strelkov@gmail.com'),
       ('Устин', 'Гелетей', 'Станиславович', '1980-01-14T21:42:48Z', 'Минск', 'Зеленая ул. 80', 'MC3021353',
        '+375 29 686-95-94', 'geletey@yandex.ru'),
       ('Харитон', 'Шумейко', 'Юхимович', '1983-01-09T00:18:39Z', 'Минск', '50 лет Победы ул. 33', 'HB3091634',
        '+375 44 554-56-53', 'shumeyko@gmail.com'),
       ('Трофим', 'Острожский', 'Петрович', '1985-01-05T05:16:57Z', 'Минск', 'Пономарева ул. 41', 'MP5348375',
        '+375 44 721-65-95', 'ostrozhskiy@protonmail.com'),
       ('Эдуард', 'Несвитайло', 'Юхимович', '1989-12-05T09:13:49Z', 'Минск', 'Севастопольская ул. 14', 'MC3986313',
        '+375 29 342-82-39', 'nesvitaylo@protonmail.com'),
       ('Гарри', 'Тарасюк', 'Иванович', '1982-12-12T18:41:35Z', 'Минск', 'Чернышевского ул. 58', 'AB2984856',
        '+375 29 597-15-81', 'tarasjuk@gmail.com'),
       ('Стефан', 'Мельников', 'Леонидович', '1990-03-27T18:04:37Z', 'Минск', 'Абрикосовая ул. 98', 'AB1286399',
        '+375 44 152-47-12', 'melnikov@protonmail.com'),
       ('Цефас', 'Коломоец', 'Анатолиевич', '1996-03-18T09:41:26Z', 'Минск', 'Аполинарьевская ул. 25', 'HB8218610',
        '+375 25 141-69-95', 'kolomoets@protonmail.com'),
       ('Бронислав', 'Самойлов', 'Станиславович', '1989-03-03T02:15:11Z', 'Минск', 'Богдана Хмельницкого ул. 84',
        'AB7334281', '+375 44 357-32-56', 'samoylov@yandex.ru'),
       ('Устин', 'Легойда', 'Михайлович', '1986-03-20T06:46:32Z', 'Минск', 'Богдановича ул. 11', 'HB1363352',
        '+375 25 186-94-34', 'legoyda@protonmail.com'),
       ('Шамиль', 'Гайчук', 'Александрович', '1985-04-24T11:04:55Z', 'Минск', 'Кольцова ул. 77', 'HB9740944',
        '+375 25 545-34-26', 'gaychuk@gmail.com'),
       ('Динар', 'Притула', 'Романович', '1982-03-11T18:44:39Z', 'Минск', 'Сурганова ул. 44', 'HB3813577',
        '+375 29 912-48-29', 'pritula@gmail.com'),
       ('Шарль', 'Шубин', 'Иванович', '1997-09-09T02:24:38Z', 'Минск', 'Автомобилистов ул. 49', 'BM7818608',
        '+375 25 265-72-76', 'shubin@yandex.ru'),
       ('Илларион', 'Городецкий', 'Львович', '1995-02-05T18:43:28Z', 'Минск', 'Азизова ул. 34', 'MP6910266',
        '+375 25 757-76-63', 'gorodetskiy@yandex.ru'),
       ('Лев', 'Кудряшов', 'Александрович', '1985-11-25T20:06:46Z', 'Минск', 'Земледельческая ул. 3', 'HB2315998',
        '+375 29 837-76-39', 'kudrjashov@protonmail.com'),
       ('Леон', 'Тимофеев', 'Валериевич', '1981-03-28T18:53:20Z', 'Минск', 'Авиации ул. 92', 'KH9639023',
        '+375 44 782-89-45', 'timofeev@yandex.ru'),
       ('Семён', 'Васильев', 'Богданович', '1997-11-11T08:19:23Z', 'Минск', 'Авроровская ул. 83', 'BM4173026',
        '+375 44 836-84-71', 'vasilev@gmail.com'),
       ('Казбек', 'Жданов', 'Данилович', '1998-02-09T17:07:56Z', 'Минск', 'Алтайская ул. 4', 'AB2306473',
        '+375 44 554-71-47', 'zhdanov@protonmail.com'),
       ('Йомер', 'Игнатов', 'Леонидович', '1994-12-19T11:01:28Z', 'Минск', 'Амбулаторная ул. 98', 'AB9951858',
        '+375 29 721-51-16', 'ignatov@protonmail.com'),
       ('Бронислав', 'Гребневский', 'Петрович', '1996-10-14T06:30:42Z', 'Минск', 'Амураторская ул. 29', 'AB5612195',
        '+375 29 752-19-66', 'grebnevskiy@gmail.com'),
       ('Савва', 'Фокин', 'Вадимович', '1997-06-30T17:06:38Z', 'Минск', 'Амурская ул. 65', 'HB3512853',
        '+375 25 337-38-18', 'fokin@gmail.com'),
       ('Чарльз', 'Родионов', 'Викторович', '1990-12-03T08:31:18Z', 'Минск', 'Ангарская ул. 71', 'AB6591938',
        '+375 25 742-27-77', 'rodionov@protonmail.com'),
       ('Осип', 'Калашников', 'Богданович', '1987-10-01T09:39:16Z', 'Минск', 'Ангарская 2-я ул. 64', 'MP7158133',
        '+375 25 534-21-75', 'kalashnikov@protonmail.com'),
       ('Бронислав', 'Трублаевский', 'Валериевич', '1986-07-20T04:46:13Z', 'Минск', 'Андреевская ул. 93', 'BM5472695',
        '+375 29 167-98-44', 'trublaevskiy@yandex.ru'),
       ('Антонин', 'Сыпченко', 'Юхимович', '1994-12-25T21:59:51Z', 'Минск', 'Аннаева ул. 69', 'KH3375253',
        '+375 25 568-56-36', 'sipchenko@yandex.ru'),
       ('Эрик', 'Гаврилов', 'Борисович', '1987-04-07T21:10:42Z', 'Минск', 'Антоновская ул. 51', 'KH4127243',
        '+375 44 986-21-49', 'gavrilov@protonmail.com'),
       ('Орландо', 'Ковалёв', 'Львович', '1988-06-02T01:10:02Z', 'Минск', 'Аранская ул. 82', 'MC0864838',
        '+375 29 261-75-93', 'kovalev@protonmail.com'),
       ('Лукиллиан', 'Милославский', 'Артёмович', '1988-01-31T07:11:14Z', 'Минск', 'Арктическая 1-я ул. 52',
        'KH7972297', '+375 29 218-74-28', 'miloslavskiy@yandex.ru'),
       ('Харитон', 'Маслов', 'Виталиевич', '1994-10-22T12:28:02Z', 'Минск', 'Арктическая 2-я ул. 4', 'AB8882883',
        '+375 44 235-81-85', 'maslov@yandex.ru'),
       ('Назар', 'Белов', 'Григорьевич', '1998-07-08T05:16:52Z', 'Минск', 'Артема ул. 77', 'MP3332808',
        '+375 29 845-46-82', 'belov@yandex.ru'),
       ('Харитон', 'Соловьёв', 'Алексеевич', '1982-03-26T03:11:21Z', 'Минск', 'Бабушкина ул. 2', 'KH8719244',
        '+375 44 193-21-83', 'solovev@gmail.com'),
       ('Семён', 'Рожков', 'Фёдорович', '1992-08-26T00:01:48Z', 'Минск', 'Багратиона ул. 27', 'BM6108563',
        '+375 29 692-49-87', 'rozhkov@gmail.com'),
       ('Харитон', 'Захаров', 'Борисович', '1994-10-01T05:50:58Z', 'Минск', 'Багряная ул. 51', 'KH8060865',
        '+375 29 764-89-74', 'zaharov@yandex.ru'),
       ('Глеб', 'Терентьев', 'Станиславович', '1983-12-17T14:09:42Z', 'Минск', 'Базисная 1-я ул. 84', 'AB8630716',
        '+375 44 161-78-81', 'terentev@yandex.ru'),
       ('Устин', 'Котовский', 'Романович', '1995-06-01T16:13:06Z', 'Минск', 'Базисная 2-я ул. 53', 'MC7238419',
        '+375 25 525-23-69', 'kotovskiy@yandex.ru'),
       ('Никита', 'Забужко', 'Юхимович', '1987-07-13T06:00:16Z', 'Минск', 'Байкальская ул. 39', 'HB8170918',
        '+375 25 641-81-49', 'zabuzhko@yandex.ru'),
       ('Йосеф', 'Емельянов', 'Юхимович', '1993-04-30T20:03:18Z', 'Минск', 'Бакинская ул. 94', 'HB6469404',
        '+375 29 388-61-26', 'emeljanov@yandex.ru'),
       ('Юрий', 'Гордеев', 'Евгеньевич', '1990-01-03T18:08:43Z', 'Минск', 'Балтийская ул. 45', 'MP5223287',
        '+375 25 377-69-82', 'gordeev@gmail.com'),
       ('Йошка', 'Трублаевский', 'Платонович', '1981-07-17T10:06:39Z', 'Минск', 'Барановщина ул. 42', 'MP1707209',
        '+375 44 432-19-33', 'trublaevskiy@protonmail.com'),
       ('Роман', 'Терентьев', 'Анатолиевич', '1981-12-06T21:15:33Z', 'Минск', 'Басиаловская ул. 86', 'AB8553809',
        '+375 25 956-71-57', 'terentev@gmail.com'),
       ('Тарас', 'Палий', 'Брониславович', '1982-04-04T16:44:38Z', 'Минск', 'Алеся Бачило ул. 71', 'BM9620542',
        '+375 44 698-96-79', 'paliy@gmail.com'),
       ('Устин', 'Токар', 'Валериевич', '1992-11-02T17:59:23Z', 'Минск', 'Беды ул. 80', 'HB2237573',
        '+375 25 155-66-93', 'tokar@gmail.com'),
       ('Устин', 'Селиверстов', 'Григорьевич', '1988-02-07T13:30:04Z', 'Минск', 'Белинского ул. 1', 'MC2303946',
        '+375 29 628-19-96', 'seliverstov@yandex.ru'),
       ('Максим', 'Повалий', 'Алексеевич', '1994-03-25T05:32:26Z', 'Минск', 'Бельского ул. 37', 'BM7170969',
        '+375 44 444-85-13', 'povaliy@protonmail.com'),
       ('Нестор', 'Спивак', 'Леонидович', '1989-08-19T13:25:36Z', 'Минск', 'Беломорская ул. 51', 'MC1633537',
        '+375 44 942-52-83', 'spivak@yandex.ru'),
       ('Карен', 'Мартынов', 'Леонидович', '1985-06-23T16:57:00Z', 'Минск', 'Белорусская ул. 36', 'KH6169015',
        '+375 29 398-28-84', 'martinov@gmail.com'),
       ('Зуфар', 'Пархоменко', 'Анатолиевич', '1989-11-20T00:13:00Z', 'Минск', 'Березинская ул. 84', 'MP2824027',
        '+375 25 489-74-12', 'parhomenko@yandex.ru'),
       ('Ярослав', 'Комаров', 'Григорьевич', '1988-05-21T09:59:50Z', 'Минск', 'Берестянская ул. 71', 'HB3096056',
        '+375 25 761-56-39', 'komarov@yandex.ru'),
       ('Борис', 'Русаков', 'Платонович', '1997-04-29T17:59:47Z', 'Минск', 'Берсона ул. 60', 'KH0434754',
        '+375 25 482-35-51', 'rusakov@protonmail.com'),
       ('Шамиль', 'Давыдов', 'Борисович', '1990-10-13T23:44:34Z', 'Минск', 'Бехтерева ул. 37', 'KH4747162',
        '+375 29 684-71-42', 'davidov@yandex.ru'),
       ('Стефан', 'Баранов', 'Михайлович', '1987-06-07T22:38:38Z', 'Минск', 'Берута ул. 31', 'MC6323348',
        '+375 44 397-21-42', 'baranov@gmail.com'),
       ('Эрик', 'Кулишенко', 'Артёмович', '1994-01-12T12:10:25Z', 'Минск', 'Белецкого ул. 100', 'AB1767239',
        '+375 29 974-22-38', 'kulishenko@protonmail.com'),
       ('Тарас', 'Кудрявцев', 'Фёдорович', '1995-01-22T12:17:01Z', 'Минск', 'Библиотечная ул. 94', 'MC6687141',
        '+375 29 439-31-18', 'kudrjavtsev@protonmail.com'),
       ('Игнат', 'Савин', 'Виталиевич', '1982-12-12T11:23:45Z', 'Минск', 'Бирюзова ул. 7', 'MP0675677',
        '+375 44 247-31-63', 'savin@yandex.ru'),
       ('Данила', 'Гурьев', 'Максимович', '1991-01-12T13:44:04Z', 'Минск', 'Бобруйская ул. 4', 'MP4166277',
        '+375 44 339-52-73', 'gurev@gmail.com'),
       ('Цезарь', 'Казаков', 'Борисович', '1991-08-08T20:31:26Z', 'Минск', 'Болотникова ул. 96', 'HB3419698',
        '+375 25 822-79-86', 'kazakov@gmail.com'),
       ('Илья', 'Красильников', 'Платонович', '1997-09-18T14:04:52Z', 'Минск', 'Бородинская ул. 65', 'MP8583761',
        '+375 44 798-38-65', 'krasilnikov@gmail.com'),
       ('Шерлок', 'Кабанов', 'Васильевич', '1989-06-07T14:09:53Z', 'Минск', 'Ботаническая ул. 19', 'MP5895844',
        '+375 29 269-35-25', 'kabanov@yandex.ru'),
       ('Феликс', 'Терентьев', 'Григорьевич', '1984-04-16T06:09:06Z', 'Минск', 'Болотная ул. 26', 'HB8520889',
        '+375 25 279-38-48', 'terentev@gmail.com'),
       ('Йомер', 'Самойлов', 'Алексеевич', '1998-12-27T08:54:04Z', 'Минск', 'Боровая ул. 87', 'AB1221444',
        '+375 44 159-36-34', 'samoylov@protonmail.com'),
       ('Савва', 'Горшков', 'Владимирович', '1986-06-23T13:52:17Z', 'Минск', 'Брагинская ул. 39', 'HB9103901',
        '+375 29 435-98-81', 'gorshkov@protonmail.com'),
       ('Тимофей', 'Зайцев', 'Борисович', '1997-04-04T08:14:43Z', 'Минск', 'Брестская ул. 78', 'MC0875709',
        '+375 29 172-65-47', 'zaytsev@protonmail.com'),
       ('Харитон', 'Лазарев', 'Борисович', '1991-09-26T12:06:57Z', 'Минск', 'Брилевская ул. 26', 'HB2711782',
        '+375 25 199-97-64', 'lazarev@yandex.ru'),
       ('Прохор', 'Шухевич', 'Львович', '1991-10-08T23:26:58Z', 'Минск', 'Петруся Бровки ул. 37', 'MC8644325',
        '+375 25 752-67-33', 'shuhevich@gmail.com'),
       ('Леонид', 'Тетерин', 'Андреевич', '1992-01-01T10:58:27Z', 'Минск', 'Брест-Литовская ул. 73', 'AB1024137',
        '+375 29 854-96-64', 'teterin@yandex.ru'),
       ('Огюст', 'Барановский', 'Анатолиевич', '1987-07-10T22:44:57Z', 'Минск', 'Буденного ул. 76', 'MP3987031',
        '+375 44 232-77-38', 'baranovskiy@gmail.com'),
       ('Роман', 'Миклашевский', 'Васильевич', '1994-09-10T09:53:29Z', 'Минск', 'Бумажкова ул. 94', 'MP1416647',
        '+375 44 838-95-12', 'miklashevskiy@gmail.com'),
       ('Дмитрий', 'Скоропадский', 'Артёмович', '1988-07-14T05:02:09Z', 'Минск', 'Бурдейного ул. 67', 'HB9764785',
        '+375 44 716-19-67', 'skoropadskiy@protonmail.com'),
       ('Феликс', 'Забужко', 'Евгеньевич', '1982-05-07T13:16:58Z', 'Минск', 'Буйло ул. 67', 'MP7848088',
        '+375 25 144-24-83', 'zabuzhko@yandex.ru'),
       ('Глеб', 'Игнатьев', 'Станиславович', '1996-12-26T23:03:27Z', 'Минск', 'Змитрока Бядули ул. 6', 'MP3057482',
        '+375 44 411-96-56', 'ignatev@yandex.ru'),
       ('Устин', 'Пасичник', 'Богданович', '1983-11-28T01:25:49Z', 'Минск', 'Вавилова ул. 9', 'MC5472710',
        '+375 44 555-93-96', 'pasichnik@yandex.ru'),
       ('Родион', 'Владимиров', 'Леонидович', '1980-03-26T03:01:53Z', 'Минск', 'Ванеева ул. 40', 'AB8733497',
        '+375 29 561-92-28', 'vladimirov@yandex.ru'),
       ('Лукьян', 'Орехов', 'Львович', '1990-03-28T10:08:56Z', 'Минск', 'Варвашени ул. 86', 'MC2191053',
        '+375 25 754-71-31', 'orehov@gmail.com');

-- 100110 - 100111
INSERT INTO faculty(name, dean_address, dean_email, dean_phone_number, description)
VALUES ('Факультет компьютерного проектирования', 'Адрес деканата ФКП...', 'some.fkp.email@gmail.com', '222-11-33',
        'Описание факультета во всей красе...'),
       ('Факультет компьютерных систем и сетей', 'Адрес деканата ФКСиС...', 'some.fksis.email@gmail.com', '333-11-22',
        'И здесь тоже описание факультета во всей красе');

-- 100112 - 100115
INSERT INTO faculty_department(name, address, email, phone_number, description, faculty_id)
VALUES ('Кафедра 1', 'Адрес расположения кафедры 1', 'kafedra1@gmail.com', '444-11-22', 'Lorem description... 1',
        100110),
       ('Кафедра 2', 'Адрес расположения кафедры 2', 'kafedra2@gmail.com', '444-22-33', 'Lorem description... 2',
        100110),
       ('Кафедра 3', 'Адрес расположения кафедры 3', 'kafedra3@gmail.com', '444-33-44', 'Lorem description... 3',
        100111),
       ('Кафедра 4', 'Адрес расположения кафедры 4', 'kafedra4@gmail.com', '444-44-55', 'Lorem description... 4',
        100111);

-- 100110 - 100117
INSERT INTO educator(person_id, position, faculty_department_id, lecture_hall_id, academic_degree, phone_number)
VALUES (100010, 'Ассистент', 100112, 100002, 'Бакалавр технических наук', '111-22-33'),
       (100011, 'Ассистент', 100112, 100003, 'Бакалавр технических наук', '112-22-33'),
       (100012, 'Преподаватель', 100113, 100004, 'Магистр технических наук', '113-22-33'),
       (100013, 'Преподаватель', 100113, 100005, 'Магистр технических наук', '114-22-33'),
       (100014, 'Старший преподаватель', 100114, 100006, 'Кандидат технических наук', '115-22-33'),
       (100015, 'Старший преподаватель', 100114, 100007, 'Кандидат технических наук', '116-22-33'),
       (100016, 'Профессор', 100115, 100008, 'Доктор технических наук', '117-22-33'),
       (100017, 'Профессор', 100115, 100009, 'Доктор технических наук', '118-22-33');

-- 100124 - 100139
INSERT INTO discipline(name, short_name, faculty_department_id)
VALUES ('Основы алгоритмизации и программирования', 'ОАиП', 100112),
       ('Конструирование программ и языки программирования', 'КПиЯП', 100112),
       ('Организация и функционирование ЭВМ', 'ОиФЭВМ', 100112),
       ('Системное программирование', 'СП', 100112),
       ('Высшая математика', 'Выш.Мат.', 100113),
       ('Дискретная математика', 'ДМ', 100113),
       ('Математическое моделирование', 'Мат.Мод.', 100113),
       ('Теория вероятностей и математическая статистика', 'ТВиМС', 100113),
       ('Английский язык (проф. лексика)', 'Англ. яз.', 100114),
       ('Основы социально-гуманитарных наук', 'ОСГН', 100114),
       ('Основы логики', 'Осн. логики', 100114),
       ('Основы охраны окружающей среды, и энергосбережение', 'ОООСиЭ', 100114),
       ('Современные платформы программирования', 'СПП', 100115),
       ('Автоматизация управленческой деятельности предприятия', 'АУДП', 100115),
       ('Надёжность программного обеспечения', 'НПО', 100115),
       ('Операционные системы', 'ОС', 100115);

-- 100140 - 100141
INSERT INTO speciality(name, code, short_name, faculty_id)
VALUES ('Программное обеспечение информационных технологий', '1-40 01 01', 'ПОИТ', 100110),
       ('Вычислительные машины, системы и сети', '1-40 02 01', 'ПОИТ', 100111);

-- 100142 - 100145
INSERT INTO student_group(name, speciality_id, study_type, current_education_year, admission, curator_id)
VALUES ('П-1234', 100140, 'FULL_TIME', 1, '2021-09-01', 100116),
       ('П-2234', 100140, 'FULL_TIME', 2, '2020-09-01', 100117),
       ('П-1334', 100141, 'PART_TIME', 1, '2021-09-01', 100118),
       ('П-2334', 100141, 'DISTANCE', 2, '2020-09-01', 100119);

-- 100146 - 100237
INSERT INTO student(record_book_number, person_id, student_group_id)
VALUES ('471260484', 100018, 100142),
       ('554115728', 100019, 100142),
       ('136139857', 100020, 100142),
       ('319303522', 100021, 100142),
       ('563911170', 100022, 100142),
       ('769015575', 100023, 100142),
       ('882708319', 100024, 100142),
       ('736615482', 100025, 100142),
       ('123180683', 100026, 100142),
       ('127952983', 100027, 100142),
       ('119097052', 100028, 100142),
       ('210823076', 100029, 100142),
       ('905150090', 100030, 100142),
       ('717530056', 100031, 100142),
       ('862425599', 100032, 100142),
       ('361009419', 100033, 100142),
       ('589825329', 100034, 100142),
       ('577149773', 100035, 100142),
       ('841078319', 100036, 100142),
       ('306273028', 100037, 100142),
       ('442493594', 100038, 100143),
       ('321322376', 100039, 100143),
       ('102839593', 100040, 100143),
       ('085784115', 100041, 100143),
       ('794279491', 100042, 100143),
       ('946735215', 100043, 100143),
       ('425456068', 100044, 100143),
       ('419857706', 100045, 100143),
       ('250808078', 100046, 100143),
       ('927505443', 100047, 100143),
       ('297667653', 100048, 100143),
       ('850736595', 100049, 100143),
       ('745697881', 100050, 100143),
       ('861766251', 100051, 100143),
       ('348712183', 100052, 100143),
       ('524229384', 100053, 100143),
       ('333277872', 100054, 100143),
       ('752399158', 100055, 100143),
       ('076384151', 100056, 100143),
       ('987591918', 100057, 100143),
       ('694970363', 100058, 100143),
       ('710454636', 100059, 100143),
       ('330449572', 100060, 100143),
       ('100666762', 100061, 100143),
       ('199990527', 100062, 100143),
       ('226844145', 100063, 100143),
       ('961286731', 100064, 100143),
       ('110938588', 100065, 100144),
       ('249992109', 100066, 100144),
       ('298061445', 100067, 100144),
       ('321963286', 100068, 100144),
       ('399739892', 100069, 100144),
       ('279418743', 100070, 100144),
       ('903579097', 100071, 100144),
       ('124810547', 100072, 100144),
       ('451332162', 100073, 100144),
       ('917719138', 100074, 100144),
       ('949982995', 100075, 100144),
       ('649397674', 100076, 100144),
       ('490735579', 100077, 100144),
       ('239812518', 100078, 100144),
       ('669879860', 100079, 100144),
       ('013551405', 100080, 100144),
       ('797162240', 100081, 100144),
       ('676870336', 100082, 100144),
       ('664348265', 100083, 100144),
       ('908289564', 100084, 100144),
       ('542670476', 100085, 100144),
       ('864340359', 100086, 100144),
       ('067763781', 100087, 100144),
       ('594231442', 100088, 100144),
       ('503921955', 100089, 100144),
       ('274382298', 100090, 100145),
       ('781379822', 100091, 100145),
       ('025831173', 100092, 100145),
       ('396006930', 100093, 100145),
       ('714832773', 100094, 100145),
       ('799230743', 100095, 100145),
       ('671636094', 100096, 100145),
       ('763391766', 100097, 100145),
       ('266856237', 100098, 100145),
       ('008145146', 100099, 100145),
       ('020389180', 100100, 100145),
       ('871418839', 100101, 100145),
       ('639754206', 100102, 100145),
       ('355817881', 100103, 100145),
       ('308449194', 100104, 100145),
       ('756367850', 100105, 100145),
       ('906586330', 100106, 100145),
       ('523385298', 100107, 100145),
       ('375053866', 100108, 100145),
       ('518012661', 100109, 100145);

INSERT INTO lecture(discipline_id, lecture_type, lecture_hall_id, educator_id, student_group_id, date)
VALUES (100124, 'LECTURE_TIME', 100002, 100116, 100142, '2022-01-25'),    -- 12
       (100125, 'LECTURE_TIME', 100003, 100117, 100143, '2022-01-25'),
       (100126, 'LECTURE_TIME', 100004, 100116, 100144, '2022-01-25'),
       (100127, 'LECTURE_TIME', 100005, 100117, 100145, '2022-01-25'),
       (100128, 'LECTURE_TIME', 100006, 100118, 100142, '2022-01-25'),    -- 13
       (100129, 'LECTURE_TIME', 100007, 100119, 100143, '2022-01-25'),
       (100130, 'LECTURE_TIME', 100008, 100118, 100144, '2022-01-25'),
       (100131, 'LECTURE_TIME', 100009, 100119, 100145, '2022-01-25'),
       (100132, 'LECTURE_TIME', 100002, 100120, 100142, '2022-01-25'),    -- 14
       (100133, 'LECTURE_TIME', 100003, 100121, 100143, '2022-01-25'),
       (100134, 'LECTURE_TIME', 100004, 100120, 100144, '2022-01-25'),
       (100135, 'LECTURE_TIME', 100005, 100121, 100145, '2022-01-25'),
       (100136, 'LECTURE_TIME', 100006, 100122, 100142, '2022-01-25'),    -- 15
       (100137, 'LECTURE_TIME', 100007, 100123, 100143, '2022-01-25'),
       (100138, 'LECTURE_TIME', 100008, 100122, 100144, '2022-01-25'),
       (100139, 'LECTURE_TIME', 100009, 100123, 100145, '2022-01-25'),
       (100124, 'LABORATORY_TIME', 100002, 100116, 100145, '2022-01-26'), -- 12
       (100125, 'LABORATORY_TIME', 100003, 100117, 100144, '2022-01-26'),
       (100126, 'LABORATORY_TIME', 100004, 100116, 100143, '2022-01-26'),
       (100127, 'LABORATORY_TIME', 100005, 100117, 100142, '2022-01-26'),
       (100128, 'LABORATORY_TIME', 100006, 100118, 100145, '2022-01-26'), -- 13
       (100129, 'LABORATORY_TIME', 100007, 100119, 100144, '2022-01-26'),
       (100130, 'LABORATORY_TIME', 100008, 100118, 100143, '2022-01-26'),
       (100131, 'LABORATORY_TIME', 100009, 100119, 100142, '2022-01-26'),
       (100132, 'LABORATORY_TIME', 100002, 100120, 100145, '2022-01-26'), -- 14
       (100133, 'LABORATORY_TIME', 100003, 100121, 100144, '2022-01-26'),
       (100134, 'LABORATORY_TIME', 100004, 100120, 100143, '2022-01-26'),
       (100135, 'LABORATORY_TIME', 100005, 100121, 100142, '2022-01-26'),
       (100136, 'LABORATORY_TIME', 100006, 100122, 100145, '2022-01-26'), -- 15
       (100137, 'LABORATORY_TIME', 100007, 100123, 100144, '2022-01-26'),
       (100138, 'LABORATORY_TIME', 100008, 100122, 100143, '2022-01-26'),
       (100139, 'LABORATORY_TIME', 100009, 100123, 100142, '2022-01-26'),
       (100124, 'CONSULTING_TIME', 100002, 100116, 100145, '2022-01-27'), -- 12
       (100125, 'TEST_TIME', 100003, 100117, 100144, '2022-01-27'),
       (100126, 'LECTURE_TIME', 100004, 100116, 100143, '2022-01-27'),
       (100127, 'CONSULTING_TIME', 100005, 100117, 100142, '2022-01-27'),
       (100128, 'TEST_TIME', 100006, 100118, 100145, '2022-01-27'),       -- 13
       (100129, 'EXAMINE_TIME', 100007, 100119, 100144, '2022-01-27'),
       (100130, 'CONSULTING_TIME', 100008, 100118, 100143, '2022-01-27'),
       (100131, 'TEST_TIME', 100009, 100119, 100142, '2022-01-27'),
       (100132, 'EXAMINE_TIME', 100002, 100120, 100145, '2022-01-27'),    -- 14
       (100133, 'CONSULTING_TIME', 100003, 100121, 100144, '2022-01-27'),
       (100134, 'TEST_TIME', 100004, 100120, 100143, '2022-01-27'),
       (100135, 'EXAMINE_TIME', 100005, 100121, 100142, '2022-01-27'),
       (100136, 'CONSULTING_TIME', 100006, 100122, 100145, '2022-01-27'), -- 15
       (100137, 'TEST_TIME', 100007, 100123, 100144, '2022-01-27'),
       (100138, 'EXAMINE_TIME', 100008, 100122, 100143, '2022-01-27'),
       (100139, 'CONSULTING_TIME', 100009, 100123, 100142, '2022-01-27');

INSERT INTO lecture(discipline_id, lecture_type, lecture_hall_id, educator_id, student_group_id)
VALUES (100124, 'LECTURE_TIME', 100002, 100116, 100142), -- 12
       (100125, 'TEST_TIME', 100003, 100117, 100143),
       (100126, 'CONSULTING_TIME', 100004, 100116, 100144),
       (100127, 'EXAMINE_TIME', 100005, 100117, 100145),
       (100128, 'LECTURE_TIME', 100006, 100118, 100142), -- 13
       (100129, 'TEST_TIME', 100007, 100119, 100143),
       (100130, 'CONSULTING_TIME', 100008, 100118, 100144),
       (100131, 'EXAMINE_TIME', 100009, 100119, 100145),
       (100132, 'LECTURE_TIME', 100002, 100120, 100142), -- 14
       (100133, 'TEST_TIME', 100003, 100121, 100143),
       (100134, 'CONSULTING_TIME', 100004, 100120, 100144),
       (100135, 'EXAMINE_TIME', 100005, 100121, 100145),
       (100136, 'LECTURE_TIME', 100006, 100122, 100142), -- 15
       (100137, 'TEST_TIME', 100007, 100123, 100143),
       (100138, 'CONSULTING_TIME', 100008, 100122, 100144),
       (100139, 'EXAMINE_TIME', 100009, 100123, 100145);

INSERT INTO users(id, login, password, enabled)
VALUES (100018, 'student', '$2a$10$Sz/ioVIcYZExKdrK/jWI.OlF2BY2Xqo4Ekr482IGciLPgZCZuxY0.', TRUE),
       (100011, 'dispatcher', '$2a$10$.w5FgtGrRZ1je9eQpgCe8OPNTNe1cL1X//u.rwQ5YWQOaCcHV3RWi', TRUE),
       (100013, 'admin', '$2a$10$AFacJmhrZtuei1S/UJNwmOBASNbeVpNLR/taEzhSqD..x/LDYwllq', TRUE);

INSERT INTO user_roles(user_id, role)
VALUES (100018, 'STUDENT'),
       (100011, 'DISPATCHER'),
       (100013, 'ADMIN')