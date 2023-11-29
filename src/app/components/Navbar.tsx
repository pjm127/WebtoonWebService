'use client'

import Link from 'next/link'
import React, { useState } from 'react'
import SearchIcon from './ui/SearchIcon'
import ModalPortal from './ModalPortal'
import SignIn from './SignIn'

export default function Navbar() {
  const [modal, setModal] = useState<boolean>(false)
  return (
    <div className = "flex flex-row p-3 justify-between items-center">
        <Link className = "" href = "/">
           <h1 className = "text-3xl font-bold text-slate-600 ml-4"> Webtoon </h1>
        </Link>
        <nav className = "flex flex-row gap-4 mr-4  text-slate-600">
            <Link href = '/search' className = 'hover:opacity-60 transition-opacity'>
              <SearchIcon></SearchIcon>
            </Link>
                <Link href = "/signup" className = 'text-white p-3 rounded-lg border-2 border-indigo-600 hover:opacity-60 transition-opacity'>
                  Sign Up
                </Link>
            <button 
              className = 'text-white p-3 border-sky-300 border-2 rounded-lg hover:opacity-60 transition-opacity'
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
