import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import CookbooksPage from "./pages/CookbooksPage";
import RecipesPage from "./pages/RecipesPage";
import ShowRecipeDetailsPage from "./pages/ShowRecipeDetailsPage";
import "./App.css";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/cookbooks" element={<CookbooksPage />} />
        <Route path="/cookbook/:id" element={<RecipesPage />} />
        <Route path="/cookbook/:cookbookId/recipe/:recipeId" element={<ShowRecipeDetailsPage />} />
      </Routes>
    </BrowserRouter>
  );
}
