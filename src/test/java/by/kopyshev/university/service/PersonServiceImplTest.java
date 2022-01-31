package by.kopyshev.university.service;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.PersonConstants.*;

class PersonServiceImplTest extends AbstractServiceTest {

    @Autowired
    PersonService service;

    @Test
    void create() {
        PersonDTO created = service.create(getNew());
        PersonDTO expected = new PersonDTO(getNew());
        expected.setId(created.id());
        PERSON_DTO_MATCHER.assertMatch(created, expected);
        PERSON_DTO_MATCHER.assertMatch(created, service.get(created.id()));
    }

    @Test
    void get() {
        PersonDTO actual = service.get(EDUCATOR_PERSON_1_ID);
        PERSON_DTO_MATCHER.assertMatch(actual, educatorPerson1);
    }

    @Test
    void getByPassport() {
        PersonDTO actual = service.getByPassport(EDUCATOR_PERSON_1_PASSPORT);
        PERSON_DTO_MATCHER.assertMatch(educatorPerson1, actual);
    }

    @Test
    void getAll() {
        List<PersonDTO> actual = service.getAll();
        PERSON_DTO_MATCHER.assertMatch(allPersons, actual);
    }

    @Test
    void update() {
        PersonDTO expected = getUpdated(EDUCATOR_PERSON_1_ID);
        service.update(getUpdated(EDUCATOR_PERSON_1_ID));
        PERSON_DTO_MATCHER.assertMatch(service.get(EDUCATOR_PERSON_1_ID), expected);
    }

    @Test
    void delete() {
    }
}