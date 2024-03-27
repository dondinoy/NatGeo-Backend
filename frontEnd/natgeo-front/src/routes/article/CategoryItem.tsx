import React from 'react'
import { Article, Category } from '../../@types/types'
import { Card } from '../../component/card/Card'

const CategoryItem = ({id,name,articles}:Category) => {


  return (
    <div>
       <Card>
        <h2>{id}</h2>
        <p>{name}</p>
        {/* <p>{articles}</p> */}
      </Card>
    </div>
  )
}

export default CategoryItem
