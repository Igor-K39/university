package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.Speciality;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends BaseRepository<Speciality> {

    @EntityGraph("speciality-with-faculty")
    @Query("SELECT s FROM Speciality s ORDER BY s.name")
    Optional<List<Speciality>> getAll();
}
