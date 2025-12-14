import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { fetchCookbooks, fetchRecipe, updateRecipe, deleteRecipe } from "../api";
import RecipeDetails from "../components/RecipeDetails";
import EditRecipeModal from "../components/EditRecipeModal";
import DeleteRecipeModal from "../components/DeleteRecipeModal";

export default function ShowRecipeDetailsPage() {
  const { cookbookId, recipeId } = useParams();
  const [recipe, setRecipe] = useState(null);
  const [cookbookName, setCookbookName] = useState("");
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCookbooks(1).then(data => {
      const cookbook = data.find(c => c.cookbookId === parseInt(cookbookId));
      if (cookbook) {
        setCookbookName(cookbook.name);
        const foundRecipe = (cookbook.recipes || []).find(r => r.recipeId === parseInt(recipeId));
        setRecipe(foundRecipe || null);
      }
    });
  }, [cookbookId, recipeId]);

  const handleEditClick = (recipe) => setShowEditModal(true);
  const handleDeleteClick = (recipe) => setShowDeleteModal(true);

  const handleUpdateRecipe = (updatedRecipe) => {
    updateRecipe(updatedRecipe).then(() => {
      fetchRecipe(recipeId).then((freshRecipe) => {
        setRecipe(freshRecipe);
        setShowEditModal(false);
      });
    });
  };

  const handleConfirmDelete = () => {
    deleteRecipe(recipe.recipeId).then(() => {
      setShowDeleteModal(false);
      navigate(`/cookbook/${cookbookId}`);
    });
  };

  if (!recipe) {
    return (
      <div style={{ padding: "2rem", color: "#e2e2e2" }}>
        <h2>No recipe found.</h2>
        <button onClick={() => navigate(-1)} style={{marginTop: '1rem'}}>Back</button>
      </div>
    );
  }

  return (
    <div style={{ padding: "2rem" }}>
      <header className="recipes-header">
        <button onClick={() => navigate(`/cookbook/${cookbookId}`)} className="back-btn">← Back to Recipes</button>
        <h1>📖 {cookbookName}</h1>
      </header>
      <RecipeDetails
        recipe={recipe}
        onEdit={handleEditClick}
        onDelete={handleDeleteClick}
      />
      {showEditModal && (
        <EditRecipeModal
          recipe={recipe}
          onClose={() => setShowEditModal(false)}
          onSubmit={handleUpdateRecipe}
        />
      )}
      {showDeleteModal && (
        <DeleteRecipeModal
          recipe={recipe}
          onClose={() => setShowDeleteModal(false)}
          onConfirm={handleConfirmDelete}
        />
      )}
    </div>
  );
}
