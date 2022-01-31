package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.PersonDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonConstants {
    public static final MatcherFactory.Matcher<PersonDTO> PERSON_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(PersonDTO.class);

    // the power of automatic generation + excel tricks
    // As a result we can easily check and edit the data
    // PS: don't kill me pls for that, it's really comfortable
    public static final int EDUCATOR_PERSON_1_ID = 100_012;
    public static final int EDUCATOR_PERSON_2_ID = 100_013;
    public static final int EDUCATOR_PERSON_3_ID = 100_014;
    public static final int EDUCATOR_PERSON_4_ID = 100_015;

    public static final String EDUCATOR_PERSON_1_LAST_NAME = "Желиба";
    public static final String EDUCATOR_PERSON_2_LAST_NAME = "Пономарёв";
    public static final String EDUCATOR_PERSON_3_LAST_NAME = "Архипов";
    public static final String EDUCATOR_PERSON_4_LAST_NAME = "Батейко";

    public static final String EDUCATOR_PERSON_1_FIRST_NAME = "Оливер";
    public static final String EDUCATOR_PERSON_2_FIRST_NAME = "Ярослав";
    public static final String EDUCATOR_PERSON_3_FIRST_NAME = "Казбек";
    public static final String EDUCATOR_PERSON_4_FIRST_NAME = "Устин";

    public static final String EDUCATOR_PERSON_1_MIDDLE_NAME = "Евгеньевич";
    public static final String EDUCATOR_PERSON_2_MIDDLE_NAME = "Данилович";
    public static final String EDUCATOR_PERSON_3_MIDDLE_NAME = "Фёдорович";
    public static final String EDUCATOR_PERSON_4_MIDDLE_NAME = "Владимирович";

    public static final LocalDate EDUCATOR_PERSON_1_BIRTH_DATE = LocalDate.parse("1996-04-23");
    public static final LocalDate EDUCATOR_PERSON_2_BIRTH_DATE = LocalDate.parse("1993-08-16");
    public static final LocalDate EDUCATOR_PERSON_3_BIRTH_DATE = LocalDate.parse("1982-09-12");
    public static final LocalDate EDUCATOR_PERSON_4_BIRTH_DATE = LocalDate.parse("1996-11-21");

    public static final String EDUCATOR_PERSON_1_CITY = "Минск";
    public static final String EDUCATOR_PERSON_2_CITY = "Минск";
    public static final String EDUCATOR_PERSON_3_CITY = "Минск";
    public static final String EDUCATOR_PERSON_4_CITY = "Минск";

    public static final String EDUCATOR_PERSON_1_ADDRESS = "Автозаводская ул. 42";
    public static final String EDUCATOR_PERSON_2_ADDRESS = "Айвазовского ул. 61";
    public static final String EDUCATOR_PERSON_3_ADDRESS = "Герасименко ул. 37";
    public static final String EDUCATOR_PERSON_4_ADDRESS = "Илимская ул. 57";

    public static final String EDUCATOR_PERSON_1_PASSPORT = "KH3531356";
    public static final String EDUCATOR_PERSON_2_PASSPORT = "MP8707734";
    public static final String EDUCATOR_PERSON_3_PASSPORT = "HB6750870";
    public static final String EDUCATOR_PERSON_4_PASSPORT = "MC0994913";

    public static final String EDUCATOR_PERSON_1_MOBILE_PHONE = "+375 29 116-66-76";
    public static final String EDUCATOR_PERSON_2_MOBILE_PHONE = "+375 25 465-34-69";
    public static final String EDUCATOR_PERSON_3_MOBILE_PHONE = "+375 29 642-26-98";
    public static final String EDUCATOR_PERSON_4_MOBILE_PHONE = "+375 29 673-31-86";

    public static final String EDUCATOR_PERSON_1_EMAIL = "zheliba@gmail.com";
    public static final String EDUCATOR_PERSON_2_EMAIL = "ponomarev@protonmail.com";
    public static final String EDUCATOR_PERSON_3_EMAIL = "arhipov@protonmail.com";
    public static final String EDUCATOR_PERSON_4_EMAIL = "bateyko@protonmail.com";

    public static final PersonDTO educatorPerson1 = new PersonDTO(EDUCATOR_PERSON_1_ID, EDUCATOR_PERSON_1_FIRST_NAME,
            EDUCATOR_PERSON_1_LAST_NAME, EDUCATOR_PERSON_1_MIDDLE_NAME, EDUCATOR_PERSON_1_BIRTH_DATE, EDUCATOR_PERSON_1_CITY,
            EDUCATOR_PERSON_1_ADDRESS, EDUCATOR_PERSON_1_PASSPORT, EDUCATOR_PERSON_1_MOBILE_PHONE, EDUCATOR_PERSON_1_EMAIL);

    public static final PersonDTO educatorPerson2 = new PersonDTO(EDUCATOR_PERSON_2_ID, EDUCATOR_PERSON_2_FIRST_NAME,
            EDUCATOR_PERSON_2_LAST_NAME, EDUCATOR_PERSON_2_MIDDLE_NAME, EDUCATOR_PERSON_2_BIRTH_DATE, EDUCATOR_PERSON_2_CITY,
            EDUCATOR_PERSON_2_ADDRESS, EDUCATOR_PERSON_2_PASSPORT, EDUCATOR_PERSON_2_MOBILE_PHONE, EDUCATOR_PERSON_2_EMAIL);

    public static final PersonDTO educatorPerson3 = new PersonDTO(EDUCATOR_PERSON_3_ID, EDUCATOR_PERSON_3_FIRST_NAME,
            EDUCATOR_PERSON_3_LAST_NAME, EDUCATOR_PERSON_3_MIDDLE_NAME, EDUCATOR_PERSON_3_BIRTH_DATE, EDUCATOR_PERSON_3_CITY,
            EDUCATOR_PERSON_3_ADDRESS, EDUCATOR_PERSON_3_PASSPORT, EDUCATOR_PERSON_3_MOBILE_PHONE, EDUCATOR_PERSON_3_EMAIL);

    public static final PersonDTO educatorPerson4 = new PersonDTO(EDUCATOR_PERSON_4_ID, EDUCATOR_PERSON_4_FIRST_NAME,
            EDUCATOR_PERSON_4_LAST_NAME, EDUCATOR_PERSON_4_MIDDLE_NAME, EDUCATOR_PERSON_4_BIRTH_DATE, EDUCATOR_PERSON_4_CITY,
            EDUCATOR_PERSON_4_ADDRESS, EDUCATOR_PERSON_4_PASSPORT, EDUCATOR_PERSON_4_MOBILE_PHONE, EDUCATOR_PERSON_4_EMAIL);

    public static final int STUDENT_PERSON_1_ID = 100030;
    public static final int STUDENT_PERSON_2_ID = 100031;
    public static final int STUDENT_PERSON_3_ID = 100032;
    public static final int STUDENT_PERSON_4_ID = 100033;
    public static final int STUDENT_PERSON_5_ID = 100034;
    public static final int STUDENT_PERSON_6_ID = 100035;
    public static final int STUDENT_PERSON_7_ID = 100036;
    public static final int STUDENT_PERSON_8_ID = 100037;

    public static final String STUDENT_PERSON_1_LAST_NAME = "Филиппов";
    public static final String STUDENT_PERSON_2_LAST_NAME = "Давыдов";
    public static final String STUDENT_PERSON_3_LAST_NAME = "Шубин";
    public static final String STUDENT_PERSON_4_LAST_NAME = "Корнейчук";
    public static final String STUDENT_PERSON_5_LAST_NAME = "Сидоров";
    public static final String STUDENT_PERSON_6_LAST_NAME = "Тимофеев";
    public static final String STUDENT_PERSON_7_LAST_NAME = "Повалий";
    public static final String STUDENT_PERSON_8_LAST_NAME = "Дзюба";

    public static final String STUDENT_PERSON_1_FIRST_NAME = "Филипп";
    public static final String STUDENT_PERSON_2_FIRST_NAME = "Ярослав";
    public static final String STUDENT_PERSON_3_FIRST_NAME = "Чарльз";
    public static final String STUDENT_PERSON_4_FIRST_NAME = "Юрий";
    public static final String STUDENT_PERSON_5_FIRST_NAME = "Аркадий";
    public static final String STUDENT_PERSON_6_FIRST_NAME = "Чарльз";
    public static final String STUDENT_PERSON_7_FIRST_NAME = "Остап";
    public static final String STUDENT_PERSON_8_FIRST_NAME = "Шарль";

    public static final String STUDENT_PERSON_1_MIDDLE_NAME = "Викторович";
    public static final String STUDENT_PERSON_2_MIDDLE_NAME = "Андреевич";
    public static final String STUDENT_PERSON_3_MIDDLE_NAME = "Петрович";
    public static final String STUDENT_PERSON_4_MIDDLE_NAME = "Алексеевич";
    public static final String STUDENT_PERSON_5_MIDDLE_NAME = "Леонидович";
    public static final String STUDENT_PERSON_6_MIDDLE_NAME = "Виталиевич";
    public static final String STUDENT_PERSON_7_MIDDLE_NAME = "Данилович";
    public static final String STUDENT_PERSON_8_MIDDLE_NAME = "Богданович";

    public static final LocalDate STUDENT_PERSON_1_BIRTH_DATE = LocalDate.parse("1984-05-16");
    public static final LocalDate STUDENT_PERSON_2_BIRTH_DATE = LocalDate.parse("1988-02-07");
    public static final LocalDate STUDENT_PERSON_3_BIRTH_DATE = LocalDate.parse("1991-04-07");
    public static final LocalDate STUDENT_PERSON_4_BIRTH_DATE = LocalDate.parse("1993-08-02");
    public static final LocalDate STUDENT_PERSON_5_BIRTH_DATE = LocalDate.parse("1984-10-14");
    public static final LocalDate STUDENT_PERSON_6_BIRTH_DATE = LocalDate.parse("1984-08-12");
    public static final LocalDate STUDENT_PERSON_7_BIRTH_DATE = LocalDate.parse("1988-11-16");
    public static final LocalDate STUDENT_PERSON_8_BIRTH_DATE = LocalDate.parse("1987-09-16");

    public static final String STUDENT_PERSON_1_CITY = "Минск";
    public static final String STUDENT_PERSON_2_CITY = "Минск";
    public static final String STUDENT_PERSON_3_CITY = "Минск";
    public static final String STUDENT_PERSON_4_CITY = "Минск";
    public static final String STUDENT_PERSON_5_CITY = "Минск";
    public static final String STUDENT_PERSON_6_CITY = "Минск";
    public static final String STUDENT_PERSON_7_CITY = "Минск";
    public static final String STUDENT_PERSON_8_CITY = "Минск";

    public static final String STUDENT_PERSON_1_ADDRESS = "Нестерова ул. 76";
    public static final String STUDENT_PERSON_2_ADDRESS = "Фучика ул. 14";
    public static final String STUDENT_PERSON_3_ADDRESS = "Автодоровская ул. 94";
    public static final String STUDENT_PERSON_4_ADDRESS = "Азовская ул. 28";
    public static final String STUDENT_PERSON_5_ADDRESS = "Алибегова ул. 75";
    public static final String STUDENT_PERSON_6_ADDRESS = "Космонавтов ул. 27";
    public static final String STUDENT_PERSON_7_ADDRESS = "Рафиева ул. 16";
    public static final String STUDENT_PERSON_8_ADDRESS = "Слободская ул. 24";

    public static final String STUDENT_PERSON_1_PASSPORT = "KH2904557";
    public static final String STUDENT_PERSON_2_PASSPORT = "BM6673737";
    public static final String STUDENT_PERSON_3_PASSPORT = "AB6198888";
    public static final String STUDENT_PERSON_4_PASSPORT = "AB4936611";
    public static final String STUDENT_PERSON_5_PASSPORT = "MC1132938";
    public static final String STUDENT_PERSON_6_PASSPORT = "BM1111309";
    public static final String STUDENT_PERSON_7_PASSPORT = "AB5400570";
    public static final String STUDENT_PERSON_8_PASSPORT = "KH7228101";

    public static final String STUDENT_PERSON_1_MOBILE_PHONE = "+375 29 296-25-27";
    public static final String STUDENT_PERSON_2_MOBILE_PHONE = "+375 29 227-16-65";
    public static final String STUDENT_PERSON_3_MOBILE_PHONE = "+375 25 674-94-29";
    public static final String STUDENT_PERSON_4_MOBILE_PHONE = "+375 25 834-52-28";
    public static final String STUDENT_PERSON_5_MOBILE_PHONE = "+375 29 727-94-44";
    public static final String STUDENT_PERSON_6_MOBILE_PHONE = "+375 29 242-76-73";
    public static final String STUDENT_PERSON_7_MOBILE_PHONE = "+375 44 479-67-13";
    public static final String STUDENT_PERSON_8_MOBILE_PHONE = "+375 29 393-42-33";

    public static final String STUDENT_PERSON_1_EMAIL = "filippov@yandex.ru";
    public static final String STUDENT_PERSON_2_EMAIL = "davidov@protonmail.com";
    public static final String STUDENT_PERSON_3_EMAIL = "shubin@yandex.ru";
    public static final String STUDENT_PERSON_4_EMAIL = "korneychuk@protonmail.com";
    public static final String STUDENT_PERSON_5_EMAIL = "sidorov@gmail.com";
    public static final String STUDENT_PERSON_6_EMAIL = "timofeev@gmail.com";
    public static final String STUDENT_PERSON_7_EMAIL = "povaliy@protonmail.com";
    public static final String STUDENT_PERSON_8_EMAIL = "dzjuba@protonmail.com";

    public static final PersonDTO studentPerson1 = new PersonDTO(STUDENT_PERSON_1_ID, STUDENT_PERSON_1_FIRST_NAME,
            STUDENT_PERSON_1_LAST_NAME, STUDENT_PERSON_1_MIDDLE_NAME, STUDENT_PERSON_1_BIRTH_DATE, STUDENT_PERSON_1_CITY,
            STUDENT_PERSON_1_ADDRESS, STUDENT_PERSON_1_PASSPORT, STUDENT_PERSON_1_MOBILE_PHONE, STUDENT_PERSON_1_EMAIL);

    public static final PersonDTO studentPerson2 = new PersonDTO(STUDENT_PERSON_2_ID, STUDENT_PERSON_2_FIRST_NAME,
            STUDENT_PERSON_2_LAST_NAME, STUDENT_PERSON_2_MIDDLE_NAME, STUDENT_PERSON_2_BIRTH_DATE, STUDENT_PERSON_2_CITY,
            STUDENT_PERSON_2_ADDRESS, STUDENT_PERSON_2_PASSPORT, STUDENT_PERSON_2_MOBILE_PHONE, STUDENT_PERSON_2_EMAIL);

    public static final PersonDTO studentPerson3 = new PersonDTO(STUDENT_PERSON_3_ID, STUDENT_PERSON_3_FIRST_NAME,
            STUDENT_PERSON_3_LAST_NAME, STUDENT_PERSON_3_MIDDLE_NAME, STUDENT_PERSON_3_BIRTH_DATE, STUDENT_PERSON_3_CITY,
            STUDENT_PERSON_3_ADDRESS, STUDENT_PERSON_3_PASSPORT, STUDENT_PERSON_3_MOBILE_PHONE, STUDENT_PERSON_3_EMAIL);

    public static final PersonDTO studentPerson4 = new PersonDTO(STUDENT_PERSON_4_ID, STUDENT_PERSON_4_FIRST_NAME,
            STUDENT_PERSON_4_LAST_NAME, STUDENT_PERSON_4_MIDDLE_NAME, STUDENT_PERSON_4_BIRTH_DATE, STUDENT_PERSON_4_CITY,
            STUDENT_PERSON_4_ADDRESS, STUDENT_PERSON_4_PASSPORT, STUDENT_PERSON_4_MOBILE_PHONE, STUDENT_PERSON_4_EMAIL);

    public static final PersonDTO studentPerson5 = new PersonDTO(STUDENT_PERSON_5_ID, STUDENT_PERSON_5_FIRST_NAME,
            STUDENT_PERSON_5_LAST_NAME, STUDENT_PERSON_5_MIDDLE_NAME, STUDENT_PERSON_5_BIRTH_DATE, STUDENT_PERSON_5_CITY,
            STUDENT_PERSON_5_ADDRESS, STUDENT_PERSON_5_PASSPORT, STUDENT_PERSON_5_MOBILE_PHONE, STUDENT_PERSON_5_EMAIL);

    public static final PersonDTO studentPerson6 = new PersonDTO(STUDENT_PERSON_6_ID, STUDENT_PERSON_6_FIRST_NAME,
            STUDENT_PERSON_6_LAST_NAME, STUDENT_PERSON_6_MIDDLE_NAME, STUDENT_PERSON_6_BIRTH_DATE, STUDENT_PERSON_6_CITY,
            STUDENT_PERSON_6_ADDRESS, STUDENT_PERSON_6_PASSPORT, STUDENT_PERSON_6_MOBILE_PHONE, STUDENT_PERSON_6_EMAIL);

    public static final PersonDTO studentPerson7 = new PersonDTO(STUDENT_PERSON_7_ID, STUDENT_PERSON_7_FIRST_NAME,
            STUDENT_PERSON_7_LAST_NAME, STUDENT_PERSON_7_MIDDLE_NAME, STUDENT_PERSON_7_BIRTH_DATE, STUDENT_PERSON_7_CITY,
            STUDENT_PERSON_7_ADDRESS, STUDENT_PERSON_7_PASSPORT, STUDENT_PERSON_7_MOBILE_PHONE, STUDENT_PERSON_7_EMAIL);

    public static final PersonDTO studentPerson8 = new PersonDTO(STUDENT_PERSON_8_ID, STUDENT_PERSON_8_FIRST_NAME,
            STUDENT_PERSON_8_LAST_NAME, STUDENT_PERSON_8_MIDDLE_NAME, STUDENT_PERSON_8_BIRTH_DATE, STUDENT_PERSON_8_CITY,
            STUDENT_PERSON_8_ADDRESS, STUDENT_PERSON_8_PASSPORT, STUDENT_PERSON_8_MOBILE_PHONE, STUDENT_PERSON_8_EMAIL);

    public static final List<PersonDTO> allPersons = new ArrayList<>();
    static {
        allPersons.add(educatorPerson3);
        allPersons.add(educatorPerson4);
        allPersons.add(studentPerson2);
        allPersons.add(studentPerson8);
        allPersons.add(educatorPerson1);
        allPersons.add(studentPerson4);
        allPersons.add(studentPerson7);
        allPersons.add(educatorPerson2);
        allPersons.add(studentPerson5);
        allPersons.add(studentPerson6);
        allPersons.add(studentPerson1);
        allPersons.add(studentPerson3);
    }

    public static PersonDTO getNew() {
        return new PersonDTO(null, "New first name", "New last name", "New middle name",
                LocalDate.of(1990, 1, 1), "new city", "new address", "XX1234567",
                "+XXX XX XXX-XX-XX", "new.email@email.com");
    }

    public static PersonDTO getUpdated(int id) {
        return new PersonDTO(id, "Upd. first name", "Upd. last name", "Upd. middle name",
                LocalDate.of(1990, 1, 1), "upd. city", "upd. address",
                "YY2345678", "+YYY YY YYY-YY-YY", "upd.email@email.com");
    }
}

