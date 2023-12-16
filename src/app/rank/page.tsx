'use client'

import React, { useEffect, useState } from 'react'
import { getAllWebtoonInfo, getWebtoonRank } from '../service/webtoonInfo'
import Card from '../components/Card';
import WebtoonList from '../components/WebtoonList';
import WebtoonListContextProvider, { WebtoonListContext } from '../context/WebtoonListContext';
import { WebtoonListPage } from '../models/webtoonType';
import { useSearchParams } from 'next/navigation';

export default function RankPage() {
  const [webtoonList, setWebtoonList] = useState<WebtoonListPage>();

  const searchParams = useSearchParams()
  const search = searchParams.get('page');

  useEffect(()=>{
    if(search === null){
    ( async()=>{
      await fetch(`http://localhost:9001/api/v1/webtoon/list?&page=0`, 
                  {method : "GET"}
      )
      .then(res=>res.json())
      .then(res=>setWebtoonList(res))
    })()
    }
  },[])

  useEffect(()=>{
    if(Number(search) > 0){
      ( async()=>{
        await fetch(`http://localhost:9001/api/v1/webtoon/list?&page=${search}`, 
                    {method : "GET"}
        )
        .then(res=>res.json())
        .then(res=>setWebtoonList(res))
      })()
    }
  },[search])

  
  useEffect(()=>{
    console.log('데이터는???', webtoonList);
  },[webtoonList])

  return (
    <>
    {/* // <WebtoonListContextProvider firstList= 'http://localhost:9001/api/v1/webtoon/list?page=0'> */}
      {
        (webtoonList)  &&
        <WebtoonList webtoonList={webtoonList} isRank={true}></WebtoonList>
      }
    {/* // </WebtoonListContextProvider> */}
    </>
  )
}
 