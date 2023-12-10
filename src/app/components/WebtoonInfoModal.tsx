import React, { Dispatch, SetStateAction } from 'react'
import { TempWebtoon, Webtoon } from '../models/webtoonType'
import Image from 'next/image'
import Link from 'next/link'
import CommentModal from './CommentModal'
import CloseButton from './ui/CloseButton'

export default function WebtoonInfoModal({
    children,
    webtoon : {title, url, thumnail, genre, likeCount, firstDate, dayOfweek, platform},
    onClose
} : {
    children : React.ReactNode,
    webtoon : TempWebtoon,
    onClose : () => void
}) {
  return (
    <div className = " bg-neutral-900/60 fixed top-0 left-0 w-full h-full">  
        <button className = 'fixed top-10 right-10' onClick = {()=>{onClose()}}>
                <CloseButton/>
        </button>
        {children}
    </div>
  )
}
