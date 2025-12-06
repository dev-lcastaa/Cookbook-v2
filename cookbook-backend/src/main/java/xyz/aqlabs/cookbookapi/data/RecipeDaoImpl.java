package xyz.aqlabs.cookbookapi.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;
import xyz.aqlabs.cookbookapi.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Integer createRecipe(final CreateRecipeDto recipe) {

        Integer status;

        try {
            if(recipe == null){
                log.info("Recipe Data was NULL unable to create recipe");
                status = 0;
            }

            status = jdbcTemplate.update(
                    Constants.CREATE_RECIPE,
                    recipe.getCookbookId(),
                    recipe.getName(),
                    recipe.getIngredients(),
                    recipe.getInstructions()
            );

        } catch (Exception e) {
            log.error("Error creating recipe", e);
            status = 0;
        }

        return status;

    }

    @Override
    public Integer updateRecipe(final Recipe recipe) {
        Integer status;

        try {

            if(recipe == null) {
                log.info("Unable to update recipe due to NULL data");
                status = 0;
            }

            status = jdbcTemplate.update(
                    Constants.UPDATE_RECIPE,
                    recipe.getRecipeName(),
                    recipe.getIngredients(),
                    recipe.getInstructions(),
                    recipe.getRecipeId()
            );

        } catch (Exception e) {
            log.error("An error has occurred while updating", e);
            status = 0;
        }

        return status;
    }

    @Override
    public Integer deleteRecipe(final Integer recipeId) {

        Integer status;

        try {
            if (recipeId == null || recipeId == 0 || recipeId < 0) {
               log.info("Received invalid recipeId, unable to delete");
               status = 0;
            }
            status = jdbcTemplate.update(
                    Constants.DELETE_RECIPE,
                    recipeId
            );
        } catch (Exception e){
            log.error("An error has occurred while deleting recipe {}", recipeId, e);
            status = 0;
        }

        return status;

    }

    @Override
    public List<Recipe> getRecipesByCookbookId(final Integer cookbookId) {
        List<Recipe> recipes = new ArrayList<>();

        try {
            if (cookbookId == null || cookbookId <= 0) {
                log.info("An invalid cookbookId was received {}", cookbookId);
                return recipes;
            }

            SqlRowSet rs = jdbcTemplate.queryForRowSet(
                    Constants.GET_RECIPES_BY_COOKBOOK_ID,
                    cookbookId
            );

            while (rs.next()) {
                recipes.add(mapRowSetToRecipe(rs));
            }

        } catch (Exception e) {
            log.error("An error has occurred while getting recipes for cookbook {}", cookbookId, e);
        }

        return recipes;

    }

    @Override
    public Recipe getRecipeByRecipeId(final Integer recipeId) {

        Recipe recipe = null;

        try {
            if (recipeId == null || recipeId <= 0) {
                log.info("An invalid recipeId was received {}", recipeId);
                return recipe;
            }

            SqlRowSet rs = jdbcTemplate.queryForRowSet(
                    Constants.GET_RECIPE_BY_RECIPE_ID,
                    recipeId
            );

            if (rs.next()) {
                return mapRowSetToRecipe(rs);
            }

        } catch (Exception e) {
            log.error("An error has occurred while getting recipe {}", recipeId, e);
        }

        return recipe;
    }


    public Recipe mapRowSetToRecipe(SqlRowSet rs) {

        return new Recipe(
                rs.getInt("recipe_id"),
                rs.getInt("cookbook_id"),
                rs.getString("recipe_name"),
                rs.getString("ingredients"),
                rs.getString("instructions")
        );

    }
}
