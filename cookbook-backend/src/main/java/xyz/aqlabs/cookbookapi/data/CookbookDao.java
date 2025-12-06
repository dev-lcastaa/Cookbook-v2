package xyz.aqlabs.cookbookapi.data;

import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;

import java.util.List;

public interface CookbookDao {

    Integer createCookbook(CreateCookbookDto cookbook);

    Integer updateCookbook(Cookbook cookbook);

    Integer deleteCookbookByCookbookId(Integer cookbookId);

    List<Cookbook> getListOfCookbooksByUserId(Integer userId);

    Cookbook getCookbookByCookbookIdAndUserId(Integer userId, Integer cookbookId);


}
