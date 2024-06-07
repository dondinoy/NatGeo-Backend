// import { useState } from "react";
// import "./DarkModeToggle.module.scss"
// import { FaMoon, FaSun } from "react-icons/fa";

// const DarkModeToggle = () => {

//   //read the value from localStorage

//   const saved = localStorage.getItem("mode") === "dark";

 

//   //if saved == true => add dark to <body class="dark"

//   saved && document.body.classList.add("dark");

 

//   //state for the icon

//   const [isDark, setDark] = useState(saved);

 

//   const toggleTheme = () => {

//     //change the icon

//     setDark((p) => !p);

//     //change body class

//     document.body.classList.toggle("dark");

//     //save the change to local storage

//     localStorage.setItem("mode", !isDark ? "dark" : "");

//   };

 

//   return (

//     <button onClick={toggleTheme}>{isDark ? <FaSun  /> : <FaMoon />}</button>

//   );

// };

 

// export default DarkModeToggle;