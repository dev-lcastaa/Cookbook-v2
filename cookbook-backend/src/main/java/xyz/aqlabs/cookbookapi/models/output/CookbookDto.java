package xyz.aqlabs.cookbookapi.models.output;

import lombok.Data;
import xyz.aqlabs.cookbookapi.models.Recipe;

import java.util.List;

@Data
public class CookbookDto {

    private int cookbookId;
    private String name;
    private String ethnicity;
    private String timeCreated;
    private String lastUpdated;
    private List<Recipe> recipes;

}
