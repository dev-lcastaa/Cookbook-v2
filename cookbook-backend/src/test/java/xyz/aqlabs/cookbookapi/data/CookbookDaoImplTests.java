package xyz.aqlabs.cookbookapi.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CookbookDaoImplTests {

    @Autowired
    private CookbookDaoImpl cookbookDao;


    @Test
    void testGetListOfCookbooksByUserId() {
        List<Cookbook> cookbooks = cookbookDao.getListOfCookbooksByUserId(1);

        assertNotNull(cookbooks);
        assertEquals(2, cookbooks.size());
        assertEquals("Quick & Easy", cookbooks.get(1).getName());
    }

    @Test
    void testGetCookbookByCookbookIdAndUserId() {
        Cookbook cookbook = cookbookDao.getCookbookByCookbookIdAndUserId(1, 1);

        assertNotNull(cookbook);
        assertEquals("Italian Classics", cookbook.getName());
        assertEquals(1, cookbook.getUserId());
    }

    @Test
    void testCreateCookbook() {
        CreateCookbookDto dto = new CreateCookbookDto(2, "New Cookbook", "Mexican");

        int rows = cookbookDao.createCookbook(dto);

        assertEquals(1, rows);

        List<Cookbook> books = cookbookDao.getListOfCookbooksByUserId(2);
        assertEquals(2, books.size());
        assertEquals("New Cookbook", books.get(1).getName());
    }

    @Test
    void testUpdateCookbook() {
        // Change the recipe added in data.sql
        Cookbook update = new Cookbook(1, 1, "Updated Name", "Greek", null, null);

        int rows = cookbookDao.updateCookbook(update);

        assertEquals(1, rows);

        Cookbook updated = cookbookDao.getCookbookByCookbookIdAndUserId(1, 1);
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testDeleteCookbookByCookbookId() {
        int rows = cookbookDao.deleteCookbookByCookbookId(1);
        assertEquals(1, rows);

        Cookbook deleted = cookbookDao.getCookbookByCookbookIdAndUserId(1, 1);
        assertNull(deleted);
    }

}
