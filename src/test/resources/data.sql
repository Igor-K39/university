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
    id         INTEGER   DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    login      VARCHAR(100),
    password   VARCHAR(100),
    person_id  INTEGER NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE,
    registered TIMESTAMP DEFAULT NOW(),
    CONSTRAINT person_id_idx UNIQUE (person_id),
    FOREIGN KEY (person_id) REFERENCES person ON DELETE CASCADE
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

INSERT INTO campus(name, number, address, description)
VALUES ('ФКП', '1', 'П.Бровки ул., 4', 'Факультет компьютерного проектирования'),
       ('ФКСиС', '4', 'Гикало ул., 9', 'Факультет компьютерных систем и сетей');

INSERT INTO lecture_hall(number, campus_id, type, capacity, description)
VALUES ('101', 100000, 'LECTURE', 200, 'Описание 1'),
       ('102', 100000, 'LABORATORY', 40, 'Описание 2'),
       ('201', 100001, 'LECTURE', 200, 'Описание 3'),
       ('202', 100001, 'LABORATORY', 40, 'Описание 4');

INSERT INTO faculty(name, dean_address, dean_email, dean_phone_number, description)
VALUES ('Факультет компьютерного проектирования', 'Адрес деканата ФКП...', 'some.fkp.email@gmail.com', '222-11-33',
        'Описание факультета во всей красе...'),
       ('Факультет компьютерных систем и сетей', 'Адрес деканата ФКСиС...', 'some.fksis.email@gmail.com', '333-11-22',
        'И здесь тоже описание факультета во всей красе');

-- 100112 - 100115
INSERT INTO faculty_department(name, address, email, phone_number, description, faculty_id)
VALUES ('Кафедра 1', 'Адрес расположения кафедры 1', 'kafedra1@gmail.com', '444-11-22', 'Lorem description... 1',
        100006),
       ('Кафедра 2', 'Адрес расположения кафедры 2', 'kafedra2@gmail.com', '444-22-33', 'Lorem description... 2',
        100006),
       ('Кафедра 3', 'Адрес расположения кафедры 3', 'kafedra3@gmail.com', '444-33-44', 'Lorem description... 3',
        100007),
       ('Кафедра 4', 'Адрес расположения кафедры 4', 'kafedra4@gmail.com', '444-44-55', 'Lorem description... 4',
        100007);

INSERT INTO person(first_name, last_name, middle_name, birth_date, city, address, passport, mobile_phone, email)
VALUES ('Оливер', 'Желиба', 'Евгеньевич', '1996-04-23T06:30:45Z', 'Минск', 'Автозаводская ул. 42', 'KH3531356',
        '+375 29 116-66-76', 'zheliba@gmail.com'),
       ('Ярослав', 'Пономарёв', 'Данилович', '1993-08-16T20:56:30Z', 'Минск', 'Айвазовского ул. 61', 'MP8707734',
        '+375 25 465-34-69', 'ponomarev@protonmail.com'),
       ('Казбек', 'Архипов', 'Фёдорович', '1982-09-12T05:42:31Z', 'Минск', 'Герасименко ул. 37', 'HB6750870',
        '+375 29 642-26-98', 'arhipov@protonmail.com'),
       ('Устин', 'Батейко', 'Владимирович', '1996-11-21T14:20:08Z', 'Минск', 'Илимская ул. 57', 'MC0994913',
        '+375 29 673-31-86', 'bateyko@protonmail.com');

-- 100110 - 100117
INSERT INTO educator(person_id, position, faculty_department_id, lecture_hall_id, academic_degree, phone_number)
VALUES (100012, 'Ассистент', 100008, 100002, 'Бакалавр технических наук', '111-22-33'),
       (100013, 'Ассистент', 100009, 100003, 'Бакалавр технических наук', '112-22-33'),
       (100014, 'Преподаватель', 100010, 100004, 'Магистр технических наук', '113-22-33'),
       (100015, 'Преподаватель', 100011, 100005, 'Магистр технических наук', '114-22-33');

INSERT INTO discipline(name, short_name, faculty_department_id)
VALUES ('Основы алгоритмизации и программирования', 'ОАиП', 100008),
       ('Конструирование программ и языки программирования', 'КПиЯП', 100009),
       ('Организация и функционирование ЭВМ', 'ОиФЭВМ', 100010),
       ('Системное программирование', 'СП', 100011);

INSERT INTO speciality(name, code, short_name, faculty_id)
VALUES ('Программное обеспечение информационных технологий', '1-40 01 01', 'ПОИТ', 100006),
       ('Вычислительные машины, системы и сети', '1-40 02 01', 'ВМСиС', 100007);


INSERT INTO student_group(name, speciality_id, study_type, current_education_year, admission, curator_id)
VALUES ('П-1234', 100024, 'FULL_TIME', 1, '2021-09-01', 100016),
       ('П-2234', 100024, 'FULL_TIME', 2, '2020-09-01', 100017),
       ('П-1334', 100025, 'PART_TIME', 1, '2021-09-01', 100018),
       ('П-2334', 100025, 'DISTANCE', 2, '2020-09-01', 100019);

INSERT INTO person(first_name, last_name, middle_name, birth_date, city, address, passport, mobile_phone, email)
VALUES ('Филипп', 'Филиппов', 'Викторович', '1984-05-16T07:45:42Z', 'Минск', 'Нестерова ул. 76', 'KH2904557',
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
        '+375 29 393-42-33', 'dzjuba@protonmail.com');

INSERT INTO student(record_book_number, person_id, student_group_id)
VALUES ('471260484', 100030, 100026),
       ('554115728', 100031, 100026),
       ('136139857', 100032, 100027),
       ('319303522', 100033, 100027),
       ('563911170', 100034, 100028),
       ('769015575', 100035, 100028),
       ('882708319', 100036, 100029),
       ('736615482', 100037, 100029);

INSERT INTO lecture(discipline_id, lecture_type, lecture_hall_id, educator_id, student_group_id, date)
VALUES (100020, 'LECTURE_TIME', 100002, 100016, 100026, '2022-01-25'),    -- 12
       (100021, 'LECTURE_TIME', 100003, 100017, 100027, '2022-01-25'),
       (100022, 'LECTURE_TIME', 100004, 100018, 100028, '2022-01-25'),
       (100023, 'LECTURE_TIME', 100005, 100019, 100029, '2022-01-25');

INSERT INTO lecture(discipline_id, lecture_type, lecture_hall_id, educator_id, student_group_id)
VALUES (100020, 'LABORATORY_TIME', 100002, 100016, 100026), -- 12
       (100021, 'TEST_TIME', 100003, 100017, 100027),
       (100022, 'CONSULTING_TIME', 100004, 100018, 100028),
       (100023, 'EXAMINE_TIME', 100005, 100019, 100029);

INSERT INTO users(login, password, person_id, enabled)
VALUES ('student', '$2a$10$Sz/ioVIcYZExKdrK/jWI.OlF2BY2Xqo4Ekr482IGciLPgZCZuxY0.', 100030, TRUE),
       ('dispatcher', '$2a$10$.w5FgtGrRZ1je9eQpgCe8OPNTNe1cL1X//u.rwQ5YWQOaCcHV3RWi', 100012, TRUE),
       ('admin', '$2a$10$AFacJmhrZtuei1S/UJNwmOBASNbeVpNLR/taEzhSqD..x/LDYwllq', 100013, TRUE);

INSERT INTO user_roles(user_id, role)
VALUES (100054, 'STUDENT'),
       (100055, 'DISPATCHER'),
       (100056, 'ADMIN')