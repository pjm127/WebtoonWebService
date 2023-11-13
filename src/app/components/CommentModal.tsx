'use client'
import { comment } from 'postcss';
import React, { useState } from 'react'
import CommentWrite from './CommentWrite';
import ModalPortal from './ModalPortal';

export default function CommentModal() {
  let [commentModal, setCommentModal] = useState(false);

  return (
    <div className = "flex col-start-1 col-end-3 justify-between">
      <p className = "">Comment</p>
      <button 
        className = "p-5 rounded-lg"
        onClick = {()=>{setCommentModal(true)}}
      >작성하기</button>
      {
        commentModal && 
        <ModalPortal>
          <CommentWrite></CommentWrite>
        </ModalPortal>
      }
    </div>
  )
}

