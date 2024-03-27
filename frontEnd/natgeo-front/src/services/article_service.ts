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
  const token = localStorage.getItem("token") ?? "";

  if (!token) {
    throw new Error("Must be logged in");
  }

  const res = await fetch(`${baseUrl}/articles`, {
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

const getArticle = async (id: string) => {
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

export const ArticleService = { getArticles , getArticlesAxios, getArticle};