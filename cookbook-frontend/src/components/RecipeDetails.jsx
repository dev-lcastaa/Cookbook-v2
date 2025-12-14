import React from "react";

export default function RecipeDetails({ recipe, onEdit, onDelete }) {

  if (!recipe) {
    return (
      <div className="details-empty-message">
        Select a recipe to view details
      </div>
    );
  }

  const ingredientsList = recipe.ingredients 
    ? recipe.ingredients.split('\n').filter(item => item.trim() !== '')
    : [];
  
  const instructionsList = recipe.instructions 
    ? recipe.instructions.split('\n').filter(item => item.trim() !== '')
    : [];

  return (
    <div className="details-container">
      <div className= "header-buttons">
          <button  onClick={() => onEdit(recipe)}> ✏️ Edit Recipe </button> 
      </div> 
      < div className="header-buttons">
          <button onClick={() => onDelete(recipe)}> 🗑️ Delete Recipe</button>
      </div>

      <div className="details-tile">
        <h2 className="details-title">{recipe.recipeName}</h2>
      </div>

      <div className="details-tile">
        <h3 className="section-title">Ingredients</h3>
        {ingredientsList.length > 0 ? (
          <ol className="details-list numbered">
            {ingredientsList.map((ingredient, index) => (
              <li key={index} className="details-list-item">
                {ingredient.trim()}
              </li>
            ))}
          </ol>
        ) : (
          <p className="details-empty">No ingredients listed</p>
        )}
      </div>

      <div className="details-tile">
        <h3 className="section-title">Instructions</h3>
        {instructionsList.length > 0 ? (
          <ol className="details-list numbered">
            {instructionsList.map((instruction, index) => (
              <li key={index} className="details-list-item">
                {instruction.trim()}
              </li>
            ))}
          </ol>
        ) : (
          <p className="details-empty">No instructions listed</p>
        )}
      </div>
    </div>
  );
}
