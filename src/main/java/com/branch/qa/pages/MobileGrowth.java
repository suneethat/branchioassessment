/**
 * 
 */
package com.branch.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.branch.qa.base.TestBase;
import com.branch.qa.util.TestUtil;

/**
 * @author BhuvanaSriKrishna
 *
 */
public class MobileGrowth extends TestBase {
	
	@FindBy(css="a[data-element-tag='mobile-growth']")
	WebElement mobileGrowthLink;

	@FindBy(css="a[href*='events']")
	WebElement upcomingEvents;
	
	@FindBy(name="first_name")
	WebElement firstNameElement;
	
	@FindBy(name="last_name")
	WebElement lastNameElement;
	
	@FindBy(name="email")
	WebElement emailElement;
	
	@FindBy(css="button[type='submit']")
	WebElement submitBtn;
	
	@FindBy(css="h2[class='chapters-title']")
	WebElement chaptersTitle;
	
	
	public MobileGrowth() {
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void goToMobileGrowth() {
		TestUtil.scrollToElement(mobileGrowthLink, false, driver);
		TestUtil.getWaitInstance(driver).until(ExpectedConditions.visibilityOf(upcomingEvents));

		TestUtil.scrollByPoint(null, driver);
	}
	
	public void joinUs(String firstName, String lastName, String email) {
		
		firstNameElement.sendKeys(firstName);
		
		lastNameElement.sendKeys(lastName);
		
		emailElement.sendKeys(email);
		
		submitBtn.click();
		
		TestUtil.getWaitInstance(driver).until(ExpectedConditions.visibilityOf(chaptersTitle));
		
	}
	
	
	
	

}
