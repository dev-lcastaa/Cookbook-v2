package xyz.aqlabs.cookbookapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbookapi.data.CookbookDaoImpl;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;
import xyz.aqlabs.cookbookapi.models.output.CookbookDto;
import xyz.aqlabs.cookbookapi.models.Recipe;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CookbookService {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CookbookDaoImpl dao;


    public Integer createCookbook(final CreateCookbookDto dto) {
        return dao.createCookbook(dto);
    }

    public Integer updateCookbook(final Cookbook cookbook){
        return dao.updateCookbook(cookbook);
    }

    public Integer deleteCookbook(final Integer cookbookId) {
        return dao.deleteCookbookByCookbookId(cookbookId);
    }

    public List<CookbookDto> getListOfCookbooksByUserId(final Integer userId) {
        List<Cookbook> cookbooks = dao.getListOfCookbooksByUserId(userId);
        List<CookbookDto> fullyPopulatedCookbooks = new ArrayList<>();
        cookbooks.forEach(
                cookbook -> {
                    List<Recipe> recipes = populateCookbookRecipes(cookbook.getCookbookId());
                    fullyPopulatedCookbooks.add(getFullyConstructedCookbook(cookbook, recipes));
        });
        return fullyPopulatedCookbooks;
    }

    public CookbookDto getCookbookByCookbookIdAndUserId(final Integer userId, final Integer cookbookId) {
        Cookbook cookbook = dao.getCookbookByCookbookIdAndUserId(userId, cookbookId);
        List<Recipe> recipes = recipeService.getRecipesByCookbookId(cookbookId);
        return getFullyConstructedCookbook(cookbook, recipes);
    }


    // -------------------------------- [ Helper Methods ] --------------------

    private List<Recipe> populateCookbookRecipes(final int cookbookId){
        return recipeService.getRecipesByCookbookId(cookbookId);
    }

    private CookbookDto getFullyConstructedCookbook(final Cookbook cookbook, final List<Recipe> recipes){
        CookbookDto dto = new CookbookDto();

        dto.setCookbookId(cookbook.getCookbookId());
        dto.setName(cookbook.getName());
        dto.setEthnicity(cookbook.getEthnicity());
        dto.setTimeCreated(cookbook.getTimeCreated());
        dto.setLastUpdated(cookbook.getLastUpdated());
        dto.setRecipes(recipes);

        return dto;

    }

}
