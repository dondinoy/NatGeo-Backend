import { useEffect, useState } from "react";
import { ArticleService } from "../../services/article_service";
import { Article } from "../../@types/types";
import ArticleItem from "../article/ArticleItem";
import Spinner from "../../component/Spinner";

const Articles = () => {


  //const [articlePage, setArticlePage] = useState<ArticlePage>();
  const [articles, setArticles] = useState<Article[]>();

  const [error, setError] = useState<string>();

  const [loading, setLoading] = useState(false);

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
    <div>
      {loading && <Spinner />}
      {error && <div>error...</div>}
      {!error && !loading && <div>{
       articles?.map((a)=>(
          <ArticleItem key={a.id} {...a} />
        )
      )}</div>
      }
    </div>
  )
};

export default Articles;