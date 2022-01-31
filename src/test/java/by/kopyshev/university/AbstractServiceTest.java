package by.kopyshev.university;

import by.kopyshev.university.testdata.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AbstractServiceTest {
    static {
        // class init order fix
        CampusConstants.getNew();
        LectureHallConstants.getNew(CampusConstants.CAMPUS_1_ID);
        PersonConstants.getNew();
        FacultyConstants.getNew();
        FacultyDepartmentConstants.getNew();
    }
}
