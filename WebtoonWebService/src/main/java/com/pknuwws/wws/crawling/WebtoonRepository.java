package com.pknuwws.wws.crawling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
	
	Boolean existsByTitle(String title);
	
	Webtoon findByTitle(String title);

}
