package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyWithDepartmentsDTO;

import java.util.List;

import static by.kopyshev.university.testdata.FacultyDepartmentConstants.*;

public class FacultyConstants {
    public static final MatcherFactory.Matcher<FacultyDTO> FACULTY_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(FacultyDTO.class);

    public static final MatcherFactory.Matcher<FacultyWithDepartmentsDTO> FACULTY_WITH_DEPARTMENTS_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(FacultyWithDepartmentsDTO.class);

    public static int FACULTY_1_ID = 100_006;
    public static int FACULTY_2_ID = 100_007;
    public static String FACULTY_1_NAME = "Факультет компьютерного проектирования";
    public static String FACULTY_2_NAME = "Факультет компьютерных систем и сетей";
    public static String FACULTY_1_DEAN_ADDRESS = "Адрес деканата ФКП...";
    public static String FACULTY_2_DEAN_ADDRESS = "Адрес деканата ФКСиС...";
    public static String FACULTY_1_EMAIL = "some.fkp.email@gmail.com";
    public static String FACULTY_2_EMAIL = "some.fksis.email@gmail.com";
    public static String FACULTY_1_DEAN_PHONE_NUMBER = "222-11-33";
    public static String FACULTY_2_DEAN_PHONE_NUMBER = "333-11-22";
    public static String FACULTY_1_DESCRIPTION = "Описание факультета во всей красе...";
    public static String FACULTY_2_DESCRIPTION = "И здесь тоже описание факультета во всей красе";

    public static final FacultyDTO faculty1DTO = new FacultyDTO(FACULTY_1_ID, FACULTY_1_NAME, FACULTY_1_DEAN_ADDRESS,
            FACULTY_1_EMAIL, FACULTY_1_DEAN_PHONE_NUMBER, FACULTY_1_DESCRIPTION);

    public static final FacultyDTO faculty2DTO = new FacultyDTO(FACULTY_2_ID, FACULTY_2_NAME, FACULTY_2_DEAN_ADDRESS,
            FACULTY_2_EMAIL, FACULTY_2_DEAN_PHONE_NUMBER, FACULTY_2_DESCRIPTION);

    public static final FacultyWithDepartmentsDTO faculty1WithDepartmentsDTO = new FacultyWithDepartmentsDTO(
            FACULTY_1_ID, FACULTY_1_NAME, FACULTY_1_DEAN_ADDRESS, FACULTY_1_EMAIL, FACULTY_1_DEAN_PHONE_NUMBER,
            FACULTY_1_DESCRIPTION, List.of(facultyDepartment1DTO, facultyDepartment2DTO));

    public static final FacultyWithDepartmentsDTO faculty2WithDepartmentsDTO = new FacultyWithDepartmentsDTO(
            FACULTY_2_ID, FACULTY_2_NAME, FACULTY_2_DEAN_ADDRESS, FACULTY_2_EMAIL, FACULTY_2_DEAN_PHONE_NUMBER,
            FACULTY_2_DESCRIPTION, List.of(facultyDepartment3DTO, facultyDepartment4DTO));

    public static FacultyDTO getNew() {
        return new FacultyDTO(null, "new name", "new dean address", "new.dean.email@email.com",
                "+XXX XX XXX-XX-XX", "The new faculty wonderful description");
    }

    public static FacultyDTO getUpdated(int facultyId) {
        return new FacultyDTO(facultyId, "upd name", "upd dean address", "upd.dean.email@email.com",
                "+YYY YY YYY-YY-YY", "The updated faculty wonderful description");
    }
}
