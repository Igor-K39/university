package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacultyDepartmentRepository extends BaseRepository<FacultyDepartment> {

    @EntityGraph(value = "with-disciplines")
    @Query("SELECT fd FROM FacultyDepartment fd WHERE fd.id = :id")
    Optional<FacultyDepartment> getWithDisciplines(@Param("id") int id);

    @Query("SELECT fd FROM FacultyDepartment fd WHERE fd.faculty.id = :facultyId ORDER BY fd.name")
    Optional<List<FacultyDepartment>> getAll(@Param("facultyId") int facultyId);

    @EntityGraph(value = "with-disciplines")
    @Query("SELECT fd FROM FacultyDepartment fd ORDER BY fd.name")
    Optional<List<FacultyDepartment>> getAllWithDisciplines();

    @EntityGraph(value = "with-disciplines")
    @Query("SELECT fd FROM FacultyDepartment fd WHERE fd.faculty.id = :facultyId ORDER BY fd.name")
    Optional<List<FacultyDepartment>> getAllByFacultyWithDisciplines(@Param("facultyId") int facultyId);
}
