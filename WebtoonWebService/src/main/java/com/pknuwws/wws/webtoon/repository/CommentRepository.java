package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.dto.CommentListRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.pknuwws.wws.webtoon.dto.CommentListRequest(c.userId, c.comment) from Comment c where c.webtoon.id = :webtoonId")
    List<CommentListRequest> findAllByWebtoonId(@Param("webtoonId") Long webtoonId);
}


