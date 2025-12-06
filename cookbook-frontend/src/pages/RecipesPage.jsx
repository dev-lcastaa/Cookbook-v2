import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import RecipeCard from "../components/RecipeCard";
import RecipeDetails from "../components/RecipeDetails";
import Modal from "../components/Modal";
import { fetchCookbooks } from "../api";

export default function RecipesPage() {
  const { id: cookbookId } = useParams();
  const [recipes, setRecipes] = useState([]);
  const [selectedRecipe, setSelectedRecipe] = useState(null);
  const [recipeDetails, setRecipeDetails] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [cookbookName, setCookbookName] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetchCookbooks(1).then(data => {
      console.log("All cookbooks:", data);
      const cookbook = data.find(c => c.cookbookId === parseInt(cookbookId));
      console.log("Found cookbook:", cookbook);
      if (cookbook) {
        setCookbookName(cookbook.name);
        setRecipes(cookbook.recipes || []);
        console.log("Set recipes:", cookbook.recipes);
      }
    });
  }, [cookbookId]);

  const handleSelectRecipe = (recipe) => {
    console.log("Selected recipe:", recipe);
    setSelectedRecipe(recipe.recipeId);
    setRecipeDetails(recipe);
  };

  const handleCreateRecipe = (name) => {
    // Add your create recipe logic here
  };

  return (
    <div className="recipes-page">
      <header className="recipes-header">
        <div>
          <button onClick={() => navigate("/cookbooks")} className="back-btn">← Back</button>
          <h1>📖 {cookbookName}</h1>
        </div>
        <button onClick={() => setShowModal(true)}>+ Add Recipe</button>
      </header>

      <div className="recipes-content">
        <div className="recipes-grid">
          {recipes && recipes.length > 0 ? (
            recipes.map((recipe) => (
              <RecipeCard 
                key={recipe.recipeId} 
                recipe={recipe} 
                onClick={() => handleSelectRecipe(recipe)} 
              />
            ))
          ) : (
            <p style={{ color: "#e2e2e2" }}>No recipes found</p>
          )}
        </div>

        {selectedRecipe && (
          <div className="recipe-details-panel">
            <RecipeDetails recipe={recipeDetails} />
          </div>
        )}
      </div>

      {showModal && (
        <Modal title="New Recipe" onClose={() => setShowModal(false)} onSubmit={handleCreateRecipe} />
      )}
    </div>
  );
}