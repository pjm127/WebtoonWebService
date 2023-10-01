package com.pknuwws.wws;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

//@Component
public class CrawlingKakaoWebtoonUrls {
	
	private WebDriver driver;
	private static final String BASE_URL = "https://webtoon.kakao.com/original-webtoon?tab=";
	private static final String[] DAYS = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
	
	public void process() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KWC\\Desktop\\Misc\\webCrawlingRequirements\\msedgedriver.exe");
		
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless", // 브라우저 안 보이게 설정
				"--disable-infobars");
		driver = new EdgeDriver();
		
		try {
			for (String day : DAYS) {
				getWebtoonListOf(day);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.close();
		driver.quit();
	}
	
	private List<String> getWebtoonListOf(String day) throws InterruptedException {
		List<String> urls = new ArrayList<>();
		
		driver.get(BASE_URL + day);

		// 자바스크립트 다 불러올 때까지 5초 기다림
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		int webtoonCount = 0;
		
		List<WebElement> elements = driver.findElements(By.className("cursor-pointer"));
		for (WebElement element : elements) {
			String url = element.getDomProperty("href");
			if (url != null) {
				System.out.println("================");
				System.out.println(url);
				urls.add(url);
			}
		}
		webtoonCount += elements.size();
		
		elements = driver.findElements(By.cssSelector("a.w-full.h-full.relative.overflow-hidden.rounded-8"));
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

}
