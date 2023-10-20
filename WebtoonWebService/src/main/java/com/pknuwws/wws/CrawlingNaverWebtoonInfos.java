package com.pknuwws.wws;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlingNaverWebtoonInfos extends CrawlingWebtoonInfos {

	public CrawlingNaverWebtoonInfos(WebtoonRepository webtoonRepository) {
		super(webtoonRepository);
	}

	private String BASE_URL = "https://comic.naver.com/webtoon?tab=";
	private String[] GENRES = new String[] {"로맨스", "액션", "판타지", "사극", "무협", "스포츠", "스릴러", "일상"};

	@Override
	protected List<String> crawlWebtoonUrlsOfDay(String day) {
		List<String> urls = new ArrayList<>();

		driver.get(BASE_URL + day);

		// 자바스크립트 다 불러올 때까지 5초까지만 기다림
		// 요일마다 적용해야 된다.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// id로 ContentList 찾고 그 안의 웹툰 원소들 얻기 
		WebElement contentList = driver.findElement(By.className("ContentList__content_list--q5KXY"));
		List<WebElement> elements = contentList.findElements(By.className("Poster__link--sopnC"));
//		System.out.println("웹툰 개수: " + elements.size());

		for (WebElement element : elements) {
			String url = element.getAttribute("href");
//			System.out.println("================");
//			System.out.println(url);
			urls.add(url);
		}

		return urls;
	}

	/*
	 * 수집 항목들:
	 * 제목 ok
	 * 링크 ok
	 * 썸네일 ok
	 * 장르: 로맨스, 액션, 판타지, 시대극, 무협, 스포츠, 미스터리, 일상 ok
	 * 조회수 (찾기 어려움. 좋아요로 대체할 수 있는가?, 최신 화 기준) ok
	 * 첫 화 날짜 (신작 웹툰 판별 목적) ok
	 * 요일 ok
	 */
	@Override
	protected Webtoon crawlWebtoons(String url) {
		Webtoon webtoon = new Webtoon();
		driver.get(url);

		// 썸네일 뜰 때까지 기다림
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.className("Poster__image--d9XTI"))
				);

		// 제목
		String title = driver.findElement(By.className("EpisodeListInfo__title--mYLjC")).getText();
		webtoon.setTitle(title);
//		System.out.println("제목: " + title);

		// 링크
		webtoon.setUrl(url);

		// 썸네일
		String thumbnail = driver.findElement(By.className("Poster__image--d9XTI")).getAttribute("src");
		webtoon.setThumbnailUrl(thumbnail);
//		System.out.println("썸네일: " + thumbnail);

		// 요일
//		System.out.print("연재 요일: ");
		String dayInfo = driver.findElement(By.className("ContentMetaInfo__info_item--utGrf")).getText();
		String days = "";
		for (String d : DAYS_KOREAN) {
			if (dayInfo.contains(d)) {
//				System.out.print(d);
				days += d + ",";
			}
		}
		webtoon.setDay(days);
//		System.out.println();

		// 태그 (장르)
		// GENRES에 지정된 키워드가 태그에 있으면 표시
		List<WebElement> tags = driver.findElements(By.className("TagGroup__tag--xu0OH"));
		String genres = "";
		for (int i = 0; i < tags.size(); i++) {
			for (String genre : GENRES) {
				if (tags.get(i).getText().contains(genre)) {
					if (!genres.contains(genre)) {
						genres += genre + ",";
					}
				}
			}
		}
		webtoon.setGenre(genres);

		// 최신 화에서 좋아요 가져오기
		// 최신 화는 tab 새로 열기
		String latestUrl = driver.findElement(By.className("EpisodeListList__link--DdClU")).getAttribute("href");
//		System.out.println("최신 화 링크: " + latestUrl);
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchToTab(2);
		driver.get(latestUrl);
		WebDriverWait latestWebDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		latestWebDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".u_cnt._count"))
				);
		String likeCount = driver.findElement(By.cssSelector(".u_cnt._count")).getText();
		webtoon.setLikeCount(Integer.parseInt(likeCount.replaceAll(",", "")));
//		System.out.println("최신화 좋아요: " + likeCount);
		driver.close();
		switchToTab(1);

		// 첫 화부터 정렬해서 첫 화 날짜 가져오기
		WebElement ascendingButton = driver.findElements(
				By.className("EpisodeListView__button_tab--NUsn2")).get(1);
//		System.out.println(ascendingButton.getText());
		ascendingButton.click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String firstDate = "20" + driver.findElement(By.className("date")).getText();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		webtoon.setFirstDate(LocalDate.parse(firstDate, dateTimeFormatter));
//		System.out.println(firstDate);

		// 플랫폼은 네이버
		webtoon.setPlatform("Naver");

		return webtoon;
	}

	@Override
	protected void login() {
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/");
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
