package by.kopyshev.university.web.controller.education.speciality;

import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.service.education.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminSpecialityRestController.ADMIN_SPECIALITY_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminSpecialityRestController extends AbstractSpecialityController {
    public static final String ADMIN_SPECIALITY_REST_URL = "/api/admin/specialities/";

    public AdminSpecialityRestController(SpecialityService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpecialityDTO> createWithLocation(@RequestBody @Valid SpecialityDTO facultyDTO) {
        SpecialityDTO created = super.create(facultyDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_SPECIALITY_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public SpecialityDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<SpecialityDTO> getAll() {
        return super.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid SpecialityDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
