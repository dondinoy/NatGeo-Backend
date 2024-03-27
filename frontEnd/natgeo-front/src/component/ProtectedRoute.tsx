import { useContext } from "react";
import { FC } from "../@types/types";
import { AuthContext } from "../context/AuthConext";
import { Navigate } from "react-router-dom";

const ProtectedRoute: FC = ({ children }) => {
  // if logged in -> continue
  const { isLoggedIn } = useContext(AuthContext);

  if (!isLoggedIn) {
    return <Navigate to={"/login"} replace />;
  }

  return children;
};

export default ProtectedRoute;