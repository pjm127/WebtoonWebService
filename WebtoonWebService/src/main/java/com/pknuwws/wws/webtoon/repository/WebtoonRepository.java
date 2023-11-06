package com.pknuwws.wws.webtoon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pknuwws.wws.webtoon.domain.Webtoon;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
	Page<Webtoon> findAll(Pageable pageable);
	Boolean existsByTitle(String title);
	Webtoon findByTitle(String title);
}
