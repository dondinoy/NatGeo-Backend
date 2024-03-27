import React, { useState } from 'react'
import { Category as CategoryType } from '../../@types/types';
import { useParams } from 'react-router';
import { CategoryService } from '../../services/category_service';
import Spinner from '../../component/Spinner';
import CategoryItem from '../article/CategoryItem';


const Animals = () => {
  const [category,setCategory]=useState<CategoryType|undefined>();
  const [error, setError] = useState<string>();
  const [loading, setLoading] = useState(false);

  const {id}= useParams()
  const feetchData=async()=>{
    try{
    const res= await CategoryService.getCategory(id!);
    console.log(res);
    setCategory(res);
    }catch(e){
    console.log(e)
      // if(
      //   e != null &&
      //     typeof e == "object" &&
      //     "message" in e &&
      //     typeof e["message"] == "string"
      //   )
      if (e instanceof Error) {
         setError(e.message);
        }
        // setError(e.message as string);
    }finally{
    setLoading(false)
    }
  };
  if(id && !category)
      feetchData();


  return (
    <div>
      {loading && <Spinner />}
      {error && <div>Error: {error}</div>}
      {!error && !loading && (
        <div>
      {category &&  <CategoryItem key={category.id} {...category} /> }
        </div>
      )}
    </div>
  )
};

export default Animals;

