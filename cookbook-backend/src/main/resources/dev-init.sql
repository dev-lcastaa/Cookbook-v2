-- Drop tables if they exist
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS cookbook;

-- Cookbook table
CREATE TABLE cookbook (
    cookbook_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    cookbook_name VARCHAR(255) NOT NULL,
    ethnicity VARCHAR(255),
    time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Recipe table
CREATE TABLE recipe (
    recipe_id INT PRIMARY KEY AUTO_INCREMENT,
    cookbook_id INT NOT NULL,
    recipe_name VARCHAR(255),
    ingredients VARCHAR,
    instructions VARCHAR,
    CONSTRAINT FK_recipe_cookbook FOREIGN KEY (cookbook_id) REFERENCES cookbook(cookbook_id) ON DELETE CASCADE
);

-- Insert sample cookbooks
INSERT INTO cookbook (user_id, cookbook_name, ethnicity) VALUES (1, 'Italian Classics', 'Italian');
INSERT INTO cookbook (user_id, cookbook_name, ethnicity) VALUES (1, 'Peepee', 'Italian');
INSERT INTO cookbook (user_id, cookbook_name, ethnicity) VALUES (2, 'Mexican Fiesta', 'Mexican');
INSERT INTO cookbook (user_id, cookbook_name, ethnicity) VALUES (1, 'Quick & Easy', 'American');

-- Insert sample recipes
INSERT INTO recipe (cookbook_id, recipe_name, ingredients, instructions) VALUES
(1, 'Spaghetti Carbonara',
 'Spaghetti
Eggs
Pancetta
Parmesan
Pepper',
 'Boil pasta.
Cook pancetta.
Mix eggs and cheese.
Combine.'),

(1, 'Pepperoni',
 'Pepperoni
Eggs
Pancetta
Parmesan
Pepper',
 'Boil pasta.
Cook pancetta.
Mix eggs and cheese.
Combine.'),

(2, 'Tacos',
 'Tortillas
Beef
Lettuce
Tomato
Cheese',
 'Cook beef.
Assemble tacos with ingredients.'),

(3, 'Grilled Cheese Sandwich',
 'Bread
Cheese
Butter',
 'Butter bread.
Place cheese.
Grill until golden.');

