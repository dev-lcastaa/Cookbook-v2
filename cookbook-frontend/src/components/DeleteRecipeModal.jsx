import "../Modal.css";

export default function DeleteRecipeModal({ recipe, onClose, onConfirm }) {
  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h2>Delete Recipe</h2>
        <p>Are you sure you want to delete <b>{recipe.recipeName}</b>?</p>
        <div className="modal-actions">
          <button onClick={onConfirm} style={{ background: '#c0392b', color: '#fff' }}>Delete</button>
          <button onClick={onClose} className="cancel">Cancel</button>
        </div>
      </div>
    </div>
  );
}
