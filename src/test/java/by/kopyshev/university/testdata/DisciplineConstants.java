package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.lecture.DisciplineDTO;

import static by.kopyshev.university.testdata.FacultyDepartmentConstants.*;

public class DisciplineConstants {
    public static final MatcherFactory.Matcher<DisciplineDTO> DISCIPLINE_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(DisciplineDTO.class);

    public static final int DISCIPLINE_1_ID = 100_020;
    public static final int DISCIPLINE_2_ID = 100_021;
    public static final int DISCIPLINE_3_ID = 100_022;
    public static final int DISCIPLINE_4_ID = 100_023;
    public static final String DISCIPLINE_1_NAME = "Основы алгоритмизации и программирования";
    public static final String DISCIPLINE_2_NAME = "Конструирование программ и языки программирования";
    public static final String DISCIPLINE_3_NAME = "Организация и функционирование ЭВМ";
    public static final String DISCIPLINE_4_NAME = "Системное программирование";
    public static final String DISCIPLINE_1_SHORT_NAME = "ОАиП";
    public static final String DISCIPLINE_2_SHORT_NAME = "КПиЯП";
    public static final String DISCIPLINE_3_SHORT_NAME = "ОиФЭВМ";
    public static final String DISCIPLINE_4_SHORT_NAME = "СП";

    public static final DisciplineDTO discipline1DTO = new DisciplineDTO(DISCIPLINE_1_ID, DISCIPLINE_1_NAME,
            DISCIPLINE_1_SHORT_NAME, FACULTY_DEPARTMENT_1_ID);

    public static final DisciplineDTO discipline2DTO = new DisciplineDTO(DISCIPLINE_2_ID, DISCIPLINE_2_NAME,
            DISCIPLINE_2_SHORT_NAME, FACULTY_DEPARTMENT_2_ID);

    public static final DisciplineDTO discipline3DTO = new DisciplineDTO(DISCIPLINE_3_ID, DISCIPLINE_3_NAME,
            DISCIPLINE_3_SHORT_NAME, FACULTY_DEPARTMENT_3_ID);

    public static final DisciplineDTO discipline4DTO = new DisciplineDTO(DISCIPLINE_4_ID, DISCIPLINE_4_NAME,
            DISCIPLINE_4_SHORT_NAME, FACULTY_DEPARTMENT_4_ID);

    public static DisciplineDTO getNew() {
        return new DisciplineDTO(null, "new discipline name", "NDN", FACULTY_DEPARTMENT_1_ID);
    }

    public static DisciplineDTO getUpdated(int id) {
        return new DisciplineDTO(id, "upd discipline name", "UDN", FACULTY_DEPARTMENT_1_ID);
    }
}
