'use client'

import React, { MouseEventHandler, MouseEvent, useState, useEffect, useRef } from 'react'
import styles from "./sector.module.css"
import "../../app/globals.css"
import { WebtoonThum } from '../service/webtoonInfo'
import Link from 'next/link'
import TransitionContext from '../context/TransitionContext'


const mainCircle = [
    '검색', 
    '신작 웹툰', 
    '웹툰 랭킹', 
    '장르별 웹툰보기', 
    '웹툰 추천받기',
]

type Props = {
    sectorNum : number,
    thumnails : WebtoonThum[],
    pageUrl : string[]
}

export default function CircularSector({sectorNum, thumnails, pageUrl} : Props) {
    const sectorOpacity : boolean[] = [];
    const sectorSkew : boolean[] = [];
    let skewExp = (10*sectorNum-32);
    let rotateExp = (0+(360/sectorNum));
    const circleRef = useRef<HTMLDivElement>();

    for(let i = 0; i < sectorNum; i++){ 
        sectorOpacity.push(false) 
        sectorSkew.push(false)
    }
    
    let [sectorOpacityState, setSectorOpacityState] = useState<boolean[]>(sectorOpacity);
    let [sectorSkewState, setSectorSkewsState] = useState<boolean[]>(sectorSkew)

    // useEffect(()=>{
    //     return ()=>{
    //         if(circleRef.current){
    //             circleRef.current.classList.add('animation')
    //         }
    //         setTimeout(()=>{}, 3000)
    //     }
    // },[])
    // useEffect(()=>{
    //     if(circleRef.current){
    //         circleRef.current.classList.remove('animation')
    //     }
    // },[])

    // function makeCircle(){
    //     let arr = []

    //     // for(let i = 0; i < sectorNum; i++){
    //     //     arr.push(
    //     //         <div className = {`sector rotate-[${0+72*i}deg] skew-x-[18deg] bg-[url(/images/${thumnails[i].thumnail})]`}></div>
    //     //     )
    //     // }
    //     // return arr;
    // }
    const mouseOver = (e : MouseEvent<HTMLDivElement>, sectorListIndex : number) => {

        const newSectorOpacityState = sectorOpacityState.map((sectorState, index)=>{
            return sectorListIndex !== index ? sectorState = true : sectorState = false
        })
        const newSectorSkewState = sectorSkewState.map((sectorState, index)=>{
            return sectorListIndex === index ? sectorState = true : sectorState = false 
        })
        const sectors = document.querySelectorAll<HTMLElement>('.sector');
        sectors[sectorListIndex].style.transform = `rotate(${rotateExp*sectorListIndex-15}deg) skew(${skewExp-20}deg)`
        sectors[sectorListIndex].style.zIndex = `${sectorNum}`
        
        setSectorOpacityState([...newSectorOpacityState])
        setSectorSkewsState([...newSectorSkewState])
    }
    const mouseOut = (e : MouseEvent<HTMLDivElement>, sectorListIndex : number) => {

        const newSectorOpacityState = sectorOpacityState.map((sectorState, index)=>{
            return sectorState = false;
        })
        const newSectorSkewState = sectorSkewState?.map((sectorState, index)=>{
            return sectorState = false;
        })
        const sectors = document.querySelectorAll<HTMLElement>('.sector');
        sectors[sectorListIndex].style.transform = `rotate(${rotateExp*sectorListIndex}deg) skew(${skewExp}deg)`
        sectors[sectorListIndex].style.zIndex = `${sectorListIndex}`

        setSectorOpacityState([...newSectorOpacityState])
        setSectorSkewsState([...newSectorSkewState])
    }
   

    return (
        <section className = "circle items-center mt-10 text-center">
            <div className = "wrapper m-auto">
                {
                    thumnails.map((thumnail, i)=>{
                        return(
                            <Link href = {pageUrl[i]}>
                                <div key = {i} className = {`sector ${(sectorOpacityState[i] === true) && 'hoverOpacity'}`}
                                    style = {{
                                        transform : `
                                            rotate(${rotateExp*i}deg) 
                                            skewX(${skewExp}deg)
                                        `,
                                        backgroundImage : `url(/images/${thumnail.thumnail})`,
                                        zIndex : `${i}`
                                    }}
                                    onMouseOver={(e)=>{mouseOver(e, i)}}
                                    onMouseOut={(e)=>{mouseOut(e, i)}}
                                >
                                    <p className = "relative top-[90%] text-right align-text-bottom">{mainCircle[i]}</p>
                                </div>
                            </Link>
                         )
                    })
                }
            </div>
        </section>
    )
}
