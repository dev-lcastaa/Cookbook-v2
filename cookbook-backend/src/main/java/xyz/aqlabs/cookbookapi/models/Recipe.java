package xyz.aqlabs.cookbookapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe {

    private Integer recipeId;
    private Integer cookbookId;
    private String recipeName;
    private String ingredients;
    private String instructions;

}
