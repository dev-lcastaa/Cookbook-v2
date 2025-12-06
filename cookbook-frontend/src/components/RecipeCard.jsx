import React from "react";

export default function RecipeCard({ recipe, onClick }) {
  return (
    <div
      className="recipe-card"
      onClick={onClick}
    >
      <h3>{recipe.recipeName}</h3>
    </div>
  );
}