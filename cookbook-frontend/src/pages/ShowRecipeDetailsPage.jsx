import { useLocation } from "react-router-dom";
import RecipeDetails from "../components/RecipeDetails";

export default function ShowRecipeDetailsPage() {
  const location = useLocation();
  const recipe = location.state?.recipe;

  if (!recipe) {
    return (
      <div style={{ padding: "2rem", color: "#e2e2e2" }}>
        <h2>No recipe data provided.</h2>
        <p>Please navigate here through a recipe card.</p>
      </div>
    );
  }

  return (
    <div style={{ padding: "2rem" }}>
      <RecipeDetails recipe={recipe} />
    </div>
  );
}
