package com.zoomcar.chennai;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZoomCar {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.zoomcar.com/chennai/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,10);
		Thread.sleep(2000);
		
		//click start your wonderful journey
		driver.findElement(By.className("search")).click();
		
		//enter pickup point
		driver.findElement(By.className("search")).sendKeys("Thuraipakkam",Keys.DOWN);
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='pac-item'][1]").click();
		Thread.sleep(2000);
		//click next
		driver.findElement(By.className("proceed")).click();
		Thread.sleep(2000);
		
		//select next day
		driver.findElement(By.xpath("(//div[@class='days']//div)[3]")).click();
		Thread.sleep(2000);
		
		//select 9 AM
		driver.findElement(By.xpath("//span[text()='09:00']")).click();
		Thread.sleep(2000);
		
		//click next
		driver.findElement(By.className("proceed")).click();
		Thread.sleep(2000);
			
		//click show more
		driver.findElement(By.className("show-more")).click();
		Thread.sleep(2000);
		
		//select tomorrow
		driver.findElement(By.xpath("//li[@class='inactive low-price']/following-sibling::li[1]")).click();
		Thread.sleep(2000);
		
		//select 6 PM
		driver.findElement(By.xpath("//span[text()='18:00']")).click();
		Thread.sleep(2000);
		
		//click done
		driver.findElement(By.className("proceed")).click();
		Thread.sleep(2000);
		
		//snapshot of pickup and drop time
		WebElement pickupdrop = driver.findElementByXPath("(//div[@class='item'])[2]");
		wait.until(ExpectedConditions.visibilityOf(pickupdrop));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		Point point = pickupdrop.getLocation();
		int eleWidth = pickupdrop.getSize().getWidth();
		int eleHeight = pickupdrop.getSize().getHeight();
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		File DestFile = new File("src/main/java/pickupAnddrop.png");
		FileUtils.copyFile(screenshot, DestFile);
		
		//validate pickup and drop time
		String pickupTime = driver.findElementByXPath("(//div[@class='time']//div)[2]").getText();
		String dropTime = driver.findElementByXPath("(//div[@class='time']//div)[4]").getText();
		if((pickupTime.contains("09:00 AM"))&& (dropTime.contains("06:00 PM")))
		{
			System.out.println("pickup time is 9 AM and drop time is 6 PM");
		}
		
		//click high to low
		driver.findElement(By.xpath("//div[text()=' Price: High to Low ']")).click();
		Thread.sleep(2000);
				
		//get all car names
		List<WebElement> carDetials = driver.findElementsByXPath("//div[@class='details']//h3");
		List<WebElement> carprice = driver.findElementsByXPath("//div[@class='new-price']");
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		
		for(int i=0;i<=carDetials.size()-1;i++)
		{
			
			WebElement carname = carDetials.get(i);
			WebElement cprice=carprice.get(i);
			map.put(carname.getText(), cprice.getText().replaceAll("[^0-9.]", ""));
			
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println("Car --> " + entry.getKey() + " Price --> " + entry.getValue());
		}
		
		//get snapshot of highest price car
		WebElement highcost = driver.findElementByXPath("//div[@class='component-car-item'][1]");
		File screenshot2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg2 = ImageIO.read(screenshot2);
		Point point2 = highcost.getLocation();
		int eleWidth2 = highcost.getSize().getWidth();
		int eleHeight2 = highcost.getSize().getHeight();
		BufferedImage eleScreenshot2= fullImg2.getSubimage(point2.getX(), point2.getY(),eleWidth2, eleHeight2);
		ImageIO.write(eleScreenshot2, "png", screenshot2);
		File DestFile2 = new File("src/main/java/Highcostcar.png");
		FileUtils.copyFile(screenshot2, DestFile2);
		
		//click know more details
		driver.findElementByXPath("(//div[@class='know-more-details'])[1]").click();
		
		//print the rate after 45 kms
		WebElement rate = driver.findElementByXPath("(//div[@class='price-info'])[1]");
		String rateafter45 = rate.getText().substring(2, 4);
		System.out.println("Rate after 45 kms:"+rateafter45+"perkm");
		driver.close();
	}

}
