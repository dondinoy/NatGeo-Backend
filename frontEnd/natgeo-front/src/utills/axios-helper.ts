import axios, { AxiosError, AxiosRequestConfig } from "axios";
import { baseUrl } from "../services/auth-service";

export type HttpError401 = { status: number; message: string };

// type guard:
export function isHttpError401(e: unknown): e is HttpError401 {
  return (
    e != null &&
    typeof e === "object" &&
    e !== null &&
    "status" in e &&
    "message" in e &&
    typeof (e as { status: unknown }).status === "number" &&
    typeof (e as { message: unknown }).message === "string" &&
    (e as { status: number }).status === 401
  );
}

export function isErrorWithMessage(e: unknown): e is { message: string } {
  return (
    e != null &&
    typeof e == "object" &&
    "message" in e &&
    typeof e.message == "string"
  );
}

export const request = (requestConfig: AxiosRequestConfig = {}) => {
  const token = localStorage.getItem("token") ?? "";
  if (!token) {
    throw new Error("must be logged in");
  }
  const client = axios.create({ baseURL: baseUrl });
  client.defaults.headers.common.Authorization = `Bearer ${token}`;
  return client(requestConfig).catch((e) => {
    if (e instanceof AxiosError) {
      throw {
        status: e.response?.status ?? 500,
        message: e.message,
        name: e.name,
        json: e.toJSON(),
        original: e,
      };
    }
    const message = "unknown error";
    if (isErrorWithMessage(e)) {
      throw e;
    }
    //all http errors run through this method
    
    throw { message };
    
  });
};

//request({url: "/posts", method: "POST",data:""})