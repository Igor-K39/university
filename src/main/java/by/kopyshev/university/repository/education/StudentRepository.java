package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends BaseRepository<Student> {

    @Query("SELECT s FROM Student s WHERE s.studentGroup.id = :groupId " +
            "ORDER BY s.person.lastName, s.person.firstName, s.person.middleName")
    Optional<List<Student>> getAll(@Param("groupId") int studentGroupId);

    @Query("SELECT s FROM Student s WHERE s.person.id = :personId ")
    Optional<Student> getByPerson(@Param("personId") int personId);
}
