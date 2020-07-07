package com.trivago.travel;

import java.util.ArrayList;
import java.util.List;
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

public class Trivago {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver();
		driver.get("https://www.trivago.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(2000);
		
		//type destination as agra
		WebElement destination = driver.findElementById("querytext");
		destination.clear();
		destination.sendKeys("Agra", Keys.DOWN, Keys.TAB);
		Thread.sleep(2000);
		
		//choose july 17 start date
		Thread.sleep(2000);
		driver.findElement(By.xpath("//time[@datetime='2020-07-19']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//time[@datetime='2020-07-26']")).click();
		Thread.sleep(1000);
		
		
		driver.findElement(By.xpath("(//button[text()='Apply'])[1]")).click();
		Thread.sleep(1000);
		
		//select 4 stars
		WebElement Accommodation = driver.findElement(By.xpath("//strong[text()='Accommodation']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(Accommodation).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='acc-type-filter-1']")).click();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[@class='refinement-row__content']//button[@title='4-star hotels']").click();
		Thread.sleep(1000);
		driver.findElementById("filter-popover-done-button").click();
		
		//rating as very good
		WebElement guestRating = driver.findElement(By.xpath("//strong[text()='Guest rating']"));
		Thread.sleep(1000);
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(guestRating).perform();
		driver.findElement(By.xpath("//span[text()='Very good']")).click();
		Thread.sleep(1000);
		
		//select hotel location as Agra
		WebElement hotelLocation = driver.findElement(By.xpath("//strong[text()='Hotel location']"));
		Actions builder3 = new Actions(driver);
		builder3.moveToElement(hotelLocation).perform();
		Thread.sleep(1000);
		WebElement location = driver.findElement(By.xpath("//select[@id='pois']"));
		Select selectLocation = new Select(location);
		selectLocation.selectByVisibleText("Agra Fort");
		driver.findElement(By.xpath("//button[text()='Done']")).click();

		//select wifi
		WebElement moreFilters = driver.findElement(By.xpath("//span[text()='Select']"));
		Actions builder4 = new Actions(driver);
		builder4.moveToElement(moreFilters).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[text()='Air conditioning']")).click();
		driver.findElement(By.xpath("//label[text()='WiFi']")).click();
		driver.findElement(By.xpath("//label[text()='Restaurant']")).click();
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		
		//select sort
		WebElement sortBy = driver.findElement(By.xpath("//select[@id='mf-select-sortby']"));
		Select selectSortBy = new Select(sortBy);
		selectSortBy.selectByVisibleText("Rating & Recommended");
		Thread.sleep(3000);
		
		//Get all hotel names from the result
		List<WebElement> hotelName = driver.findElementsByXPath("//span[@data-qa='item-name']");
		//System.out.println(hotelName);
		int hotelnos=hotelName.size();
		
		ArrayList<String> al = new ArrayList<String>(); 
		
		for(int i=0;i<=hotelnos-1;i++)
		{
			
			WebElement sec_column = hotelName.get(i);
			
			String text=sec_column.getText();
			al.add(text);
			
		}
		System.out.println(al);	
	}

}
