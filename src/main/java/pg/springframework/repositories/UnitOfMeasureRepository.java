package pg.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import pg.springframework.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);

}
