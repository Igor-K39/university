package by.kopyshev.university.repository.building;

import by.kopyshev.university.domain.building.LectureHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface LectureHallRepository extends JpaRepository<LectureHall, Integer> {

    @Query("SELECT lh FROM LectureHall lh WHERE lh.campus.number = :campusNumber AND lh.number = :number")
    Optional<LectureHall> getByNumber(@Param("campusNumber") String campusNumber, @Param("number") String number);

    @Query("SELECT lh FROM LectureHall lh ORDER BY lh.number")
    Optional<List<LectureHall>> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM LectureHall lh WHERE lh.id = :id")
    int delete(@Param("id") int id);
}
