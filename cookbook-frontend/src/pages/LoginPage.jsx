import { useNavigate } from "react-router-dom";
import "../Login.css";

export default function LoginPage() {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/cookbooks");
  };

  return (
    <div className="login-screen">
      <div className="login-box">
        <h1>The Cookbook</h1>
        <button onClick={handleLogin}>Enter</button>
      </div>
    </div>
  );
}
