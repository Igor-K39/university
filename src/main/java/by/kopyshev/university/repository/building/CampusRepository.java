package by.kopyshev.university.repository.building;

import by.kopyshev.university.domain.building.Campus;
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
public interface CampusRepository extends JpaRepository<Campus, Integer> {

    Optional<Campus> getByNumber(@Param("number") String number);

    @Query("SELECT c FROM Campus c ORDER BY c.name")
    Optional<List<Campus>> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Campus c WHERE c.id = :id")
    int delete(@Param("id") int id);
}
