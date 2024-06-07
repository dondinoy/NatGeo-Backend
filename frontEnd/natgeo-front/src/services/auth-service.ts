import { RegisterRequest } from "../routes/register/Register";
import { LoginRequest } from "../routes/login/Login";


export const baseUrl = `http://localhost:8080/api/v1`;

async function register(body: RegisterRequest) {
  const res = await fetch(`${baseUrl}/auth/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  const json = await res.json();
 

  if (!res.ok) {
    throw json;
  }
  return json;
}


async function login(body: LoginRequest) {
  const res = await fetch(`${baseUrl}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  const json = await res.json();

  if (!res.ok) {
    throw json;
  }
  return json;
}

export const Auth = { register, login };

// export class Auth {
//   static register(params: RegisterRequest) {}
// }