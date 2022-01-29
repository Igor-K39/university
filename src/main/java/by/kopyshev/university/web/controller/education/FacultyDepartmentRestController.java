package by.kopyshev.university.web.controller.education;

import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.service.education.FacultyDepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = FacultyDepartmentRestController.FACULTY_DEPARTMENT_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyDepartmentRestController extends AbstractFacultyDepartmentController {
    public static final String FACULTY_DEPARTMENT_REST_URL = "/api/faculties/departments";

    public FacultyDepartmentRestController(FacultyDepartmentService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public FacultyDepartmentDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<FacultyDepartmentDTO> getAll(@RequestParam(value = "facultyId", required = false) Integer facultyId) {
        return super.getAll(facultyId);
    }
}
