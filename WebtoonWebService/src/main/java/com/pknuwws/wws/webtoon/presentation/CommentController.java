package com.pknuwws.wws.webtoon.presentation;

import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.dto.CommentListResponse;
import com.pknuwws.wws.webtoon.dto.CommentRequest;
import com.pknuwws.wws.webtoon.application.CommentService;
import com.pknuwws.wws.webtoon.application.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CommentListResponse>> getWebtoon(@PathVariable("webtoonId") Long webtoonId){
        return ResponseEntity.ok(commentService.getAllComment(webtoonId));
    }


    //댓글 작성
    @PostMapping("/create/{id}")
    @Operation(summary = "댓글 작성")
    public ResponseEntity<String> createComment(@PathVariable("id") Long webtoonId,
                                                @RequestBody CommentRequest createCommentRequest) {

        commentService.createComment(webtoonId,createCommentRequest);
        return ResponseEntity.ok("댓글작성");
    }

    //댓글 수정
    @PutMapping("/update/{id}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long commentId, @RequestBody CommentRequest createCommentRequest) {

        commentService.updateComment(commentId, createCommentRequest);
        return ResponseEntity.ok("댓글수정");
    }

    //댓글 삭제
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글삭제");
    }
}
