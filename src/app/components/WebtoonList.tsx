'use client'

import React, { useState, useEffect } from 'react'
import { Webtoon } from '../models/webtoonType'
import Card from './Card'
import Rank from './Rank'
import useSWR from 'swr'

type Props = {
    webtoonList : Webtoon[],
    isRank? : boolean
}

const fetcher = (url : string) => fetch(url).then(res=>res.json());

const onePageCountWebtoon = 10;
const onePageCountPage = 10;

export default function WebtoonList({webtoonList, isRank = false} : Props) {
  const [nowPageNum, setPageNum] = useState<number>(1);
  // const {data, isLoading, error} = useSWR('/api/list', fetcher)
  const {data, error} = useSWR<Webtoon[]>('http://localhost:9001/api/vi/webtoon/list?page=1', fetcher)

  // useEffect(()=>{
  //   const webtoons = fetch(`/api/list?page=${nowPageNum}`)
  //                     .then((res)=>res.json())
  //                     .then(data)
  // }, [nowPageNum])

  function listButton(){
    let arr 
  }
  // 왜 안됨.
  return (
    <div className = "p-10 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4"> 
      {
        (data as Webtoon[]).map((webtoon, index)=>{
          return(
            <Card webtoon={webtoon}>
              {
                isRank &&
                <Rank rank={index+1}/>
              }
            </Card>
          )
        })
      }
    </div>  
  )
}
