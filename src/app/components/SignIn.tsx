'use client'
import React, { FormEvent } from 'react'
import CloseButton from './ui/CloseButton';

type Props = {
    onClose : ()=>void
}

export default function SignIn({onClose} : Props) {

    const onSubmit = (e : FormEvent) => {
        e.preventDefault();
    }

    return (
        <div className = 'fixed w-full h-full top-0 left-0 bg-neutral-900/60 z-50'>
            <div className = 'fixed top-[20%] left-[35%] w-[500px] h-[300px] m-auto bg-white text-black rounded-xl p-5'>
                <form onSubmit={onSubmit} className = 'p-5 flex flex-col items-center'>
                    <input 
                        type = 'text' 
                        placeholder='아이디' 
                        className = 'p-2 shadow-sm mb-7'
                    ></input>
                    <input 
                        type = 'password' 
                        placeholder='비밀번호'
                        className= 'p-2 shadow-sm mb-10'
                    ></input>
                    <input type='submit' className='p-3 w-20 text-white rounded-lg bg-sky-500'>로그인</input>
                </form>
            </div>
            <button onClick={onClose} className='fixed top-[10%] right-[15%]'>
                <CloseButton></CloseButton>
            </button>
        </div>
    )
}
