import { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import "./HamburgerMenu.css";

export default function HamburgerMenu() {
  const [open, setOpen] = useState(false);
  const menuRef = useRef(null);

  // Close on click outside
  useEffect(() => {
    function handleClickOutside(e) {
      if (menuRef.current && !menuRef.current.contains(e.target)) {
        setOpen(false);
      }
    }

    function handleEsc(e) {
      if (e.key === "Escape") setOpen(false);
    }

    document.addEventListener("mousedown", handleClickOutside);
    document.addEventListener("keydown", handleEsc);

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
      document.removeEventListener("keydown", handleEsc);
    };
  }, []);

  return (
    <div className="hamburger-menu" ref={menuRef}>
      <button
        className={`hamburger-icon ${open ? "open" : ""}`}
        onClick={() => setOpen(!open)}
        aria-label="Open menu"
      >
        <span />
        <span />
        <span />
      </button>

      <nav className={`menu-dropdown ${open ? "show" : ""}`}>
        <Link to="/cookbooks" onClick={() => setOpen(false)}>Cookbooks</Link>
        <Link to="/conversion-tool" onClick={() => setOpen(false)}>Conversion Tool</Link>
        <Link to="/recommendations" onClick={() => setOpen(false)}>Recommendations</Link>
      </nav>
    </div>
  );
}
