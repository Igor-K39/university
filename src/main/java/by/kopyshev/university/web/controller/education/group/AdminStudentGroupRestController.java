package by.kopyshev.university.web.controller.education.group;

import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.service.education.StudentGroupService;
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
        value = AdminStudentGroupRestController.ADMIN_STUDENT_GROUP_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminStudentGroupRestController extends AbstractStudentGroupController {
    public static final String ADMIN_STUDENT_GROUP_REST_URL = "/api/admin/groups/";

    public AdminStudentGroupRestController(StudentGroupService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentGroupDTO> createWithLocation(@RequestBody
                                                                        @Valid StudentGroupUpdateDTO facultyDTO) {
        StudentGroupDTO created = super.create(facultyDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_STUDENT_GROUP_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public StudentGroupDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("{id}/with-students")
    public StudentGroupDTO getWithStudents(@PathVariable("id") int id) {
        return super.getWithStudent(id);
    }

    @GetMapping
    public List<StudentGroupDTO> getAll() {
        return super.getAll();
    }

    @GetMapping("with-students")
    public List<StudentGroupWithStudentsDTO> getAllWithStudents() {
        return super.getAllWithStudents();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid StudentGroupUpdateDTO studentGroupUpdateDTO, @PathVariable int id) {
        super.update(studentGroupUpdateDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
