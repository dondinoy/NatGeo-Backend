import { createContext, useEffect, useState } from "react";
import { FC } from "../@types/Theme";

interface AuthContextType {
  isLoggedIn: boolean;
  jwt?: string | null;
  login: (jwt: string) => void;
  logout: () => void;
}

const initialValues: AuthContextType = {
  isLoggedIn: false,
  jwt: "",
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  login: (_jwt) => {},
  logout: () => {},
};


export const AuthContext = createContext<AuthContextType>(initialValues);

// FC with children
export const AuthContextProvider: FC = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [jwt, setJWT] = useState<string | null>();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setJWT(token);
      setIsLoggedIn(true);
    } else {
      setJWT(null);
      setIsLoggedIn(false);
    }
  }, []);

  const login = (jwt: string) => {
    setJWT(jwt);
    setIsLoggedIn(true);
    localStorage.setItem("token", jwt);
  };

  const logout = () => {
    setJWT(null);
    setIsLoggedIn(false);
    localStorage.removeItem("token");
  };

  const values = {jwt, isLoggedIn, login, logout}
  return (
    <AuthContext.Provider value={values}>
      {children}
    </AuthContext.Provider>
  );
};

