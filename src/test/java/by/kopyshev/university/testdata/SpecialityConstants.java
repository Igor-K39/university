package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.dto.education.SpecialityDTO;

import static by.kopyshev.university.testdata.FacultyConstants.FACULTY_1_ID;

public class SpecialityConstants {
    public static final MatcherFactory.Matcher<SpecialityDTO> SPECIALITY_DTO_MATCHER =
            MatcherFactory.usingIgnoreFieldComparator(SpecialityDTO.class);

    public static final int SPECIALITY_1_ID = 100_024;
    public static final int SPECIALITY_2_ID = 100_025;
    public static final String SPECIALITY_1_NAME = "Программное обеспечение информационных технологий";
    public static final String SPECIALITY_2_NAME = "Вычислительные машины, системы и сети";
    public static final String SPECIALITY_1_CODE = "1-40 01 01";
    public static final String SPECIALITY_2_CODE = "1-40 02 01";
    public static final String SPECIALITY_1_SHORT_NAME = "ПОИТ";
    public static final String SPECIALITY_2_SHORT_NAME = "ВМСиС";

    public static final SpecialityDTO speciality1DTO = new SpecialityDTO(SPECIALITY_1_ID, SPECIALITY_1_NAME,
            SPECIALITY_1_CODE, SPECIALITY_1_SHORT_NAME, FACULTY_1_ID);

    public static final SpecialityDTO speciality2DTO = new SpecialityDTO(SPECIALITY_2_ID, SPECIALITY_2_NAME,
            SPECIALITY_2_CODE, SPECIALITY_2_SHORT_NAME, FacultyConstants.FACULTY_2_ID);

    public static SpecialityDTO getNew() {
        return new SpecialityDTO(null, "new speciality", "X-XX XX XX", "NS", FACULTY_1_ID);
    }

    public static SpecialityDTO getUpdated(int id) {
        return new SpecialityDTO(id, "upd speciality", "Y-YY YY YY", "US", FACULTY_1_ID);
    }
}
