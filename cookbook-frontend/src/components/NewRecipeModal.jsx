import { useState } from "react";
import "../Modal.css";

export default function NewRecipeModal({ title, cookbookId, onClose, onSubmit }) {
  const [recipeName, setRecipeName] = useState("");
  const [ingredients, setIngredients] = useState("");
  const [instructions, setInstructions] = useState("");

  const handleSubmit = () => {
    if (recipeName.trim() !== "" && ingredients.trim() !== "" && instructions.trim() !== "") {
      const createRecipeDto = {
        cookbookId: parseInt(cookbookId),
        recipeName: recipeName,
        ingredients: ingredients,
        instructions: instructions
      };
      console.log("NewRecipeModal sending DTO:", createRecipeDto);
      onSubmit(createRecipeDto);
      onClose();
    }
  };

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h2>{title}</h2>

        <input
          type="text"
          placeholder="Recipe name..."
          value={recipeName}
          onChange={(e) => setRecipeName(e.target.value)}
        />

        <textarea
          placeholder="Ingredients..."
          value={ingredients}
          onChange={(e) => setIngredients(e.target.value)}
          rows="5"
        />

        <textarea
          placeholder="Instructions..."
          value={instructions}
          onChange={(e) => setInstructions(e.target.value)}
          rows="5"
        />

        <div className="modal-actions">
          <button onClick={handleSubmit}>Create</button>
          <button onClick={onClose} className="cancel">
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}