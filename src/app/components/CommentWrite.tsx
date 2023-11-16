'use client'
import React, { Dispatch, SetStateAction, useState } from 'react'

interface Props {
  // setCommentModal : Dispatch<SetStateAction<boolean>>
  setCommentModal : () => void
}

export default function CommentWrite({setCommentModal} : Props) {
  let [comment, setComment] = useState('')

  return (
    <div className = "fixed top-[20%] left-0 w-full h-full">
        <div className = "m-auto p-6 w-[450px] h-[300px] border-gray-400 border-2 bg-white ">
            <form>
              <textarea
                className = 'w-full h-[200px] p-2 rounded-xl border-[#eee] border-2'
                placeholder = '댓글을 작성해주세요.'
                required
                value={comment}
                onChange={e=>setComment(e.target.value)}
              />
            </form>
            <button className = "rounded-md w-[15%] p-1 mr-4 mt-3 text-white bg-sky-300 border-sky-300 border-2">작성</button>
            <button 
              className = "rounded-md w-[15%] p-1 border-gray-300 bg-gray-300 border-2"
              onClick={(e)=>{setCommentModal()}}
            >취소</button>
        </div>
    </div>
  )
}

