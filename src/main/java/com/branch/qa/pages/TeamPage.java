package com.branch.qa.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.branch.qa.base.TestBase;
import com.branch.qa.util.TestUtil;

/**
 * This Page Will handle all the operations in the Team page.
 * @author Suneetha Tellamsetty
 *
 */
public class TeamPage extends TestBase {

	
	/*  WebElements used */
	@FindBy(className = "info-block")
	List<WebElement> teamMembers;

	@FindBy(css = "ul.team-categories")
	WebElement teamCategories;

	@FindBy(css = "a[rel='all']")
	WebElement allTab;

	@FindBy(css = "a[rel='data']")
	WebElement dataTab;

	@FindBy(css = "a[rel='engineering']")
	WebElement engTab;

	@FindBy(css = "a[rel='marketing']")
	WebElement markTab;

	@FindBy(css = "a[rel='operations']")
	WebElement opsTab;

	@FindBy(css = "a[rel='partner-growth']")
	WebElement pgTab;

	@FindBy(css = "a[rel='product']")
	WebElement prodTab;

	@FindBy(css = "a[rel='recruiting']")
	WebElement recTab;

	WebDriverWait teamWait;

	@FindBy(linkText = "Team")
	WebElement teamLink;

	@FindBy(css = "a selected")
	WebElement currentCategory;

	@FindBy(className = "container-fluid teams-container")
	WebElement teamContainerLink;

	/* Structures for holding the data both individual and All teams */
	HashMap<String, List<Map<String, List<String>>>> branchTeam = new HashMap<>();
	HashMap<String, List<String>> dataTeam = new HashMap<>();
	HashMap<String, List<String>> engTeam = new HashMap<>();
	HashMap<String, List<String>> prodTeam = new HashMap<>();
	HashMap<String, List<String>> opsTeam = new HashMap<>();
	HashMap<String, List<String>> recTeam = new HashMap<>();
	HashMap<String, List<String>> pgTeam = new HashMap<>();
	HashMap<String, List<String>> markTeam = new HashMap<>();

	public List<String> getDataTeamMap() {
		return dataTeam.get("data");
	}

	public List<Map<String, List<String>>> getBranchTeamMap() {
		return branchTeam.get("all");
	}

	public List<String> getEngTeamMap() {
		return engTeam.get("engineering");
	}

	public List<String> getProdTeamMap() {
		return prodTeam.get("product");
	}

	public List<String> getOpsTeamMap() {
		return opsTeam.get("operations");
	}

	public List<String> getRecTeamMap() {
		return recTeam.get("recruiting");
	}

	public List<String> getPgTeamMap() {
		return pgTeam.get("partner growth");
	}

	public List<String> getMarkTeamMap() {
		return markTeam.get("marketing");
	}

	/**
	 * Getting individual team data from the All team data.
	 * @param deptKey  Department key to search in the list.
	 * @return
	 */
	public List<String> getDeptTeamMap(String deptKey) {

		for (Map<String, List<String>> deptList : getBranchTeamMap()) {
			return deptList.get(deptKey);
		}

		return null;
	}

	JavascriptExecutor js;

	public TeamPage() {
		super();
		PageFactory.initElements(driver, this);
//		js = ((JavascriptExecutor) driver);
	}

	/**
	 * Scroll to the bottom of the page.
	 */
	private WebElement getParentElement(WebElement childElement) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		WebElement parentElement = (WebElement) js.executeScript("return arguments[0].parentNode;", childElement);

