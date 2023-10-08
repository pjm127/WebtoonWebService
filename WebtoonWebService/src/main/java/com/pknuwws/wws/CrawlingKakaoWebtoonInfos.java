package com.pknuwws.wws;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//@Component
public class CrawlingKakaoWebtoonInfos extends CrawlingWebtoonInfos {

	public CrawlingKakaoWebtoonInfos() {
		BASE_URL = "https://webtoon.kakao.com/original-webtoon?tab=";
		GENRES = new String[] {"로맨스", "액션", "판타지", "시대극", "무협", "스포츠", "미스테리", "일상"};
	}

	@Override
	public void process() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KWC\\Desktop\\Misc\\webCrawlingRequirements\\msedgedriver.exe");

		options = new EdgeOptions();
		options.addArguments("--disable-infobars"
//				"--headless",
//				"--no-sandbox",
//				"--user-data-dir=C:\\Users\\KWC\\AppData\\Local\\Microsoft\\Edge\\User Data"
				);
		driver = new EdgeDriver(options);
		
		// 성인 인증 웹툰을 위해 수동 로그인
		login();

		try {
			Set<String> urls = new HashSet<>();

			// 요일별 웹툰 링크 크롤링
			for (String day : DAYS) {
				switchToTab(0);
				urls.addAll(getWebtoonListOfDay(day));

//				driver.quit();
			}
			// 웹툰별 정보 크롤링
			for (String url : urls) {
//				driver = new EdgeDriver(options);
				((JavascriptExecutor) driver).executeScript("window.open()");
				switchToTab(1);
				getWebtoonInfos(url);
				driver.close();
				switchToTab(-1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	@Override
	protected List<String> getWebtoonListOfDay(String day) {
		List<String> urls = new ArrayList<>();
		
		driver.get(BASE_URL + day);

		// 자바스크립트 다 불러올 때까지 5초 기다림
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebElement container = driver.findElement(By.cssSelector(".flex.flex-col.gap-4"));
		
		// 요일 맨 위에 크게 있는 웹툰
		List<WebElement> elements = container.findElements(By.className("cursor-pointer"));
//		for (WebElement element : elements) {
//			String url = element.getAttribute("href");
//			if (url != null) {
//				System.out.println("================");
//				System.out.println(url);
//				urls.add(url);
//			}
//		}
		
		// 그 아래 있는 작은 웹툰
		elements.addAll(container.findElements(By.cssSelector("a.w-full.h-full.relative.overflow-hidden.rounded-8")));
		for (WebElement element : elements) {
			String url = element.getAttribute("href");
			System.out.println("================");
			System.out.println(url);
			urls.add(url);
		}
		
		return urls;
	}

	/*
	 * 수집 항목들:
	 * 제목
	 * 링크
	 * 썸네일
	 * 장르: 로맨스, 액션, 판타지, 시대극, 무협, 스포츠, 미스터리, 일상
	 * 조회수 (찾기 어려움. 좋아요로 대체할 수 있는가?, 최신 화 기준)
	 * 첫 화 날짜 (신작 웹툰 판별 목적)
	 * 연재 요일
	 * 요일
	 */
	@Override
	protected Map<String, String> getWebtoonInfos(String url) {
		Map<String, String> webtoonInfos = new HashMap<>();
		driver.get(url);

		// 썸네일 뜰 때까지 기다림
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.className("Poster__image--d9XTI"))
				);

		// 제목
		String title = driver.findElement(By.className("EpisodeListInfo__title--mYLjC")).getText();
		webtoonInfos.put("title", title);
		System.out.println("제목: " + title);

		// 썸네일
		String thumbnail = driver.findElement(By.className("Poster__image--d9XTI")).getAttribute("src");
		webtoonInfos.put("thumbnail", thumbnail);
		System.out.println("썸네일: " + thumbnail);
		
		// 요일
		System.out.print("연재 요일: ");
		String dayInfo = driver.findElement(By.className("ContentMetaInfo__info_item--utGrf")).getText();
		List<String> days = new ArrayList<>();
		for (String d : DAYS_KOREAN) {
			if (dayInfo.contains(d)) {
				System.out.print(d);
				days.add(d);
			}
		}
		System.out.println();

		// 태그 (장르)
		// GENRES에 지정된 키워드가 태그에 있으면 표시
		List<WebElement> tags = driver.findElements(By.className("TagGroup__tag--xu0OH"));
		int genreIndex = 0;
		for (int i = 0; i < tags.size(); i++) {
			for (String genre : GENRES) {
				if (tags.get(i).getText().contains(genre)) {
					if (!webtoonInfos.values().contains(genre)) {
						webtoonInfos.put("genre" + (genreIndex + 1), genre);
					}
					System.out.println("태그(장르) " + (genreIndex + 1) + ": " + genre);
					genreIndex++;
				}
			}
		}

		// 최신 화에서 좋아요 가져오기
		// 최신 화는 Webdriver 새로 열기
		String latestUrl = driver.findElement(By.className("EpisodeListList__link--DdClU")).getAttribute("href");
		System.out.println("최신 화 링크: " + latestUrl);
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchToTab(2);
		driver.get(latestUrl);
		WebDriverWait latestWebDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		latestWebDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".u_cnt._count"))
				);
		String likeCount = driver.findElement(By.cssSelector(".u_cnt._count")).getText();
		webtoonInfos.put("likeCount", likeCount);
		System.out.println("최신화 좋아요: " + likeCount);
		driver.close();
		switchToTab(1);

		// 첫 화부터 정렬해서 첫 화 날짜 가져오기
		WebElement ascendingButton = driver.findElements(
				By.className("EpisodeListView__button_tab--NUsn2")).get(1);
		System.out.println(ascendingButton.getText());
		ascendingButton.click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String firstDate = driver.findElement(By.className("date")).getText();
		webtoonInfos.put("firstDate", firstDate);
		System.out.println(firstDate);

		return webtoonInfos;
	}
	
	@Override
	protected void login() {
		driver.get("https://webtoon.kakao.com/more");
		
		WebElement element = driver.findElement(By.cssSelector(".flex.justify-center.items-start"));
		element.click();
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
