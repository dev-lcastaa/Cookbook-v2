package xyz.aqlabs.cookbookapi.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;
import xyz.aqlabs.cookbookapi.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class CookbookDaoImpl implements CookbookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Cookbook> getListOfCookbooksByUserId(final Integer userId) {
        List<Cookbook> cookbooks = new ArrayList<>();

        try {
            if (userId == null) {
                log.info("UserId was NULL");
                return cookbooks;

            }
            SqlRowSet rs = jdbcTemplate.queryForRowSet(
                    Constants.GET_COOKBOOKS_BY_USER_ID,
                    userId
            );
            while (rs.next()) {
                cookbooks.add(mapRowToCookbook(rs));
            }
        } catch (Exception e) {
            log.error("An error has occurred retrieving cookbooks for userId : {}", userId);
        }

        return cookbooks;
    }

    @Override
    public Cookbook getCookbookByCookbookIdAndUserId(final Integer userId, final Integer cookbookId) {

        Cookbook cookbook = null;

        try {

            if (userId == null || cookbookId == null) {
                return null;
            }
            SqlRowSet rs = jdbcTemplate.queryForRowSet(
                    Constants.GET_COOKBOOK_BY_COOKBOOK_ID_AND_USER_ID,
                    cookbookId,
                    userId
            );

            if (rs.next()) {
                cookbook = mapRowToCookbook(rs);
            }
        } catch (Exception e) {
            log.error(
                    "An error has occurred while getting cookbooks for user : {} ",
                    userId,
                    e
            );
        }

        return cookbook;
    }

    @Override
    public Integer createCookbook(final CreateCookbookDto cookbookToCreate) {
        Integer status = Integer.MIN_VALUE;
        try {
            if (cookbookToCreate == null) {
                log.info("Data to create cookbook was NULL");
                status = 1;
            }
            status = jdbcTemplate.update(
                    Constants.CREATE_COOKBOOK,
                    cookbookToCreate.getUserId(),
                    cookbookToCreate.getName(),
                    cookbookToCreate.getEthnicity()
            );
            return status;

        } catch (Exception e) {
            log.error("An error has occurred cookbook");
        }
        return status;
    }

    @Override
    public Integer updateCookbook(final Cookbook cookbookToUpdate) {

        Integer status = Integer.MIN_VALUE;

        try {
            if (cookbookToUpdate.getCookbookId() == null || cookbookToUpdate.getUserId() == null) {
                log.info(
                        "Either UserId : {} or cookbookId : {} was NULL",
                        cookbookToUpdate.getUserId(),
                        cookbookToUpdate.getCookbookId()
                );
                status = 1;
            }
            status = jdbcTemplate.update(
                    Constants.UPDATE_COOKBOOK,
                    cookbookToUpdate.getName(),
                    cookbookToUpdate.getEthnicity(),
                    cookbookToUpdate.getCookbookId(),
                    cookbookToUpdate.getCookbookId()
            );
            return status;

        } catch (Exception e) {
            log.error(
                    "An error has occurred updating cookbookId : {}",
                    cookbookToUpdate.getCookbookId()
            );
        }
        return status;
    }

    @Override
    public Integer deleteCookbookByCookbookId(final Integer cookbookId) {
        Integer status = Integer.MIN_VALUE;

        try {
            if (cookbookId == null) {
                log.info("cookbookId was NULL");
                status = 1;
            }

            status = jdbcTemplate.update(
                    Constants.DELETE_COOKBOOK,
                    cookbookId
            );
            return status;

        } catch (Exception e) {
            log.error("An error has occurred updating cookbookId", e);
        }
        return status;
    }


    //-------------------------------------[Helpers]----------------------------------------


    private Cookbook mapRowToCookbook(final SqlRowSet rs) {
        return new Cookbook(
                rs.getInt("cookbook_id"),
                rs.getInt("user_id"),
                rs.getString("cookbook_name"),
                rs.getString("ethnicity"),
                rs.getString("time_created"),
                rs.getString("last_updated")
        );
    }


}
