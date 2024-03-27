
import Login from "./login/Login";
import Register from "./register/Register";
import Root from "../layout/root/Root";
import { RouteObject } from "react-router-dom";
import Article from "./article/Article";
import Articles from "./articles/Articles";
import ArticleIdError from "./error/ArticleIdError";
import ErrorPage from "./error/ErrorPage";
import Menu from "./menu/Menu";
import Animals from "./menuRouts/Animals";
import Scince from "./menuRouts/Scince";
import HisAndCul from "./menuRouts/HisAndCul";
import Environment from "./menuRouts/Environment";


export const routes: RouteObject[] = [

  {
    path: "/",
    element: <Root />,
    errorElement:<ErrorPage />,
    children:[
  {
    path:"/register",
    element:<Register />,
  },
  {
    path:"/menu",
    element:<Menu />,
  },
  {
    path:"/animals",
    element:<Animals />,
  },{
    path:"/scince",
    element:<Scince />,
  },
    {
    path:"/history",
    element:<HisAndCul />,
  },{
    path:"/environment",
    element:<Environment />,
  },
  {
    path:"/login",
    element:<Login />,
  },
  {
    index:true,
    element:<Articles />,
    errorElement:<ErrorPage />
  },
  {
    path:"/articles",
    element:<Articles />,
    errorElement:<ArticleIdError />

  },
  {
    path:"/article/:id",
    element:<Article />,
    errorElement:<ArticleIdError />
  },
  {
    path:"/error",
    element:<ErrorPage />,
  },
]
},
];