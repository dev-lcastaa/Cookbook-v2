import { useState } from "react";
import { updateRecipe } from "../api";
import "../Modal.css";

export default function EditRecipeModal({ recipe, onClose, onSubmit }) {
  const [recipeName, setRecipeName] = useState(recipe.recipeName || "");
  const [ingredients, setIngredients] = useState(recipe.ingredients || "");
  const [instructions, setInstructions] = useState(recipe.instructions || "");

  const handleSubmit = async () => {
    if (recipeName.trim() && ingredients.trim() && instructions.trim()) {
      const updatedRecipe = {
        ...recipe,
        recipeId: recipe.recipeId,
        recipeName,
        ingredients,
        instructions,
      };
      try {
        await updateRecipe(updatedRecipe);
        if (onSubmit) onSubmit(updatedRecipe);
        onClose();
      } catch (e) {
        alert("Failed to update recipe.");
      }
    }
  };

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h2>Edit Recipe</h2>
        <input
          type="text"
          placeholder="Recipe name..."
          value={recipeName}
          onChange={e => setRecipeName(e.target.value)}
        />
        <textarea
          placeholder="Ingredients..."
          value={ingredients}
          onChange={e => setIngredients(e.target.value)}
          rows="5"
        />
        <textarea
          placeholder="Instructions..."
          value={instructions}
          onChange={e => setInstructions(e.target.value)}
          rows="5"
        />
        <div className="modal-actions">
          <button onClick={handleSubmit}>Update</button>
          <button onClick={onClose} className="cancel">Cancel</button>
        </div>
      </div>
    </div>
  );
}
