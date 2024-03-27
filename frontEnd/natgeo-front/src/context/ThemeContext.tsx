import { createContext, useEffect, useState } from "react";

import type { FC, Theme } from "../@types/Theme";

const initialValues = { isDark: false, toggleTheme: () => {} };

export const ThemeContext = createContext<Theme>(initialValues);

export const ThemeProvider: FC = (props) => {
  // useEffect hook -> run code once - when the component is first loaded

  useEffect(() => {
    const isDark = localStorage.getItem("theme") === "dark";

    setDark(isDark);

    if (isDark) {
      document.body.classList.add("dark");
    }
  }, []);

  // add localStorage (load / save)

  // document body class => add/remove .dark

  const [isDark, setDark] = useState(false);

  const toggleTheme = () => {
    setDark((d) => !d);
  };

  const values = { toggleTheme, isDark };
  return (
    <ThemeContext.Provider value={values}>
      {props.children}
    </ThemeContext.Provider>
  );
};
