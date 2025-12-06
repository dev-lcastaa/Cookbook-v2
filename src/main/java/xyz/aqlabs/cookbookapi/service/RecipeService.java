package xyz.aqlabs.cookbookapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbookapi.data.RecipeDaoImpl;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;

import java.util.List;

@Log4j2
@Service
public class RecipeService {

    @Autowired
    private RecipeDaoImpl dao;


    public Integer createRecipe(CreateRecipeDto recipe) {
        return dao.createRecipe(recipe);
    }


    public Integer updateRecipe(Recipe recipe) {
        return dao.updateRecipe(recipe);
    }


    public Integer deleteRecipe(Integer recipeId) {
        return dao.deleteRecipe(recipeId);
    }


    public List<Recipe> getRecipesByCookbookId(Integer cookbookId) {
        return dao.getRecipesByCookbookId(cookbookId);
    }


    public Recipe getRecipeByRecipeId(Integer recipeId) {
        return dao.getRecipeByRecipeId(recipeId);
    }

}
