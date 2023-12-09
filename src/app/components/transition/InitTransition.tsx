'use client'

import React, { useEffect, useLayoutEffect, useRef, useState } from 'react'
import gsap from 'gsap'

export default function InitTransition() {
    const background = useRef<HTMLDivElement>(null)
    const texts = useRef<HTMLSpanElement>(null)
    const [transitState, setTransitState] = useState<boolean>(true)

    gsap.set(background.current,{
        opacity : 1
    })
    const tl = gsap.timeline({
        defaults : {
            ease : "power3.inOut",
            duration : 1,
        }
    })
    tl.to(background.current, {
        opacity : 0,
        zIndex : "-1",
        onComplete : ()=>{
            setTransitState(false);
        }
    })
    useEffect(()=>{
        transitState && tl.play();
    },[])

    return (
        <div ref={background} className = 'w-full h-full fixed top-0 left-0 text-center bg-gray-800 z-[100]'>
            <span ref={texts} className = 'text-6xl font-bold text-white'>WEBTOON</span>
        </div>
    )
}
