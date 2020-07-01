package com.azure.container;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Azure {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	public void verifyDownloadedFileName(String fname) {
		String downloadPath="C:\\Users\\home\\Downloads";
	    Assert.assertTrue(isFileDownloaded(downloadPath, fname), "Failed to download Expected document");
	    System.out.println(fname+": exists in downloads folder");
	}
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}

	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//Click Pricing
		driver.findElement(By.id("navigation-pricing")).click();
		Thread.sleep(2000);
		
		//click Pricing calculator
		driver.findElement(By.linkText("Pricing calculator")).click();
		Thread.sleep(2000);
		
		//click containers
		WebElement cotainer = driver.findElement(By.xpath("//button[@value='containers']"));
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",cotainer);
		cotainer.click();
		Thread.sleep(2000);
		
		//click container instances
		driver.findElement(By.xpath("(//span[text()='Container Instances'])[3]")).click();
		Thread.sleep(1000);
		
		WebElement view = driver.findElementById("new-module-loc");
		wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(view)).click();
		Thread.sleep(2000);
		
		//select region, ram, duration
		WebElement region = driver.findElement(By.xpath("(//select[@name='region'])[1]")); 
		js.executeScript("arguments[0].scrollIntoView(false);",region);
		new Select(region).selectByVisibleText("South India"); 
		Thread.sleep(2000);
		WebElement duration = driver.findElementByXPath("(//input[@name='seconds'])[1]");
		duration.clear();
		duration.sendKeys(Keys.BACK_SPACE);
		duration.sendKeys("180000");
		Thread.sleep(1000);
		WebElement memory = driver.findElement(By.xpath("(//select[@name='memory'])[1]")); 
		new Select(memory).selectByValue("4"); 
		Thread.sleep(2000);
		
		//dev test toggler
		WebElement toggler = driver.findElement(By.id("devtest-toggler"));
		js.executeScript("arguments[0].scrollIntoView(false);",toggler);
		toggler.click();
		Thread.sleep(1000);
		WebElement devtestmsg = driver.findElementByXPath("//span[text()='Dev/Test pricing has been applied']");
		js.executeScript("arguments[0].scrollIntoView(false);",devtestmsg);
		String devtstmsg = wait.until(ExpectedConditions.visibilityOf(devtestmsg)).getText();
		System.out.println(devtstmsg);
		
		//select INR
		WebElement uSDollarEuro = driver.findElement(By.xpath("//select[@class='select currency-dropdown']")); 
		new Select(uSDollarEuro).selectByValue("INR");
		
		//estimated price
		String estimatedPrice = driver.findElement(By.cssSelector("section#azure-calculator>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>section>div:nth-of-type(6)>div>div:nth-of-type(2)>div:nth-of-type(2)>span>span")).getText();
		System.out.println("estimated price :"+estimatedPrice);
		
		//click export
		driver.findElement(By.xpath("(//button[contains(@class,'calculator-button button-transparent')])[4]")).click();
		Thread.sleep(2000);
		
		//verify download file
		new Azure().verifyDownloadedFileName("ExportedEstimate.xlsx");
		
		//navigate to example scenarios
		WebElement exScenarios = driver.findElementByLinkText("Example Scenarios");
		js.executeScript("arguments[0].scrollIntoView(false);",exScenarios);
		exScenarios.click();
		Thread.sleep(2000);
		
		//navigate to ci/cd container
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0, 400)");
		
		driver.findElementByXPath("(//button[@data-event-property='cicd-for-containers'])[2]").click();
		WebElement alert = driver.findElementByXPath("//span[text()='Estimate added!']");
		String alerttext = wait.until(ExpectedConditions.visibilityOf(alert)).getText();
		System.out.println(alerttext);
		
		//change INR
		WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		js.executeScript("arguments[0].scrollIntoView(false);",currency);
		new Select(currency).selectByValue("INR");
		
		//dev test toggler
		WebElement toggler2 = driver.findElement(By.id("devtest-toggler"));
		js.executeScript("arguments[0].scrollIntoView(false);",toggler2);
		toggler2.click();
		Thread.sleep(1000);
		WebElement devtestmsg2 = driver.findElementByXPath("//span[text()='Dev/Test pricing has been applied']");
		js.executeScript("arguments[0].scrollIntoView(false);",devtestmsg2);
		String devtstmsg2 = wait.until(ExpectedConditions.visibilityOf(devtestmsg2)).getText();
		System.out.println(devtstmsg2);
		
		//click export
		driver.findElement(By.xpath("(//button[contains(@class,'calculator-button button-transparent')])[4]")).click();
		Thread.sleep(2000);
		
		//verify download file
		new Azure().verifyDownloadedFileName("ExportedEstimate (1).xlsx");
				
				
	}

}
