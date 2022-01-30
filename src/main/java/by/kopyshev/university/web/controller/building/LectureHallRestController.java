package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.service.building.LectureHallService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = LectureHallRestController.LECTURE_HALL_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LectureHallRestController extends AbstractLectureHallController {
    public static final String LECTURE_HALL_REST_URL = "/api/halls/";

    public LectureHallRestController(LectureHallService service) {
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
