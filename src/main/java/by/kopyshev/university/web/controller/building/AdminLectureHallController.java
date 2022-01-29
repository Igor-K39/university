package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.dto.building.LectureHallUpdateDTO;
import by.kopyshev.university.service.building.LectureHallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminLectureHallController.ADMIN_HALL_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminLectureHallController extends AbstractLectureHallRestController {
    public static final String ADMIN_HALL_REST_URL = "/api/admin/halls/";

    public AdminLectureHallController(LectureHallService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LectureHallDTO> createWithLocation(@RequestBody @Valid LectureHallUpdateDTO hallUpdateDTO) {
        LectureHallDTO created = super.create(hallUpdateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_HALL_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
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

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid LectureHallUpdateDTO hallUpdateDTO, @PathVariable int id) {
        super.update(hallUpdateDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
