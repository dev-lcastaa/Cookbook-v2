import React from "react";

export default function RecipeDetails({ recipe }) {
    
    if (!recipe) {
        return (
            <div style={{ color: "#b0b0b0", textAlign: "center", marginTop: "2rem" }}>
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
        <div style={{ color: "#e2e2e2" }}>
            <h2 style={{ fontSize: "1.5rem", marginTop: 0, color: "#ffffff" }}>
                {recipe.recipeName}
            </h2>

            <section style={{ marginBottom: "1.5rem" }}>
                <h3 style={{ fontSize: "1.1rem", color: "#ffffff", marginBottom: "0.5rem" }}>
                    Ingredients
                </h3>
                {ingredientsList.length > 0 ? (
                    <ol style={{ color: "#b0b0b0", paddingLeft: "1.5rem" }}>
                        {ingredientsList.map((ingredient, index) => (
                            <li key={index} style={{ marginBottom: "0.5rem" }}>
                                {ingredient.trim()}
                            </li>
                        ))}
                    </ol>
                ) : (
                    <p style={{ color: "#b0b0b0" }}>No ingredients listed</p>
                )}
            </section>

            <section>
                <h3 style={{ fontSize: "1.1rem", color: "#ffffff", marginBottom: "0.5rem" }}>
                    Instructions
                </h3>
                {instructionsList.length > 0 ? (
                    <ol style={{ color: "#b0b0b0", paddingLeft: "1.5rem" }}>
                        {instructionsList.map((instruction, index) => (
                            <li key={index} style={{ marginBottom: "0.5rem" }}>
                                {instruction.trim()}
                            </li>
                        ))}
                    </ol>
                ) : (
                    <p style={{ color: "#b0b0b0" }}>No instructions listed</p>
                )}
            </section>
        </div>
    );
}