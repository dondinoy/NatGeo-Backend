import {
  FieldErrors,
  FieldValues,
  Path,
  UseFormRegister,
  ValidationRule,
} from "react-hook-form";

type InputFieldProps<T extends FieldValues> = {
  register: UseFormRegister<T>;
  errors: FieldErrors<T>;
  name: Path<T>; 
  pattern?: ValidationRule<RegExp> | undefined;
} & Omit<Omit<React.InputHTMLAttributes<HTMLInputElement>, "name">, "pattern">;

const RegistationInputField = <T extends FieldValues>({
  errors,
  register,
  name,
  pattern,
  ...rest
}: InputFieldProps<T>) => {
  const err = (errors[name]?.message ?? "") as string;
  const MAX = Number.MAX_SAFE_INTEGER;
  return (
    <div className="p-2">
      <input
        className=" bg-black rounded-md p-2 w-full border-2 border-slate-800 text-yellow-300"
        placeholder={`${name}`}
        {...rest}
        {...register(name, {
          required: `${name} is required`,
          minLength: {
            value: rest.minLength ?? 2,
            message: `Min length is ${rest.minLength ?? 2}`,
          },
          maxLength: {
            value: rest.maxLength ?? MAX,
            message: `Max length is ${rest.maxLength ?? MAX}`,
          },
          pattern: pattern,
        })}
        type="text"
        autoComplete="username"
      />
      {errors[name] && <p className="text-red-500">{err}</p>}
    </div>
  );
};

export default RegistationInputField;