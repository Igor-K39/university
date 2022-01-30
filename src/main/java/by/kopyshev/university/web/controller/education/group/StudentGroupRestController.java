package by.kopyshev.university.web.controller.education.group;

import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.service.education.StudentGroupService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = StudentGroupRestController.ADMIN_STUDENT_GROUP_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentGroupRestController extends AbstractStudentGroupController {
    public static final String ADMIN_STUDENT_GROUP_REST_URL = "/api/groups/";

    public StudentGroupRestController(StudentGroupService service) {
        super(service);
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
}
