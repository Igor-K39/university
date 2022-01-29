package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.service.education.role.EducatorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = EducatorRestController.ADMIN_EDUCATOR_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EducatorRestController extends AbstractEducatorController {
    public static final String ADMIN_EDUCATOR_REST_URL = "/api/educators";

    public EducatorRestController(EducatorService service) {
        super(service);
    }

    @GetMapping("{id}")
    public EducatorDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<EducatorDTO> getAll(@RequestParam(value = "departmentId", required = false) Integer facultyId) {
        return super.getAll(facultyId);
    }
}
