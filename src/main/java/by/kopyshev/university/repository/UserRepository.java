package by.kopyshev.university.repository;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> getByUsername(String username);

    @EntityGraph("user-with-person")
    @Query("SELECT u FROM User u ORDER BY u.person.lastName, u.person.firstName")
    Optional<List<User>> getAll();
}
