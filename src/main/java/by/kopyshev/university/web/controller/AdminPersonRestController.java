package by.kopyshev.university.web.controller;

import by.kopyshev.university.dto.PersonDTO;
import by.kopyshev.university.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminPersonRestController.ADMIN_PERSON_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminPersonRestController extends AbstractPersonController {
    public static final String ADMIN_PERSON_REST_URL = "/api/admin/persons/";

    public AdminPersonRestController(PersonService personService) {
        super(personService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> createWithLocation(@RequestBody @Valid PersonDTO personDTO) {
        PersonDTO created = super.create(personDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_PERSON_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
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

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PersonDTO personDTO, @PathVariable int id) {
        super.update(personDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
