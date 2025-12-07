-- Drop tables if they exist
DROP TABLE IF EXISTS recipe CASCADE;
DROP TABLE IF EXISTS cookbook CASCADE;

-- Cookbook table
CREATE TABLE cookbook (
    cookbook_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    cookbook_name VARCHAR(255) NOT NULL,
    ethnicity VARCHAR(255),
    time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Recipe table
CREATE TABLE recipe (
    recipe_id SERIAL PRIMARY KEY,
    cookbook_id INT NOT NULL,
    recipe_name VARCHAR(255),
    ingredients TEXT,
    instructions TEXT,
    CONSTRAINT FK_recipe_cookbook FOREIGN KEY (cookbook_id) REFERENCES cookbook(cookbook_id) ON DELETE CASCADE
);
