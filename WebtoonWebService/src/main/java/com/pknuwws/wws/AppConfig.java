package com.pknuwws.wws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public CrawlingKakaoWebtoonUrls crawlingKakaoWebtoonUrls() {
		return new CrawlingKakaoWebtoonUrls();
	}
	
	@Bean
	public CrawlingNaverWebtoonUrls crawlingNaverWebtoonUrls() {
		return new CrawlingNaverWebtoonUrls();
	}

}
