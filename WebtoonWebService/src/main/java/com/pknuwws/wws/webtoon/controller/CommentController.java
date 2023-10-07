package com.pknuwws.wws.webtoon.controller;

import com.pknuwws.wws.webtoon.dto.CommentRequest;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.service.CommentService;
import com.pknuwws.wws.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final WebtoonService webtoonService;

    //댓글 작성
    @PostMapping("/create/{id}")
    public ResponseEntity<String> createComment(@PathVariable("id") Long webtoonId, Principal principal,
                                                @RequestBody CommentRequest createCommentRequest) {
        Webtoon webtoon = webtoonService.getWebtoonForComment(webtoonId);
        commentService.createComment(webtoon, createCommentRequest);
        return ResponseEntity.ok("댓글작성");
    }

    //댓글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long commnetId, @RequestBody CommentRequest createCommentRequest) {
        Comment comment = commentService.getComment(commnetId);
        commentService.updateComment(comment, createCommentRequest);
        return ResponseEntity.ok("댓글수정");
    }

    //댓글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commnetId) {
        Comment comment = commentService.getComment(commnetId);
        commentService.deleteComment(comment);
        return ResponseEntity.ok("댓글삭제");
    }
}
