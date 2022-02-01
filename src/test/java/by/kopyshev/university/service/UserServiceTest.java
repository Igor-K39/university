package by.kopyshev.university.service;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.UserMapper;
import by.kopyshev.university.testdata.UserConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.UserConstants.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Autowired
    UserMapper mapper;

    @Test
    void create() {
        UserUpdateDTO userUpdate = getNew();
        UserDTO created = service.create(userUpdate);
        created.setId(userUpdate.getId());

        UserDTO expected = mapper.toDTO(mapper.toEntity(userUpdate));
        USER_DTO_MATCHER.assertMatch(expected, created);
    }

    @Test
    void get() {
        UserDTO actual = service.get(UserConstants.STUDENT_ID);
        USER_DTO_MATCHER.assertMatch(studentDTO, actual);
    }

    @Test
    void getAll() {
        List<UserDTO> actual = service.getAll();
        USER_DTO_MATCHER.assertMatch(List.of(studentDTO, dispatcherDTO, adminDTO), actual);
    }

    @Test
    void update() {
        UserUpdateDTO updated = getUpdated(studentDTO);
        service.update(updated);
        USER_DTO_MATCHER.assertMatch(mapper.toDTO(mapper.toEntity(updated)), service.get(STUDENT_ID));
    }

    @Test
    void delete() {
        service.delete(STUDENT_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(STUDENT_ID));
    }

    @Test
    void loadUserByUsername() {
        UserDTO actual = (UserDTO) service.loadUserByUsername(STUDENT_USERNAME);
        USER_DTO_MATCHER.assertMatch(studentDTO, actual);
    }
}