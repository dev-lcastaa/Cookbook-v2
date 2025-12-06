import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CookbookCard from "../components/CookbookCard";
import Modal from "../components/Modal";
import { fetchCookbooks } from "../api";


export default function CookbooksPage() {
  const [cookbooks, setCookbooks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCookbooks(1).then(data => {
      console.log("Cookbooks data:", data);
      setCookbooks(data);
    });
  }, []);

  const handleCreate = (name) => {
    // Add your create cookbook logic here
  };

  return (
    <div className="cookbooks-page">
      <header className="cookbooks-header">
        <h1>📚 My Cookbooks</h1>
        <button onClick={() => setShowModal(true)}>+ Create Cookbook</button>
      </header>

      <div className="cookbooks-grid">
        {cookbooks.map((cookbook) => (
          <CookbookCard
            key={cookbook.cookbookId}
            cookbook={cookbook}
            onClick={() => navigate(`/cookbook/${cookbook.cookbookId}`)}
          />
        ))}
      </div>

      {showModal && <Modal title="New Cookbook" onClose={() => setShowModal(false)} onSubmit={handleCreate} />}
    </div>
  );
}