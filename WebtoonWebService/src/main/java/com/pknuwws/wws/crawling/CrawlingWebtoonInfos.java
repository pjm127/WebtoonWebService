package com.pknuwws.wws.crawling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrawlingWebtoonInfos  {
	
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
		options.addArguments();
		driver = new EdgeDriver(options);

		login();
		// 검색 환경 개선 팝업 뜰 때까지 6분 대기
		try {
			Thread.sleep(1000 * 60 * 6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Webtoon> webtoons = new ArrayList<>();
		Set<String> urls = new HashSet<>();

		// 요일별 웹툰 링크 크롤링
		for (String day : DAYS) {
			switchToTab(0);
			urls.addAll(crawlWebtoonUrlsOfDay(day));
		}
		System.out.println("총 url 개수: "  + urls.size());
		
		// 웹툰별 정보 크롤링
		for (String url : urls) {
			((JavascriptExecutor) driver).executeScript("window.open()");
			switchToTab(1);
			try {
				webtoons.add(crawlWebtoons(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
			driver.close();
			System.out.println("현재 크롤링한 웹툰 수: " + webtoons.size());
			switchToTab(-1);
		}
		driver.quit();
		System.out.println("크롤링 완료");
		
		// 플랫폼별 전체 좋아요 수, (좋아요 수) / (플랫폼별 전체 좋아요 수)
		int overallLikeCount = 0;
		for (Webtoon webtoon : webtoons) {
			overallLikeCount += webtoon.getLikeCount();
		}
		
		for (Webtoon webtoon : webtoons) {
			webtoon.updateLikeProportion((float) (webtoon.getLikeCount() / (float) overallLikeCount));
			webtoon.updateOverallLikeCount(overallLikeCount);
			
			System.out.println("제목: " + webtoon.getTitle());
			System.out.println("링크: " + webtoon.getUrl());
			System.out.println("연재 요일: " + webtoon.getDayOfWeek());
			System.out.println("장르: " + webtoon.getGenre());
			System.out.println("플랫폼: " + webtoon.getPlatform());
			System.out.println("썸네일: " + webtoon.getThumbnailUrl());
			System.out.println("좋아요 수: " + webtoon.getLikeCount());
			System.out.println("전체 좋아요 수: " + webtoon.getOverallLikeCount());
			System.out.println("(좋아요 수) / (전체 좋아요 수): " + webtoon.getLikeProportion());
			System.out.println("첫 화 날짜: " + webtoon.getFirstDate());
			System.out.println("============================================================================");
			
			Webtoon original = webtoonRepository.findByTitle(webtoon.getTitle());
			// 해당 제목의 웹툰이 db에 있는 경우
			if (original != null) {
				System.out.println("저장돼 있는 웹툰");
				// db 정보 업데이트
				original.updateTitle(webtoon.getTitle());
				original.updateAuthor(webtoon.getAuthor());
				original.updateUrl(webtoon.getUrl());
				original.updateDayOfWeek(webtoon.getDayOfWeek());
				original.updateGenre(webtoon.getGenre());
				original.updatePlatform(webtoon.getPlatform());
				original.updateThumbnailUrl(webtoon.getThumbnailUrl());
				original.updateLikeCount(webtoon.getLikeCount());
				original.updateOverallLikeCount(webtoon.getOverallLikeCount());
				original.updateLikeProportion(webtoon.getLikeProportion());
				original.updateFirstDate(webtoon.getFirstDate());
				webtoonRepository.save(original);
				System.out.println("저장소에 갱신 완료");
				continue;
			}
			
			// 해당 제목의 웹툰이 db에 없는 경우 새로운 정보 저장
			webtoonRepository.save(webtoon);
			System.out.println("저장소에 저장 완료");
		}
		
		System.out.println("============================================================================");
		System.out.println("전체 웹툰 수: " + webtoons.size());
		System.out.println("============================================================================");
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
