package by.kopyshev.university.service.building;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.CampusConstants.*;

class CampusServiceImplTest extends AbstractServiceTest {

    @Autowired
    CampusService service;

    @Test
    void create() {
        CampusDTO created = service.create(getNew());
        CampusDTO expected = new CampusDTO(getNew());
        expected.setId(created.id());
        CAMPUS_MATCHER.assertMatch(created, expected);
        CAMPUS_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        CampusDTO actual = service.get(CAMPUS_1_ID);
        CAMPUS_MATCHER.assertMatch(campusDTO1, actual);
    }

    @Test
    void getByNumber() {
        CampusDTO actual = service.getByNumber(CAMPUS_1_NUMBER);
        CAMPUS_MATCHER.assertMatch(campusDTO1, actual);
    }

    @Test
    void getWithHalls() {
        CampusWithHallsDTO actual = service.getWithHalls(CAMPUS_1_ID);
        CAMPUS_MATCHER.assertMatch(campusWithHallsDTO1, actual);
    }

    @Test
    void getAll() {
        List<CampusDTO> actual = service.getAll();
        CAMPUS_MATCHER.assertMatch(List.of(campusDTO1, campusDTO2), actual);
    }

    @Test
    void getAllWithHalls() {
        List<CampusWithHallsDTO> actual = service.getAllWithHalls();
        CAMPUS_WITH_HALLS_MATCHER.assertMatch(List.of(campusWithHallsDTO1, campusWithHallsDTO2), actual);
    }

    @Test
    void update() {
        CampusDTO expected = getUpdated(CAMPUS_1_ID);
        service.update(getUpdated(CAMPUS_1_ID));
        CAMPUS_MATCHER.assertMatch(service.get(CAMPUS_1_ID), expected);
    }

    @Test
    void delete() {
        service.delete(CAMPUS_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(CAMPUS_1_ID));
    }
}