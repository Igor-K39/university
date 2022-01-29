package by.kopyshev.university.repository.education.role;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EducatorRepository extends BaseRepository<Educator> {

    @Query("SELECT e FROM Educator e WHERE e.facultyDepartment.id = :facultyDepartmentId " +
            "ORDER BY e.person.lastName, e.person.firstName, e.person.middleName")
    Optional<List<Educator>> getAll(@Param("facultyDepartmentId") int facultyDepartmentId);
}
