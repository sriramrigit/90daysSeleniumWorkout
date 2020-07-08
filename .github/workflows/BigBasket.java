package com.bigbasket.juice;

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
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,10);
		Thread.sleep(2000);
		
		driver.findElementByCssSelector("div#cityWidget>label>input").click();
		driver.findElementByXPath("(//span[contains(@class,'btn btn-default')])[1]").click();
		driver.findElementByXPath("(//span[@ng-bind='city.name'])[5]").click();
		driver.findElementByXPath("(//button[@name='skipandexplore'])[1]").click();
		// mouse over shop by
		WebElement shopBy = driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		action = new Actions(driver);
		action.moveToElement(shopBy).perform();
		Thread.sleep(2000);

		// go to beverages and juices
		WebElement beverages = driver.findElementByXPath("(//a[@href='/cl/beverages/?nc=nb'])[2]");
		Thread.sleep(2000);
		action.moveToElement(beverages).perform();
		WebElement jd = driver.findElementByXPath("(//a[@ng-href='/pc/beverages/fruit-juices-drinks/?nc=nb'])[2]");
		action.moveToElement(jd).perform();

		// click juices , click tropicana under real juices
		WebElement juices = driver
				.findElementByXPath("(//a[@ng-href='/pc/beverages/fruit-juices-drinks/juices-sweetened/?nc=nb'])[2]");
		Thread.sleep(2000);
		action.moveToElement(juices).click().perform();
		Thread.sleep(2000);
		WebElement searchBrand = driver.findElementByXPath("(//input[@ng-model='vm.brandSearch'])[1]");
		searchBrand.clear();
		searchBrand.sendKeys("Tropicana");
		Thread.sleep(5000);
		driver.findElementByXPath("(//span[text()='Tropicana'])[1]").click();
		
		searchBrand.clear();
		Thread.sleep(1000);
		WebElement real = driver.findElementByXPath("(//span[text()='Real'])[1]");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",real);
		real.click();
		Thread.sleep(5000);

		// check the total no of juices
		WebElement total = driver.findElementByXPath("(//h2[@class='ng-binding'])[3]");
		String totaljuice = total.getText();
		driver.findElementByXPath("(//span[@class='clearfilter'])[2]").click();
		Thread.sleep(5000);
		WebElement totalafterremoval = driver.findElementByXPath("(//h2[@class='ng-binding'])[3]");
		Thread.sleep(5000);
		String totaltropicana = totalafterremoval.getText();
		System.out.println("Total juices :" + totaljuice + "Tropicana juices :" + totaltropicana);

		// check whether products with Add button
		WebElement check = driver.findElementByXPath("(//span[@class='bskt-icon'])[1]");
		if (check.isDisplayed()) {
			System.out.println("Add button is visible");
			
		}

		// select first listed product
		check.click();
		Thread.sleep(3000);
		try {
			driver.findElementByLinkText("Continue").click();
		} catch (WebDriverException e) {
			System.out.println("Address popup not displayed");
		}
		Thread.sleep(2000);
		
		//click change address
		
		//js.executeScript("window.scrollBy(0,-300)", "");
		WebElement address = driver.findElement(By.xpath("//span[@class='hvc']"));
		js.executeScript("arguments[0].scrollIntoView(false);",address);
		js.executeScript("arguments[0].click()", address);
	
		//select alandur
		
		js.executeScript("window.scrollBy(0,-200)");
		driver.findElement(By.xpath("(//span[@class='btn btn-default form-control ui-select-toggle'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='ui-select-choices-row-inner'])[5]")).click();
		WebElement area = driver.findElement(By.id("areaselect"));
		area.sendKeys("Alandur", Keys.DOWN, Keys.TAB);
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text() = 'Continue']").click();
		Thread.sleep(2000);
		
		//take screenshot, print product name price
		WebElement basket = driver.findElementByXPath("//a[@qa='myBasket']");
		action.moveToElement(basket).perform();
		Thread.sleep(2000);
		WebElement viewcart = driver.findElementByXPath("//ul[@ng-show='vm.basketDrop']");
		wait.until(ExpectedConditions.visibilityOf(viewcart));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		Point point = viewcart.getLocation();
		int eleWidth = viewcart.getSize().getWidth();
		int eleHeight = viewcart.getSize().getHeight();
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		File DestFile = new File("src/main/java/basket.png");
		FileUtils.copyFile(screenshot, DestFile);
		WebElement prodname = driver.findElementByXPath("(//a[@href='#'])[2]");
		WebElement count = driver.findElementByXPath("//input[contains(@class,'text-change-qty-search-popup ng-pristine')]");
		WebElement price = driver.findElementByXPath("(//span[@class='ng-binding'])[4]");
		System.out.println(prodname.getText()+count.getText()+price.getText());
	 
		//view basket
		driver.findElementByXPath("//button[@qa='viewBasketMB']").click();
		Thread.sleep(2000);
		
		//close
		driver.findElementByXPath("//button[@class ='close']").click();
		driver.close();
	}

}
