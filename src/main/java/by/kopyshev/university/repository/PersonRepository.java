package by.kopyshev.university.repository;

import by.kopyshev.university.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.passport = :passport")
    Optional<Person> getByPassport(@Param("passport") String passport);

    @Query("SELECT p FROM Person p ORDER BY p.lastName, p.firstName, p.middleName")
    Optional<List<Person>> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Person p WHERE p.id = :id")
    int delete(@Param("id") int id);
}
