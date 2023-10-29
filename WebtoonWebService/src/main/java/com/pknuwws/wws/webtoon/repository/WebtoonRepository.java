package com.pknuwws.wws.webtoon.repository;


import com.pknuwws.wws.webtoon.domain.Webtoon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
  Page<Webtoon> findAll(Pageable pageable);

  @Query("SELECT w FROM Webtoon w WHERE w.genre = :genre")
  Page<Webtoon> findByGenre(@Param("genre") String genre, Pageable pageable);
}
