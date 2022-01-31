package by.kopyshev.university.repository;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.repository.building.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> getByUsername(String username);
}
