package xyz.aqlabs.cookbookapi.models.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRecipeDto {

    private int cookbookId;
    private String recipeName;
    private String ingredients;
    private String instructions;

}