		return parentElement;
	}

	static List<String> categoryList = new ArrayList<>();

	/**
	 * Getting details of the Partner Growth team
	 * 
	 * @return
	 */
	public void getPartnerGrowthTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the partner growth team tab.
		pgTab.click();

		this.getTeamMemberDetails(pgTab, pgTeam);
	}

	/**
	 * Getting details of the Data team
	 * 
	 * @return
	 */
	public void getDataTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the data team tab.
		dataTab.click();

		this.getTeamMemberDetails(dataTab, dataTeam);

	}

	/**
	 * Getting details of the Engineering team
	 * 
	 * @return
	 */
	public void getEngineeringTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the engineering team tab.
		engTab.click();

		this.getTeamMemberDetails(engTab, engTeam);

	}

	/**
	 * Getting details of the Marketing team
	 * 
	 * @return
	 */
	public void getMarketingTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the marketing team tab.
		markTab.click();

		this.getTeamMemberDetails(markTab, markTeam);
	}

	/**
	 * Getting details of the Operations team
	 * 
	 * @return
	 */
	public void getOperationsTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the operations team tab.
		opsTab.click();

		this.getTeamMemberDetails(opsTab, opsTeam);
	}

	/**
	 * Getting details of the Product team.
	 * 
	 * @return
	 */
	public void getProductTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the product team tab.
		prodTab.click();

		this.getTeamMemberDetails(prodTab, prodTeam);

	}

	/**
	 * Getting details of the Recruiting team.
	 * 
	 * @return
	 */
	public void getRecruitingTeam() {

		teamWait = new WebDriverWait(driver, 30);

		// selecting the rec team tab.
		recTab.click();

		this.getTeamMemberDetails(recTab, recTeam);
	}

	/**
	 * Getting details of the Branch/All team
	 * 
	 * @return
	 */
	public void getAllTeam() {
		teamWait = new WebDriverWait(driver, 30);

		// clicking on the all tab
		allTab.click();

		this.getTeamMemberDetails(allTab, null, branchTeam);
		
	}
	/**
	 * Getting categories for the branch.
	 */
	public void getCategories() {

		teamWait = new WebDriverWait(driver, 30);

		// scrolling to bottom of the page.
		TestUtil.scrollToElement(teamLink, false, driver);
		teamWait.until(ExpectedConditions.visibilityOf(teamCategories));

		TestUtil.scrollByPoint(null, driver);

		List<WebElement> categories = teamCategories.findElements(By.cssSelector("a")); //

		for (WebElement category : categories) {
			categoryList.add(category.getText());
		}

	}

	/**
	 * Extracting the Member info and their related deprartment information.
	 * 
	 * @param teamElement
	 * @param teamMap
	 */
	private void getTeamMemberDetails(WebElement teamElement, HashMap<String, List<String>> deptMap) {

		getTeamMemberDetails(teamElement, deptMap, null);

	}

	/**
	 * Extracting the Member info and their related deprartment information.
	 * 
	 * @param teamElement
	 * @param teamMap
	 */
	private void getTeamMemberDetails(WebElement teamElement, HashMap<String, List<String>> deptMap,
			HashMap<String, List<Map<String, List<String>>>> teamMap) {

		for (WebElement memberElement : teamMembers) {

			String memberName = memberElement.findElement(By.tagName("h2")).getText().toLowerCase();
			String memberDept = memberElement.findElement(By.tagName("h4")).getText().toLowerCase();

			if (!memberName.isEmpty()) {

				if (!categoryList.contains(memberDept.toUpperCase())) {
					WebElement grandParent = memberElement
							.findElement(By.xpath("./ancestor::div[contains(@class, 'category-all')]"));
					String className = grandParent.getAttribute("class");
					if (className.contains("category")) {

						String classElements = className.replaceAll("\\s+", " ");
						Pattern pattern = Pattern.compile("all category-(.*?) col");
						Matcher matcher = pattern.matcher(classElements);
						if (matcher.find()) {
							memberDept = matcher.group(1).replaceAll("\\W", " ").toLowerCase();
						}

					}
				}

				// handling all teams
				if (teamMap != null) {

					if (teamMap.isEmpty()) {
						// create value list for holding all depts along with their team members

						List<Map<String, List<String>>> listOfTeams = new ArrayList<>();

						// create map for individual dept with team members list
						Map<String, List<String>> indivTeam = new HashMap<>();

						// create individual team member list
						List<String> teamList = new ArrayList<>();

						// adding team member to the team list
						teamList.add(memberName);

						// mapping team dept with team member
						indivTeam.put(memberDept, teamList);

						// adding team map to overall list
						listOfTeams.add(indivTeam);

						// mapping all to overall list
						teamMap.put("all", listOfTeams);

					} else {

						// first get the all map list
						List<Map<String, List<String>>> listOfTeams = teamMap.get("all");

						// work on individual category dept list
						for (Map<String, List<String>> indvTeam : listOfTeams) {

							// now go through each individual dept map
							if (indvTeam.containsKey(memberDept)) {
								List<String> teamList = indvTeam.get(memberDept);
								teamList.add(memberName);
								indvTeam.put(memberDept, teamList);

							} else {
								List<String> teamList = new ArrayList<>();
								teamList.add(memberName);
								indvTeam.put(memberDept, teamList);
							}

						}
					}
				} else {

					if (deptMap != null && !deptMap.isEmpty() && deptMap.containsKey(memberDept)) {
						List<String> currList = deptMap.get(memberDept);
						if (currList == null) {
							currList = new ArrayList<String>();
						}
						currList.add(memberName);
						deptMap.put(memberDept, currList);
					} else {
						List<String> teamMembers = new ArrayList<String>();
						teamMembers.add(memberName);
						deptMap.put(memberDept, teamMembers);

					}

				}

			}

		}
	}

	// Reference work

