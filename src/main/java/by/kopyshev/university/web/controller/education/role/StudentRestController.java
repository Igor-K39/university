package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.StudentDTO;
import by.kopyshev.university.service.education.role.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = StudentRestController.ADMIN_STUDENT_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentRestController extends AbstractStudentController {
    public static final String ADMIN_STUDENT_REST_URL = "/api/students/";

    public StudentRestController(StudentService service) {
        super(service);
    }

    @GetMapping("{id}")
    public StudentDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<StudentDTO> getAll(@RequestParam(value = "groupId", required = false) Integer departmentId) {
        return super.getAll(departmentId);
    }
}
