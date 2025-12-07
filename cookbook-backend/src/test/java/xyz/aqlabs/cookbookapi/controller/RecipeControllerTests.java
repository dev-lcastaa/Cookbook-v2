package xyz.aqlabs.cookbookapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;
import xyz.aqlabs.cookbookapi.service.RecipeService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTests {

    private MockMvc mockMvc;

    @Mock
    private RecipeService service;

    @InjectMocks
    private RecipeController controller;

    private ObjectMapper objectMapper;

    private Recipe recipe;
    private CreateRecipeDto createDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        createDto = new CreateRecipeDto(
                1,
                "Pasta",
                "Noodles, Sauce",
                "Boil noodles"
        );

        recipe = new Recipe(
                1,
                1,
                "Pasta",
                "Noodles, Sauce",
                "Boil noodles"
        );
    }


    @Test
    void testCreateRecipe() throws Exception {
        when(service.createRecipe(createDto)).thenReturn(1);

        mockMvc.perform(post("/api/v1/recipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).createRecipe(createDto);
    }


    @Test
    void testUpdateRecipe() throws Exception {
        when(service.updateRecipe(recipe)).thenReturn(1);

        mockMvc.perform(put("/api/v1/recipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).updateRecipe(recipe);
    }


    @Test
    void testDeleteRecipe() throws Exception {
        when(service.deleteRecipe(1)).thenReturn(1);

        mockMvc.perform(delete("/api/v1/recipe")
                        .param("recipeId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).deleteRecipe(1);
    }
}
