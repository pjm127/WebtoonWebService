package com.pknuwws.wws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	
	private final WebtoonRepository webtoonRepository;
	
	@Bean
	public CrawlingKakaoWebtoonInfos crawlingKakaoWebtoonUrls() {
		return new CrawlingKakaoWebtoonInfos(webtoonRepository);
	}
	
	@Bean
	public CrawlingNaverWebtoonInfos crawlingNaverWebtoonUrls() {
		return new CrawlingNaverWebtoonInfos(webtoonRepository);
	}

}
