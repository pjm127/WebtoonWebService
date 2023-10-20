package com.pknuwws.wws;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Thread naverWebtoonThread = new Thread() {
			@Override
			public void run() {
				System.out.println("===================================\n"
						+ "네이버웹툰\n"
						+ "===================================");
				CrawlingNaverWebtoonInfos crawlingNaverWebtoonInfos = context.getBean(CrawlingNaverWebtoonInfos.class);
				crawlingNaverWebtoonInfos.process();
			}
		};
		naverWebtoonThread.start();
		
		Thread kakaoWebtoonThread = new Thread() {
			@Override
			public void run() {
				System.out.println("===================================\n"
						+ "카카오웹툰\n"
						+ "===================================");
				CrawlingKakaoWebtoonInfos crawlingKakaoWebtoonInfos = context.getBean(CrawlingKakaoWebtoonInfos.class);
				crawlingKakaoWebtoonInfos.process();
			}
		};
		kakaoWebtoonThread.start();
		
		context.close();
	}

}
