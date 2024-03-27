import { NavLink } from "react-router-dom";
import { Article } from "../../@types/types";
import { Card } from "../../component/card/Card";

const ArticleItem = ({ id, title, description,content }: Article) => {

  return (
    <Card>
      <h2>{title}</h2>
      <p>{description}</p>
      <p>{content}</p>
      <NavLink to={"/article/"+id}>Go to article</NavLink>
    </Card>
  );
};

export default ArticleItem;