package com.amazon.lgtv;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_LGTV_prices {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Lg TV", Keys.ENTER);
		Thread.sleep(2000);

		List<WebElement> lgwithPrices = driver.findElementsByXPath("//span[@class='a-price-whole']");

		for (int i = 1; i <= lgwithPrices.size(); i++) {
			String xpath1 = "(//span[@class='a-price-whole'])" + "[" + i + "]";
			String xpath2 = "/preceding::span[1]/../../../../../../../../..//../div[1]//div//div//div//h2//a//span";
			String xpathfinal = xpath1.concat(xpath2);
			WebElement tvPrices = driver.findElementByXPath(xpathfinal);
			System.out.println(tvPrices.getText());
		}

	}

}
