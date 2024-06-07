
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
import CreatePage from "./crud/CreatePage"
import UpdatePage from "./crud/UpdatePage";
import HomePage from "./homepage/HomePage";
import SearchBar from "../component/search/SearchBar";
import ProtectedRoute from "../component/ProtectedRoute";

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
    element:<ProtectedRoute><Animals /></ProtectedRoute>,
  },{
    path:"/scince",
    element:<ProtectedRoute> <Scince /> </ProtectedRoute>,
  },
    {
    path:"/history",
    element:<ProtectedRoute><HisAndCul /></ProtectedRoute>,
  },{
    path:"/environment",
    element:<ProtectedRoute><Environment /></ProtectedRoute>,
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
    element:<ProtectedRoute><Article /></ProtectedRoute>,
    errorElement:<ArticleIdError />
  },
  {
    path:"/error",
    element:<ErrorPage />,
  }
  ,
  {
    path:"/createPage",
    element:<CreatePage />,
  },
  {
    path:"/updatePage/:id",
    element:<UpdatePage />,
  },
  {
    path:"/homepage",
    element:<HomePage />,
  },
  {
    path:"/search",
    element:<SearchBar setResults={ undefined } />,
  }

]
},
];