package pg.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import pg.springframework.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
