import React from 'react'
import { Webtoon } from '../models/webtoonType'
import Card from './Card'
import Image from 'next/image'
import Rank from './Rank'

type Props = {
    webtoonList : Webtoon[],
    isRank? : boolean
}

export default function WebtoonList({webtoonList, isRank = false} : Props) {
  return (
    <div className = "grid grid-cols-5 grid-rows-2 gap-4 m-auto"> 
      {
        webtoonList.map((webtoon, index)=>{
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
