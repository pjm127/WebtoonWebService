package com.pknuwws.wws;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		CrawlingWebtoonUrls crawlingExample = context.getBean(CrawlingWebtoonUrls.class);
		crawlingExample.process();
		
		context.close();
	}

}
