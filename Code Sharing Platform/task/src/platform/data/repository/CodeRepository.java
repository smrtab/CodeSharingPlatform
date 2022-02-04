package platform.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.data.entity.Code;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    List<Code> findAll();
    List<Code> findTop10ByOrderByDateDesc();
    Optional<Code> findByToken(String token);

    @Query(value = "SELECT * " +
        "FROM code " +
        "WHERE views = -1 " +
        "AND time = -1 " +
        "ORDER BY date DESC " +
        "LIMIT 10", nativeQuery = true)
    List<Code> findAvailable();

    @Query(value = "SELECT * " +
        "FROM code " +
        "WHERE (views > 0 OR views = -1) " +
        "AND (time = -1 OR (DATEDIFF('SECOND', date, CURRENT_TIMESTAMP) < time)) " +
        "AND token = ?1", nativeQuery = true)
    Optional<Code> findOptionalByToken(String token);
}