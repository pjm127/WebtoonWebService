package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
