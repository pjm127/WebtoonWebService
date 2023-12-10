import React, { useRef } from 'react'
import { TempWebtoon, Webtoon } from '../models/webtoonType'
import Image from 'next/image'
import Link from 'next/link'
import CommentModal from './CommentModal'
import CommentList from './CommentList'

export default function InfoModalDetail({
    webtoon : {title, url, creator, thumnail, genre, likeCount, firstDate, dayOfweek, platform},
} : {
    webtoon : TempWebtoon
}) {
  
  return (
    <section className = "p-10 w-[500px] h-[700px] m-auto gap-4 grid grid-rows-[repeat(10,minmax(0,1fr))] grid-cols-2 bg-white rounded-xl mt-6">
            <Image 
                src = {`/images${thumnail}`} 
                alt = {'modal image'}
                width = {200}
                height = {400}
                className = "max-h-full max-w-full overfl row-start-1 row-end-6"
            ></Image>
            <p>제목 : {title}</p>
            <p>작가 : {creator}</p>
            <p>장르 : {genre}</p>
            <p>요일 : {dayOfweek}</p>
            <p>플랫폼 : {platform}</p>
            <Link 
                href = {url} 
                className = "p-3 w-[30%] h-[50px] m-auto text-center text-neutral-50 rounded-lg bg-blue-500 col-start-1 col-end-3 hover:opacity-80"
            >보러가기</Link>
            <CommentModal></CommentModal>
            <div className = 'p-2 row-start-[8] row-end-[10] col-start-1 col-end-3 border-[0.05rem] border-black rounded-sm'>
              <CommentList></CommentList>  
            </div>   
    </section>
  )
}
