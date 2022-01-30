package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.StudentDTO;
import by.kopyshev.university.dto.education.role.StudentUpdateDTO;
import by.kopyshev.university.service.education.role.StudentService;
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
        value = AdminStudentRestController.ADMIN_STUDENT_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminStudentRestController extends AbstractStudentController {
    public static final String ADMIN_STUDENT_REST_URL = "/api/admin/students/";

    public AdminStudentRestController(StudentService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> createWithLocation(@RequestBody @Valid StudentUpdateDTO facultyDepartmentDTO) {
        StudentDTO created = super.create(facultyDepartmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_STUDENT_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public StudentDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<StudentDTO> getAll(@RequestParam(value = "groupId", required = false) Integer departmentId) {
        return super.getAll(departmentId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid StudentUpdateDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
