package xyz.aqlabs.cookbookapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.aqlabs.cookbookapi.data.CookbookDaoImpl;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;
import xyz.aqlabs.cookbookapi.models.output.CookbookDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CookbookServiceTests {

    @Mock
    private CookbookDaoImpl cookbookDao;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private CookbookService cookbookService;

    private Cookbook cookbook;
    private Recipe recipe;
    private CreateCookbookDto createCookbookDto;

    @BeforeEach
    void setUp() {
        cookbook = new Cookbook(1, 1, "Italian Cookbook", "Italian", "2025-12-06", "2025-12-06");
        recipe = new Recipe(1, 1, "Pasta", "Noodles, Sauce", "Boil noodles");
        createCookbookDto = new CreateCookbookDto(1, "New Cookbook", "Mexican", null, null);
    }


    @Test
    void testCreateCookbook() {
        when(cookbookDao.createCookbook(createCookbookDto)).thenReturn(1);

        int result = cookbookService.createCookbook(createCookbookDto);

        assertEquals(1, result);
        verify(cookbookDao, times(1)).createCookbook(createCookbookDto);
    }


    @Test
    void testUpdateCookbook() {
        when(cookbookDao.updateCookbook(cookbook)).thenReturn(1);

        int result = cookbookService.updateCookbook(cookbook);

        assertEquals(1, result);
        verify(cookbookDao, times(1)).updateCookbook(cookbook);
    }


    @Test
    void testDeleteCookbook() {
        when(cookbookDao.deleteCookbookByCookbookId(1)).thenReturn(1);

        int result = cookbookService.deleteCookbook(1);

        assertEquals(1, result);
        verify(cookbookDao, times(1)).deleteCookbookByCookbookId(1);
    }


    @Test
    void testGetListOfCookbooksByUserId() {
        when(cookbookDao.getListOfCookbooksByUserId(1)).thenReturn(Collections.singletonList(cookbook));
        when(recipeService.getRecipesByCookbookId(1)).thenReturn(Collections.singletonList(recipe));

        List<CookbookDto> result = cookbookService.getListOfCookbooksByUserId(1);

        assertNotNull(result);
        assertEquals(1, result.size());

        CookbookDto dto = result.get(0);
        assertEquals(cookbook.getCookbookId(), dto.getCookbookId());
        assertEquals(cookbook.getName(), dto.getName());
        assertEquals(1, dto.getRecipes().size());
        assertEquals(recipe.getRecipeName(), dto.getRecipes().get(0).getRecipeName());

        verify(cookbookDao, times(1)).getListOfCookbooksByUserId(1);
        verify(recipeService, times(1)).getRecipesByCookbookId(1);
    }


    @Test
    void testGetCookbookByCookbookIdAndUserId() {
        when(cookbookDao.getCookbookByCookbookIdAndUserId(1, 1)).thenReturn(cookbook);
        when(recipeService.getRecipesByCookbookId(1)).thenReturn(Arrays.asList(recipe));

        CookbookDto result = cookbookService.getCookbookByCookbookIdAndUserId(1, 1);

        assertNotNull(result);
        assertEquals(cookbook.getCookbookId(), result.getCookbookId());
        assertEquals(cookbook.getName(), result.getName());
        assertEquals(1, result.getRecipes().size());
        assertEquals(recipe.getRecipeName(), result.getRecipes().get(0).getRecipeName());

        verify(cookbookDao, times(1)).getCookbookByCookbookIdAndUserId(1, 1);
        verify(recipeService, times(1)).getRecipesByCookbookId(1);
    }

}
