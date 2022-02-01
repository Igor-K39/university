package by.kopyshev.university.repository.building;

import by.kopyshev.university.domain.building.Campus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampusRepository extends BaseRepository<Campus> {

    Optional<Campus> getByNumber(@Param("number") String number);

    @EntityGraph(value = "campus-with-lecture-halls")
    @Query("SELECT c FROM Campus c WHERE c.id = :id")
    Optional<Campus> getWithHalls(@Param("id") int id);

    @EntityGraph(value = "campus-with-lecture-halls")
    @Query("SELECT c FROM Campus c ORDER BY c.number")
    Optional<List<Campus>> getAllWithHalls();

    @Query("SELECT c FROM Campus c ORDER BY c.number")
    Optional<List<Campus>> getAll();
}
