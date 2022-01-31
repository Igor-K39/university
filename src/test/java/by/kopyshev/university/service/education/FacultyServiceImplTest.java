package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyWithDepartmentsDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.FacultyConstants.*;

class FacultyServiceImplTest extends AbstractServiceTest {

    @Autowired
    FacultyService service;

    @Test
    void create() {
        FacultyDTO created = service.create(getNew());
        FacultyDTO expected = new FacultyDTO(getNew());
        expected.setId(created.id());
        FACULTY_DTO_MATCHER.assertMatch(created, expected);
        FACULTY_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        FacultyDTO actual = service.get(FACULTY_1_ID);
        FACULTY_DTO_MATCHER.assertMatch(faculty1DTO, actual);
    }

    @Test
    void getWithDepartments() {
        FacultyWithDepartmentsDTO actual = service.getWithDepartments(FACULTY_1_ID);
        FACULTY_WITH_DEPARTMENTS_DTO_MATCHER.assertMatch(faculty1WithDepartmentsDTO, actual);
    }

    @Test
    void getAll() {
        List<FacultyDTO> actual = service.getAll();
        FACULTY_DTO_MATCHER.assertMatch(List.of(faculty1DTO, faculty2DTO), actual);
    }

    @Test
    void getAllWithDepartments() {
        List<FacultyWithDepartmentsDTO> actual = service.getAllWithDepartments();
        FACULTY_WITH_DEPARTMENTS_DTO_MATCHER
                .assertMatch(List.of(faculty1WithDepartmentsDTO, faculty2WithDepartmentsDTO), actual);
    }

    @Test
    void update() {
        FacultyDTO expected = getUpdated(FACULTY_1_ID);
        service.update(expected);
        FACULTY_DTO_MATCHER.assertMatch(expected, service.get(FACULTY_1_ID));
    }

    @Test
    void delete() {
        service.delete(FACULTY_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(FACULTY_1_ID));
    }
}