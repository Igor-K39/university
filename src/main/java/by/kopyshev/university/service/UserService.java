package by.kopyshev.university.service;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.UserMapper;
import by.kopyshev.university.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO create(UserUpdateDTO userUpdateDTO) {
        checkNew(userUpdateDTO);
        User user = userMapper.toEntity(userUpdateDTO);
        System.out.println(user);
        encodePassword(user);
        return userMapper.toDTO(repository.save(user));
    }

    public UserDTO get(int id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = repository.getAll().orElse(List.of());
        return userMapper.toDTO(users);
    }

    @Transactional
    public void update(UserUpdateDTO userUpdateDTO) {
        int id = userUpdateDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
        User user = userMapper.toEntity(userUpdateDTO);
        encodePassword(user);
        repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Student.class, id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException(User.class, "username = " + username));
        return userMapper.toDTO(user);
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
