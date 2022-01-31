package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.building.LectureHallType;
import by.kopyshev.university.dto.building.LectureHallDTO;

import static by.kopyshev.university.testdata.CampusConstants.*;

public class LectureHallConstants {
    public static final MatcherFactory.Matcher<LectureHallDTO> LECTURE_HALL_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(LectureHallDTO.class);

    public static final int HALL_1_ID = 100_002;
    public static final int HALL_2_ID = 100_003;
    public static final int HALL_3_ID = 100_004;
    public static final int HALL_4_ID = 100_005;
    public static final String HALL_1_NUMBER = "101";
    public static final String HALL_2_NUMBER = "102";
    public static final String HALL_3_NUMBER = "201";
    public static final String HALL_4_NUMBER = "202";
    public static final LectureHallType HALL_1_TYPE = LectureHallType.LECTURE;
    public static final LectureHallType HALL_2_TYPE = LectureHallType.LABORATORY;
    public static final LectureHallType HALL_3_TYPE = LectureHallType.LECTURE;
    public static final LectureHallType HALL_4_TYPE = LectureHallType.LABORATORY;
    public static final int HALL_1_CAPACITY = 200;
    public static final int HALL_2_CAPACITY = 40;
    public static final int HALL_3_CAPACITY = 200;
    public static final int HALL_4_CAPACITY = 40;
    public static final String HALL_1_DESCRIPTION = "Описание 1";
    public static final String HALL_2_DESCRIPTION = "Описание 2";
    public static final String HALL_3_DESCRIPTION = "Описание 3";
    public static final String HALL_4_DESCRIPTION = "Описание 4";

    public static final LectureHall hall1
            = new LectureHall(HALL_1_ID, HALL_1_NUMBER, campus1, HALL_1_TYPE, HALL_1_CAPACITY, HALL_1_DESCRIPTION);

    public static final LectureHall hall2
            = new LectureHall(HALL_2_ID, HALL_2_NUMBER, campus1, HALL_2_TYPE, HALL_2_CAPACITY, HALL_2_DESCRIPTION);

    public static final LectureHall hall3
            = new LectureHall(HALL_3_ID, HALL_3_NUMBER, campus2, HALL_3_TYPE, HALL_3_CAPACITY, HALL_3_DESCRIPTION);

    public static final LectureHall hall4
            = new LectureHall(HALL_4_ID, HALL_4_NUMBER, campus2, HALL_4_TYPE, HALL_4_CAPACITY, HALL_4_DESCRIPTION);

    public static final LectureHallDTO hallDTO1
            = new LectureHallDTO(HALL_1_ID, HALL_1_NUMBER, CAMPUS_1_ID, HALL_1_TYPE, HALL_1_CAPACITY, HALL_1_DESCRIPTION);

    public static final LectureHallDTO hallDTO2
            = new LectureHallDTO(HALL_2_ID, HALL_2_NUMBER, CAMPUS_1_ID, HALL_2_TYPE, HALL_2_CAPACITY, HALL_2_DESCRIPTION);

    public static final LectureHallDTO hallDTO3
            = new LectureHallDTO(HALL_3_ID, HALL_3_NUMBER, CAMPUS_2_ID, HALL_3_TYPE, HALL_3_CAPACITY, HALL_3_DESCRIPTION);

    public static final LectureHallDTO hallDTO4
            = new LectureHallDTO(HALL_4_ID, HALL_4_NUMBER, CAMPUS_2_ID, HALL_4_TYPE, HALL_4_CAPACITY, HALL_4_DESCRIPTION);

    public static LectureHallDTO getNew(Integer campusId) {
        return new LectureHallDTO(null, HALL_1_NUMBER, campusId,
                LectureHallType.LECTURE, 100, "New Description");
    }

    public static LectureHallDTO getUpdated(Integer id) {
        LectureHallDTO updated = new LectureHallDTO(hallDTO1);
        updated.setId(id);
        return updated;
    }
}
