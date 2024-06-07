import React, { useEffect } from 'react'
import { NavLink, useHistory, useNavigate  } from 'react-router-dom'
import './menuelist.scss'

const MenuList = () => {

  const handleMouseEnter = (bgImage) => {
   

    document.getElementById('main-container').style.backgroundImage = `url(${bgImage})`;
  }

  const handleMouseLeave = () => {
    document.getElementById('main-container').style.backgroundImage = null;
  }

  return (
    <div id="main-container" className=" flex flex-col justify-center bg-slate-950  text-white  text-center">
      <div className="text-5xl mb-5 hover:text-yellow-400 ">
        <NavLink to="/animals" className="nav-link" 
          onMouseEnter={() => handleMouseEnter('../../assets/C0287477-Fairy_ring_3x2.jpg')}
          onMouseLeave={handleMouseLeave}
        >
          Animals
        </NavLink>
      </div>
      <div className="text-5xl  mb-5  hover:text-yellow-400">
        <NavLink to="/history" className="nav-link"
          onMouseEnter={() => handleMouseEnter('../../assets/C0287477-Fairy_ring_3x2.jpg')}
          onMouseLeave={handleMouseLeave}
        >
          History & Culture
        </NavLink>
      </div>
      <div className="text-5xl mb-5  hover:text-yellow-400">
        <NavLink to="/environment" className="nav-link"
          onMouseEnter={() => handleMouseEnter('../../assets/C0287477-Fairy_ring_3x2.jpg')}
          onMouseLeave={handleMouseLeave}
        >
          Environment
        </NavLink>
      </div>
      <div className="text-5xl max-h-full mb-5  hover:text-yellow-400">
        <NavLink to="/scince" className="nav-link"
          onMouseEnter={() => handleMouseEnter('../../assets/C0287477-Fairy_ring_3x2.jpg')}
          onMouseLeave={handleMouseLeave}
        >
          Science
        </NavLink>
      </div>
    </div>
  )
}

export default MenuList
