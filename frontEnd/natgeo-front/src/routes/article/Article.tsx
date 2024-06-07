import { useEffect, useState } from "react";
import { ArticleService } from "../../services/article_service";
import { Article as ArticleType } from "../../@types/types";
import { NavLink, useParams } from "react-router-dom";
import Spinner from "../../component/Spinner";
import InArticleItem from "./InArticleItem";

const Article = () => {
  const [article, setArticle] = useState<ArticleType | undefined>();
  const [error, setError] = useState<string>();
  const [loading, setLoading] = useState(false);

  const { id } = useParams();
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        setError(undefined);

        // const res= (await ArticleService.getArticle())as Article;
        const res = await ArticleService.getArticle(id!);
        console.log(res);
        setArticle(res);
      } catch (e) {
        console.log(e);
        if (e instanceof Error) {
          setError(e.message);
        }
      } finally {
        setLoading(false);
      }
    };
    if (id && !article) fetchData();
  });
  

  return (
    <div>
      {loading && <Spinner />}
      {error && <div>Error: {error}</div>}
      {!error && !loading && (
        <div>
          {article && (
            <InArticleItem
              key={article.id}
              {...article}
              imageData={article.imageData}
            />
          )}
          
        </div>
      )}
    </div>
  );
};
export default Article;
