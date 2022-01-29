package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacultyDepartmentRepository extends BaseRepository<FacultyDepartment> {

    @Query("SELECT fd FROM FacultyDepartment fd WHERE fd.faculty.id = :facultyId ORDER BY fd.name")
    Optional<List<FacultyDepartment>> getAll(@Param("facultyId") int facultyId);
}
