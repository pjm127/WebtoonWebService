package com.pknuwws.wws.webtoon.application;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.dto.CommentListResponse;
import com.pknuwws.wws.webtoon.dto.CommentRequest;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.repository.CommentRepository;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_COMMENT;
import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_WEBTOON;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final WebtoonRepository webtoonRepository;

    //해당 웹툰 - 댓글 전체
    public List<CommentListResponse> getAllComment(Long webtoonId){
        return commentRepository.findAllByWebtoonId(webtoonId);

    }

    //해당 웹툰 - 댓글 한개 (수정, 삭제)
    public Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(()->new CustomException(NOT_FOUND_WEBTOON));
    }


    //댓글작성
    public void createComment(Long webtoonId, CommentRequest createCommentRequest) {
        Webtoon webtoon = webtoonRepository.findById(webtoonId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_WEBTOON));
        Comment com = Comment.builder()
                .webtoon(webtoon)
                .comment(createCommentRequest.getComment())
                .userId(createCommentRequest.getUserId())
                .build();
        commentRepository.save(com); //action t est

    }

    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentRequest createCommentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        comment.update(createCommentRequest.getComment());
    }
    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        commentRepository.delete(comment); //삭제e
    }
}
