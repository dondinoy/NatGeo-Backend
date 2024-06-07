import { NavLink } from "react-router-dom";
import { Article } from "../../@types/types";
import { Card } from "../../component/card/Card";
import { AuthContext } from "../../context/AuthConext";
import { useContext } from "react";
import { FaArrowRight } from "react-icons/fa";
const ArticleItem = ({
  id,
  title,
  description,
  imageData,
}: Article & { imageData: string }) => {
  return (
    <div className="mt-5 min-h-[21rem] w-full sm:w-[100vw] md:w-full lg:w-[40vw] flex flex-col justify-center text-xl text-slate-900  dark:text-black mb-4 p-4 ml-2 text-center">
      {imageData && (
        <img
          src={`data:image/jpg;base64,${imageData}`}
          alt="Main Image"
          height="100px"
          className="w-full h-64 object-cover "
        />
      )}

      <h1 className="font-black p-4">{title}</h1>
      <p>{description}</p>
      <div className=" flex flex-row justify-center mt-4">
        <div className="p-1">
          <FaArrowRight />
        </div>
        <div>
          <NavLink className="p-3 hover:font-black" to={"/article/" + id}>
            Go to article
          </NavLink>
        </div>
      </div>
    </div>
  );
};

export default ArticleItem;
