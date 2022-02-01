package by.kopyshev.university.repository;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends BaseRepository<Person> {

    @Query("SELECT p FROM Person p WHERE p.passport = :passport")
    Optional<Person> getByPassport(@Param("passport") String passport);

    @Query("SELECT p FROM Person p ORDER BY p.firstName, p.lastName")
    Optional<List<Person>> getAll();
}
