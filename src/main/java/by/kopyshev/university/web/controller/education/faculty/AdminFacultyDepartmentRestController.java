package by.kopyshev.university.web.controller.education.faculty;

import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.service.education.FacultyDepartmentService;
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
        value = AdminFacultyDepartmentRestController.ADMIN_FACULTY_DEPARTMENT_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminFacultyDepartmentRestController extends AbstractFacultyDepartmentController {
    public static final String ADMIN_FACULTY_DEPARTMENT_REST_URL = "/api/admin/faculties/departments";

    public AdminFacultyDepartmentRestController(FacultyDepartmentService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDepartmentDTO> createWithLocation(@RequestBody
                                                                   @Valid
                                                                           FacultyDepartmentDTO facultyDepartmentDTO) {
        FacultyDepartmentDTO created = super.create(facultyDepartmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_FACULTY_DEPARTMENT_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public FacultyDepartmentDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<FacultyDepartmentDTO> getAll(@RequestParam(value = "facultyId", required = false) Integer facultyId) {
        return super.getAll(facultyId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid FacultyDepartmentDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
