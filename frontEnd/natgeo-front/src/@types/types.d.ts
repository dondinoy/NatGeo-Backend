export type FC = (props: { children: ReactNode }) => ReactNode;

export interface Theme {
  isDark: boolean;
  toggleTheme: () => void;
}

export type ArticlePage = {
  totalArticles: number;
  pageNo: number;
  pageSize: number;
  totalPages: number;
  articles: Array<Article>;
};

export type Article = {
  imageData: string;
  id: number;
  title: string;
  content: string;
  description: string;
  tags: Array<Tag>;
};

export type Tag = {
  id: number;
  name: string;
  articles: Array<Article>;

};

export type Category = {
  id: number;
  name: string;
  articles: Array<Article>;
};

export type User = {
  id: number;
  username: string;
  email: string;
};

