package by.kopyshev.university.web.controller.education.lecture;

import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.service.education.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = AdminLectureRestController.ADMIN_LECTURE_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminLectureRestController extends AbstractLectureController {
    public static final String ADMIN_LECTURE_REST_URL = "/api/admin/lectures";

    public AdminLectureRestController(LectureService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LectureDTO> createWithLocation(@RequestBody @Valid LectureUpdateDTO lectureUpdateDTO) {
        LectureDTO created = super.create(lectureUpdateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_LECTURE_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public LectureDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<LectureDTO> getAll(@RequestParam(value = "groupId", required = false) Integer groupId,
                                   @RequestParam(value = "start", required = false) LocalDate start,
                                   @RequestParam(value = "end", required = false) LocalDate end) {
        return super.getAllBetween(groupId, start, end);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid LectureUpdateDTO lectureUpdateDTO, @PathVariable int id) {
        super.update(lectureUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
