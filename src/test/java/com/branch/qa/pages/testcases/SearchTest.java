package com.branch.qa.pages.testcases;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
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

/**
 * This class will handle all the search and team page related operations.
 * @author Suneetha Tellamsetty
 *
 */
public class SearchTest extends TestBase {

	private SearchPage searchPage = null;
	private TeamPage teamPage = null;
	
	public SearchTest() {
		super();
	}

	@Test(enabled=false)
	public void validateImg() {
		String alt_title = searchPage.validateImg();
		Assert.assertEquals(alt_title, "Google");
	}

	@Test(enabled=false)
	public void validateTitle() {
		String pageTitle = searchPage.validateTitle();
		Assert.assertEquals(pageTitle, "Google");
	}

	
	@Test(enabled=false)
	public void validateLinkText() {
		String linkText = searchPage.validateLinkText();
		String linkTextCmp = "Branch - A mobile linking platform powering deep links and mobile ...";
		Assert.assertEquals(linkText, linkTextCmp);
	}
	
	@Test(priority=1)
	public void submitQuery() {
		searchPage.submitQuery(props.getProperty("query"), props.getProperty("branchioURL"));
		teamPage.getCategories();
		
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, enabled=false)
	public void getDataFromAllTabs() {
		teamPage.getAllTeam();
		teamPage.getDataTeam();
		teamPage.getEngineeringTeam();
		teamPage.getMarketingTeam();
		teamPage.getOperationsTeam();
		teamPage.getPartnerGrowthTeam();
		teamPage.getProductTeam();
		teamPage.getRecruitingTeam();
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromAllTab() {
		teamPage.getAllTeam();
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups = {"data"})
	public void getDataFromDataTab() {
		teamPage.getDataTeam();
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromEngineeringTab() {
		teamPage.getEngineeringTeam();
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromProductTab() {
		teamPage.getProductTeam();
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromRecruitingTab() {
		teamPage.getRecruitingTeam();
		
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromPartnerGrowthTab() {
		teamPage.getPartnerGrowthTeam();
	}
	
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromMarketingTab() {
		teamPage.getMarketingTeam();
		
	}
	
	@Test(dependsOnMethods= {"submitQuery"}, groups= {"data"})
	public void getDataFromOperationsTab() {
		teamPage.getOperationsTeam();
	}
	
	@Test(dependsOnMethods = { "submitQuery"} )
	public void validateTeamCount() {

		int totalTeamSize = 0;
		int individualTeamSize = 0;

		List<Map<String, List<String>>> allTeams = teamPage.getBranchTeamMap();
		if(allTeams != null) {
			for(Map<String, List<String>> teams : allTeams) {
				for (String key : teams.keySet()) {
					int size = teams.get(key).size();
					totalTeamSize += size;
				}
			}
		}
			

		// Get Totals of Individual teams.
		int markTeamSize = teamPage.getMarkTeamMap().size();
		int prodTeamSize = teamPage.getProdTeamMap().size();
		int engTeamSize = teamPage.getEngTeamMap().size();
		int recTeamSize = teamPage.getRecTeamMap().size();
		int opsTeamSize = teamPage.getOpsTeamMap().size();
		int pgTeamSize = teamPage.getPgTeamMap().size();
		int dataTeamSize = teamPage.getDataTeamMap().size();

		individualTeamSize =  markTeamSize + prodTeamSize + engTeamSize + recTeamSize + opsTeamSize + pgTeamSize + dataTeamSize;
		
		Assert.assertEquals(individualTeamSize, totalTeamSize, "Differences in Team Size between All Tab and Others");
	}
	
	
	
//	@Test(dependsOnMethods= {"submitQuery", "getDataFromAllTabs"})
	@Test(dependsOnMethods= {"submitQuery", "getDataFromDataTab"})
	public void validateDataTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
			
		
		List<String> teamList = teamPage.getDeptTeamMap("data");
		List<String> dataTeamMap = teamPage.getDataTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(dataTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
	}
	
	@Test(dependsOnMethods= {"getDataFromDataTab"})
	public void validateDataTeam() {

		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("data");
		List<String> dataTeamMap = teamPage.getDataTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(dataTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromProductTab"})
	public void validateProductTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("product");
		List<String> prodTeamMap = teamPage.getProdTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(prodTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
		
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromProductTab"})
	public void validateProductTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("product");
		List<String> prodTeamMap = teamPage.getProdTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(prodTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
		
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromEngineeringTab"})
	public void validateEngineeringTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("engineering");
		List<String> engTeamMap = teamPage.getEngTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(engTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
		
		
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromEngineeringTab"})
	public void validateEngineeringTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("engineering");
		List<String> engTeamMap = teamPage.getEngTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(engTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
		
	}
	
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromRecruitingTab"})
	public void validateRecruitingTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("recruiting");
		List<String> recTeamMap = teamPage.getRecTeamMap();
		
		//validating the map to teammap.
		teamList.removeAll(recTeamMap);
		if (teamList != null) {
			teamList.removeAll(recTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
	}
	
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromRecruitingTab"})
	public void validateRecruitingTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("recruiting");
		List<String> recTeamMap = teamPage.getRecTeamMap();
		
		//validating the map to teammap.
		teamList.removeAll(recTeamMap);
		if (teamList != null) {
			teamList.removeAll(recTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
	}
	
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromMarketingTab"})
	public void validateMarketingTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("marketing");
		List<String> markTeamMap = teamPage.getMarkTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(markTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
	}

	@Test(dependsOnMethods= {"submitQuery", "getDataFromMarketingTab"})
	public void validateMarketingTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("marketing");
		List<String> markTeamMap = teamPage.getMarkTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(markTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromPartnerGrowthTab"})
	public void validatePartnerGrowthTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("partner growth");
		List<String> pgTeamMap = teamPage.getPgTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(pgTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
		
	}
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromPartnerGrowthTab"})
	public void validatePartnerGrowthTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("partner growth");
		List<String> pgTeamMap = teamPage.getPgTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(pgTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
		
	}
	
	@Test(dependsOnMethods= {"submitQuery","getDataFromOperationsTab"})
	public void validateOperationsTeamSize() {
		
		int size = -1;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("operations");
		List<String> opsTeamMap = teamPage.getOpsTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(opsTeamMap);
			result = teamList.toString();
			size = teamList.size();
		}
		
		Assert.assertEquals(0, size, result);
		
	}
	
	
	@Test(dependsOnMethods= {"submitQuery", "getDataFromOperationsTab"})
	public void validateOperationsTeam() {
		
		boolean isEmpty = false;
		String result = "We found team list as Null";
		
		List<String> teamList = teamPage.getDeptTeamMap("operations");
		List<String> opsTeamMap = teamPage.getOpsTeamMap();
		
		//validating the map to teammap.
		if (teamList != null) {
			teamList.removeAll(opsTeamMap);
			result = teamList.toString();
			isEmpty = teamList.isEmpty();
		}
		
		Assert.assertTrue(isEmpty, result);
	}
	

	@BeforeMethod
	public void beforeMethod() {
		
	}

	@AfterMethod
	public void afterMethod() {
		searchPage = null;
		
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}
	
	@BeforeClass
	public void beforeClass() {
		initiate();
		searchPage = new SearchPage();
		teamPage = new TeamPage();

	}

	
	@AfterClass
	public void afterClass() {
		searchPage = null;
		teamPage = null;
		quitSession();
		
	}
}
