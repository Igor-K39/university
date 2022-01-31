package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;

import java.util.List;

import static by.kopyshev.university.testdata.LectureHallConstants.*;

public class CampusConstants {
    public static MatcherFactory.Matcher<CampusDTO>
            CAMPUS_MATCHER = MatcherFactory.usingIgnoreFieldComparator(CampusDTO.class);
    public static MatcherFactory.Matcher<CampusWithHallsDTO>
            CAMPUS_WITH_HALLS_MATCHER = MatcherFactory.usingIgnoreFieldComparator(CampusWithHallsDTO.class);

    public static int CAMPUS_1_ID = 100000;
    public static int CAMPUS_2_ID = 100001;
    public static String CAMPUS_1_NAME = "ФКП";
    public static String CAMPUS_2_NAME = "ФКСиС";
    public static String CAMPUS_1_NUMBER = "1";
    public static String CAMPUS_2_NUMBER = "4";
    public static String CAMPUS_1_ADDRESS = "П.Бровки ул., 4";
    public static String CAMPUS_2_ADDRESS = "Гикало ул., 9";
    public static String CAMPUS_1_DESCRIPTION = "Факультет компьютерного проектирования";
    public static String CAMPUS_2_DESCRIPTION = "Факультет компьютерных систем и сетей";

    public static Campus campus1 = new Campus(CAMPUS_1_ID, CAMPUS_1_NAME, CAMPUS_1_NUMBER,
            CAMPUS_1_ADDRESS, CAMPUS_1_DESCRIPTION, List.of(hall1, hall2));

    public static Campus campus2 = new Campus(CAMPUS_2_ID, CAMPUS_2_NAME, CAMPUS_2_NUMBER,
            CAMPUS_2_ADDRESS, CAMPUS_2_DESCRIPTION, List.of(hall3, hall4));

    public static CampusDTO campusDTO1 = new CampusDTO(CAMPUS_1_ID, CAMPUS_1_NAME, CAMPUS_1_NUMBER,
            CAMPUS_1_ADDRESS, CAMPUS_1_DESCRIPTION);

    public static CampusDTO campusDTO2 = new CampusDTO(CAMPUS_2_ID, CAMPUS_2_NAME, CAMPUS_2_NUMBER,
            CAMPUS_2_ADDRESS, CAMPUS_2_DESCRIPTION);

    public static CampusWithHallsDTO campusWithHallsDTO1 = new CampusWithHallsDTO(CAMPUS_1_ID, CAMPUS_1_NAME,
            CAMPUS_1_NUMBER, CAMPUS_1_ADDRESS, CAMPUS_1_DESCRIPTION, List.of(hallDTO1, hallDTO2));

    public static CampusWithHallsDTO campusWithHallsDTO2 = new CampusWithHallsDTO(CAMPUS_2_ID, CAMPUS_2_NAME,
            CAMPUS_2_NUMBER, CAMPUS_2_ADDRESS, CAMPUS_2_DESCRIPTION, List.of(hallDTO3, hallDTO4));


    public static CampusDTO getNew() {
        return new CampusDTO(null, "New_Name", "XXX", "New_Address",
                "New_Description");
    }

    public static CampusDTO getUpdated(Integer id) {
        return new CampusDTO(id, "Updated_Name", "YYY", "Updated_Address",
                "Updated_Description");
    }
}
