package com.pknuwws.wws.webtoon.presentation;

import com.pknuwws.wws.webtoon.dto.CommentListRequest;
import com.pknuwws.wws.webtoon.dto.CommentRequest;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.application.CommentService;
import com.pknuwws.wws.webtoon.application.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final WebtoonService webtoonService;

    //웹툰선택시 해당 웹툰의 댓글 전체
    @GetMapping("/detail/{webtoonId}")
    @Operation(summary = "웹툰 선택 시 해당 웹툰의 댓글 전체 리스트")
    public ResponseEntity<List<CommentListRequest>> getWebtoon(@PathVariable("webtoonId") Long webtoonId){
        return ResponseEntity.ok(commentService.getAllComment(webtoonId));
    }


    //댓글 작성
    @PostMapping("/create/{id}")
    @Operation(summary = "댓글 작성")
    public ResponseEntity<String> createComment(@PathVariable("id") Long webtoonId, Principal principal,
                                                @RequestBody CommentRequest createCommentRequest) {
        Webtoon webtoon = webtoonService.getWebtoonForComment(webtoonId);
        commentService.createComment(webtoon, createCommentRequest);
        return ResponseEntity.ok("댓글작성");
    }

    //댓글 수정
    @PutMapping("/update/{id}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long commnetId, @RequestBody CommentRequest createCommentRequest) {
        Comment comment = commentService.getComment(commnetId);
        commentService.updateComment(comment, createCommentRequest);
        return ResponseEntity.ok("댓글수정");
    }

    //댓글 삭제
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commnetId) {
        Comment comment = commentService.getComment(commnetId);
        commentService.deleteComment(comment);
        return ResponseEntity.ok("댓글삭제");
    }
}
