import React, { Dispatch, SetStateAction } from 'react'
import { Webtoon } from '../models/webtoonType'
import Image from 'next/image'
import Link from 'next/link'
import CommentModal from './CommentModal'
import CloseButton from './ui/CloseButton'

export default function WebtoonInfoModal({
    webtoon : {title, url, thumnail, genre, likeCount, firstDate, dayOfWeek, platform},
    onClose
} : {
    webtoon : Webtoon,
    onClose : () => void
}) {
  return (
    <div className = "fixed top-0 left-0 w-full h-full" onClick={()=>{onClose()}}>
        <div className = "p-10 w-[500px] h-[700px] m-auto gap-4 grid grid-rows-[repeat(8,minmax(0,1fr))] grid-cols-2 bg-white rounded-xl mt-6">
            <Image 
                src = {`/images${thumnail}`} 
                alt = {'modal image'}
                width = {200}
                height = {400}
                className = "max-h-full max-w-full overfl row-start-1 row-end-5"
            ></Image>
            <p>제목 : {title}</p>
            <p>장르 : {genre}</p>
            <p>요일 : {dayOfWeek}</p>
            <p>플랫폼 : {platform}</p>
            <Link 
                href = {url} 
                className = "p-3 w-[30%] h-[50px] m-auto text-center text-neutral-50 rounded-lg bg-blue-500 col-start-1 col-end-3"
            >보러가기</Link>
            <CommentModal></CommentModal>
            
        </div>
        <button className = 'fixed top-10 right-10' onClick = {()=>{onClose()}}>
                <CloseButton/>
        </button>
    </div>
  )
}
