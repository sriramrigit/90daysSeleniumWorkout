package com.makemytrip;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMytrip {
		public static ChromeDriver driver;
		public static WebDriverWait wait;
		public static JavascriptExecutor js;
		public static Actions action;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//String userAgent = "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0";
		//options.addArguments(String.format("user-agent=%s", userAgent));
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.get("https://www.makemytrip.com/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementByXPath("//li[@data-cy='account']").click();
		//click hotels
		js=(JavascriptExecutor)driver;
		WebElement hotel = driver.findElementByXPath("(//span[contains(@class,'chNavIcon appendBottom2')])[2]");
		js.executeScript("arguments[0].click();", hotel);
		Thread.sleep(2000);
		//choose goa,india
		WebElement place = driver.findElementById("city");
		place.sendKeys("Goa",Keys.DOWN,Keys.ENTER);
		Thread.sleep(2000);
		
		//check in as july 15
		driver.findElementById("checkin").click();	
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		Thread.sleep(1000);
		//checkout by july 19
		driver.findElementByXPath("(//div[text()='19'])[2]").click();
		Thread.sleep(2000);
		
		//click guest
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		
		//child age 12
		WebElement cHILDAGE = driver.findElement(By.tagName("select")); 
		new Select(cHILDAGE).selectByVisibleText("12"); 
		
		//click apply
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();
		Thread.sleep(2000);
		
		//click search
		driver.findElementById("hsw_search_button").click();
		Thread.sleep(2000);
		
		
		//driver.findElementByClassName("bodyFixed overlayWholeBlack").click();
		//search by location Baga
		
		wait=new WebDriverWait(driver,10);
		WebElement locality = driver.findElementByXPath("//input[@placeholder='Enter location or property name']");
		action=new Actions(driver);
		action.doubleClick(locality).perform();
		wait.until(ExpectedConditions.visibilityOf(locality)).click();
		locality.sendKeys("Baga",Keys.DOWN);
		Thread.sleep(2000);
		
		//filter 3& above
		WebElement filter = driver.findElementByXPath("//label[text()='3 & above (Good)']");
		js.executeScript("arguments[0].scrollIntoView(false);",filter);
		filter.click();
		Thread.sleep(2000);
		
		//sort by low to high
		driver.findElementByCssSelector("div#hlistpg_sortby_option>span").click();
		driver.findElementByXPath("//li[text()='Price - Low to High']").click();
		Thread.sleep(2000);
		
		//click 1st result
		driver.findElementByXPath("(//p[@itemprop='name']//span)[1]").click();
		
		//switch to next window
		 Set<String> windowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList<String>(windowHandles);
			driver.switchTo().window(list.get(1));
			driver.manage().deleteAllCookies();
			String currentUrl = driver.getCurrentUrl();
			driver.get(currentUrl);
			Thread.sleep(2000);
			try {
			driver.findElement(By.id("closePanel")).click();
			}
			catch(Exception e)
			{
				System.out.println("no such panel exists");
			}
		//print hotel name
			String hotelname = driver.findElementByXPath("(//ul[@id='detpg_bread_crumbs']//li)[3]").getText();
			System.out.println("Hotel name is : "+hotelname);
		//view combo
			try
			{
				driver.findElementById("detpg_multi_view_combo").click();
				driver.findElementById("detpg_book_combo_btn").click();
			}
			catch(Exception e)
			{
				System.out.println("no view combo option present");
			}
			
			Thread.sleep(2000);
		//Book combo
			driver.findElementById("detpg_headerright_book_now").click();
			
			Thread.sleep(3000);
			try
			{
				driver.findElement(By.className("close")).click();
			}
			catch(Exception e)
			{
				System.out.println("no such element exists");
			}
			WebElement payable = driver.findElementById("revpg_total_payable_amt");
			js.executeScript("arguments[0].scrollIntoView(false);",payable);
			System.out.println("total amount payable :"+payable.getText());
			
			driver.quit();
	}

}