//	private int totalDataTeam;
//	private int totalOpsTeam;
//	private int totalprodTeam;
//	private int totalengTeam;
//	private int totalDataTeam;
//	private int totalDataTeam;
//	private int totalDataTeam;
//	private int totalDataTeam;
//	private int totalDataTeam;

	/**
	 * WE are getting the total count of the employees in the All tab.
	 * 
	 * @deprecated
	 * @return
	 */
	public int getTotalTeamCount() {
		teamWait = new WebDriverWait(driver, 10);

		// scrolling to bottom of the page.
		TestUtil.scrollToElement(teamLink, false, driver);
//		teamWait.until(ExpectedConditions.visibilityOf(teamContainerLink));

//		scrollToElement(allTab);
//		// This is just to make sure we are landing on the All tab.
//		if(!allTab.isSelected()) {
//			allTab.click();
//			teamWait.until(ExpectedConditions.elementToBeSelected(allTab));
//		}
		TestUtil.scrollByPoint(null, driver);
		teamWait.until(ExpectedConditions.visibilityOf(teamCategories));
		if (teamMembers != null && teamMembers.size() > 1) {
			for (WebElement memberElement : teamMembers) {
//			System.out.println(memberElement.toString());

//			System.out.println("we got the member elements as : "+ memberElement.getAttribute("css"));
//				String[] teamInfo = this.getTeamMemberDetails(memberElement);
//			
//				System.out.println("memberelement is : " + Arrays.toString(teamInfo));

				System.out.println("getting the parent of the memberelement  : " + getParentElement(memberElement));
//			
//			branchTeam.put(teamInfo[1], teamInfo[0]);
			}
		} else {
			System.out.println("Sorry buddy we got nothing to display");
		}

		return 0;
	}

	/**
	 * WE are getting the total count of the employees in the All tab. This is a
	 * working method but we need to clean it up after we complete the assignment.
	 * 
	 * @return
	 */
	/*
	 * public int getAllTeam() {
	 * 
	 * teamWait = new WebDriverWait(driver, 30);
	 * 
	 * // scrolling to bottom of the page. scrollToElement(teamLink, false);
	 * teamWait.until(ExpectedConditions.visibilityOf(teamCategories));
	 * 
	 * // This is for getting the list of catagories except the "All".
	 * List<WebElement> categories =
	 * teamCategories.findElements(By.cssSelector("li:not(:first-child) a")); //
	 * List<String> categoryList = new ArrayList<>(); for (WebElement category :
	 * categories) { categoryList.add(category.getText()); }
	 * 
	 * for (WebElement memberElement : teamMembers) {
	 * 
	 * String[] teamInfo = this.getTeamMemberDetails(memberElement);
	 * 
	 * if (!categoryList.contains(teamInfo[1].toUpperCase())) { WebElement
	 * grandParent = memberElement
	 * .findElement(By.xpath("./ancestor::div[contains(@class, 'category-all')]"));
	 * String className = grandParent.getAttribute("class"); if
	 * (className.contains("category")) {
	 * 
	 * String classElements = className.replaceAll("\\s+", " "); Pattern pattern =
	 * Pattern.compile("all category-(.*?) col"); Matcher matcher =
	 * pattern.matcher(classElements); if (matcher.find()) { teamInfo[1] =
	 * matcher.group(1).replaceAll("\\W", " ").toUpperCase(); }
	 * 
	 * } else {
	 * System.out.println("Sorry this category doesn't contain in the lsit ::: " +
	 * teamInfo[1] + Arrays.toString(categoryList.toArray())); } }
	 * 
	 * branchTeam.put(teamInfo[0], teamInfo[1]);
	 * 
	 * } System.out.println("we got the size of the team as : " +
	 * branchTeam.size()); return 0; }
	 */

}
