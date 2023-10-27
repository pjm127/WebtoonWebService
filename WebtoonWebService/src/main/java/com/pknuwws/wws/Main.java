package com.pknuwws.wws;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		System.out.println("===================================\n"
				+ "네이버웹툰\n"
				+ "===================================");
		CrawlingNaverWebtoonUrls crawlingNaverWebtoonUrls = context.getBean(CrawlingNaverWebtoonUrls.class);
		crawlingNaverWebtoonUrls.process();
		
		System.out.println("===================================\n"
				+ "카카오웹툰\n"
				+ "===================================");
		CrawlingKakaoWebtoonUrls crawlingKakaoWebtoonUrls = context.getBean(CrawlingKakaoWebtoonUrls.class);
		crawlingKakaoWebtoonUrls.process();
		
		context.close();
	}

}
