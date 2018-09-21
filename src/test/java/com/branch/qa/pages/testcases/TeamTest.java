/**
 * 
 */
package com.branch.qa.pages.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.branch.qa.pages.TeamPage;

/**
 * @author BhuvanaSriKrishna
 *
 */
public class TeamTest extends TeamPage {
	
	private TeamPage teamPage;
	
	public TeamTest() {
		super();
	}
	
	
	@BeforeMethod
	public void beforeMethod() {
		initiate();
		teamPage = new TeamPage();
	}
	
	@Test
	public void validateAllCount() {
		
		int count = teamPage.getTotalTeamCount();
		
//		Assert.failSame(message);
		
	}
	

}
