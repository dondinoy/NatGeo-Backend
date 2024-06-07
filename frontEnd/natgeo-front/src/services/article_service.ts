import { request } from "../utills/axios-helper";
import { baseUrl } from "./auth-service";
import axios from "axios";

const getData = async () => {
  const token = localStorage.getItem("token") ?? "";

  if (!token) {
    throw new Error("Must be logged in");
  }

  const response = await axios.get(`${baseUrl}/articles`, {
    headers: {
      Authorization: `bearer ${token}`,
    },
  });

  return response.data;
};

const getArticlesAxios = async () => {
  const res = await request({ url: "/articles" });
  return res.data;
};

const getArticles = async () => {
  const res = await fetch(`${baseUrl}/articles`, {
    method: "GET",
    headers: {
    },
  });

  const json = await res.json();

  if (!res.ok) {
    throw json;
  }
  return json;
};

 export const getArticle = async (id:string) => {

  const token = localStorage.getItem("token") ?? "";

 if (!token) {
        throw new Error("Must be logged in");
  }

  const res = await fetch(`${baseUrl}/articles/${id}`, {
    method: "GET",
    headers: {
      Authorization: `bearer ${token}`,
    },
  });

  const json = await res.json();

  if (!res.ok) {
    throw json;
  }
  return json;
};

export const addArticle = async (articleData: unknown) => {
  console.log(articleData)
  const token = localStorage.getItem("token") ?? "";

  const res = await fetch(`${baseUrl}/articles`, {
    method: "POST",
    headers: {
    "Content-Type": "application/json", 
      Authorization: `bearer ${token}`,
    },
        body: JSON.stringify(articleData), 
  });
  const json = await res.json();

  if (!res.ok) {
    throw json;
  }
  return json;
};
    export const addArticleToCategory = async ( formData:FormData, category:string) => {
    console.log(formData);
    const token = localStorage.getItem("token") ?? "";
    let url=""
      if(category==""){
        url=`${baseUrl}/articles`
      }else{
        url= `${baseUrl}/articles/${category}`
      }
      try{
        const response=await axios.post(url,formData,{
          headers:{
            Authorization: `Bearer ${token}`,
            "Content-Type": "multipart/form-data",
          }
        });
        return response.data;


        
      }catch(error){
        console.error(error)
        throw error
      }
};



export const updateArticle = async (id: string, updatedArticle: { title: string, description: string, content: string }) => {
  const token = localStorage.getItem("token") ?? "";
  console.log("Article updated successfully");

  const res = await fetch(`${baseUrl}/articles/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `bearer ${token}`,
    },
    body: JSON.stringify(updatedArticle),
  });

  const json = await res.json();

  if (!res.ok) {
    throw json;
  }

  return json;
};

export const deleteArticle = async (id) => {
    const token = localStorage.getItem("token") ?? "";

    if (!token) {
        throw new Error("Must be logged in");
    }

    const res = await fetch(`${baseUrl}/articles/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            Authorization: `bearer ${token}`,
        },
            body: JSON.stringify(deleteArticle),

    });
    
    const json = await res.json();

    if (!res.ok) {
        throw json;
    }

    return res.status; 
};


export const ArticleService = {getArticle, getArticles, getArticlesAxios, addArticle,updateArticle,addArticleToCategory ,deleteArticle};
