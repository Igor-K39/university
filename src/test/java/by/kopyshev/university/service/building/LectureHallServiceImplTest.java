package by.kopyshev.university.service.building;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.CampusConstants.CAMPUS_1_ID;
import static by.kopyshev.university.testdata.CampusConstants.CAMPUS_1_NUMBER;
import static by.kopyshev.university.testdata.LectureHallConstants.*;

class LectureHallServiceImplTest extends AbstractServiceTest {

    @Autowired
    LectureHallService service;

    @Test
    void create() {
        LectureHallDTO created = service.create(getNew(CAMPUS_1_ID));
        LectureHallDTO expected = getNew(CAMPUS_1_ID);
        expected.setId(created.getId());
        LECTURE_HALL_DTO_MATCHER.assertMatch(created, expected);
        LECTURE_HALL_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        LectureHallDTO actual = service.get(HALL_1_ID);
        LECTURE_HALL_DTO_MATCHER.assertMatch(actual, hallDTO1);
    }

    @Test
    void getByNumber() {
        LectureHallDTO actual = service.getByNumber(CAMPUS_1_NUMBER, HALL_1_NUMBER);
        LECTURE_HALL_DTO_MATCHER.assertMatch(actual, hallDTO1);
    }

    @Test
    void getAll() {
        List<LectureHallDTO> actual = service.getAll();
        LECTURE_HALL_DTO_MATCHER.assertMatch(actual, List.of(hallDTO1, hallDTO2, hallDTO3, hallDTO4));
    }

    @Test
    void update() {
        LectureHallDTO updated = getUpdated(HALL_1_ID);
        service.update(updated);
        LECTURE_HALL_DTO_MATCHER.assertMatch(service.get(updated.id()), updated);
    }

    @Test
    void delete() {
        service.delete(HALL_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(HALL_1_ID));
    }
}