package com.pknuwws.wws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;

public abstract class CrawlingWebtoonInfos {
	
	protected WebDriver driver;
	protected EdgeOptions options;
	protected List<String> tabs;
	protected String BASE_URL;
//	protected static final String[] DAYS = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
	protected static final String[] DAYS = {"mon"};
	protected static final String[] DAYS_KOREAN = {"월", "화", "수", "목", "금", "토", "일"};
	protected String[] GENRES;
	
	public abstract void process();
	
	protected abstract List<String> getWebtoonListOfDay(String day);
	
	protected abstract Map<String, String> getWebtoonInfos(String url);
	
	protected abstract void login();
	
	protected void switchToTab(int tabIndex) {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		if (tabIndex < 0) {
			tabIndex += tabs.size();
		}
		driver.switchTo().window(tabs.get(tabIndex));
	}
	
}
