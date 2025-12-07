package xyz.aqlabs.cookbookapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aqlabs.cookbookapi.data.RecipeDaoImpl;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTests {

    @Mock
    private RecipeDaoImpl recipeDao;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;
    private CreateRecipeDto createRecipeDto;

    @BeforeEach
    void setUp() {
        recipe = new Recipe(
                1,
                1,
                "Pasta",
                "Noodles, Sauce",
                "Boil noodles"
        );

        createRecipeDto = new CreateRecipeDto(
                1,
                "Pasta",
                "Noodles, Sauce",
                "Boil noodles"
        );
    }


    @Test
    void testCreateRecipe() {
        when(recipeDao.createRecipe(createRecipeDto)).thenReturn(1);

        int result = recipeService.createRecipe(createRecipeDto);

        assertEquals(1, result);
        verify(recipeDao, times(1)).createRecipe(createRecipeDto);
    }


    @Test
    void testUpdateRecipe() {
        when(recipeDao.updateRecipe(recipe)).thenReturn(1);

        int result = recipeService.updateRecipe(recipe);

        assertEquals(1, result);
        verify(recipeDao, times(1)).updateRecipe(recipe);
    }


    @Test
    void testDeleteRecipe() {
        when(recipeDao.deleteRecipe(1)).thenReturn(1);

        int result = recipeService.deleteRecipe(1);

        assertEquals(1, result);
        verify(recipeDao, times(1)).deleteRecipe(1);
    }


    @Test
    void testGetRecipesByCookbookId() {
        when(recipeDao.getRecipesByCookbookId(1)).thenReturn(Collections.singletonList(recipe));

        List<Recipe> result = recipeService.getRecipesByCookbookId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pasta", result.get(0).getRecipeName());

        verify(recipeDao, times(1)).getRecipesByCookbookId(1);
    }


    @Test
    void testGetRecipeByRecipeId() {
        when(recipeDao.getRecipeByRecipeId(1)).thenReturn(recipe);

        Recipe result = recipeService.getRecipeByRecipeId(1);

        assertNotNull(result);
        assertEquals("Pasta", result.getRecipeName());

        verify(recipeDao, times(1)).getRecipeByRecipeId(1);
    }

}

