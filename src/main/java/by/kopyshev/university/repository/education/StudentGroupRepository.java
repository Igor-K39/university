package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentGroupRepository extends BaseRepository<StudentGroup> {

    @EntityGraph(value = "student-group-deep-all")
    @Query("SELECT sg FROM StudentGroup sg WHERE sg.id = :id")
    Optional<StudentGroup> getWithStudents(@Param("id") int id);

    @EntityGraph(value = "student-group-deep-all")
    @Query("SELECT sg FROM StudentGroup sg ORDER BY sg.name")
    Optional<List<StudentGroup>> getAllWithStudents();

    @EntityGraph(value = "student-group-deep-all")
    @Query("SELECT sg FROM StudentGroup sg ORDER BY sg.name")
    Optional<List<StudentGroup>> getAll();
}
