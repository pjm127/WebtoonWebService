package com.pknuwws.wws.webtoon.service;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.dto.CommentRequest;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_WEBTOON;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    //해당 웹툰 - 댓글 전체
    public void getAllComment() {
        commentRepository.findAll();
    }

    //해당 웹툰 - 댓글 한개 (수정, 삭제)
    public Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(()->new CustomException(NOT_FOUND_WEBTOON));
    }


    //댓글작성
    public void createComment(Webtoon webtoon, CommentRequest createCommentRequest) {
        log.info("createCommentRequest = {}", createCommentRequest);
        log.info("web = {}", webtoon);
        Comment com = Comment.builder()
                .webtoon(webtoon)
                .coment(createCommentRequest.getComment())
                .build();
        commentRepository.save(com);

    }

    //댓글 수정
    @Transactional
    public void updateComment(Comment comment, CommentRequest createCommentRequest) {
        comment.update(createCommentRequest.getComment());
    }
    //댓글 삭제
    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
