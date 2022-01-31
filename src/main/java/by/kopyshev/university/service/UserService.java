package by.kopyshev.university.service;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.exception.NotFoundException;
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

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        checkNew(user);
        encodePassword(user);
        return repository.save(user);
    }

    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(User user) {
        int id = user.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
        encodePassword(user);
        repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Student.class, id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getByUsername(username).orElseThrow(() -> new NotFoundException(User.class, "username = " + username));
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
