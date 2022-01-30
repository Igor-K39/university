package by.kopyshev.university.web.controller.education.faculty;

import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.role.FacultyWithDepartmentsDTO;
import by.kopyshev.university.service.education.FacultyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = FacultyRestController.FACULTY_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyRestController extends AbstractFacultyController {
    public static final String FACULTY_REST_URL = "/api/faculties/";

    public FacultyRestController(FacultyService service) {
        super(service);
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
}
