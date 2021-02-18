package pg.springframework.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pg.springframework.model.*;
import pg.springframework.repositories.CategoryRepository;
import pg.springframework.repositories.RecipeRepository;
import pg.springframework.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }


    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get Unit Of Measures
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cups");
        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of Measure Not Found");
        }


        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();


        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 avocado, salt etc.");


        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Notes to prepare Guacamole");

        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(3), teaSpoonUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Lime", new BigDecimal(4), tableSpoonUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Onion", new BigDecimal(5), eachUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Chills", new BigDecimal(6), dashUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(7), pintUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Pepper", new BigDecimal(8), cupsUom));
        guacamoleRecipe.getIngredients().add(new Ingredient("Tomato", new BigDecimal(9), eachUom));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacamoleRecipe);

        return recipes;
    }
}
