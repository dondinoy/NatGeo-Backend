import { ReactNode } from "react";

export type FC = (props: { children: ReactNode }) => ReactNode;


export interface Theme {

  isDark: boolean;

  toggleTheme: () => void;

}