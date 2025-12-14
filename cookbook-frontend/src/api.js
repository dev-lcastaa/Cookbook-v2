// src/api.js
const API_BASE = import.meta.env.VITE_API_URL

// Cookbooks
export async function fetchCookbooks(userId) {
  return fetch(`${API_BASE}/api/v1/cookbook/list?userId=${userId}`).then(response => response.json());
}

export async function createCookbook(data) {
  console.log("Creating cookbook with data:", data);
  return fetch(`${API_BASE}/api/v1/cookbook`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  }).then(res => res.json());
}

// Recipes
export async function createRecipe(data) {
  console.log("Creating recipe with data:", data);
  return fetch(`${API_BASE}/api/v1/recipe`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  }).then(res => res.json());
}

export async function updateRecipe(data) {
  console.log("Updating recipe with data:", data);
  return fetch(`${API_BASE}/api/v1/recipe`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
}

export async function deleteRecipe(recipeId) {
  return fetch(`${API_BASE}/api/v1/recipe?recipeId=${recipeId}`, {
    method: "DELETE",
  });
}

export async function fetchRecipe(recipeId) {
  return fetch(`${API_BASE}/api/v1/recipe?recipeId=${recipeId}`).then(response => response.json());
}