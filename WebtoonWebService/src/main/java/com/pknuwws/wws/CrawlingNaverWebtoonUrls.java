package com.pknuwws.wws;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlingNaverWebtoonUrls {

	private WebDriver driver;
	private EdgeOptions options;
	private static final String BASE_URL = "https://comic.naver.com/webtoon?tab=";
	private static final String[] DAYS = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
	private static final String[] GENRES = {"로맨스", "액션", "판타지", "시대극", "무협", "스포츠", "미스터리", "일상"};

	public void process() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KWC\\Desktop\\Misc\\webCrawlingRequirements\\msedgedriver.exe");

		options = new EdgeOptions();
		options.addArguments("--disable-infobars");

		try {
			Set<String> urls = new HashSet<>();

			// 요일별 웹툰 링크 크롤링
			for (String day : DAYS) {
				driver = new EdgeDriver(options);
				urls.addAll(getWebtoonListOf(day));

				driver.quit();
			}
			// 웹툰별 정보 크롤링
			for (String url : urls) {
				driver = new EdgeDriver(options);
				getWebtoonInfos(url);

				driver.quit();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Set<String> getWebtoonListOf(String day) throws InterruptedException {
		Set<String> urls = new HashSet<>();

		driver.get(BASE_URL + day);

		// 자바스크립트 다 불러올 때까지 5초까지만 기다림
		// 요일마다 적용해야 된다.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		int webtoonCount = 0;

		// TODO
		// Poster__link--sopnC 중 첫 3개는 추천 요일웹툰인데
		// 이후 수집할 링크에 이미 포함되어 있으므로 제외해야 함
		List<WebElement> elements = driver.findElements(By.className("Poster__link--sopnC"));

		for (WebElement element : elements) {
			String url = element.getAttribute("href");
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
	 * TODO
	 * 수집 항목들:
	 * 제목 ok
	 * 링크 ok
	 * 썸네일 ok
	 * 장르: 로맨스, 액션, 판타지, 시대극, 무협, 스포츠, 미스터리, 일상 ok
	 * 조회수 (찾기 어려움. 좋아요로 대체할 수 있는가?, 최신 화 기준) ok
	 * 첫 화 날짜 (신작 웹툰 판별 목적) ok
	 * 연재 요일 ok
	 * 로그인 구현 필요
	 */
	private Map<String, String> getWebtoonInfos(String url) {
		Map<String, String> webtoonInfos = new HashMap<>();
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(url);
		
		// 성인 웹툰인 경우 몇 초 지난 후 로그인 페이지로 이동되므로
		// 이 시간 동안 기다림
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 성인 웹툰이라서 로그인 페이지로 이동하면 로그인 진행
		if (driver.getTitle().contains("로그인")) {
			login();
		}
		
		// 썸네일 뜰 때까지 기다림
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
		
		// 태그 (장르)
		// GENRES에 지정된 키워드가 태그에 있으면 표시
		List<WebElement> tags = driver.findElements(By.className("TagGroup__tag--xu0OH"));
		for (int i = 0; i < tags.size(); i++) {
			for (String genre : GENRES) {
				if (tags.get(i).getText().contains(genre)) {
					webtoonInfos.put("genre" + (i + 1), genre);
					System.out.println("태그 (장르)" + (i + 1) + ": " + genre);
				}
			}
		}
		
		// 최신 화에서 좋아요 가져오기
		// 최신 화는 Webdriver 새로 열기
		String latestUrl = driver.findElement(By.className("EpisodeListList__link--DdClU")).getAttribute("href");
		System.out.println("최신 화 링크: " + latestUrl);
		WebDriver latestDriver = new EdgeDriver(options);
		latestDriver.get(latestUrl);
		WebDriverWait latestWebDriverWait = new WebDriverWait(latestDriver, Duration.ofSeconds(5));
		latestWebDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".u_cnt._count"))
				);
		String likeCount = latestDriver.findElement(By.cssSelector(".u_cnt._count")).getText();
		webtoonInfos.put("likeCount", likeCount);
		System.out.println("최신화 좋아요: " + likeCount);
		latestDriver.quit();
		
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
	
	private void login() {
		Scanner scanner = null;
		try {
			// 아래 경로에 성인 인증 가능한 아이디, 비밀번호 한 줄 씩 적힌 파일 있어야 함
			scanner = new Scanner(new File("./src/main/resources/idpw.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// id 입력
		WebElement element = driver.findElement(By.id("id"));
		element.click();
		element.sendKeys(scanner.nextLine());
		
		// pw 입력
		element = driver.findElement(By.id("pw"));
		element.click();
		element.sendKeys(scanner.nextLine());
		
		// 로그인 버튼 클릭
		element = driver.findElement(By.id("log.login"));
		element.click();
	}

}
