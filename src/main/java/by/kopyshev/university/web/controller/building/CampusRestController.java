package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.service.building.CampusService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CampusRestController.CAMPUS_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CampusRestController extends AbstractCampusController {
    public static final String CAMPUS_REST_URL = "/api/campuses/";

    public CampusRestController(CampusService campusService) {
        super(campusService);
    }

    @GetMapping("{id}")
    public CampusDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/by")
    public CampusDTO getBy(@RequestParam("number") String number) {
        return super.getByNumber(number);
    }

    @GetMapping("{id}/with-halls")
    public CampusDTO getWithHalls(@PathVariable("id") int id) {
        return super.getWithHalls(id);
    }

    @GetMapping
    public List<CampusDTO> getAll() {
        return super.getAll();
    }

    @GetMapping("/with-halls")
    public List<CampusWithHallsDTO> getAllWithHalls() {
        return super.getAllWithHalls();
    }
}
