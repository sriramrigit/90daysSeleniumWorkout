package com.pepperfry.furniture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

public class Pepper_fry {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		//close popup
		
		WebElement cpop = driver.findElementByXPath("//div[@id='regPopUp']/a");
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(cpop));
		action=new Actions(driver);
		action.click(cpop);
		
		WebElement offerframe = driver.findElementByXPath("//iframe[@id='webklipper-publisher-widget-container-notification-frame']");
		driver.switchTo().frame(offerframe);
		WebElement offerpopup = driver.findElementByXPath("//span[contains(@class,'wewidgeticon we_close')]");
		wait.until(ExpectedConditions.visibilityOf(offerpopup)).click();
		driver.switchTo().defaultContent();
		
		//wait.until(ExpectedConditions.visibilityOf(cpop)).click();
		
		
		//mouse over furniture
		WebElement furniture = driver.findElementByXPath("(//div[@id='menu_wrapper']//a)[1]");
		action.moveToElement(furniture).perform();
		
		//select office chairs
		driver.findElementByCssSelector("div#meta-furniture>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(12)>a").click();
		Thread.sleep(2000);
		
		//click executive chair
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();
		Thread.sleep(2000);
		
		//enter minimum height as 50
		WebElement minheight = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",minheight);
		minheight.clear();
		minheight.sendKeys("50",Keys.TAB);
		Thread.sleep(2000);
		
		//add Poise executive chair black in wishlist
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		Thread.sleep(2000);
		
		//mouse over furniture
		WebElement furniture2 = driver.findElementByXPath("(//div[@id='menu_wrapper']//a)[1]");
		js.executeScript("arguments[0].scrollIntoView(false);",furniture2);
		action.moveToElement(furniture2).perform();
		
		//click office tables
		driver.findElementByCssSelector("div#meta-furniture>div>div:nth-of-type(4)>div:nth-of-type(2)>div:nth-of-type(10)>a").click();
		Thread.sleep(2000);
		
		//click Executive table
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();
		Thread.sleep(2000);
		
		//select filter 20k to 40k
		js.executeScript("window.scrollBy(0, 150)");
		Thread.sleep(1000);
		driver.findElementByXPath("//label[@for='price20000-40000']").click();
		Thread.sleep(2000);
		
		//click wishlist for brown table
		driver.findElementById("clip_wishlist_").click();
		Thread.sleep(2000);
		
		//get count alert
		WebElement wishCount = driver.findElementByXPath("(//span[@class='count_alert'])[2]");
		js.executeScript("arguments[0].scrollIntoView(false);",wishCount);
		System.out.println("Total items in wish list :"+wishCount.getText());
		
		//click wishlist
		driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();
		Thread.sleep(2000);
		
		//get offerprice and coupon code for table
		String offerPrice = driver.findElementByXPath("(//span[@class='txt-green'])[1]").getText();
		System.out.println("Offer price of Brown table :"+offerPrice);
		String coupon = driver.findElementByXPath("(//strong[text()='FREEDOM'])[1]").getText();
		System.out.println("Coupon code :"+coupon);

		WebElement offerframe2 = driver.findElementByXPath("//iframe[@id='webklipper-publisher-widget-container-notification-frame']");
		driver.switchTo().frame(offerframe2);
		WebElement offerpopup2 = driver.findElementByXPath("//span[contains(@class,'wewidgeticon we_close')]");
		wait.until(ExpectedConditions.visibilityOf(offerpopup2)).click();
		driver.switchTo().defaultContent();
		
		
		//move table to cart
		driver.findElementByXPath("(//a[@class='addtocart_icon'])[1]").click();
		Thread.sleep(2000);
		
		//check availability at 600128
		driver.findElementByClassName("srvc_pin_text").sendKeys("600128");
		driver.findElement(By.className("check_available")).click();
		Thread.sleep(2000);
		
		//click proceed to pay
		driver.findElementByClassName("proceed_cta").click();
		Thread.sleep(2000);
		
		//enter coupon code and apply
		driver.findElementById("coupon_code").sendKeys(coupon);
		driver.findElementById("cpn_check_btn").click();
		Thread.sleep(2000);
		
		//click place order
		driver.findElementByClassName("btn_green_big").click();
		Thread.sleep(2000);
		
		//expand order summary and take screenshot
		driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-left'])[1]").click();
		
		//get screenshot of selected item
		WebElement order = driver.findElementByXPath("//li[@data-slick-index='0']");
		action.moveToElement(order).perform();
		//TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		Point point = order.getLocation();
		int eleWidth = order.getSize().getWidth();
		int eleHeight = order.getSize().getHeight();
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		//File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("src/main/java/order.png");
		FileUtils.copyFile(screenshot, DestFile);
	}
}
