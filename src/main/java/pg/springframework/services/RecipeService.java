package pg.springframework.services;

import pg.springframework.model.Recipe;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
