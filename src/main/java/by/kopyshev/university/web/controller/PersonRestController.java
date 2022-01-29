package by.kopyshev.university.web.controller;

import by.kopyshev.university.dto.PersonDTO;
import by.kopyshev.university.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = PersonRestController.PERSON_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonRestController extends AbstractPersonController {
    public static final String PERSON_REST_URL = "/api/persons/";

    public PersonRestController(PersonService personService) {
        super(personService);
    }

    @GetMapping("{id}")
    public PersonDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/by")
    public PersonDTO getBy(@RequestParam("passport") String passport) {
        return super.getByPassport(passport);
    }

    @GetMapping
    public List<PersonDTO> getAll() {
        return super.getAll();
    }
}
