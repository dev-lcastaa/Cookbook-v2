package xyz.aqlabs.cookbookapi.data;


import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;

import java.util.List;

public interface RecipeDao {

    Integer createRecipe(CreateRecipeDto recipe);

    Integer updateRecipe(Recipe recipe);

    Integer deleteRecipe(Integer recipeId);

    List<Recipe> getRecipesByCookbookId(Integer cookbookId);

    Recipe getRecipeByRecipeId(Integer recipeId);

}
