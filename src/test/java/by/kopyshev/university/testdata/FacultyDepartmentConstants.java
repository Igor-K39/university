package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;

import java.util.List;

import static by.kopyshev.university.testdata.DisciplineConstants.*;

public class FacultyDepartmentConstants {
    public static final MatcherFactory.Matcher<FacultyDepartmentDTO> FACULTY_DEPARTMENT_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(FacultyDepartmentDTO.class);

    public static final MatcherFactory.Matcher<FacultyDepartmentWithDisciplinesDTO> WITH_DISCIPLINES_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(FacultyDepartmentWithDisciplinesDTO.class);

    public static final int FACULTY_DEPARTMENT_1_ID = 100_008;
    public static final int FACULTY_DEPARTMENT_2_ID = 100_009;
    public static final int FACULTY_DEPARTMENT_3_ID = 100_010;
    public static final int FACULTY_DEPARTMENT_4_ID = 100_011;
    public static final String FACULTY_DEPARTMENT_1_NAME = "Кафедра 1";
    public static final String FACULTY_DEPARTMENT_2_NAME = "Кафедра 2";
    public static final String FACULTY_DEPARTMENT_3_NAME = "Кафедра 3";
    public static final String FACULTY_DEPARTMENT_4_NAME = "Кафедра 4";
    public static final String FACULTY_DEPARTMENT_1_ADDRESS = "Адрес расположения кафедры 1";
    public static final String FACULTY_DEPARTMENT_2_ADDRESS = "Адрес расположения кафедры 2";
    public static final String FACULTY_DEPARTMENT_3_ADDRESS = "Адрес расположения кафедры 3";
    public static final String FACULTY_DEPARTMENT_4_ADDRESS = "Адрес расположения кафедры 4";
    public static final String FACULTY_DEPARTMENT_1_EMAIL = "kafedra1@gmail.com";
    public static final String FACULTY_DEPARTMENT_2_EMAIL = "kafedra2@gmail.com";
    public static final String FACULTY_DEPARTMENT_3_EMAIL = "kafedra3@gmail.com";
    public static final String FACULTY_DEPARTMENT_4_EMAIL = "kafedra4@gmail.com";
    public static final String FACULTY_DEPARTMENT_1_PHONE_NUMBER = "444-11-22";
    public static final String FACULTY_DEPARTMENT_2_PHONE_NUMBER = "444-22-33";
    public static final String FACULTY_DEPARTMENT_3_PHONE_NUMBER = "444-33-44";
    public static final String FACULTY_DEPARTMENT_4_PHONE_NUMBER = "444-44-55";
    public static final String FACULTY_DEPARTMENT_1_DESCRIPTION = "Lorem description... 1";
    public static final String FACULTY_DEPARTMENT_2_DESCRIPTION = "Lorem description... 2";
    public static final String FACULTY_DEPARTMENT_3_DESCRIPTION = "Lorem description... 3";
    public static final String FACULTY_DEPARTMENT_4_DESCRIPTION = "Lorem description... 4";

    public static final FacultyDepartmentDTO facultyDepartment1DTO =
            new FacultyDepartmentDTO(FACULTY_DEPARTMENT_1_ID, FACULTY_DEPARTMENT_1_NAME, FACULTY_DEPARTMENT_1_ADDRESS,
                    FACULTY_DEPARTMENT_1_EMAIL, FACULTY_DEPARTMENT_1_PHONE_NUMBER, FACULTY_DEPARTMENT_1_DESCRIPTION,
                    FacultyConstants.FACULTY_1_ID);

    public static final FacultyDepartmentDTO facultyDepartment2DTO =
            new FacultyDepartmentDTO(FACULTY_DEPARTMENT_2_ID, FACULTY_DEPARTMENT_2_NAME, FACULTY_DEPARTMENT_2_ADDRESS,
                    FACULTY_DEPARTMENT_2_EMAIL, FACULTY_DEPARTMENT_2_PHONE_NUMBER, FACULTY_DEPARTMENT_2_DESCRIPTION,
                    FacultyConstants.FACULTY_1_ID);

    public static final FacultyDepartmentDTO facultyDepartment3DTO =
            new FacultyDepartmentDTO(FACULTY_DEPARTMENT_3_ID, FACULTY_DEPARTMENT_3_NAME, FACULTY_DEPARTMENT_3_ADDRESS,
                    FACULTY_DEPARTMENT_3_EMAIL, FACULTY_DEPARTMENT_3_PHONE_NUMBER, FACULTY_DEPARTMENT_3_DESCRIPTION,
                    FacultyConstants.FACULTY_2_ID);

