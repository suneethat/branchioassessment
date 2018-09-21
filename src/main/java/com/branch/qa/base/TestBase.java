package com.branch.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.branch.qa.util.TestUtil;

/**
 * This class is the base class and handles initialization of the driver and reading configuration properties.
 * @author Suneetha Tellamsetty
 *
 */
public class TestBase {

	public static WebDriver driver;
	public Properties props = new Properties();
	
	public TestBase()
	{
		try {
			props.load(new FileInputStream(new File(TestUtil.PROPERTIESFILE)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Initiating the driver and setting the firefox driver path, timers for the implicit and page load timeouts.
	 */
	public void initiate() {
		
		String browserName = props.getProperty("browser");
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty(TestUtil.FIREFOXDRIVER, TestUtil.FIREFOXPATH);
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")){
			System.setProperty(TestUtil.CHROMEDRIVER, TestUtil.CHROMEPATH);
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOADTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOADTIMEOUT, TimeUnit.SECONDS);
		driver.get(props.getProperty("url"));
	}
	
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}
	
	/**
	 * For Closing the browser session after the operations.
	 */
	public void quitSession() {
		driver.close();
	}
	
	
	

}
