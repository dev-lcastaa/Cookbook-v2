// src/api.js
export const API_BASE = "http://localhost:8080/api/v1"; // Adjust your Spring Boot base URL

// Cookbooks
export async function fetchCookbooks(userId) {
  return fetch(`${API_BASE}/cookbook/list?userId=${userId}`).then(response => response.json());
}

export async function createCookbook(data) {
  return fetch(`${API_BASE}/cookbook`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  }).then(res => res.json());
}
