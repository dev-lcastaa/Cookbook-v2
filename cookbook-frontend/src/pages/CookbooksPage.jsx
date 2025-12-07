import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CookbookCard from "../components/CookbookCard";
import NewCookbookModal from "../components/NewCookbookModal.jsx";
import { fetchCookbooks, createCookbook } from "../api";

export default function CookbooksPage() {
  const [cookbooks, setCookbooks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCookbooks(1).then(setCookbooks);
  }, []);

 const handleCreate = (createCookbookDto) => {
  createCookbook(createCookbookDto)
    .then(() => {
      // After successful creation, refresh the cookbook list
      return fetchCookbooks(1);
    })
    .then(updatedCookbooks => {
      setCookbooks(updatedCookbooks);
      setShowModal(false);
    })
    .catch(error => {
      console.error("Error creating cookbook:", error);
    });
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

      {showModal && (
        <NewCookbookModal 
          title="New Cookbook" 
          onClose={() => setShowModal(false)} 
          onSubmit={handleCreate} 
        />
      )}
    </div>
  );
}