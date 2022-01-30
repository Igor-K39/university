package by.kopyshev.university.web.controller.education.discipline;

import by.kopyshev.university.dto.education.DisciplineDTO;
import by.kopyshev.university.service.education.DisciplineService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = DisciplineRestController.DISCIPLINE_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DisciplineRestController extends AbstractDisciplineController {
    public static final String DISCIPLINE_REST_URL = "/api/disciplines";

    public DisciplineRestController(DisciplineService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public DisciplineDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<DisciplineDTO> getAll(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return super.getAll(departmentId);
    }
}
