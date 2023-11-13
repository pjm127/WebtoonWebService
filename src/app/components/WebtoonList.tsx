import React from 'react'
import { Webtoon } from '../models/webtoonType'
import Card from './Card'
import Image from 'next/image'

type Props = {
    webtoonList : Webtoon[]
}

export default function WebtoonList({webtoonList} : Props) {
  return (
    <div className = "grid grid-cols-5 grid-rows-2 gap-4 m-auto"> 
      {
        webtoonList.map((webtoon)=>{
          return(
            <Card webtoon={webtoon}></Card>
          )
        })
      }
    </div>  
  )
}
