package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.service.building.CampusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminCampusRestController.ADMIN_CAMPUS_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminCampusRestController extends AbstractCampusController {
    public static final String ADMIN_CAMPUS_REST_URL = "/api/admin/campuses/";

    public AdminCampusRestController(CampusService campusService) {
        super(campusService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampusDTO> createWithLocation(@RequestBody @Valid CampusDTO campusDTO) {
        CampusDTO created = super.create(campusDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_CAMPUS_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public CampusDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/by")
    public CampusDTO getBy(@RequestParam("number") String number) {
        return super.getByNumber(number);
    }

    @GetMapping
    public List<CampusDTO> getAll() {
        return super.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid CampusDTO campusDTO, @PathVariable int id) {
        super.update(campusDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
