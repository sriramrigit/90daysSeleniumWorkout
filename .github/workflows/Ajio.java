package com.Ajio;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	public static int prodPrice=0;
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//click shop sale
		driver.findElementByXPath("(//img[@class='btn-cat-nav img-animate'])[4]").click();
		Thread.sleep(2000);
		try
		{
			driver.findElement(By.className("ic-close-quickview")).click();
		}
		catch(Exception e)
		{
			System.out.println("No location popup displayed");
		}
		//enter bags in search
		driver.findElement(By.name("searchVal")).sendKeys("Bags");
		Thread.sleep(2000);
		
		//select Bags in women
		driver.findElementByXPath("(//span[text()='Bags in '])[1]").click();
		Thread.sleep(2000);
		
		//select 5 grid
		driver.findElement(By.className("five-grid")).click();
		Thread.sleep(2000);
		
		//select filter
		WebElement sortByRele = driver.findElement(By.tagName("select")); 
		new Select(sortByRele).selectByVisibleText("What's New");
		Thread.sleep(2000);
		
		//select price 2500-5000
		driver.findElementByXPath("(//div[@class='facet-head '])[1]").click();
		WebElement minprice = driver.findElementById("minPrice");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",minprice);
		minprice.sendKeys("2500");
		WebElement maxprice = driver.findElementById("maxPrice");
		js.executeScript("arguments[0].scrollIntoView(false);",maxprice);
		maxprice.sendKeys("5000",Keys.ENTER);
		Thread.sleep(2000);
		
		//select Tommy hilfiger sling bag with chain strap
		WebElement slingbag = driver.findElementByXPath("//div[text()='Sling Bag with Chain Strap']");
		js.executeScript("arguments[0].scrollIntoView(false);",slingbag);
		slingbag.click();
		Thread.sleep(2000);
		
		//switch to next window
		 Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		//product actual price
		String actual = driver.findElement(By.className("prod-sp")).getText().replaceAll("[^0-9]", "");
		System.out.println(Integer.parseInt(actual));
		if(Integer.parseInt(actual)>2890)
		{
			String useCode = driver.findElement(By.className("promo-title")).getText();
			String discountedPrice= driver.findElement(By.xpath("//div[@class='promo-discounted-price']//span[1]")).getText().replaceAll("[^0-9]", "");
			prodPrice=Integer.parseInt(discountedPrice);
		}
		System.out.println("Discounted price :"+prodPrice);
		String useCodeBLAZE = "BLAZE";
		//System.out.println(useCodeBLAZE);
		
		//check availability for 560043 and expected delivery date
		driver.findElementByXPath("//span[contains(@class,'edd-pincode-msg-details edd-pincode-msg-details-pointer')]").click();
		WebElement pincode = driver.findElementByName("pincode");
		
		js.executeScript("arguments[0].click()", pincode);
		pincode.sendKeys("560043",Keys.ENTER);
		Thread.sleep(2000);
		String delivery = driver.findElementByClassName("edd-message-success-details-highlighted").getText();
		System.out.println("Estimated delivery date :"+delivery);
		
		//click other info toggle
		WebElement otherinfo = driver.findElement(By.className("other-info-toggle"));
		js.executeScript("arguments[0].scrollIntoView(false);",otherinfo);
		otherinfo.click();
		WebElement contact = driver.findElement(By.cssSelector("div#appContainer>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>section>h2>ul>li:nth-of-type(11)>span:nth-of-type(3)"));
		js.executeScript("arguments[0].scrollIntoView(false);",contact);
		System.out.println("Contact details :"+contact.getText());
		
		// Click on ADD TO BAG and then GO TO BAG 
		WebElement gotob = driver.findElement(By.xpath("//span[text()='ADD TO BAG']"));
		js.executeScript("arguments[0].scrollIntoView(false);",gotob);
		gotob.click();
		Thread.sleep(3000);
		WebElement gotobag = driver.findElementByXPath("//span[text()='GO TO BAG']");
		wait=new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.textToBePresentInElement(gotobag, "GO TO BAG"));
		gotobag.click();
		Thread.sleep(2000);
		
		//check order total before applying coupon
		String ordertotal = driver.findElementByXPath("//span[@class='price-value bold-font']").getText().replaceAll("[^0-9]", "");
		System.out.println("Before applying coupon code :"+(Integer.parseInt(ordertotal)/100));
		driver.findElement(By.id("couponCodeInput")).sendKeys(useCodeBLAZE);
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		Thread.sleep(2000);
		
		String rs = driver.findElement(By.xpath("//div[@class='net-price best-price-strip']")).getText().replaceAll("[^0-9]", "");
		//System.out.println(Integer.parseInt(rs));
		if((Integer.parseInt(rs)/100)==prodPrice)
		{
			System.out.println("Coupon applied and discounted price matched");
		}
		driver.findElement(By.className("delete-btn")).click();
		driver.findElement(By.xpath("//div[text()='DELETE']")).click();
		
	}

}
