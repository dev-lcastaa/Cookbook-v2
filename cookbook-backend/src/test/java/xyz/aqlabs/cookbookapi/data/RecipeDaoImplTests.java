package xyz.aqlabs.cookbookapi.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RecipeDaoImplTests {

    @Autowired
    private RecipeDaoImpl recipeDao;

    @Test
    void testGetRecipesByCookbookId() {
        List<Recipe> recipes = recipeDao.getRecipesByCookbookId(1);
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("New Recipe", recipes.get(0).getRecipeName());
    }

    @Test
    void testCreateRecipe() {
        CreateRecipeDto dto = new CreateRecipeDto(1, "New Recipe", "Ing1, Ing2", "Mix");
        int rows = recipeDao.createRecipe(dto);
        assertEquals(1, rows);

        List<Recipe> recipes = recipeDao.getRecipesByCookbookId(1);
        assertEquals(2, recipes.size());
    }

    @Test
    void testUpdateRecipe() {
        Recipe recipe = recipeDao.getRecipeByRecipeId(1);
        recipe.setRecipeName("Updated Recipe");
        int rows = recipeDao.updateRecipe(recipe);
        assertEquals(1, rows);

        Recipe updated = recipeDao.getRecipeByRecipeId(1);
        assertEquals("Updated Recipe", updated.getRecipeName());
    }

    @Test
    void testDeleteRecipe() {
        int rows = recipeDao.deleteRecipe(1);
        assertEquals(1, rows);

        Recipe deleted = recipeDao.getRecipeByRecipeId(1);
        assertNull(deleted);
    }
}
