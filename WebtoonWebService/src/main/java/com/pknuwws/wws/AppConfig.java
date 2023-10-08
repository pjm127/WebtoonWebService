package com.pknuwws.wws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public CrawlingKakaoWebtoonInfos crawlingKakaoWebtoonUrls() {
		return new CrawlingKakaoWebtoonInfos();
	}
	
	@Bean
	public CrawlingNaverWebtoonInfos crawlingNaverWebtoonUrls() {
		return new CrawlingNaverWebtoonInfos();
	}

}
