package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.service.building.LectureHallService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = LectureHallController.HALL_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LectureHallController extends AbstractLectureHallRestController {
    public static final String HALL_REST_URL = "/api/halls/";

    public LectureHallController(LectureHallService service) {
        super(service);
    }

    @GetMapping("{id}")
    public LectureHallDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/by")
    public LectureHallDTO getBy(@RequestParam("campus") String campusNumber, @RequestParam("hall") String number) {
        return super.getByNumber(campusNumber, number);
    }

    @GetMapping
    public List<LectureHallDTO> getAll() {
        return super.getAll();
    }
}
