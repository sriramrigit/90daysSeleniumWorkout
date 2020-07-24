package com.servicenow.apple;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Servicenow {
	public static FirefoxDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://dev92834.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//switch to frame
		WebElement logFrame = driver.findElementById("gsft_main");
		driver.switchTo().frame(logFrame);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		//enter service catalog
		driver.findElement(By.id("filter")).sendKeys("Service Catalog",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title'])[4]")).click();
		Thread.sleep(2000);
		
		WebElement mobileFrame = driver.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(mobileFrame);
		driver.findElementByXPath("//h2[text()[normalize-space()='Mobiles']]").click();
		Thread.sleep(2000);
		//driver.switchTo().defaultContent();
		
		//choose iphone6s
		WebElement iphone6s = driver.findElementByXPath("//strong[text()='Apple iPhone 6s']");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);", iphone6s);
		iphone6s.click();
		Thread.sleep(2000);
		
		//chooose monthly allowance
		WebElement iOebedaadeeda = driver.findElementByCssSelector("table#item_table>tbody>tr>td>table:nth-of-type(2)>tbody>tr>td>table>tbody>tr:nth-of-type(4)>td>div>div>div:nth-of-type(2)>select"); 
		new Select(iOebedaadeeda).selectByValue("unlimited"); 
		
		//select color as rosegold
		WebElement color = driver.findElement(By.cssSelector("table#item_table>tbody>tr>td>table:nth-of-type(2)>tbody>tr>td>table>tbody>tr:nth-of-type(5)>td>div>div>div:nth-of-type(2)>select")); 
		new Select(color).selectByValue("rose"); 
		
		//select 128GB
		WebElement memory = driver.findElement(By.cssSelector("table#item_table>tbody>tr>td>table:nth-of-type(2)>tbody>tr>td>table>tbody>tr:nth-of-type(6)>td>div>div>div:nth-of-type(2)>select")); 
		new Select(memory).selectByValue("onetwentyeight"); 
		
		driver.findElement(By.id("oi_order_now_button")).click();
		Thread.sleep(2000);
		
		//capture response
		String thankYouYour = driver.findElement(By.cssSelector("div#sc_order_status_intro_text>div>span")).getText();
		if(thankYouYour.contains("Thank you, your request has been submitted"))
		{
			
			String rEQ = driver.findElement(By.cssSelector("a#requesturl>b")).getText();
			System.out.println("Expected message displayed :"+thankYouYour+"Order number :"+rEQ);
		}
		String request = driver.findElement(By.cssSelector("a#requesturl>b")).getText();
		
		driver.switchTo().defaultContent();
		
		
		//update service ticket
		//enter service catalog
		driver.findElement(By.xpath("//div[text()='Requests']")).click();
		Thread.sleep(2000);
		
		//search for request
		//switch to frame
		WebElement requestFrame = driver.findElementByXPath("//iframe[@id='gsft_main']");
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gsft_main"));
		driver.switchTo().frame(requestFrame);
		Thread.sleep(4000);
		
		WebElement searchRequest = driver.findElementByXPath("(//input[@placeholder='Search'])[1]");
		action=new Actions(driver);
		action.doubleClick(searchRequest).perform();
		//searchRequest.sendKeys(rEQ);
		searchRequest.sendKeys(request,Keys.ENTER);
		Thread.sleep(5000);
		
		driver.findElementByXPath("//a[@class='linked formlink']").click();
		Thread.sleep(5000);
		
		//Changing approval to rejected
		WebElement approval = driver.findElementById("sc_request.approval");
		Select approvalDropDown = new Select(approval);
		approvalDropDown.selectByValue("rejected");
		
		//Clicking update button
		driver.findElementById("sysverb_update").click();
		Thread.sleep(2000);
		
		//clicking the All in filter
		driver.findElementByXPath("//b[text()='All']").click();
		Thread.sleep(3000);
				
		//searching the request no
		driver.findElementByXPath("(//input[@placeholder='Search'])[1]").sendKeys(request, Keys.ENTER);
		Thread.sleep(2000);
		
		//getting the status of the request
		String reqStatus = driver.findElementByXPath("(//td[@class='vt'])[4]").getText();
		if(reqStatus.contentEquals("Closed Rejected"))
		{
				System.out.println("Request Status is verified");
				
		}

						
				
		
		
				
	}

}
