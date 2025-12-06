package xyz.aqlabs.cookbookapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cookbook {

    private Integer cookbookId;
    private Integer userId;
    private String name;
    private String ethnicity;
    private String timeCreated;
    private String lastUpdated;

}
