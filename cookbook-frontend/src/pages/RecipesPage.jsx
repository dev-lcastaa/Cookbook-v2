import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import RecipeCard from "../components/RecipeCard";
import RecipeDetails from "../components/RecipeDetails";
import { fetchCookbooks, createRecipe } from "../api";
import NewRecipeModal from "../components/NewRecipeModal";

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
      const cookbook = data.find(c => c.cookbookId === parseInt(cookbookId));
      if (cookbook) {
        setCookbookName(cookbook.name);
        setRecipes(cookbook.recipes || []);
      }
    });
  }, [cookbookId]);

  const handleSelectRecipe = (recipe) => {
    setSelectedRecipe(recipe.recipeId);
    setRecipeDetails(recipe);
  };

  const handleCreateRecipe = (createRecipeDto) => {
     console.log("Creating recipe with DTO:", createRecipeDto);
    createRecipe(createRecipeDto)
      .then(() => {
        // After successful creation, refresh the cookbook list
        return fetchCookbooks(1);
      })
      .then(data => {
        const cookbook = data.find(c => c.cookbookId === parseInt(cookbookId));
        if (cookbook) {
          setRecipes(cookbook.recipes || []);
        }
        setShowModal(false);
      })
      .catch(error => {
        console.error("Error creating recipe:", error);
      });
  };

  return (
    <div className="recipes-page">
      <header className="recipes-header">
        <div>
          <button onClick={() => navigate("/cookbooks")} className="back-btn">← Back</button>
          <h1>📖 {cookbookName}</h1>
        </div>
        <button onClick={() => setShowModal(true)}>+ Add Recipe</button>
        <button 
            disabled={!selectedRecipe}
            // onClick={() => setShowEditModal(true)}
        > ✏️ Edit Recipe
        </button>
        <button 
          disabled={!selectedRecipe}
          // onClick={handleDeleteRecipe}
        > 🗑️ Delete Recipe</button>
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
        <NewRecipeModal 
          title="New Recipe"
          cookbookId={cookbookId} 
          onClose={() => setShowModal(false)} 
          onSubmit={handleCreateRecipe} 
        />
      )}
    </div>
  );
}