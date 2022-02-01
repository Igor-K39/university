package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.domain.education.StudyType;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;

import java.time.LocalDate;
import java.util.List;

import static by.kopyshev.university.testdata.EducatorConstants.*;
import static by.kopyshev.university.testdata.SpecialityConstants.*;
import static by.kopyshev.university.testdata.StudentConstants.*;

public class StudentGroupConstants {
    public static final MatcherFactory.Matcher<StudentGroupDTO> STUDENT_GROUP_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(StudentGroupDTO.class);

    public static final MatcherFactory.Matcher<StudentGroupWithStudentsDTO> GROUP_WITH_STUDENTS_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(StudentGroupWithStudentsDTO.class);

    public static final int STUDENT_GROUP_1_ID = 100_026;
    public static final int STUDENT_GROUP_2_ID = 100_027;
    public static final int STUDENT_GROUP_3_ID = 100_028;
    public static final int STUDENT_GROUP_4_ID = 100_029;
    public static final String STUDENT_GROUP_1_NAME = "П-1234";
    public static final String STUDENT_GROUP_2_NAME = "П-2234";
    public static final String STUDENT_GROUP_3_NAME = "П-1334";
    public static final String STUDENT_GROUP_4_NAME = "П-2334";
    public static final StudyType STUDENT_GROUP_1_STUDY_TYPE = StudyType.FULL_TIME;
    public static final StudyType STUDENT_GROUP_2_STUDY_TYPE = StudyType.FULL_TIME;
    public static final StudyType STUDENT_GROUP_3_STUDY_TYPE = StudyType.PART_TIME;
    public static final StudyType STUDENT_GROUP_4_STUDY_TYPE = StudyType.DISTANCE;
    public static final int STUDENT_GROUP_1_CURRENT_EDUCATION_YEAR = 1;
    public static final int STUDENT_GROUP_2_CURRENT_EDUCATION_YEAR = 2;
    public static final int STUDENT_GROUP_3_CURRENT_EDUCATION_YEAR = 1;
    public static final int STUDENT_GROUP_4_CURRENT_EDUCATION_YEAR = 2;
    public static final LocalDate STUDENT_GROUP_1_ADMISSION = LocalDate.parse("2021-09-01");
    public static final LocalDate STUDENT_GROUP_2_ADMISSION = LocalDate.parse("2020-09-01");
    public static final LocalDate STUDENT_GROUP_3_ADMISSION = LocalDate.parse("2021-09-01");
    public static final LocalDate STUDENT_GROUP_4_ADMISSION = LocalDate.parse("2020-09-01");

    public static final StudentGroupDTO studentGroup1DTO = new StudentGroupDTO(STUDENT_GROUP_1_ID, STUDENT_GROUP_1_NAME,
            speciality1DTO, STUDENT_GROUP_1_STUDY_TYPE, STUDENT_GROUP_1_CURRENT_EDUCATION_YEAR, STUDENT_GROUP_1_ADMISSION,
            educator1DTO);

    public static final StudentGroupDTO studentGroup2DTO = new StudentGroupDTO(STUDENT_GROUP_2_ID, STUDENT_GROUP_2_NAME,
            speciality1DTO, STUDENT_GROUP_2_STUDY_TYPE, STUDENT_GROUP_2_CURRENT_EDUCATION_YEAR, STUDENT_GROUP_2_ADMISSION,
            educator2DTO);

    public static final StudentGroupDTO studentGroup3DTO = new StudentGroupDTO(STUDENT_GROUP_3_ID, STUDENT_GROUP_3_NAME,
            speciality2DTO, STUDENT_GROUP_3_STUDY_TYPE, STUDENT_GROUP_3_CURRENT_EDUCATION_YEAR, STUDENT_GROUP_3_ADMISSION,
            educator3DTO);

    public static final StudentGroupDTO studentGroup4DTO = new StudentGroupDTO(STUDENT_GROUP_4_ID, STUDENT_GROUP_4_NAME,
            speciality2DTO, STUDENT_GROUP_4_STUDY_TYPE, STUDENT_GROUP_4_CURRENT_EDUCATION_YEAR, STUDENT_GROUP_4_ADMISSION,
            educator4DTO);

    public static final StudentGroupWithStudentsDTO groupWithStudents1 =
            new StudentGroupWithStudentsDTO(studentGroup1DTO, List.of(student1DTO, student2DTO));

    public static final StudentGroupWithStudentsDTO groupWithStudents2 =
            new StudentGroupWithStudentsDTO(studentGroup2DTO, List.of(student3DTO, student4DTO));

    public static final StudentGroupWithStudentsDTO groupWithStudents3 =
            new StudentGroupWithStudentsDTO(studentGroup3DTO, List.of(student5DTO, student6DTO));

    public static final StudentGroupWithStudentsDTO groupWithStudents4 =
            new StudentGroupWithStudentsDTO(studentGroup4DTO, List.of(student8DTO, student7DTO));

    public static final List<StudentGroupDTO> allStudentGroups =
            List.of(studentGroup1DTO, studentGroup3DTO, studentGroup2DTO, studentGroup4DTO);

    public static final List<StudentGroupWithStudentsDTO> allWithStudents =
            List.of(groupWithStudents1, groupWithStudents3, groupWithStudents2, groupWithStudents4);

    public static StudentGroupUpdateDTO getNew() {
        return new StudentGroupUpdateDTO(null, "new name", SPECIALITY_1_ID, StudyType.DISTANCE,
                1, LocalDate.now(), EDUCATOR_1_ID);
    }

    public static StudentGroupUpdateDTO getUpdated(int id) {
        return new StudentGroupUpdateDTO(id, "upd name", SPECIALITY_2_ID, StudyType.FULL_TIME,
                2, LocalDate.now().minusYears(1), EDUCATOR_2_ID);
    }
}
