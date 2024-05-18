import { useForm } from "react-hook-form"
import * as z from "zod"
import { useEffect, useState } from "react";
import {zodResolver} from "@hookform/resolvers/zod"
import EditorPage from "../../component/editor/EditorPage";

import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import {getArticle} from "../../services/article_service"
import { useNavigate, useParams  } from "react-router-dom"
import { useContext } from "react"
import { AuthContext } from "../../context/AuthConext"
import Editor from "../../component/editor/EditorPage"
import { addArticle } from "../../services/article_service";

export default function UpdatePage(){
  const { role, isLoggedIn } = useContext(AuthContext);
  const [art,setArt]=useState({})
  const nav=useNavigate();
   const {id} = useParams();

   useEffect(()=>{
  
       const getArt=async()=>{
             const articleId=await getArticle(id);
              setArt(articleId)
             console.log(articleId)
        
      }
  getArt()
  },[id])
console.log(art)

  const [value, setValue] = useState('');

 
  if(!isLoggedIn||role==="ROLE_USER"){
    nav("/")
    console.log("not a user")
    
  }
  const formScheme=z.object({
    title:z
      .string()
      .min(5,{message:"MustBe More Than 5 Letters For Title"}),
    description:z
      .string()
      .min(5,{message:"MustBe More Than 5 Letters For Description"}),
    content:z
      .string()
      //.min(5,{message:"MustBe More Than 5 Letters For Content"})
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
  const [data,setData]=useState({})
   async function onSubmit(values: z.infer<typeof formScheme>) {
  
  try {

     console.log("title",values.title);
     console.log("desc", values.description);
     console.log("content",values.content);
     setData({
      title:values.title,
      description:values.description,
      content:value

    })
    console.log(data)
    // Once validated, call your addArticle function to add the article to the database
    const addedArticle = await addArticle(data);

    console.log("Article added successfully:", addedArticle);

  } catch (error) {
    // Handle validation errors or server errors
    console.error("Error adding article:", error);
  }

}


  return(
      <main className="p-24">
        <div className="flex flex-col text-center justify-center" >
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="flex flex-col shadow-xl m-5">
              <input value={art.title}  placeholder="Title" {...register('title')} />
               {errors.title && <span>{errors.title.message}</span>}
            </div>
            
             <div className="flex flex-col shadow-xl m-5">
                <textarea value={art.description} placeholder="Description"  {...register('description')} ></textarea>
                {errors.description && <span>{errors.description.message}</span>}
            </div>
            <div>
<ReactQuill theme="snow" value={art.content} onChange={setValue}  />;
               {/* <textarea placeholder="content"  { ...register('content')} ></textarea> */}
            {/* <EditorPage register={register} name={"content"} /> */}
          {errors.content && <span>{errors.content.message}</span>}
            </div>
            <div className="flex flex-col shadow-xl m-5 max-w-52">
            <button type="submit">Submit</button>
            </div>
          </form>
        </div>
      </main>
    )
}



