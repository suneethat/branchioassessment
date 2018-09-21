package com.branch.qa.pages.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.branch.qa.base.TestBase;
import com.branch.qa.pages.SearchPage;
import com.branch.qa.pages.TeamPage;

public class MobileGrowthTest extends TestBase{

	private TeamPage teamPage = null;
	private SearchPage searchPage = null;
	private com.branch.qa.pages.MobileGrowth mobileGrowth = null;

	public MobileGrowthTest() {
		super();
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		initiate();
		searchPage = new SearchPage();
		teamPage = new TeamPage();
		mobileGrowth = new com.branch.qa.pages.MobileGrowth();

	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}


	@Test(groups="goto")
	
	public void goToMobileGrowth() {
		searchPage.submitQuery(props.getProperty("query"), props.getProperty("branchioURL"));
		mobileGrowth.goToMobileGrowth();
	}

	@Test(dependsOnMethods= {"goToMobileGrowth"})
	public void joinUs() {
		
		mobileGrowth.joinUs(props.getProperty("firstName"), props.getProperty("lastName"), props.getProperty("email"));
	}
}
