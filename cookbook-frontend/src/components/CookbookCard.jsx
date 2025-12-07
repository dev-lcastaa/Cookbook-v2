import React from "react";

export default function CookbookCard({ cookbook, onClick }) {
  return (
    <div
      className="cookbook-card"
      onClick={onClick}
    >
      <h2>{cookbook.name}</h2>
      <p>{cookbook.description}</p>
    </div>
  );
}