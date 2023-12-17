'use client'
import React, { MouseEvent, ChangeEvent, FormEvent, useState } from 'react'
import CloseButton from './ui/CloseButton';

type Props = {
    onClose : ()=>void
}

export default function SignIn({onClose} : Props) {
    const [loginInfo, setLoginInfo] = useState({
        userId : "",
        password : "",
    })
    const handleUserId = (e : ChangeEvent<HTMLInputElement>) => {
        setLoginInfo((prevState)=>{
            return {...prevState, userId : e.target.value};
        })
    }
    const handlePassword = (e : ChangeEvent<HTMLInputElement>) => {
        setLoginInfo((prevState)=>{
            return {...prevState, password : e.target.value};
        })
    }

    async function logIn(e : MouseEvent<HTMLInputElement>){
        console.log("데이터 맞냐?", JSON.stringify(loginInfo));
        (
            async()=>{
                await fetch('http://localhost:9001/api/v1/user/login',{
                    method : 'POST',
                    headers : {
                        "Content-Type" : 'application/json',
                    },
                    body : JSON.stringify(loginInfo),
                })
                .then(res => console.log("응답은?? : ",res))
            }
        )()
    }

    return (
        <div className = 'fixed w-full h-full top-0 left-0 bg-neutral-900/60 z-50'>
            <div className = 'absolute top-[20%] left-[35%] w-[500px] h-[300px] m-auto bg-white text-black rounded-xl p-5'>
                <form name = 'login' className = 'p-5 flex flex-col items-center'>
                    <input 
                        type = 'text' 
                        name = 'userId'
                        placeholder='아이디' 
                        className = 'p-2 shadow-sm mb-7'
                        onChange={handleUserId}
                    ></input>
                    <input 
                        type = 'password' 
                        name = 'password'
                        placeholder='비밀번호'
                        className= 'p-2 shadow-sm mb-10'
                        onChange={handlePassword}
                    ></input>
                    <input type='button' className='p-3 w-20 text-white rounded-lg bg-sky-500' value = '로그인' onClick = {logIn}/>
                </form>
            </div>
            <button onClick={onClose} className='fixed top-[10%] right-[15%]'>
                <CloseButton></CloseButton>
            </button>
        </div>
    )
}
