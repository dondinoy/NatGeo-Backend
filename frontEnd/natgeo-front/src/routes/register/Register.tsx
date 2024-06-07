import { useForm } from "react-hook-form";
import { DevTool } from "@hookform/devtools";
import { Dialogs } from "../../ui/dialogs";
import InputField from "../../component/inputFields/RegistationInputField";
import { NavLink, useNavigate } from "react-router-dom";
import { Auth } from "../../services/auth-service";

export type RegisterRequest = {
  username: string;
  email: string;
  password: string;
};

const Register = () => {
  const nav = useNavigate();
  const {
    register,
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<RegisterRequest>({
    mode: "onChange",
  });

  const onSubmit = async (data: RegisterRequest) => {
    try {
      await Auth.register(data);
      await Dialogs.success("Go Login!");
      //go login
      nav("/login");
    } catch (e) {
      Dialogs.error(e);
    }
  };

  return (
    <div className="flex flex-col  bg-black h-full justify-center">
      <div className="flex flex-col p-9 justify-center">
      <h1 className="text-center text-5xl  text-yellow-400">Sign Up</h1>
      <form
        onSubmit={handleSubmit(onSubmit)}
        noValidate
        className="flex flex-col gap-5 w-1/1 mx-4 md:w-1/2 md:mx-auto shadow-2xl rounded-xl p-9 text-xl"
      >
        <InputField
          autoComplete="username"
          register={register}
          errors={errors}
          name="username"
        />
        <InputField
          autoComplete="email"
          pattern={{
            message: "Email must be valid",
            value: /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
          }}
          register={register}
          errors={errors}
          name="email"
        />

        <InputField
          register={register}
          errors={errors}
          type="password"
          autoComplete="new-password"
          name="password"
          pattern={{
            value: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*!@$%^&]).{8,32}$/,
            message:
              "password must contain at least 1 lowercase letter,1 uppercase letter,1 digit and 1 special character",
          }}
        />
        <input
          className="rounded-md bg-yellow-400  text-black p-2"
          type="submit"
          value="Register"
        />
        <div className="text-center text-yellow-400">
          <p>Already a member?</p>
          <NavLink to={"/login"}>Login</NavLink>
        </div>
      </form>

      <DevTool control={control} />
    </div>

    </div>
    
  );
};

export default Register;