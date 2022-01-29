package by.kopyshev.university.repository.building;

import by.kopyshev.university.domain.building.LectureHall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureHallRepository extends BaseRepository<LectureHall> {

    @Query("SELECT lh FROM LectureHall lh WHERE lh.campus.number = :campusNumber AND lh.number = :number")
    Optional<LectureHall> getByNumber(@Param("campusNumber") String campusNumber, @Param("number") String number);
}
