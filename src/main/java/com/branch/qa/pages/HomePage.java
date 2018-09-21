package com.branch.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.branch.qa.base.TestBase;

public class HomePage extends TestBase {
	
	
	JavascriptExecutor js;
	
	@FindBy(linkText="team")
	WebElement teamElement;
	
	
	
	public HomePage() {
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	public void validateTeamLink() {
		
	}
	
	public void clickTeamLink() {
		
	}
	
	
	
	

}
