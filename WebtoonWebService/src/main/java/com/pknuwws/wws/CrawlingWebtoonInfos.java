package com.pknuwws.wws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrawlingWebtoonInfos {
	
	protected final WebtoonRepository webtoonRepository;
	
	protected WebDriver driver;
	protected EdgeOptions options;
	protected List<String> tabs;
	protected static final String[] DAYS = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
//	protected static final String[] DAYS = {"mon"};
	protected static final String[] DAYS_KOREAN = {"월", "화", "수", "목", "금", "토", "일"};

	public void process() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KWC\\Desktop\\Misc\\webCrawlingRequirements\\msedgedriver.exe");

		options = new EdgeOptions();
		options.addArguments("--disable-infobars"
				);
		driver = new EdgeDriver(options);

		login();

		List<Webtoon> webtoons = new ArrayList<>();
		try {
			Set<String> urls = new HashSet<>();

			// 요일별 웹툰 링크 크롤링
			for (String day : DAYS) {
				switchToTab(0);
				urls.addAll(crawlWebtoonUrlsOfDay(day));
			}
			// 웹툰별 정보 크롤링
			for (String url : urls) {
				((JavascriptExecutor) driver).executeScript("window.open()");
				switchToTab(1);
				webtoons.add(crawlWebtoons(url));
				driver.close();
				switchToTab(-1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		System.out.println("크롤링 완료");
		
		// 플랫폼별 전체 좋아요 수, (좋아요 수) / (플랫폼별 전체 좋아요 수)
		int overallLikeCount = 0;
		for (Webtoon webtoon : webtoons) {
			overallLikeCount += webtoon.getLikeCount();
		}
		
		System.out.println("============================================================================");
		System.out.println("전체 웹툰 수: " + webtoons.size());
		System.out.println("============================================================================");
		for (Webtoon webtoon : webtoons) {
			webtoon.setLikePerOverall((float) (webtoon.getLikeCount() / (float) overallLikeCount));
			webtoon.setOverallLikeCount(overallLikeCount);
			
			System.out.println("제목: " + webtoon.getTitle());
			System.out.println("링크: " + webtoon.getUrl());
			System.out.println("연재 요일: " + webtoon.getDay());
			System.out.println("장르: " + webtoon.getGenre());
			System.out.println("플랫폼: " + webtoon.getPlatform());
			System.out.println("썸네일: " + webtoon.getThumbnailUrl());
			System.out.println("좋아요 수: " + webtoon.getLikeCount());
			System.out.println("전체 좋아요 수: " + webtoon.getOverallLikeCount());
			System.out.println("(좋아요 수) / (전체 좋아요 수): " + webtoon.getLikePerOverall());
			System.out.println("첫 화 날짜: " + webtoon.getFirstDate());
			System.out.println("============================================================================");
			
			webtoonRepository.save(webtoon);
			System.out.println("저장소에 저장 완료");
		}
	}
	
	protected abstract List<String> crawlWebtoonUrlsOfDay(String day);
	
	protected abstract Webtoon crawlWebtoons(String url);
	
	protected abstract void login();
	
	protected void switchToTab(int tabIndex) {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		if (tabIndex < 0) {
			tabIndex += tabs.size();
		}
		driver.switchTo().window(tabs.get(tabIndex));
	}
	
}
