package by.kopyshev.university.web.controller.education.faculty;

import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyWithDepartmentsDTO;
import by.kopyshev.university.service.education.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminFacultyRestController.ADMIN_FACULTY_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminFacultyRestController extends AbstractFacultyController {
    public static final String ADMIN_FACULTY_REST_URL = "/api/admin/faculties/";

    public AdminFacultyRestController(FacultyService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDTO> createWithLocation(@RequestBody @Valid FacultyDTO facultyDTO) {
        FacultyDTO created = super.create(facultyDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_FACULTY_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public FacultyDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("{id}/with-departments")
    public FacultyWithDepartmentsDTO getWithDepartments(@PathVariable("id") int id) {
        return super.getWithDepartments(id);
    }

    @GetMapping
    public List<FacultyDTO> getAll() {
        return super.getAll();
    }

    @GetMapping("with-departments/")
    public List<FacultyWithDepartmentsDTO> getAllWithDepartments() {
        return super.getAllWithDepartments();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid FacultyDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
