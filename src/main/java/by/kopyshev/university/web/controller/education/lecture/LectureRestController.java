package by.kopyshev.university.web.controller.education.lecture;

import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.service.education.LectureService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = LectureRestController.LECTURE_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LectureRestController extends AbstractLectureController {
    public static final String LECTURE_REST_URL = "/api/lectures";

    public LectureRestController(LectureService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public LectureDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<LectureDTO> getAll(@RequestParam(value = "groupId", required = false) Integer groupId,
                                   @RequestParam(value = "start", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                   @RequestParam(value = "end", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return super.getAllBetween(groupId, start, end);
    }
}
