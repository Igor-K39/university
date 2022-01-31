package by.kopyshev.university.web.controller.user;

import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;
import by.kopyshev.university.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.ADMIN_USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserRestController {
    public static final String ADMIN_USER_REST_URL = "/api/admin/users/";

    public AdminRestController(UserService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createWithLocation(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        UserDTO created = super.create(userUpdateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USER_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public UserDTO get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @GetMapping()
    public List<UserDTO> getAll() {
        return super.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @PathVariable int id) {
        super.update(userUpdateDTO, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
