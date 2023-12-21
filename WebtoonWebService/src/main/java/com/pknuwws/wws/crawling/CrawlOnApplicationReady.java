/*
package com.pknuwws.wws.crawling;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pknuwws.wws.webtoon.repository.WebtoonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CrawlOnApplicationReady {
	
	private final WebtoonRepository webtoonRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		CrawlingNaverWebtoonInfos crawlingNaverWebtoonInfos = new CrawlingNaverWebtoonInfos(webtoonRepository);
		CrawlingKakaoWebtoonInfos crawlingKakaoWebtoonInfos = new CrawlingKakaoWebtoonInfos(webtoonRepository);
		
		Thread naverWebtoonThread = new Thread(() -> {
			crawlingNaverWebtoonInfos.process();
		});
		Thread kakaoWebtoonThread = new Thread(() -> {
			crawlingKakaoWebtoonInfos.process();
		});
		
		naverWebtoonThread.start();
		kakaoWebtoonThread.start();
	}

}
*/
