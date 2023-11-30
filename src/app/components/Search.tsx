'use client'
import React, { FormEvent, useState } from 'react'
import { genre } from '../models/globalVar';

const dayOfWeek = ['월', '화', '수', '목', '금', '토', '일']

export default function Search() {
    const [keyword, setKeyword] = useState<string>('');
    const [genres, setGenres] = useState<number>(-1);
    const [days, setDays] = useState<number>(-1);
    // const {data, isLoading, error} = useSWR(`/api/search/?keyword=${keyword}&genre`)

    const onSubmit = (e : FormEvent) => {
        e.preventDefault();
    }

    return (
        <div className = 'p-5 mt-10 m-auto w-[80%] sm:w-[600px] h-[50%] text-gray-50'>    
            <form onSubmit={onSubmit} className = 'mb-9'>
                <input 
                    type = 'text' 
                    autoFocus 
                    placeholder='Search for a Webtoon title'
                    value = {keyword}
                    onChange = {(e)=>{setKeyword(e.target.value)}}
                    className = 'p-3 mr-4 w-[50%] h-7 rounded-md text-black'
                />
                <button type = "submit" onClick = {()=>{
                    setGenres(-1);
                    setDays(-1);   
                }}>검색</button>
            </form>          
            <span>장르</span>                  
            <div className = 'grid grid-rows-2 grid-cols-4 gap-3 mb-9 mt-1'>
                {
                    genre.map((genre, index)=>{
                        return(
                            <button 
                                className ={`p-3 border-sky-200 border-[0.05rem] rounded-md ${index === genres && 'bg-sky-200 text-black border-white'}`}
                                onClick = {()=>{setGenres(index)}}
                            >{genre}</button>
                        )
                    })
                }
            </div>
            <span>요일</span>
            <div className = 'flex justify-between mt-1'>
                {
                    dayOfWeek.map((day, index)=>{
                        return(
                            <button 
                                className ={`p-3 w-[10%] border-sky-200 border-[0.05rem] rounded-md ${index === days && 'bg-sky-200 text-black border-white'}`}
                                onClick = {()=>{setDays(index)}}
                            >{day}</button>
                        )
                    })
                }
            </div>
        </div>
    )
}
