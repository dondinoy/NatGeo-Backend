  import { NavLink } from "react-router-dom";
  // import DarkModeToggle from "../darkModeToggle/DarkModeToggle";
  import { BsFillMenuAppFill, BsHouse, BsMenuApp, BsMenuButtonWide, BsMenuUp, BsOption } from "react-icons/bs";
  import { useContext } from "react";
  import { AuthContext } from "../../context/AuthConext";

  const Navbar = () => {
  const { isLoggedIn, logout } = useContext(AuthContext);

  return (
    <nav className="flex flex-row justify-between bg-slate-100 text-xl text-slate-900 dark:bg-slate-700 dark:text-white shadow-lg mb-1 p-4">
      <div className="flex flex-row gap-2">
        <NavLink to="/">
          <BsHouse />
        </NavLink>
      </div>

      <div className="flex flex-row gap-2">
        
        {!isLoggedIn && <NavLink to="/login">Login</NavLink>}
        {!isLoggedIn && <NavLink to="/register">Register</NavLink>}
       
        { isLoggedIn && <button onClick={()=>{logout()}}>Logout</button>}
        {/* <DarkModeToggle /> */}
        <NavLink to={"/menu"}><BsMenuUp /></NavLink>

      </div>
    </nav>
  );
};

export default Navbar;