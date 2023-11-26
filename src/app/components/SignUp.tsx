import React from 'react'

export default function SignUp() {
    const date = new Date()
    const year = date.getFullYear();
    const inputStyle = 'w-[70%] py-2 pl-3 rounded-md mb-5 text-black'
    const buttonStyle = 'w-[100px] p-2 mt-10 m-auto border-[0.05rem] border-sky-200 rounded-lg'

    return (
        <div className = 'p-5 mt-16 w-[80%] md:w-[600px] m-auto text-white transition-all duration-500'>
            <form className = 'flex flex-col'>
                    <div>
                        <input type = 'input' placeholder='아이디' className = {`${inputStyle}`}/>
                        <button className = {`${buttonStyle} ml-4`}>중복 확인</button>
                    </div>
                    <input type = 'password' placeholder='비밀번호' className = {`${inputStyle}`}/>
                    <div>
                        <input type = 'password'  placeholder='비밀번호 확인' className = {`${inputStyle}`}/>
                        <button className = {`${buttonStyle} ml-4`}>중복 확인</button>
                    </div>
                    <input type = 'input' placeholder='닉네임' className = {`${inputStyle}`}/>
                    <div className = 'mb-5'>
                        <input type = 'radio' className = 'mr-3'/><label>남</label>
                        <input type = 'radio' className = 'mr-3 ml-3'/><label>여</label>
                    </div>
                    <div>
                        <span className = 'mr-3'>생년월일</span>
                        <input type = 'date' min={'1900-01-01'} max={`${year}-12-31`} className = 'p-1 text-black rounded-md'/>
                    </div>
                    <input type = 'submit' className = {`${buttonStyle}`}/>
            </form>
        </div>
    )
}
