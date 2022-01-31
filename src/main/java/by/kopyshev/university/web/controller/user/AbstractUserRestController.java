package by.kopyshev.university.web.controller.user;

import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;
import by.kopyshev.university.service.UserService;
import by.kopyshev.university.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class AbstractUserRestController {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserRestController.class);
    private final UserService service;

    public AbstractUserRestController(UserService service) {
        this.service = service;
    }

    public UserDTO create(UserUpdateDTO userUpdateDTO) {
        log.info("Creating a new user from {}", userUpdateDTO);
        return service.create(userUpdateDTO);
    }

    public UserDTO get(int id) {
        log.info("Getting user with id {}", id);
        return service.get(id);
    }

    public List<UserDTO> getAll() {
        log.info("Getting all users");
        return service.getAll();
    }

    public void update(UserUpdateDTO userUpdateDTO, int id) {
        log.info("Updating user with id {} by {}", id, userUpdateDTO);
        ValidationUtil.assureIdConsistent(userUpdateDTO, id);
        service.update(userUpdateDTO);
    }

    public void delete(@PathVariable int id) {
        log.info("Deleting user with id {}", id);
        service.delete(id);
    }
}
