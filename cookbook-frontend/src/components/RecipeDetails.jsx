import React from "react";

export default function RecipeDetails({ recipe }) {
    
    if (!recipe) {
        return (
            <div style={{ color: "#b0b0b0", textAlign: "center", marginTop: "2rem" }}>
                Select a recipe to view details
            </div>
        );
    }

    return (
        <div style={{ color: "#e2e2e2" }}>
            <h2 style={{ fontSize: "1.5rem", marginTop: 0, color: "#ffffff" }}>
                {recipe.recipeName}
            </h2>

            <section style={{ marginBottom: "1.5rem" }}>
                <h3 style={{ fontSize: "1.1rem", color: "#ffffff", marginBottom: "0.5rem" }}>
                    Ingredients
                </h3>
                <p style={{ color: "#b0b0b0", whiteSpace: "pre-wrap" }}>
                    {recipe.ingredients || "No ingredients listed"}
                </p>
            </section>

            <section>
                <h3 style={{ fontSize: "1.1rem", color: "#ffffff", marginBottom: "0.5rem" }}>
                    Instructions
                </h3>
                <p style={{ color: "#b0b0b0", whiteSpace: "pre-wrap" }}>
                    {recipe.instructions || "No instructions listed"}
                </p>
            </section>
        </div>
    );
}