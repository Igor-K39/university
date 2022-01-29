package by.kopyshev.university.web.controller.education;

import by.kopyshev.university.dto.education.DisciplineDTO;
import by.kopyshev.university.service.education.DisciplineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = AdminDisciplineRestController.ADMIN_DISCIPLINE_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDisciplineRestController extends AbstractDisciplineController {
    public static final String ADMIN_DISCIPLINE_REST_URL = "/api/admin/disciplines";

    public AdminDisciplineRestController(DisciplineService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisciplineDTO> createWithLocation(@RequestBody @Valid DisciplineDTO disciplineDTO) {
        DisciplineDTO created = super.create(disciplineDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_DISCIPLINE_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public DisciplineDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<DisciplineDTO> getAll(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return super.getAll(departmentId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DisciplineDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
