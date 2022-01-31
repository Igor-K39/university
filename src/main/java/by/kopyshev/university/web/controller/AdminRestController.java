package by.kopyshev.university.web.controller;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.service.UserService;
import by.kopyshev.university.util.ValidationUtil;
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
public class AdminRestController {
    public static final String ADMIN_USER_REST_URL = "/api/admin/users/";

    private final UserService service;

    public AdminRestController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody @Valid User user) {
        User created = service.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USER_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public User get(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    @GetMapping()
    public List<User> getAll() {
        return service.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid User user, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(user, id);
        service.update(user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
