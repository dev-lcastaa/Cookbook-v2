package xyz.aqlabs.cookbookapi.models.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCookbookDto {

    private Integer userId;
    private String name;
    private String ethnicity;
    private String timeCreated;
    private String lastUpdated;

}
