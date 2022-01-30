package by.kopyshev.university.web.controller.person;

import by.kopyshev.university.dto.PersonDTO;
import by.kopyshev.university.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractPersonController {
    private static final Logger log = LoggerFactory.getLogger(AbstractPersonController.class);

    private final PersonService personService;

    protected AbstractPersonController(PersonService personService) {
        this.personService = personService;
    }

    public PersonDTO create(PersonDTO personDTO) {
        log.info("Creating a new Person from {}", personDTO);
        return personService.create(personDTO);
    }

    public PersonDTO get(int id) {
        log.info("Getting Person with id {}", id);
        return personService.get(id);
    }

    public PersonDTO getByPassport(String passport) {
        log.info("Getting Person with passport {}", passport);
        return personService.getByPassport(passport);
    }

    public List<PersonDTO> getAll() {
        log.info("Getting all persons ");
        return personService.getAll();
    }

    public void update(PersonDTO personDTO, int id) {
        log.info("Updating Person with id {} by {} ", id, personDTO);
        assureIdConsistent(personDTO, id);
        personService.update(personDTO);
    }

    public void delete(int id) {
        log.info("Deleting Person with id {}", id);
        personService.delete(id);
    }
}
