import React from 'react'
import CircularSector from './CircularSector'
import { getGenreCircleThumnail } from '../service/webtoonInfo'

const webtoonPageUrl = [
    '/jenre/action',
    '/jenre/fantasy',
    '/jenre/wuxia',
    '/jenre/daily',
    '/jenre/sports',
    '/jenre/romance',
    '/jenre/mystery',
]

export default async function GenreCircle() {
    
    const thumnails = await getGenreCircleThumnail()

    return (
        <CircularSector sectorNum={7} thumnails={thumnails} pageUrl = {webtoonPageUrl}></CircularSector>
     )
}
