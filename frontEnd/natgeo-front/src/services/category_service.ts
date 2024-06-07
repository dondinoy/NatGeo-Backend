import axios from "axios";
import { request } from "../utills/axios-helper";
import { baseUrl } from "./auth-service";

const redirectToLogin = () => {
  window.location.href = '/login'; 
};

const getData= async() =>{
  const token=localStorage.getItem("token") ?? "";

  if(!token){
    throw new Error("Must Be Logged In!")
  } 
  
  const response= await axios.get(`${baseUrl}/categories`, {
    headers: {
      Authorization: `bearer ${ token }`,
    },
  } );
    return response.data;
  };

  const getCategoriesAxios= async()=>{
    const res= await request({url: "/categories"});
    return res.data;
  };

  const getAllCategories=async()=>{
    const token=localStorage.getItem("token")??"";

     if (!token) {
    redirectToLogin();
    return;
  }

    const res= await fetch(`${baseUrl}/categories`, {
      headers: {
        Authorization: `bearer ${ token }`,
      },
    });
    const json=await res.json();
    if(!res.ok){
      throw json;
    }
    return json;
    };

  const getCategory= async(id:string) =>{
    const token=localStorage.getItem("token")??"";

   if (!token) {
    redirectToLogin();
    return;
  }
    const res= await fetch(`${baseUrl}/categories/${id}`,{
      headers: {
        Authorization: `bearer ${ token }`,
      },
    });

    const json=await res.json();
    if(!res.ok){
      throw json;
    }
    return json;
    };
    
    




    export const CategoryService = { getAllCategories , getCategoriesAxios, getCategory};