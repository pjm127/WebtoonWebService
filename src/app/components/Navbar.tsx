'use client'

import Link from 'next/link'
import React from 'react'
import SearchIcon from './ui/SearchIcon'

export default function Navbar() {
  return (
    <div className = "flex flex-row p-3 justify-between items-center">
        <Link className = "" href = "/">
           <h1 className = "text-3xl font-bold text-slate-600 ml-4"> Webtoon </h1>
        </Link>
        <nav className = "flex flex-row gap-4 mr-4  text-slate-600">
            <SearchIcon></SearchIcon>
            <Link className = "p-3 border-solid border-2 border-indigo-600 rounded-lg"
                    href = "/login"
            >Login</Link>
        </nav>
    </div>
  )
}
