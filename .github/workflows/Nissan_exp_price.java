package com.autofinder.nissan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nissan_exp_price {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		driver = new ChromeDriver(options);
		driver.get("https://autoportal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,10);
		
		driver.findElementByXPath("//span[@data-type='city-title']").click();
		driver.findElement(By.id("ac_user_city")).sendKeys("Chennai",Keys.DOWN);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='red_but'])[1]")).click();
		Thread.sleep(2000);
		WebElement brand = driver.findElement(By.name("brand")); 
		new Select(brand).selectByVisibleText("Renault");
		
		WebElement model = driver.findElement(By.name("model")); 
		new Select(model).selectByVisibleText("Arkana");
		driver.findElement(By.cssSelector("form#brand_filter>div>input")).click();
		Thread.sleep(2000);
		WebElement expected = driver.findElementByXPath("//div[@class='nm_price m_b-10']");
		System.out.println(expected.getText());
		
	}

}
