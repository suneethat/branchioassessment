package com.branch.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Util class for maintaining all constants.
 * @author Suneetha Tellamsetty
 *
 */
public class TestUtil {
	
	public static final String PROPERTIESFILE = "C:\\Users\\BhuvanaSriKrishna\\eclipse-workspace\\BranchTest\\src\\main\\java\\com\\branch\\qa\\config\\config.properties";
	public static final String FIREFOXDRIVER = "webdriver.gecko.driver";
	public static final String CHROMEDRIVER = "webdriver.chrome.driver";
	public static final String FIREFOXPATH = "C:\\Softwares_downloaded\\geckodriver\\geckodriver.exe";
	public static final String CHROMEPATH = "/path/to/chromedriver";
	
	
	// Implicit and Explicit Timers
	public static final long IMPLICITWAIT = 10;
	public static final long PAGELOADTIMEOUT = 20;
	
	
	/**
	 * Scroll to the bottom of the page.
	 */
	public static void scrollToElement(WebElement element, boolean scroll_by_point, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (!scroll_by_point) {
			if (element != null) {
				js.executeScript("arguments[0].scrollIntoView();", element);

				element.click();
			}
		} else {
			scrollByPoint(js, driver);
		}

	}

	/**
	 * Scroll to the bottom of the page.
	 */
	public static void scrollByPoint(JavascriptExecutor js, WebDriver driver) {
		if (js == null)
			js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(100, 500);");

	}

	private static WebDriverWait wait;
	
	/**
	 * 
	 */
	public static WebDriverWait getWaitInstance(WebDriver driver) {
		if (wait == null) {
			wait = new WebDriverWait(driver, 30);
		}
		return wait;
	}

}
