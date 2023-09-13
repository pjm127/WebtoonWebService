package com.pknuwws.wws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public CrawlingWebtoonUrls crawlingExample() {
		return new CrawlingWebtoonUrls();
	}

}
