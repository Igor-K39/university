package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.SpecialityConstants.*;

class SpecialityServiceImplTest extends AbstractServiceTest {

    @Autowired
    SpecialityService service;

    @Test
    void create() {
        SpecialityDTO created = service.create(getNew());
        SpecialityDTO expected = new SpecialityDTO(getNew());
        expected.setId(created.getId());
        SPECIALITY_DTO_MATCHER.assertMatch(created, expected);
        SPECIALITY_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        SpecialityDTO actual = service.get(SPECIALITY_1_ID);
        SPECIALITY_DTO_MATCHER.assertMatch(speciality1DTO, actual);
    }

    @Test
    void getAll() {
        List<SpecialityDTO> actual = service.getAll();
        SPECIALITY_DTO_MATCHER.assertMatch(List.of(speciality2DTO, speciality1DTO), actual);
    }

    @Test
    void update() {
        SpecialityDTO expected = getUpdated(SPECIALITY_1_ID);
        service.update(expected);
        SPECIALITY_DTO_MATCHER.assertMatch(service.get(SPECIALITY_1_ID), expected);
    }

    @Test
    void delete() {
        service.delete(SPECIALITY_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(SPECIALITY_1_ID));
    }
}