package com.pknuwws.wws.webtoon.repository;


import com.pknuwws.wws.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
}
