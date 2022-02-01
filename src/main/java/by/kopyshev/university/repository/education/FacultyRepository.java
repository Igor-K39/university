package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends BaseRepository<Faculty> {

    @EntityGraph(value = "faculty-with-departments")
    @Query("SELECT f FROM Faculty f WHERE f.id = :id")
    Optional<Faculty> getWithDepartments(@Param("id") Integer id);

    @EntityGraph(value = "faculty-with-departments")
    @Query("SELECT f FROM Faculty f")
    Optional<List<Faculty>> getAllWithDepartments();

    @Query("SELECT f FROM Faculty f ORDER BY f.name")
    Optional<List<Faculty>> getAll();
}
