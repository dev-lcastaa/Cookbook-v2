import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import CookbooksPage from "./pages/CookbooksPage";
import RecipesPage from "./pages/RecipesPage";
import "./App.css";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/cookbooks" element={<CookbooksPage />} />
        <Route path="/cookbook/:id" element={<RecipesPage />} />
      </Routes>
    </BrowserRouter>
  );
}
