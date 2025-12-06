package xyz.aqlabs.cookbookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbookapi.models.Recipe;
import xyz.aqlabs.cookbookapi.models.input.CreateRecipeDto;
import xyz.aqlabs.cookbookapi.service.RecipeService;


@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;


    @PostMapping
    public ResponseEntity<Integer> createRecipe(@RequestBody CreateRecipeDto dto) {
        Integer status = service.createRecipe(dto);
        return ResponseEntity
                .ok()
                .body(status);
    }

    @PutMapping
    public ResponseEntity<Integer> updateRecipe(@RequestBody Recipe recipe) {
        Integer status = service.updateRecipe(recipe);
        return ResponseEntity
                .ok()
                .body(status);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteRecipe(@RequestParam("recipeId") final int recipeId) {
        Integer status = service.deleteRecipe(recipeId);
        return ResponseEntity
                .ok()
                .body(status);
    }

}
