package com.pknuwws.wws.crawling;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;

public class CrawlingKakaoWebtoonInfos extends CrawlingWebtoonInfos {

	private String BASE_URL = "https://webtoon.kakao.com/original-webtoon?tab=";
	private String[] GENRES = new String[] {"로맨스", "액션", "판타지", "무협", "스포츠", "스릴러", "일상"};
	
	public CrawlingKakaoWebtoonInfos(WebtoonRepository webtoonRepository) {
		super(webtoonRepository);
	}

	@Override
	public void process() {
		super.process();
	}

	@Override
	protected List<String> crawlWebtoonUrlsOfDay(String day) {
		List<String> urls = new ArrayList<>();

		driver.get(BASE_URL + day);

		// 썸네일 뜰 때까지 기다림
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".overflow-hidden.rounded-8"))
				);

		WebElement container = driver.findElement(By.cssSelector(".flex.flex-col.gap-4"));

		// 요일 맨 위에 크게 있는 웹툰
		List<WebElement> elements = container.findElements(By.className("cursor-pointer"));

		// 그 아래 있는 작은 웹툰
		elements.addAll(container.findElements(By.cssSelector("a.w-full.h-full.relative.overflow-hidden.rounded-8")));
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
	 * 장르: ok
	 * 조회수: 네이버에서 좋아요 수로 대체했기 때문에 좋아요로 대체 ok
	 * 첫 화 날짜 ok
	 * 요일
	 */
	@Override
	protected Webtoon crawlWebtoons(String url) {
		// 대량의 요청 발생 시 서버에서 차단되는 것을 막기 위해 기다림
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		Webtoon webtoon = new Webtoon();
		driver.get(url);

		// 썸네일 뜰 때까지 기다림
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("character-container"))
				);

		// 제목
		String title = driver.findElement(By.cssSelector(".whitespace-pre-wrap.break-all.break-words.support-break-word.overflow-hidden.text-ellipsis.s22-semibold-white.text-center.leading-26")).getText();
//		webtoon.setTitle(title);
//		System.out.println("제목: " + title);

		// 링크
//		webtoon.setUrl(url);

		// 썸네일
		String thumbnail = driver.findElement(By.cssSelector(".flex.w-full.h-full")).findElement(By.tagName("img")).getAttribute("src");
//		webtoon.setThumbnailUrl(thumbnail);
//		System.out.println("썸네일: " + thumbnail);

		// 장르
		// GENRES에 지정된 키워드가 태그에 있으면 표시
		WebElement listAbout = driver.findElement(By.cssSelector(".flex.justify-center.items-start.h-14.mt-8.leading-14"));
		List<WebElement> items = listAbout.findElements(By.cssSelector(".whitespace-pre-wrap.break-all.break-words.support-break-word.s12-regular-white.ml-2.opacity-75"));
		String genresFromWeb = items.get(0).getText();
		String genres = "";
		for (String genre : GENRES) {
			if (genresFromWeb.contains(genre)) {
				if (!genres.contains(genre)) {
					genres += genre + ",";
				}
			}
		}
//		webtoon.setGenre(genres);

		// 웹툰 좋아요 가져오기
		String likeCount = items.get(2).getText();
		if (likeCount.contains(",")) {
			likeCount = likeCount.replaceAll(",", "");
		}
		if (likeCount.contains("만")) {
			likeCount = likeCount.replaceAll("[,만]", ""); // 맨 끝의 "만" 제거
			likeCount = Integer.toString((int) (Double.parseDouble(likeCount) * 10_000));
		}
//		webtoon.setLikeCount(Integer.parseInt(likeCount));
//		System.out.println("likeCount: " + likeCount);

		// 첫 화 날짜 가져오기
		List<WebElement> dates = driver.findElements(By.cssSelector(".whitespace-pre-wrap.break-all.break-words.support-break-word.overflow-hidden.text-ellipsis.leading-14.opacity-50.s11-regular-white"));
		String firstDate = "20" + dates.get(dates.size() - 1).getText();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//		webtoon.setFirstDate(LocalDate.parse(firstDate, dateTimeFormatter));
//		System.out.println(firstDate);

		// 요일
		WebElement infoItem = driver.findElement(By.cssSelector(".whitespace-pre-wrap.break-all.break-words.support-break-word.s14-bold-white.opacity-40"));
		infoItem.click();
//		System.out.print("연재 요일: ");
		String dayInfo = driver.findElement(By.cssSelector(".flex.flex-wrap.gap-4.mb-12")).getText();
		String days = "";
		for (String d : DAYS_KOREAN) {
			if (dayInfo.contains(d)) {
				days += d + ",";
//				System.out.print(d);
			}
		}
//		webtoon.setDayOfWeek(days);
//		System.out.println();

		// 플랫폼은 카카오
//		webtoon.setPlatform("Kakao");
		
		return Webtoon.builder()
				.title(title)
				.url(url)
				.thumbnailUrl(thumbnail)
				.genre(genres)
				.likeCount(Integer.parseInt(likeCount))
				.firstDate(LocalDate.parse(firstDate, dateTimeFormatter))
				.dayOfWeek(days)
				.platform("Kakao")
				.build();
		
//		return webtoon;
	}

	@Override
	protected void login() {
		driver.get("https://webtoon.kakao.com/more");

		WebElement element = driver.findElement(By.cssSelector(".flex.justify-center.items-start"));
		element.click();

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
