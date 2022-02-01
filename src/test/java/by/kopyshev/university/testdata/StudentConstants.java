package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.student.StudentDTO;

import static by.kopyshev.university.testdata.PersonConstants.*;
import static by.kopyshev.university.testdata.StudentGroupConstants.*;

public class StudentConstants {

    public static final MatcherFactory.Matcher<StudentGroupDTO> STUDENT_GROUP_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(StudentGroupDTO.class);

    public static final int STUDENT_1_ID = 100_038;
    public static final int STUDENT_2_ID = 100_039;
    public static final int STUDENT_3_ID = 100_040;
    public static final int STUDENT_4_ID = 100_041;
    public static final int STUDENT_5_ID = 100_042;
    public static final int STUDENT_6_ID = 100_043;
    public static final int STUDENT_7_ID = 100_044;
    public static final int STUDENT_8_ID = 100_045;
    public static final String STUDENT_1_RECORD_BOOK = "471260484";
    public static final String STUDENT_2_RECORD_BOOK = "554115728";
    public static final String STUDENT_3_RECORD_BOOK = "136139857";
    public static final String STUDENT_4_RECORD_BOOK = "319303522";
    public static final String STUDENT_5_RECORD_BOOK = "563911170";
    public static final String STUDENT_6_RECORD_BOOK = "769015575";
    public static final String STUDENT_7_RECORD_BOOK = "882708319";
    public static final String STUDENT_8_RECORD_BOOK = "736615482";

    public static final StudentDTO student1DTO = new StudentDTO(STUDENT_1_ID, STUDENT_1_RECORD_BOOK, studentPerson1,
            STUDENT_GROUP_1_ID);
    public static final StudentDTO student2DTO = new StudentDTO(STUDENT_2_ID, STUDENT_2_RECORD_BOOK, studentPerson2,
            STUDENT_GROUP_1_ID);
    public static final StudentDTO student3DTO = new StudentDTO(STUDENT_3_ID, STUDENT_3_RECORD_BOOK, studentPerson3,
            STUDENT_GROUP_2_ID);
    public static final StudentDTO student4DTO = new StudentDTO(STUDENT_4_ID, STUDENT_4_RECORD_BOOK, studentPerson4,
            STUDENT_GROUP_2_ID);
    public static final StudentDTO student5DTO = new StudentDTO(STUDENT_5_ID, STUDENT_5_RECORD_BOOK, studentPerson5,
            STUDENT_GROUP_3_ID);
    public static final StudentDTO student6DTO = new StudentDTO(STUDENT_6_ID, STUDENT_6_RECORD_BOOK, studentPerson6,
            STUDENT_GROUP_3_ID);
    public static final StudentDTO student7DTO = new StudentDTO(STUDENT_7_ID, STUDENT_7_RECORD_BOOK, studentPerson7,
            STUDENT_GROUP_4_ID);
    public static final StudentDTO student8DTO = new StudentDTO(STUDENT_8_ID, STUDENT_8_RECORD_BOOK, studentPerson8,
            STUDENT_GROUP_4_ID);
}
