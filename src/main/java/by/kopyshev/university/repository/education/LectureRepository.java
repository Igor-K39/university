package by.kopyshev.university.repository.education;

import by.kopyshev.university.domain.education.lecture.Lecture;
import by.kopyshev.university.repository.building.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends BaseRepository<Lecture> {

    @EntityGraph(value = "all-attributes")
    @Query("SELECT l FROM Lecture l WHERE l.date >= :start AND l.date <= :end")
    Optional<List<Lecture>> getAllByDate(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @EntityGraph(value = "all-attributes")
    @Query("SELECT l FROM Lecture l WHERE l.studentGroup.id = :groupId AND l.date >= :start AND l.date <= :end")
    Optional<List<Lecture>> getAllByGroupAndDate(@Param("groupId") int groupId,
                                                 @Param("start") LocalDate start, @Param("end") LocalDate end);
}
