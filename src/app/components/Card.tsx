'use client'
import React, { useState } from 'react'
import Image from 'next/image'
import ModalPortal from './ModalPortal'
import WebtoonInfoModal from './WebtoonInfoModal'
import { Webtoon } from '../models/webtoonType'

type Props = {
    webtoon : Webtoon
}

export default function Card({webtoon} : Props ) {
    let [modal, setModal] = useState<boolean>(false)

    return (
        <div className = 'border-2 text-center rounded-md border-none w-[200px] overflow-hidden'>
            <Image 
                className = "border-none border-2 rounded-xl" 
                src = {`/images${webtoon.thumnail}`} 
                alt = 'card image' 
                width = {200} 
                height = {400}
                onClick={(event)=>{
                    if(event.target === event.currentTarget) setModal(false);
                    setModal(true)
                }}
            ></Image>
            <p className = 'mt-2  text-stone-400'>{webtoon.title}</p>
            {
                modal && 
                <ModalPortal>
                    <p>모달창 열렸슴니당!</p>
                    <WebtoonInfoModal webtoon={webtoon}></WebtoonInfoModal>
                </ModalPortal>
            }
        </div>
    )
}
