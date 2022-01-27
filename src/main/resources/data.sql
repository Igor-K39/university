DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS lecture_type;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS student_group;
DROP TABLE IF EXISTS study_type;
DROP TABLE IF EXISTS specialization;
DROP TABLE IF EXISTS speciality;
DROP TABLE IF EXISTS discipline;
DROP TABLE IF EXISTS faculty_department;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS educator;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS lecture_hall;
DROP TABLE IF EXISTS campus;

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
    type        VARCHAR(80) NOT NULL,
    capacity    INTEGER     NOT NULL,
    description VARCHAR(1200),
    CONSTRAINT campus_id_idx UNIQUE (campus_id, number)
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

CREATE TABLE educator
(
    id              INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    person_id       INTEGER     NOT NULL,
    position        VARCHAR(50) NOT NULL,
    lecture_hall_id INTEGER,
    academic_degree VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(20),
    FOREIGN KEY (lecture_hall_id) REFERENCES lecture_hall ON DELETE SET NULL
);

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

CREATE TABLE discipline
(
    id                    INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name                  VARCHAR(100) NOT NULL,
    short_name            VARCHAR(20)  NOT NULL,
    faculty_department_id INTEGER,
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

CREATE TABLE specialization
(
    id            INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name          VARCHAR(80) NOT NULL,
    code          VARCHAR(15) NOT NULL,
    speciality_id INTEGER     NOT NULL,
    CONSTRAINT specialization_code_idx UNIQUE (code),
    FOREIGN KEY (speciality_id) REFERENCES speciality ON DELETE CASCADE
);

CREATE TABLE study_type
(
    id         INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name       VARCHAR(25) NOT NULL,
    short_name VARCHAR(8)  NOT NULL
);

CREATE TABLE student_group
(
    id                     INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    name                   VARCHAR(15) NOT NULL,
    speciality_id          INTEGER     NOT NULL,
    specialization_id      INTEGER,
    study_type_id          INTEGER,
    current_education_year INTEGER     NOT NULL,
    admission              DATE        NOT NULL,
    curator_id             INTEGER,
    CHECK (current_education_year BETWEEN 1 AND 8),
    FOREIGN KEY (study_type_id) REFERENCES study_type ON DELETE SET NULL,
    FOREIGN KEY (curator_id) REFERENCES educator ON DELETE SET NULL
);

CREATE TABLE student
(
    id                 INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    record_book_number VARCHAR(15) NOT NULL,
    person_id          INTEGER     NOT NULL,
    student_group_id   INTEGER     NOT NULL,
    leader             BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX student_record_book_number_idx ON student (record_book_number);

CREATE TABLE lecture_type
(
    id         INTEGER DEFAULT NEXTVAL('global_sequence'),
    name       VARCHAR(100) NOT NULL,
    short_name VARCHAR(8)   NOT NULL
);

CREATE TABLE lecture
(
    id               INTEGER DEFAULT NEXTVAL('global_sequence') PRIMARY KEY,
    discipline_id    INTEGER NOT NULL,
    lecture_type_id  INTEGER NOT NULL,
    lecture_hall_id  INTEGER NOT NULL,
    educator_id      INTEGER NOT NULL,
    student_group_id INTEGER NOT NULL,
    date             INTEGER NOT NULL,
    FOREIGN KEY (discipline_id) REFERENCES discipline ON DELETE CASCADE,
    FOREIGN KEY (lecture_hall_id) REFERENCES lecture_hall ON DELETE SET NULL,
    FOREIGN KEY (educator_id) REFERENCES educator ON DELETE SET NULL,
    FOREIGN KEY (student_group_id) REFERENCES student_group ON DELETE CASCADE
);