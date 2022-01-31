package by.kopyshev.university;

import by.kopyshev.university.testdata.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        config = @SqlConfig(encoding = "UTF-8"),
        scripts = {
                "classpath:data.sql"
        })
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
