import React from 'react'
import { NavLink } from 'react-router-dom'

const MenuList = () => {
  return (
  <div>
    <div>
      <NavLink to={"/animals"}>Animals</NavLink>
    </div>
    <div>
      <NavLink to="history">History & Culture</NavLink>
    </div>
    <div>
      <NavLink to="environment">Environment</NavLink>
    </div>
    <div>
      <NavLink to="scince">Animals</NavLink>
    </div>
  </div>
  )
}

export default MenuList
