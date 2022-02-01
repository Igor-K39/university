package by.kopyshev.university.repository.building;

import by.kopyshev.university.domain.building.LectureHall;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureHallRepository extends BaseRepository<LectureHall> {

    @Query("SELECT lh FROM LectureHall lh WHERE lh.campus.number = :campusNumber AND lh.number = :number")
    Optional<LectureHall> getByNumber(@Param("campusNumber") String campusNumber, @Param("number") String number);

    @EntityGraph("lecture-hall-with-campus")
    @Query("SELECT lh FROM LectureHall lh ORDER BY lh.number, lh.campus.number")
    Optional<List<LectureHall>> getAll();
}
