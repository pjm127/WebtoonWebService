import React from 'react'

export default function SignUp() {
    const date = new Date()
    const year = date.getFullYear();
    const inputStyle = 'w-[70%] py-2 pl-3 rounded-md mb-5 text-black'
    const buttonStyle = 'w-[100px] p-2 mt-10 m-auto border-[0.05rem] border-sky-200 rounded-lg'

    return (
        <div className = 'p-5 mt-16 w-[80%] md:w-[600px] m-auto text-white transition-all duration-500'>
            <form className = 'flex flex-col' action='http://localhost:9001/api/v1/user/join' method='post' name = 'signup' encType='application/json'>
                    <label>
                        <input type = 'text' name = 'userId' placeholder='아이디' className = {`${inputStyle}`} required/>
                        <button className = {`${buttonStyle} ml-4`}>중복 확인</button>
                    </label>
                    <input type = 'password' name = 'password' placeholder='비밀번호' className = {`${inputStyle}`} required/>
                    <label>
                        <input type = 'password'  placeholder='비밀번호 확인' className = {`${inputStyle}`}/>
                        <button className = {`${buttonStyle} ml-4`}>중복 확인</button>
                    </label>
                    <input type = 'text' name = 'nickName' placeholder='닉네임' className = {`${inputStyle}`} required/>
                    <label className = 'mb-5'>
                        <input type = 'radio' name = 'gender' className = 'mr-3' value = 'MALE' /><label>남</label>
                       <input type = 'radio' name = 'gender' className = 'mr-3 ml-3' value = 'FEMALE' /> <label>여</label>
                    </label>
                    <label>
                        <label className = 'mr-3'>
                            생년월일
                            
                        </label>
                        <input type = 'date' name = 'birthDate' min={'1900-01-01'} max={`${year}-12-31`} className = 'p-1 ml-4 text-black rounded-md' required/>
                    </label>
                    <input type = 'submit' className = {`${buttonStyle}`} value="가입"/>
            </form>
        </div>
    )
}
