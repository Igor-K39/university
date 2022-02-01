package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.lecture.Discipline;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DisciplineRepository extends BaseRepository<Discipline> {

    @EntityGraph(value = "discipline-with-department")
    @Query("SELECT d FROM Discipline d WHERE d.facultyDepartment.id = :departmentId ORDER BY d.name")
    Optional<List<Discipline>> getAll(Integer departmentId);

    @EntityGraph(value = "discipline-with-department")
    @Query("SELECT d FROM Discipline d ORDER BY d.name")
    Optional<List<Discipline>> getAll();
}