    public static final FacultyDepartmentDTO facultyDepartment4DTO =
            new FacultyDepartmentDTO(FACULTY_DEPARTMENT_4_ID, FACULTY_DEPARTMENT_4_NAME, FACULTY_DEPARTMENT_4_ADDRESS,
                    FACULTY_DEPARTMENT_4_EMAIL, FACULTY_DEPARTMENT_4_PHONE_NUMBER, FACULTY_DEPARTMENT_4_DESCRIPTION,
                    FacultyConstants.FACULTY_2_ID);

    public static final FacultyDepartmentWithDisciplinesDTO facultyDepartment1WithDisciplinesDTO =
            new FacultyDepartmentWithDisciplinesDTO(FACULTY_DEPARTMENT_1_ID, FACULTY_DEPARTMENT_1_NAME,
                    FACULTY_DEPARTMENT_1_ADDRESS, FACULTY_DEPARTMENT_1_EMAIL, FACULTY_DEPARTMENT_1_PHONE_NUMBER,
                    FACULTY_DEPARTMENT_1_DESCRIPTION, FacultyConstants.FACULTY_1_ID, List.of(discipline1DTO));

    public static final FacultyDepartmentWithDisciplinesDTO facultyDepartment2WithDisciplinesDTO =
            new FacultyDepartmentWithDisciplinesDTO(FACULTY_DEPARTMENT_2_ID, FACULTY_DEPARTMENT_2_NAME,
                    FACULTY_DEPARTMENT_2_ADDRESS, FACULTY_DEPARTMENT_2_EMAIL, FACULTY_DEPARTMENT_2_PHONE_NUMBER,
                    FACULTY_DEPARTMENT_2_DESCRIPTION, FacultyConstants.FACULTY_1_ID, List.of(discipline2DTO));

    public static final FacultyDepartmentWithDisciplinesDTO facultyDepartment3WithDisciplinesDTO =
            new FacultyDepartmentWithDisciplinesDTO(FACULTY_DEPARTMENT_3_ID, FACULTY_DEPARTMENT_3_NAME,
                    FACULTY_DEPARTMENT_3_ADDRESS, FACULTY_DEPARTMENT_3_EMAIL, FACULTY_DEPARTMENT_3_PHONE_NUMBER,
                    FACULTY_DEPARTMENT_3_DESCRIPTION, FacultyConstants.FACULTY_2_ID, List.of(discipline3DTO));

    public static final FacultyDepartmentWithDisciplinesDTO facultyDepartment4WithDisciplinesDTO =
            new FacultyDepartmentWithDisciplinesDTO(FACULTY_DEPARTMENT_4_ID, FACULTY_DEPARTMENT_4_NAME,
                    FACULTY_DEPARTMENT_4_ADDRESS, FACULTY_DEPARTMENT_4_EMAIL, FACULTY_DEPARTMENT_4_PHONE_NUMBER,
                    FACULTY_DEPARTMENT_4_DESCRIPTION, FacultyConstants.FACULTY_2_ID, List.of(discipline4DTO));

    public static final List<FacultyDepartmentDTO> ofFaculty1 = List.of(facultyDepartment1DTO, facultyDepartment2DTO);
    public static final List<FacultyDepartmentDTO> ofAllFaculties =
            List.of(facultyDepartment1DTO, facultyDepartment2DTO, facultyDepartment3DTO, facultyDepartment4DTO);

    public static final List<FacultyDepartmentWithDisciplinesDTO> ofAllFacultiesWithDisciplines =
            List.of(facultyDepartment1WithDisciplinesDTO, facultyDepartment2WithDisciplinesDTO,
                    facultyDepartment3WithDisciplinesDTO, facultyDepartment4WithDisciplinesDTO);

    public static final List<FacultyDepartmentWithDisciplinesDTO> ofFaculty1WithDisciplines =
            List.of(facultyDepartment1WithDisciplinesDTO, facultyDepartment2WithDisciplinesDTO);

    public static FacultyDepartmentDTO getNew() {
        return new FacultyDepartmentDTO(null, "new department name", "new department address",
                "faculty.dep@email.com", "+XXX XX XXX-XX-XX", "The description of department",
                FacultyConstants.FACULTY_1_ID);
    }

    public static FacultyDepartmentDTO getUpdated(int id) {
        return new FacultyDepartmentDTO(id, "upd department name", "upd department address",
                "faculty.upd@email.com", "+YYY YY YYY-YY-YY", "The updated of department",
                FacultyConstants.FACULTY_1_ID);
    }
}
