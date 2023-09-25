package com.pknuwws.wws;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class CrawlingNaverWebtoonUrls {
	
	private WebDriver driver;
	private static final String baseUrl = "https://comic.naver.com/webtoon?tab=";
	private static final String[] days = { "mon"/* , "tue", "wed", "thu", "fri", "sat", "sun" */}; 
	
	public void process() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KWC\\Desktop\\Misc\\webCrawlingRequirements\\msedgedriver.exe");
		
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless", // 브라우저 안 보이게 설정
				"--disable-infobars");
		
		try {
			Set<String> urls = new HashSet<>();
			
			// 요일별 웹툰 링크 크롤링
			for (String day : days) {
				driver = new EdgeDriver();
				urls.addAll(getDataList(day));
				
				driver.close();
				driver.quit();
			}
			// 웹툰별 정보 크롤링
			for (String url : urls) {
				driver = new EdgeDriver();
				getWebtoonInfos(url);
				
				driver.close();
				driver.quit();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Set<String> getDataList(String day) throws InterruptedException {
		Set<String> urls = new HashSet<>();
		
		driver.get(baseUrl + day);
		
		// 자바스크립트 다 불러올 때까지 5초 기다림
		// 요일마다 적용해야 된다.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		int webtoonCount = 0;
		
		// TODO
		// Poster__link--sopnC 중 첫 3개는 추천 요일웹툰인데
		// 이후 수집할 링크에 이미 포함되어 있으므로 제외해야 함
		List<WebElement> elements = driver.findElements(By.className("Poster__link--sopnC"));
		
		for (WebElement element : elements) {
			String url = element.getDomProperty("href");
			System.out.println("================");
			System.out.println(url);
			urls.add(url);
		}
		webtoonCount += elements.size();
		
		System.out.println("================");
		System.out.println("webtoonCount(" + day + "): " + webtoonCount);
		
		return urls;
	}
	
	/*
	 * 수집 항목들:
	 * 제목
	 * 링크
	 * 썸네일
	 * 조회수 (찾기 어려움. 좋아요로 대체할 수 있는가?)
	 * 장르
	 * 첫 화 날짜
	 * 연재 요일
	 */
	private void getWebtoonInfos(String url) {
		driver.get(url);
		// 자바스크립트 다 불러올 때까지 5초 기다림
		// 요일마다 적용해야 된다.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// 제목
		String title = driver.findElement(By.className("EpisodeListInfo__title--mYLjC")).getText();
		System.out.println("제목: " + title);
		
		// 썸네일
		// TODO: 현재 null로 표시되는 문제 수정할 것
		String thumbnail = driver.findElement(By.className("Poster__image--d9XTI")).getDomAttribute("src");
		System.out.println("썸네일: " + thumbnail);
	}

}
