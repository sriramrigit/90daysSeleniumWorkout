package com.jira.story;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Jira_newFeature {
	public static FirefoxDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		String storyName="Created by Sriram";
		
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://id.atlassian.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//enter user name and password
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@testleaf.com");
		driver.findElementByXPath("//span[text()='Continue']").click();
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("India@123");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		Thread.sleep(2000);
		
		//click rest-api
		driver.findElementByXPath("//span[@class='sc-iRbamj GLwkR']/h5[1]").click();
		Thread.sleep(2000);
		
		//get issue count
		String issues = driver.findElement(By.className("ghx-issue-count")).getText();
		
		System.out.println("Total no of issues: "+issues.substring(5, 8));
		
		//click create story
		driver.findElementByXPath("//span[text()='Create']").click();
		Thread.sleep(2000);
		
		//select issue type as story and crete new
		WebElement create = driver.findElement(By.id("issuetype-field"));
		create.clear();
		create.sendKeys("Story",Keys.TAB);
		Thread.sleep(3000);
		driver.findElement(By.id("summary")).sendKeys(storyName);
		driver.findElement(By.className("issue-drop-zone__button")).click();
		Thread.sleep(5000);
		//WebElement sample = driver.findElementByXPath("//div[contains(@class,'overlay image selectable')][1]");
		//if(!sample.isDisplayed())
			driver.findElementByTagName("//span[text()='Upload a file']").click();
			StringSelection ss = new StringSelection("D:\\photos\\Shared pics\\DSC00618.JPG");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		    //imitate mouse events like ENTER, CTRL+C, CTRL+V
		    Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    driver.findElement(By.xpath("//span[text()='Insert  1 file']")).click();

		
	/*
	 * else { sample.click();
	 * driver.findElement(By.xpath("//span[text()='Insert  1 file']")).click(); }
	 */	
		driver.findElement(By.id("create-issue-submit")).click();
		Thread.sleep(2000);
		
		//verify the newly created story by searching it
		WebElement searchStory = driver.findElementByXPath("//input[@data-test-id='search-dialog-input']");
		wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(searchStory)).click();
		searchStory.sendKeys(storyName);
		
		driver.findElementByCssSelector("div#helpPanelContainer>div>div>div>header>div>div>div>div>div>div>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>div:nth-of-type(2)>a>div:nth-of-type(2)>div>span:nth-of-type(2)").click();
		
		WebElement storyCreated = driver.findElementByXPath("//h1[@data-test-id='issue.views.issue-base.foundation.summary.heading']");
		if(storyName.equalsIgnoreCase(storyCreated.getText()))
		{
			System.out.println("Story name matched with created :"+storyName);
			
		}
		WebElement assingee = driver.findElementByXPath("//div[text()='Unassigned']");
		assingee.click();
		assingee.clear();
		assingee.sendKeys("Vivek",Keys.DOWN,Keys.ENTER);
		
		//change priority
		WebElement priority = driver.findElementByCssSelector("div#helpPanelContainer>div>div>div:nth-of-type(3)>div>div>div>div>div>div:nth-of-type(3)>div>div>div>div>div:nth-of-type(2)>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>div>div>div>div>spandiv#helpPanelContainer>div>div>div:nth-of-type(3)>div>div>div>div>div>div:nth-of-type(3)>div>div>div>div>div:nth-of-type(2)>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>div>div>div>div>span");
		priority.click();
		driver.findElement(By.xpath("//span[text()='Low']")).click();
		
		//navigate to backlog
		driver.findElementByXPath("//div[text()='Backlog']").click();
		driver.findElement(By.xpath("//span[text()='Recently Updated']")).click();
		Thread.sleep(2000);
		
		//validate the assignee
		WebElement assingeeText = driver.findElementByCssSelector("div.js-issue:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1) > img:nth-child(1)");
		String assigneeName = assingeeText.getAttribute("data-tooltip");
		if(assigneeName.contains("Vivek"))
		{
			System.out.println("Assignee name matched");
		}
		WebElement priorityImage = driver.findElementByCssSelector("html.mozilla body#jira.aui-layout.aui-theme-default.adg3.ghx-agile.bento-enabled.ghx-ondemand.ghx-scroll-columns.ghx-sprint-support.ghx-days-in-column-disabled.ghx-mode-planning.ghx-plan-band-2.js-focus-visible div#page div#page-body div#content.z-index-content div#gh.ghx-gh.ghx-no-touch.aui-page-panel div#ghx-content-main.ghx-content-main div#ghx-rabid div#ghx-plan div#ghx-plan-group.ghx-plan-group div#ghx-backlog-column.ghx-backlog-column div#ghx-backlog.ghx-backlog div#ghx-content-group.ui-sortable div.ghx-backlog-group div.ghx-backlog-container.ghx-open.ghx-everything-else.ui-droppable div.js-issue-list.ghx-issues.ghx-has-issues div.js-issue.ghx-backlog-card.ghx-newcard.js-sortable.js-parent-drag.ghx-issue-compact.ghx-type-10001.ghx-no-card-color.ghx-selected.ghx-selected-primary div.ghx-issue-content div.ghx-row.ghx-end.ghx-items-container span.ghx-end.ghx-items-container span.ghx-priority img");
		String priorityName = priorityImage.getAttribute("alt");
		if(priorityName.contains("Low"))
		{
			System.out.println("Priority also matched");
		}
		
		WebElement issueDes = driver.findElementByCssSelector("html.mozilla body#jira.aui-layout.aui-theme-default.adg3.ghx-agile.bento-enabled.ghx-ondemand.ghx-scroll-columns.ghx-sprint-support.ghx-days-in-column-disabled.ghx-mode-planning.ghx-plan-band-2.js-focus-visible div#page div#page-body div#content.z-index-content div#gh.ghx-gh.ghx-no-touch.aui-page-panel div#ghx-content-main.ghx-content-main div#ghx-rabid div#ghx-plan div#ghx-plan-group.ghx-plan-group div#ghx-backlog-column.ghx-backlog-column div#ghx-backlog.ghx-backlog div#ghx-content-group.ui-sortable div.ghx-backlog-group div.ghx-backlog-container.ghx-open.ghx-everything-else.ui-droppable div.js-issue-list.ghx-issues.ghx-has-issues div.js-issue.ghx-backlog-card.ghx-newcard.js-sortable.js-parent-drag.ghx-issue-compact.ghx-type-10001.ghx-no-card-color.ghx-selected.ghx-selected-primary div.ghx-issue-content div.ghx-row.ghx-plan-main-fields div.ghx-summary span.ghx-inner");
		issueDes.click();
		
		//delete story
		driver.findElementByCssSelector("div#jira-issue-header>div>div>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>button>span>span>span").click();
		
		WebElement delete = driver.findElementByXPath("//span[text()='Delete']");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);", delete);
		delete.click();
		Thread.sleep(3000);
		
		 driver.findElementByXPath("//span[text()='Delete']").click();
		 
	}

}
