package com.hp.laptop;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HPLaptop_i5 {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--disable notifications");
		 */
		driver = new ChromeDriver();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 40);
		js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		try {
			driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		} catch (WebDriverException e) {
			System.out.println("Alert does not exist");
		}
		try {
			WebElement iframe = driver.findElementByXPath("//iframe[@class='inside_popup_iframe']");
			boolean frame = wait.until(ExpectedConditions.visibilityOf(iframe)).isDisplayed();
			WebElement closeframe = driver
					.findElement(By.xpath("//div[contains(@class,'inside_closeButton fonticon')]"));
			wait.until(ExpectedConditions.visibilityOf(closeframe)).click();
			/*
			 * if (frame == true) { driver.switchTo().frame(iframe); driver.findElement(By.
			 * xpath("//div[contains(@class,'inside_closeButton fonticon')]")).click();
			 * driver.switchTo().defaultContent(); }
			 */
		} catch (Exception e) {
			System.out.println("No such frame");
		}
		WebElement laptops = driver.findElementByXPath("(//a[contains(@class,'level-top ui-corner-all')]//span)[3]");
		action = new Actions(driver);
		action.moveToElement(laptops).perform();
		try {

			WebElement closeframe2 = driver
					.findElement(By.xpath("//div[contains(@class,'inside_closeButton fonticon')]"));
			wait.until(ExpectedConditions.visibilityOf(closeframe2)).click();
			/*
			 * if (frame == true) { driver.switchTo().frame(iframe); driver.findElement(By.
			 * xpath("//div[contains(@class,'inside_closeButton fonticon')]")).click();
			 * driver.switchTo().defaultContent(); }
			 */
		} catch (Exception e) {
			System.out.println("No such frame");
		}

		// select pavillion
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		Thread.sleep(5000);
		
		// step 3: Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		// driver.navigate().refresh();
		WebElement processAgain = driver.findElementByXPath("(//span[text()='Processor'])[2]");
		if (processAgain.isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(processAgain)).click();
			Thread.sleep(3000);
			try {

				WebElement closeframe3 = driver
						.findElement(By.xpath("//div[contains(@class,'inside_closeButton fonticon')]"));
				wait.until(ExpectedConditions.visibilityOf(closeframe3)).click();
				/*
				 * if (frame == true) { driver.switchTo().frame(iframe); driver.findElement(By.
				 * xpath("//div[contains(@class,'inside_closeButton fonticon')]")).click();
				 * driver.switchTo().defaultContent(); }
				 */
			} catch (Exception e) {
				System.out.println("No such frame");
			}

			// select i7
			WebElement i7 = driver.findElementByXPath("//span[text()='Intel Core i5']");
			js.executeScript("arguments[0].scrollIntoView(false);", i7);
			i7.click();
			Thread.sleep(3000);

		} else {
			WebElement processer = driver.findElementByXPath("(//span[text()='Processor'])[1]");
			wait.until(ExpectedConditions.elementToBeClickable(processer));
			js.executeScript("arguments[0].click()", processer);
			Thread.sleep(3000);

			wait.until(ExpectedConditions.elementToBeClickable(processAgain)).click();
			Thread.sleep(3000);
			// select i7
			WebElement i7 = driver.findElementByXPath("//span[text()='Intel Core i5']");
			js.executeScript("arguments[0].scrollIntoView(false);", i7);
			i7.click();
			Thread.sleep(3000);

		}

		// select 500gb to 1TB
		WebElement Harddrive = driver.findElementByXPath("//span[text()='Hard Drive Capacity']");
		wait.until(ExpectedConditions.elementToBeClickable(Harddrive)).click();
		Thread.sleep(2000);
		WebElement hd = driver.findElementByXPath("//span[text()='500 GB to 1 TB']");
		js.executeScript("arguments[0].scrollIntoView(false);", hd);
		hd.click();
		Thread.sleep(3000);

		// find 1st element of instock
		WebElement firstavailablePrice = driver.findElementByXPath(
				"(//div[@class='stock-messaging in-stock-messaging'])[1]/preceding-sibling::div//div//div//div[2]//span[1]//span[1]//span");
		WebElement firstavailableproduct = driver.findElementByXPath(
				"(//div[@class='stock-messaging in-stock-messaging'])[1]//ancestor::div[6]//ol[1]//li[1]//div[1]/div[2]//strong//a");
		String firstProduct = firstavailableproduct.getText();
		String firstProductprice = firstavailablePrice.getText().replaceAll("\\D", "");
		System.out.println(firstProduct + ":" + firstProductprice);

		// click add to cart product in stock
		driver.findElementByXPath(
				"(//div[@class='stock-messaging in-stock-messaging'])[1]/following-sibling::form//button//span")
				.click();
		Thread.sleep(5000);

		// click view cart
		driver.findElementByXPath("//a[@class='action showcart']").click();
		Thread.sleep(2000);

		// click and view and edit cart
		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		Thread.sleep(2000);

		// pincode availability
		driver.findElementByName("pincode").sendKeys("600128");
		driver.findElementByXPath("//button[@class='primary standard_delivery-button']").click();
		Thread.sleep(8000);

		// get order total and compare
		WebElement ordertotal = driver
				.findElementByXPath("//table[contains(@class,'data table')]/tbody[1]/tr[3]/td[1]/strong[1]/span[1]");
		String OrderTotalvalue = ordertotal.getText().replaceAll("\\D", "");

		if (OrderTotalvalue.equalsIgnoreCase(firstProductprice)) {
			System.out.println("Order total value matched with selected product");
			WebElement pincodeCheck = driver.findElementByXPath("//div[@class='estimate']");
			wait.until(ExpectedConditions.visibilityOf(pincodeCheck));
			WebElement ptoc = driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]");
			wait.until(ExpectedConditions.elementToBeClickable(ptoc)).click();
			Thread.sleep(2000);

			// take snapshot of order summary
			Thread.sleep(5000);
			WebElement OrdSummary = driver.findElementByXPath("//div[@class='col-mp mp-12']");
			wait.until(ExpectedConditions.visibilityOf(OrdSummary));
			Thread.sleep(3000);
			File src = OrdSummary.getScreenshotAs(OutputType.FILE);
			File dest = new File("src/main/java/LaptopOrder.png");
			FileUtils.copyFile(src, dest);

			// click place order
			WebElement placeOrder2 = driver.findElementByXPath("(//button[@title='Place Order']//span)[5]");
			js.executeScript("arguments[0].scrollIntoView(false);",placeOrder2);
			js.executeScript("arguments[0].click()",placeOrder2);
			//placeOrder2.click();
			Thread.sleep(2000);

			String emailvalidation = driver
					.findElementByXPath("//input[@class='input-text mage-error']/following-sibling::div[1]").getText();
			if (!emailvalidation.isEmpty()) {
				System.out.println("Email id mandatory validation message found" + emailvalidation);

			}
			String firstNameErr = driver.findElementByXPath("(//span[text()='First Name']/following::div/span)[1]")
					.getText();
			if (!firstNameErr.isEmpty()) {
				System.out.println("First name mandatory validation message found" + firstNameErr);
			}
			String lastNameErr = driver.findElementByXPath("(//span[text()='Last Name']/following::div/span)[1]")
					.getText();
			if (!lastNameErr.isEmpty()) {
				System.out.println("Last name mandatory validation message found" + lastNameErr);
			}
			String phonenoErr = driver.findElementByXPath("(//span[text()='Phone Number']/following::div/span)[2]")
					.getText();
			if (!phonenoErr.isEmpty()) {
				System.out.println("phone mandatory validation message found" + phonenoErr);
			}
			String addressErr = driver.findElementByXPath("(//span[text()='Street Address']/following::div/span)[1]")
					.getText();
			if (!addressErr.isEmpty()) {
				System.out.println("Address mandatory validation message found" + addressErr);
			}
			String termsErr = driver.findElementByXPath("(//a[text()='Privacy Policy']/following::div)[1]").getText();
			if (!termsErr.isEmpty()) {
				System.out.println("Term & conditions mandatory messsage" + termsErr);
			}

		}

		driver.close();

	}

}
