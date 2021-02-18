package pg.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import pg.springframework.model.Recipe;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByDescription(String description);

}
