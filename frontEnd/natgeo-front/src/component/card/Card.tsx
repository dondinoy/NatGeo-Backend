import { ReactNode } from "react";

type FC = (props: { children: ReactNode }) => ReactNode;

export const Card: FC = (props) => {

  return (

    <div className="bg-white border-red-200 border p-5 text-slate-500 text-xl shadow-2xl rounded-lg flex flex-col justify-evenly m-1">
      {props.children}

    </div>

  );

};