/**
 * 
 */
package com.branch.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.branch.qa.base.TestBase;

/**
 * This page will handle search, click and landing on to the branchio page.
 * @author Suneetha Tellamsetty
 *
 */
public class SearchPage extends TestBase {
	
	

	@FindBy(xpath="//input[@name='q']")
	WebElement query;
	
	@FindBy(xpath="//input[@type = \"submit\"]")
	WebElement submitBtn;
	
	@FindBy(linkText="Branch - A mobile linking platform powering deep links and mobile ...")
	WebElement branchLink;
	
	@FindBy(partialLinkText="href")
	WebElement branchHttpLink;
	
	@FindBy(tagName="a")
	List<WebElement> anchorTags;

	
	@FindBy(id="hplogo")
	WebElement googleLogo;
	
	@FindBy(id="logo")
	WebElement branchLogo;
	
	@FindBy(id="CybotCookiebotDialogBodyButtonAccept")
	WebElement cookieBannerElement;
	
	@FindBy(id="resultStats")
	WebElement resultStats;
	
	public SearchPage() {
		super();

		PageFactory.initElements(driver, this);
		

	}
	
	public String validateUrl() {
		return driver.getCurrentUrl();
	}
	
	public String validateTitle() {
		return driver.getTitle();
	}
	
	public String validateImg() {
		return googleLogo.getAttribute("alt");
	}
	
	public String validateLinkText() {
		return branchLink.getText();
	}
	
	
	
	/**
	 * This method will send required query to google page for search and then it will click on the branchio link
	 * @param keysToSend  Query string
	 * @param linkText
	 */
	public void submitQuery(String keysToSend, String linkText) {

		query.sendKeys(keysToSend);

		WebDriverWait timerWait = new WebDriverWait(driver, 10);
		
		timerWait.until(ExpectedConditions.elementToBeClickable(submitBtn));
		
		submitBtn.click();
		
		//checking the results stats on the google search results page
		timerWait.until(ExpectedConditions.visibilityOf(resultStats));
		
		//finding the branchio url to click.
		for (WebElement linkElement : anchorTags) {
			if (linkElement != null &&  linkElement.isDisplayed()) {
				String href = linkElement.getAttribute("href");
				if (href != null && href.equalsIgnoreCase(linkText)) {
					timerWait.until(ExpectedConditions.elementToBeClickable(linkElement));
					linkElement.click();
					break;
				}
			}
		}
		
		//waiting for the branchio company logo to show up
		timerWait.until(ExpectedConditions.visibilityOf(branchLogo));
		
		// waiting for the cookie element to be visible and once it is visible accept the cookies.
		timerWait.until(ExpectedConditions.visibilityOf(cookieBannerElement));
		
		if(cookieBannerElement.isDisplayed()) {
			cookieBannerElement.click();
		}
		
	}

}
