import { useState } from "react";
import "../Modal.css";

export default function Modal({ title, onClose, onSubmit }) {
  const [value, setValue] = useState("");

  const handleSubmit = () => {
    if (value.trim() !== "") {
      onSubmit(value);
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
          value={value}
          onChange={(e) => setValue(e.target.value)}
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
