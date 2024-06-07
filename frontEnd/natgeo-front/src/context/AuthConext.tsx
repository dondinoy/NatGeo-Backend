import { createContext, useEffect, useState } from "react";
import { FC } from "../@types/Theme";
import { jwtDecode } from 'jwt-decode' // import dependency

interface AuthContextType {
  isLoggedIn: boolean;
  role:string | null;
  jwt?: string | null;
  login: (jwt: string) => void;
  logout: () => void;
}

const initialValues: AuthContextType = {
  isLoggedIn: false,
  role:null,
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
  const [role,setRole]=useState<string|null>();
    const token = localStorage.getItem("token");
const admin=localStorage.getItem('user')
  useEffect(() => {
    if (token) {
      setJWT(token);
      setIsLoggedIn(true);
      const user = jwtDecode(token); // decode your token here
     setRole(admin)

    } else {
      setJWT(null);
      setIsLoggedIn(false);
      setRole(null);
    }
  }, [admin]);

  const login = (jwt: string) => {
    setJWT(jwt);
    setIsLoggedIn(true);
    const user = jwtDecode(jwt); // decode your token here
    // const admin=localStorage.getItem('user')
    // if(admin){
    // setRole(admin)

    // }
    console.log(user.scope)
    localStorage.setItem("token", jwt);
  };

  const logout = () => {
    setJWT(null);
    setIsLoggedIn(false);
    setRole(null)
    localStorage.removeItem("token");
    localStorage.removeItem("user");
  };

  const values = {jwt, isLoggedIn, login, logout,role}
  return (
    <AuthContext.Provider value={values}>
      {children}
    </AuthContext.Provider>
  );
};

