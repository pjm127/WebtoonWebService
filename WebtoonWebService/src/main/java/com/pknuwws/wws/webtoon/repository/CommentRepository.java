package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.dto.CommentListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.pknuwws.wws.webtoon.dto.CommentListResponse(c.id,c.userId, c.comment) from Comment c where c.webtoon.id = :webtoonId")
    List<CommentListResponse> findAllByWebtoonId(@Param("webtoonId") Long webtoonId);
}


