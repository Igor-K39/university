package by.kopyshev.university.web.controller.education;

import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.service.education.SpecialityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = SpecialityRestController.SPECIALITY_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecialityRestController extends AbstractSpecialityController {
    public static final String SPECIALITY_REST_URL = "/api/specialities/";

    public SpecialityRestController(SpecialityService service) {
        super(service);
    }

    @GetMapping("{id}")
    public SpecialityDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<SpecialityDTO> getAll() {
        return super.getAll();
    }
}
