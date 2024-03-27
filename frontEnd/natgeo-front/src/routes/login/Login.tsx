import { DevTool } from "@hookform/devtools";
import InputField from "../../component/inputFields/RegistationInputField";

import { Dialogs } from "../../ui/dialogs";
import { Auth } from "../../services/auth-service";
import { useContext, useEffect  } from "react";
import { AuthContext } from "../../context/AuthConext";
import { useForm } from "react-hook-form";
import {useNavigate } from "react-router-dom";


export type LoginRequest = {
  username: string;
  password: string;
};

const Login = () => {
  const { login, isLoggedIn } = useContext(AuthContext);
  const nav = useNavigate();
  const {
    register,
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<LoginRequest>({
    mode: "onChange",
  });

  useEffect(()=>{
  if (isLoggedIn) {
    nav("/");
  }},([isLoggedIn,nav]));
  

  const onSubmit = async (data: LoginRequest) => {
    try {
      const res = await Auth.login(data);
      await Dialogs.success("Logged in");

      login(res.jwt);
      nav("/");
    } catch (e) {
      Dialogs.error(e);
    }
  };

  return (
    <>
      <h1 className="text-center text-lg my-2">Login</h1>
      <form
        onSubmit={handleSubmit(onSubmit)}
        noValidate
        className="flex flex-col gap-5 w-1/1 mx-4 md:w-1/2 md:mx-auto shadow-2xl rounded-xl p-5 text-xl"
      >
        <InputField
          autoComplete="username"
          register={register}
          errors={errors}
          name="username"
        />
        <InputField
          autoComplete="current-password"
          register={register}
          errors={errors}
          type="password"
          name="password"
          pattern={{
            value: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*!@$%^&]).{8,32}$/,
            message:
              "password must contain at least 1 lowercase letter,1 uppercase letter,1 digit and 1 special character",
          }}
        />

        <input
          className="rounded-md bg-blue-500 text-white p-2"
          type="submit"
          value="Login"
        />
      </form>

      <DevTool control={control} />
    </>
  );
};

export default Login;