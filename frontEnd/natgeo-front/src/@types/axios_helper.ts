import axios, { AxiosRequestConfig } from "axios";

import { baseUrl } from "../services/auth-service";

export const request = (requestConfig: AxiosRequestConfig = {}) => {
  const token = localStorage.getItem("token") ?? "";

  if (!token) {
    throw new Error("must be logged in");
  }

  const client = axios.create({ baseURL: baseUrl });

  client.defaults.headers.common.Authorization = `Bearer ${token}`;

  return client(requestConfig).catch((e) => {
    let message = "Unknown error";

    if (
      e != null &&
      typeof e == "object" &&
      "message" in e &&
      typeof e.message == "string"
    ) {
      message = e["message"];
    }

    //all http errors run through this method

    console.log(message);

    throw { message };
  });
};

//request({url: "/posts", method: "POST",data:""})
