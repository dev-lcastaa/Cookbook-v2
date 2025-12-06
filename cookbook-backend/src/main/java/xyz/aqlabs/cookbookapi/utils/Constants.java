package xyz.aqlabs.cookbookapi.utils;

public class Constants {

    // prevents instantiation
    private Constants(){}

    // ==============================
    // COOKBOOK QUERIES
    // ==============================

    public static final String GET_COOKBOOK_BY_COOKBOOK_ID_AND_USER_ID =
            "SELECT cookbook_id, user_id, cookbook_name, ethnicity, time_created, last_updated " +
                    "FROM cookbook " +
                    "WHERE cookbook_id = ? AND user_id = ?";

    public static final String GET_COOKBOOKS_BY_USER_ID =
            "SELECT cookbook_id, user_id, cookbook_name, ethnicity, time_created, last_updated " +
                    "FROM cookbook " +
                    "WHERE user_id = ?";

    public static final String CREATE_COOKBOOK =
            "INSERT INTO cookbook (user_id, cookbook_name, ethnicity, time_created, last_updated) " +
                    "VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

    public static final String UPDATE_COOKBOOK =
            "UPDATE cookbook SET cookbook_name = ?, ethnicity = ?, last_updated = CURRENT_TIMESTAMP " +
                    "WHERE cookbook_id = ? AND user_id = ?";

    public static final String DELETE_COOKBOOK =
            "DELETE FROM cookbook WHERE cookbook_id = ?";


    // ==============================
    // RECIPE QUERIES
    // ==============================

    public static final String GET_RECIPES_BY_COOKBOOK_ID =
            "SELECT recipe_id, cookbook_id, recipe_name, ingredients, instructions " +
                    "FROM recipe " +
                    "WHERE cookbook_id = ?";

    public static final String GET_RECIPE_BY_RECIPE_ID =
            "SELECT recipe_id, cookbook_id, recipe_name, ingredients, instructions " +
                    "FROM recipe " +
                    "WHERE recipe_id = ?";

    public static final String CREATE_RECIPE =
            "INSERT INTO recipe (cookbook_id, recipe_name, ingredients, instructions) " +
                    "VALUES (?, ?, ?, ?)";

    public static final String UPDATE_RECIPE =
            "UPDATE recipe SET recipe_name = ?, ingredients = ?, instructions = ? " +
                    "WHERE recipe_id = ?";

    public static final String DELETE_RECIPE =
            "DELETE FROM recipe WHERE recipe_id = ?";
}
