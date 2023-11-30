'use client'
import React, { useState } from 'react'
import Image from 'next/image'
import ModalPortal from './ModalPortal'
import WebtoonInfoModal from './WebtoonInfoModal'
import { Webtoon } from '../models/webtoonType'
import InfoModalDetail from './InfoModalDetail'
import Link from 'next/link'
import CommentModal from './CommentModal'
import useSWR from 'swr'

type Props = {
    webtoon : Webtoon,
    children : React.ReactNode
}

export default function Card({webtoon, children} : Props ) {
    const {title, url, thumnail, genre, likeCount, firstDate, dayOfWeek, platform} = webtoon;
    let [modal, setModal] = useState<boolean>(false)

    return (
        <div className = 'relative border-2  text-center rounded-md border-none w-[100%] overflow-hidden'>
            <div className = ' mx-auto relative min-w-[200px] max-w-[250px] h-[300px]'>
                <Image 
                    className = "border-none border-2 rounded-xl cursor-pointer" 
                    src = {`/images${thumnail}`} 
                    alt = 'card image' 
                    fill = {true}
                    onClick={(event)=>{
                        if(event.target === event.currentTarget) setModal(false);
                        setModal(true)
                    }}
                ></Image>
            </div>
            <div className = 'h-[10%]'>
                <p className = 'mt-2  text-stone-400'>{title}</p>
            </div>
            {
                modal && 
                <ModalPortal>
                    <p>모달창 열렸슴니당!</p>
                    <WebtoonInfoModal webtoon={webtoon} onClose = {()=>setModal(false)}>
                        {/* <div className = "w-[500px] h-[700px] m-auto gap-4 grid grid-rows-[repeat(8,minmax(0,1fr))] grid-cols-2 bg-white rounded-xl mt-6">
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
                        </div> */}
                        <InfoModalDetail webtoon={webtoon}></InfoModalDetail>
                    </WebtoonInfoModal> 
                </ModalPortal>
            }
            {children}
        </div>
    )
}
