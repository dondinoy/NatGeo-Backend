import { useForm } from "react-hook-form";
import * as z from "zod";
import { useEffect, useState,useContext,useRef } from "react";
import { zodResolver } from "@hookform/resolvers/zod";

import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { getArticle, updateArticle } from "../../services/article_service";
import { useNavigate, useParams } from "react-router-dom";
// import { useContext } from "react";
import { AuthContext } from "../../context/AuthConext";
import "react-quill/dist/quill.snow.css";
  
export default function UpdatePage() {
  const { role, isLoggedIn } = useContext(AuthContext);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [content, setContent] = useState("");

  const nav = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const getArt = async () => {
      if (!id) return;
      try {
        const article = await getArticle(id);
        setTitle(article.title);
        setDescription(article.description);
        setContent(article.content);
      } catch (error) {
        console.error("Error fetching article:", error);
      }
    };
    getArt();
  }, [id]);

   const formScheme = z.object({
    title: z.string(),
    description: z.string(),
    content: z.string(),
  });
   const { register, handleSubmit, formState: { errors } } = useForm<z.infer<typeof formScheme>>({
    resolver: zodResolver(formScheme),
    mode: "onChange",
    defaultValues: {
      title: "",
      description: "",
      content: "",
    },
  });




  const formats = [
  'header',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'indent',
  'link',
  'image',
  'code',
  'color',
  'background',
  'code-block',
  'align',
];
const modules = {
      toolbar: {
        container: [
          [{ header: [2, 3, 4, false] }],
          ["bold", "italic", "underline", "blockquote"],
          [{ color: [] }],
          [
            { list: "ordered" },
            { list: "bullet" },
            { indent: "-1" },
            { indent: "+1" },
          ],
          ["link", "image"],
          ["clean"],
        ],
      },
      clipboard: {
        matchVisual: true,
      },
    }
      const quillRef = useRef<ReactQuill | null>(null);
      useEffect(() => {
    const editor = quillRef.current?.getEditor();
    if (editor) {
      const observer = new MutationObserver((mutationsList) => {
        for (const mutation of mutationsList) {
          if (mutation.type === 'childList') {
            console.warn('DOM Mutation Event detected, consider using MutationObserver instead.');
          }
        }
      });

      observer.observe(editor.root, {
        childList: true,
        subtree: true,
      });

      return () => {
        observer.disconnect();
      };
    }
  }, []);

const onSubmit = async (values: z.infer<typeof formScheme>) => {
    try {
      const updatedArticle = {
        title,
        description,
        content,
      };


      await updateArticle(id, updatedArticle); 

      nav("/"); // Redirect to homepage after successful update
    } catch (error) {
      console.error("Error updating article:", error);
    }
  };
   if (!isLoggedIn || role === "ROLE_USER") {
    nav("/");
    console.log("not a user");
    return null; // Returning null to avoid rendering anything when user is not logged in or not authorized
  }

const handler = handleSubmit(onSubmit)
  const debuggingHandler = (e: any) => {
    console.log({
      title,
      description,
      content      
  })
    handler(e)
  }

  return (
    <main className="p-24">
      <div className="flex flex-col text-center justify-center">
        <form onSubmit={debuggingHandler}>
          <div className="flex flex-col shadow-xl m-5">
            <input value={title} placeholder="Title" {...register("title")} onChange={(e) => setTitle(e.target.value)}/>
            {errors.title && <span>{errors.title.message}</span>}
          </div>

          <div className="flex flex-col shadow-xl m-5">
            <textarea
              value={description}
              placeholder="Description"
              {...register("description")}
              onChange={(e) => setDescription(e.target.value)}
            ></textarea>
            {errors.description && <span>{errors.description.message}</span>}
          </div>
          <div>
            <ReactQuill 
                           
              theme="snow"
              value={content}
              onChange={setContent}
              formats={formats}
              modules={modules}
              ref={quillRef}
            />;
            {errors.content && <span>{errors.content.message}</span>}
          </div>
          <div className="shadow-xl m-5 bg-black text-white p-9 ">
            <button type="submit" className="flex flex-row  w-full justify-center">Submit</button>
          </div>
        </form>
      </div>
    </main>
  );
}
