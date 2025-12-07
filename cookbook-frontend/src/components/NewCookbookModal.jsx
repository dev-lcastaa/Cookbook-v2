import { useState } from "react";
import "../Modal.css";

export default function NewCookbookModal({ title, onClose, onSubmit }) {

  const [cookbookName, setCookbookName] = useState("");
  const [ethnicity, setEthnicity] = useState("");

  const handleSubmit = () => {
    if (cookbookName.trim() !== "" && ethnicity.trim() !== "") {
      const createCookbookDto = {
        userId: 1,
        name: cookbookName,
        ethnicity: ethnicity
      }
      console.log("NewCookbookModal sending DTO:", createCookbookDto);
      onSubmit(createCookbookDto);
      onClose();
    }
  };

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h2>{title}</h2>

        <input
          type="text"
          placeholder="Enter name..."
          value={cookbookName}
          onChange={(e) => setCookbookName(e.target.value)}
        />

        <input
          type="text"
          placeholder="Enter ethnicity..."
          value={ethnicity}
          onChange={(e) => setEthnicity(e.target.value)}
        />

        <div className="modal-actions">
          <button onClick={handleSubmit}>Create</button>
          <button onClick={onClose} className="cancel">
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}