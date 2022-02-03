package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.domain.education.lecture.LectureType;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;

import java.time.LocalDate;

import static by.kopyshev.university.domain.education.lecture.LectureType.EXAMINE_TIME;
import static by.kopyshev.university.domain.education.lecture.LectureType.LECTURE_TIME;
import static by.kopyshev.university.testdata.CampusConstants.CAMPUS_1_NUMBER;
import static by.kopyshev.university.testdata.CampusConstants.CAMPUS_2_NUMBER;
import static by.kopyshev.university.testdata.DisciplineConstants.*;
import static by.kopyshev.university.testdata.EducatorConstants.*;
import static by.kopyshev.university.testdata.LectureHallConstants.*;
import static by.kopyshev.university.testdata.StudentGroupConstants.*;

public class LectureConstants {
    public static final MatcherFactory.Matcher<LectureDTO> LECTURE_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(LectureDTO.class);

    public static final int LECTURE_1_ID = 100046;
    public static final int LECTURE_2_ID = 100047;
    public static final int LECTURE_3_ID = 100048;
    public static final int LECTURE_4_ID = 100049;
    public static final int LECTURE_5_ID = 100050;
    public static final int LECTURE_6_ID = 100051;
    public static final int LECTURE_7_ID = 100052;
    public static final int LECTURE_8_ID = 100053;
    public static final LectureType LECTURE_1_TYPE = LECTURE_TIME;
    public static final LectureType LECTURE_2_TYPE = LECTURE_TIME;
    public static final LectureType LECTURE_3_TYPE = LECTURE_TIME;
    public static final LectureType LECTURE_4_TYPE = LECTURE_TIME;
    public static final LectureType LECTURE_5_TYPE = LectureType.LABORATORY_TIME;
    public static final LectureType LECTURE_6_TYPE = LectureType.TEST_TIME;
    public static final LectureType LECTURE_7_TYPE = LectureType.CONSULTING_TIME;
    public static final LectureType LECTURE_8_TYPE = LectureType.EXAMINE_TIME;
    public static final LocalDate LECTURE_1_DATE = LocalDate.parse("2022-01-25");
    public static final LocalDate LECTURE_2_DATE = LocalDate.parse("2022-01-25");
    public static final LocalDate LECTURE_3_DATE = LocalDate.parse("2022-01-25");
    public static final LocalDate LECTURE_4_DATE = LocalDate.parse("2022-01-25");
    public static final LocalDate LECTURE_5_DATE = LocalDate.now();
    public static final LocalDate LECTURE_6_DATE = LocalDate.now();
    public static final LocalDate LECTURE_7_DATE = LocalDate.now();
    public static final LocalDate LECTURE_8_DATE = LocalDate.now();

    public static final LectureDTO lecture1DTO = new LectureDTO(LECTURE_1_ID, discipline1DTO, LECTURE_1_TYPE,
        HALL_1_NUMBER, CAMPUS_1_NUMBER, educator1DTO.getPerson().getShortName(), STUDENT_GROUP_1_NAME, STUDENT_GROUP_1_ID,
        LECTURE_1_DATE);

    public static final LectureDTO lecture2DTO = new LectureDTO(LECTURE_2_ID, discipline2DTO, LECTURE_2_TYPE,
        HALL_2_NUMBER, CAMPUS_1_NUMBER, educator2DTO.getPerson().getShortName(), STUDENT_GROUP_2_NAME, STUDENT_GROUP_2_ID,
        LECTURE_2_DATE);

    public static final LectureDTO lecture3DTO = new LectureDTO(LECTURE_3_ID, discipline3DTO, LECTURE_3_TYPE,
        HALL_3_NUMBER, CAMPUS_2_NUMBER, educator3DTO.getPerson().getShortName(), STUDENT_GROUP_3_NAME, STUDENT_GROUP_3_ID,
        LECTURE_3_DATE);

    public static final LectureDTO lecture4DTO = new LectureDTO(LECTURE_4_ID, discipline4DTO, LECTURE_4_TYPE,
        HALL_4_NUMBER, CAMPUS_2_NUMBER, educator4DTO.getPerson().getShortName(), STUDENT_GROUP_4_NAME, STUDENT_GROUP_4_ID,
        LECTURE_4_DATE);

    public static final LectureDTO lecture5DTO = new LectureDTO(LECTURE_5_ID, discipline1DTO, LECTURE_5_TYPE,
        HALL_1_NUMBER, CAMPUS_1_NUMBER, educator1DTO.getPerson().getShortName(), STUDENT_GROUP_1_NAME, STUDENT_GROUP_1_ID,
        LECTURE_5_DATE);

    public static final LectureDTO lecture6DTO = new LectureDTO(LECTURE_6_ID, discipline2DTO, LECTURE_6_TYPE,
        HALL_2_NUMBER, CAMPUS_1_NUMBER, educator2DTO.getPerson().getShortName(), STUDENT_GROUP_2_NAME, STUDENT_GROUP_2_ID,
        LECTURE_6_DATE);

    public static final LectureDTO lecture7DTO = new LectureDTO(LECTURE_7_ID, discipline3DTO, LECTURE_7_TYPE,
        HALL_3_NUMBER, CAMPUS_2_NUMBER, educator3DTO.getPerson().getShortName(), STUDENT_GROUP_3_NAME, STUDENT_GROUP_3_ID,
        LECTURE_7_DATE);

    public static final LectureDTO lecture8DTO = new LectureDTO(LECTURE_8_ID, discipline4DTO, LECTURE_8_TYPE,
        HALL_4_NUMBER, CAMPUS_2_NUMBER, educator4DTO.getPerson().getShortName(), STUDENT_GROUP_4_NAME, STUDENT_GROUP_4_ID,
        LECTURE_8_DATE);

    public static LectureUpdateDTO getNew() {
        return new LectureUpdateDTO(null, DISCIPLINE_1_ID, LECTURE_TIME, HALL_1_ID, EDUCATOR_1_ID, STUDENT_GROUP_1_ID,
                LocalDate.now().plusDays(1));
    }

    public static LectureUpdateDTO getUpdated(int id) {
        return new LectureUpdateDTO(id, DISCIPLINE_2_ID, EXAMINE_TIME, HALL_2_ID, EDUCATOR_2_ID, STUDENT_GROUP_2_ID,
                LocalDate.now().plusDays(2));
    }
}
