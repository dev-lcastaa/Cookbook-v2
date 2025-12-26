import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import CookbooksPage from "./pages/CookbooksPage";
import RecipesPage from "./pages/RecipesPage";
import ShowRecipeDetailsPage from "./pages/ShowRecipeDetailsPage";
import HamburgerMenu from "./components/HamburgerMenu";
import "./App.css";
import ConversionTool from "./components/ConversionTool";

function AppContent() {
  const location = useLocation();
  const hideMenu = location.pathname === "/";

  return (
    <>
      {!hideMenu && <HamburgerMenu />}
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/cookbooks" element={<CookbooksPage />} />
        <Route path="/cookbook/:id" element={<RecipesPage />} />
        <Route path="/conversion-tool" element={<ConversionTool />} />
        <Route path="/cookbook/:cookbookId/recipe/:recipeId" element={<ShowRecipeDetailsPage />} />
      </Routes>
    </>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <AppContent />
    </BrowserRouter>
  );
}