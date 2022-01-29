package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.EducatorUpdateDTO;
import by.kopyshev.university.service.education.role.EducatorService;
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
        value = AdminEducatorRestController.ADMIN_EDUCATOR_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminEducatorRestController extends AbstractEducatorController {
    public static final String ADMIN_EDUCATOR_REST_URL = "/api/admin/educators";

    public AdminEducatorRestController(EducatorService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EducatorDTO> createWithLocation(@RequestBody @Valid EducatorUpdateDTO facultyDepartmentDTO) {
        EducatorDTO created = super.create(facultyDepartmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_EDUCATOR_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public EducatorDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<EducatorDTO> getAll(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return super.getAll(departmentId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid EducatorUpdateDTO facultyDTO, @PathVariable int id) {
        super.update(facultyDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
