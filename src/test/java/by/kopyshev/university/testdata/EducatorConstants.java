package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;

import static by.kopyshev.university.testdata.FacultyDepartmentConstants.*;
import static by.kopyshev.university.testdata.LectureHallConstants.*;
import static by.kopyshev.university.testdata.PersonConstants.*;

public class EducatorConstants {
    public static final MatcherFactory.Matcher<EducatorDTO> EDUCATOR_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(EducatorDTO.class);

    public static final MatcherFactory.Matcher<EducatorPreviewDTO> EDUCATOR_PREVIEW_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(EducatorPreviewDTO.class);

    public static final int EDUCATOR_1_ID = 100_016;
    public static final int EDUCATOR_2_ID = 100_017;
    public static final int EDUCATOR_3_ID = 100_018;
    public static final int EDUCATOR_4_ID = 100_019;
    public static final String EDUCATOR_1_POSITION = "Ассистент";
    public static final String EDUCATOR_2_POSITION = "Ассистент";
    public static final String EDUCATOR_3_POSITION = "Преподаватель";
    public static final String EDUCATOR_4_POSITION = "Преподаватель";
    public static final String EDUCATOR_1_DEGREE = "Бакалавр технических наук";
    public static final String EDUCATOR_2_DEGREE = "Бакалавр технических наук";
    public static final String EDUCATOR_3_DEGREE = "Магистр технических наук";
    public static final String EDUCATOR_4_DEGREE = "Магистр технических наук";
    public static final String EDUCATOR_1_PHONE_NUMBER = "111-22-33";
    public static final String EDUCATOR_2_PHONE_NUMBER = "112-22-33";
    public static final String EDUCATOR_3_PHONE_NUMBER = "113-22-33";
    public static final String EDUCATOR_4_PHONE_NUMBER = "114-22-33";

    public static final EducatorDTO educator1DTO = new EducatorDTO(EDUCATOR_1_ID, educatorPerson1, EDUCATOR_1_POSITION,
            HALL_1_ID, FACULTY_DEPARTMENT_1_ID, EDUCATOR_1_DEGREE, EDUCATOR_1_PHONE_NUMBER);

    public static final EducatorDTO educator2DTO = new EducatorDTO(EDUCATOR_2_ID, educatorPerson2, EDUCATOR_2_POSITION,
            HALL_2_ID, FACULTY_DEPARTMENT_2_ID, EDUCATOR_2_DEGREE, EDUCATOR_2_PHONE_NUMBER);

    public static final EducatorDTO educator3DTO = new EducatorDTO(EDUCATOR_3_ID, educatorPerson3, EDUCATOR_3_POSITION,
            HALL_3_ID, FACULTY_DEPARTMENT_3_ID, EDUCATOR_3_DEGREE, EDUCATOR_3_PHONE_NUMBER);

    public static final EducatorDTO educator4DTO = new EducatorDTO(EDUCATOR_4_ID, educatorPerson4, EDUCATOR_4_POSITION,
            HALL_4_ID, FACULTY_DEPARTMENT_4_ID, EDUCATOR_4_DEGREE, EDUCATOR_4_PHONE_NUMBER);


    public static EducatorUpdateDTO getNew(int personId) {
        return new EducatorUpdateDTO(null, personId, "new position", HALL_1_ID, FACULTY_DEPARTMENT_1_ID,
                "new degree", "+XXX XX XXX-XX-XX");
    }

    public static EducatorUpdateDTO getUpdated(int id, int personId) {
        return new EducatorUpdateDTO(id, personId, "upd position", HALL_1_ID, FACULTY_DEPARTMENT_1_ID,
                "upd degree", "+YYY YY YYY-YY-YY");
    }
}
