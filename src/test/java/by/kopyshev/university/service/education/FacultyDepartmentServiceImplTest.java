package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.FacultyConstants.FACULTY_1_ID;
import static by.kopyshev.university.testdata.FacultyDepartmentConstants.*;

class FacultyDepartmentServiceImplTest extends AbstractServiceTest {

    @Autowired
    FacultyDepartmentService service;

    @Test
    void create() {
        FacultyDepartmentDTO created = service.create(getNew());
        FacultyDepartmentDTO expected = new FacultyDepartmentDTO(getNew());
        expected.setId(created.id());
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(created, expected);
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        FacultyDepartmentDTO actual = service.get(FACULTY_DEPARTMENT_1_ID);
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(facultyDepartment1DTO, actual);
    }

    @Test
    void getWithDisciplines() {
        FacultyDepartmentWithDisciplinesDTO actual = service.getWithDisciplines(FACULTY_DEPARTMENT_1_ID);
        WITH_DISCIPLINES_DTO_MATCHER.assertMatch(facultyDepartment1WithDisciplinesDTO, actual);
    }

    @Test
    void getAll() {
        List<FacultyDepartmentDTO> actual = service.getAll(FACULTY_1_ID);
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(ofFaculty1, actual);

        actual = service.getAll(null);
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(ofAllFaculties, actual);
    }

    @Test
    void getAllWithDisciplines() {
        List<FacultyDepartmentWithDisciplinesDTO> actual = service.getAllWithDisciplines(FACULTY_1_ID);
        WITH_DISCIPLINES_DTO_MATCHER.assertMatch(ofFaculty1WithDisciplines, actual);

        actual = service.getAllWithDisciplines(null);
        WITH_DISCIPLINES_DTO_MATCHER.assertMatch(ofAllFacultiesWithDisciplines, actual);
    }

    @Test
    void update() {
        FacultyDepartmentDTO expected = getUpdated(FACULTY_DEPARTMENT_1_ID);
        service.update(expected);
        FACULTY_DEPARTMENT_DTO_MATCHER.assertMatch(expected, service.get(FACULTY_DEPARTMENT_1_ID));
    }

    @Test
    void delete() {
        service.delete(FACULTY_DEPARTMENT_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(FACULTY_DEPARTMENT_1_ID));
    }
}