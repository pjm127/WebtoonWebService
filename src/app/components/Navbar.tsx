'use client'

import Link from 'next/link'
import React, { useState } from 'react'
import SearchIcon from './ui/SearchIcon'
import ModalPortal from './ModalPortal'
import SignIn from './SignIn'
import Navigate from './Navigate'
import { usePathname } from 'next/navigation'


export default function Navbar() {
  const [modal, setModal] = useState<boolean>(false)
  const path = usePathname();
  return (
    <div className = "flex flex-row p-3 justify-between items-center">
            <Navigate href= "/" disabled={path==='/' && true}>
              <h1 className = "text-3xl font-bold text-slate-600 ml-4"> Webtoon </h1>
            </Navigate>        
        <nav className = "flex flex-row gap-4 mr-4  text-slate-600">
            <Navigate href= "/search" disabled={path==='/search' && true}>
              <SearchIcon></SearchIcon>
            </Navigate>
            {/* <Link className = "p-3 border-solid border-2 border-indigo-600 rounded-lg hover:opacity-60 hover:text-white" href = "/signup"> */}
              <Navigate href= "/signup" disabled={path==='/signup' && true}>
                  <div className = "p-3 border-solid border-2 border-indigo-600 rounded-lg hover:opacity-60 hover:text-white">
                      Sign Up
                  </div>
              </Navigate>
            {/* </Link> */}
            <button 
              className = 'p-3 border-sky-300 border-2 rounded-lg hover:opacity-60 hover:text-white'
              onClick={()=>{setModal(true)}}
            >Sign In</button>
        </nav>
        {
          modal &&
          <ModalPortal>
            <SignIn onClose={()=>{setModal(false)}}></SignIn>
          </ModalPortal>
        }
    </div>
  )
}
