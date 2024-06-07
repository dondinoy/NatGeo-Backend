import { useContext, useEffect, useState } from "react";
import { ArticleService } from "../../services/article_service";
import { Article } from "../../@types/types";
import ArticleItem from "../article/ArticleItem";
import Spinner from "../../component/Spinner";
import { NavLink } from "react-router-dom";
import { AuthContext } from "../../context/AuthConext";

const Articles = () => {


  //const [articlePage, setArticlePage] = useState<ArticlePage>();
  const [articles, setArticles] = useState<Article[]>();

  const [error, setError] = useState<string>();

  const [loading, setLoading] = useState(false);
  
  const { role } = useContext(AuthContext);


  useEffect(() => {

    const asyncFunction = async () => {
      try {
        setLoading(true)
        setError(undefined);
        //const res = (await ArticleService.getArticles()) as ArticlePage;
        //setArticlePage(res);
        const res = (await ArticleService.getArticles()) as Article[];
        setArticles(res);
      } catch (e) {
        if (
          e != null &&
          typeof e == "object" &&
          "message" in e &&
          typeof e["message"] == "string"
        )
          setError(e.message as string);
      }finally{
        setLoading(false)
      }
    };

    asyncFunction();

  }, []);
  
  return (
    <div  className="container mx-auto">
      <div className="flex flex-col text-center mb-5 mt-7">
        {role==="admin" && <NavLink to={"/createPage/"} 
        className="bg-slate-900 p-5  text-white hover:text-yellow-300">Create New article</  NavLink>}

      </div>
      
      {loading && <Spinner />}
      {error && <div>{error}...</div>}
      {!error && !loading && <div style={{    display: "flex",
      flexDirection: "row",
      alignItems: "center",
      justifyContent: "center",
      flexWrap: "wrap"}}>{
       articles?.map((a)=>(
  
          <ArticleItem key={a.id} {...a} />
     
        )
      )}</div>
      }
    </div>
  )
};

export default Articles;