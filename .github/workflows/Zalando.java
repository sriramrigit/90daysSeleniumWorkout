package com.zalando.uk;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.get("https://www.zalando.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		//get alert text and close
		String alert =driver.switchTo().alert().getText(); 
		System.out.println(alert);
		
		//click zalando.uk
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//a[@href='https://www.zalando.co.uk/']")).click();
		Thread.sleep(2000);
		try
		{
			driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
		}
		catch(Exception e)
		{
			System.out.println("No such preference found");
		}
		try
		{
			driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
		}
		catch(Exception e)
		{
			System.out.println("No such preference found");
		}
		
		//Mouse over women and click coats
		WebElement women = driver.findElementByXPath("(//a[@href='/women-home/']//span)[1]");
		   wait=new WebDriverWait(driver,20);
		   wait.until(ExpectedConditions.visibilityOf(women)).click();
		   Thread.sleep(2000);
		   WebElement clothing = driver.findElementByXPath("//span[text()='Clothing']");
		   action=new Actions(driver);
		   action.moveToElement(clothing).perform();
		   driver.findElementByXPath("//a[@href='/womens-clothing-coats/']//span[1]").click();
		   Thread.sleep(2000);
		  
		//click material and select cotton 100%
		   js=(JavascriptExecutor)driver;
		   js.executeScript("window.scrollBy(0, 250)");
		   Thread.sleep(2000);
		   WebElement material = driver.findElementByXPath("//span[text()='Material']");
		   js.executeScript("arguments[0].click()", material);
		   driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		   driver.findElementByXPath("//button[text()='Save']").click();
		   Thread.sleep(4000);
		   
		 //select length
		   WebElement length = driver.findElementByXPath("//span[text()='Length']");
		   js.executeScript("arguments[0].scrollIntoView(false);", length); 
		   action.moveToElement(length).click().perform();
		  // length.click();
		   Thread.sleep(2000);
		   WebElement size = driver.findElementByXPath("//span[text()='thigh-length']");
		   js.executeScript("arguments[0].click()", size);
		   driver.findElementByXPath("//button[text()='Save']").click();
		   Thread.sleep(3000);
		   
		 //click vermoda
		   WebElement vermoda = driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']");
		   js.executeScript("arguments[0].scrollIntoView(false);", vermoda);
		   vermoda.click();
		   Thread.sleep(3000);
		
		 //select size as M
		   driver.findElementByXPath("//span[text()='Choose your size']").click();
		   Thread.sleep(1000);
		   driver.findElementByXPath("//span[text()='Manufacturer sizes']").click();
		   driver.findElementByXPath("(//li[@role='option']//div)[1]").click();
		   Thread.sleep(2000);
		   
		 //click add to bag
		   WebElement freeDeliver = driver.findElementByXPath("(//span[text()='Free'])[1]");
		   if(freeDeliver.getText().contains("Free"))
		   {
			   driver.findElementByXPath("//span[text()='Add to bag']").click();
			   Thread.sleep(5000);
		   }
		   
		  //click go to bag
		   WebElement gotobag = driver.findElementByXPath("//a[contains(@class,'z-1-button z-coast-base-primary-accessible')]");
		   wait.until(ExpectedConditions.visibilityOf(gotobag)).click();
		   Thread.sleep(2000);
		   
		  //get estimated delivery date
		   WebElement estimatedDelivery = driver.findElementByXPath("(//span[contains(@class,'z-2-text z-2-text-body')])[1]");
		   System.out.println("Estimated delivery for the selected product :"+estimatedDelivery.getText());
		   
		   //click go to checkout
		   driver.findElementByXPath("(//div[text()='Go to checkout'])[1]").click();
		   Thread.sleep(2000);
		   
		   //enter login id
		   driver.findElementById("login.email").sendKeys("ss@ss.com");
		   
		   //click login button and capture error message
		   driver.findElementByXPath("//span[text()='Login']").click();
		   WebElement errmsg = driver.findElementByCssSelector("html>body>div:nth-of-type(4)>div:nth-of-type(2)>div>div>main>div>div>div>div:nth-of-type(2)>div>div>div>div>form>div:nth-of-type(2)>div>div:nth-of-type(2)>span:nth-of-type(2)");
		   System.out.println(errmsg.getText());
		   

	   
	}

}
