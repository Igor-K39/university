package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.lecture.DisciplineDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.DisciplineConstants.*;
import static by.kopyshev.university.testdata.FacultyDepartmentConstants.FACULTY_DEPARTMENT_1_ID;

class DisciplineServiceImplTest extends AbstractServiceTest {

    @Autowired
    DisciplineService service;

    @Test
    void create() {
        DisciplineDTO created = service.create(getNew());
        DisciplineDTO expected = new DisciplineDTO(getNew());
        expected.setId(created.id());
        DISCIPLINE_DTO_MATCHER.assertMatch(created, expected);
        DISCIPLINE_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        DisciplineDTO actual = service.get(DISCIPLINE_1_ID);
        DISCIPLINE_DTO_MATCHER.assertMatch(discipline1DTO, actual);
    }

    @Test
    void getAll() {
        List<DisciplineDTO> actual = service.getAll(FACULTY_DEPARTMENT_1_ID);
        DISCIPLINE_DTO_MATCHER.assertMatch(List.of(discipline1DTO), actual);

        actual = service.getAll(null);
        DISCIPLINE_DTO_MATCHER.assertMatch(List.of(discipline2DTO, discipline3DTO, discipline1DTO, discipline4DTO), actual);
    }

    @Test
    void update() {
        DisciplineDTO expected = getUpdated(DISCIPLINE_1_ID);
        service.update(expected);
        DISCIPLINE_DTO_MATCHER.assertMatch(expected, service.get(DISCIPLINE_1_ID));
    }

    @Test
    void delete() {
        service.delete(DISCIPLINE_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(DISCIPLINE_1_ID));
    }
}