package com.angular.calculator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calculator {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	public static int num=0;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,10);
		String a1="20";
		String a2="5";
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys(a1);
		WebElement multiply = driver.findElement(By.tagName("select")); 
		Select mul=new Select(multiply);
		mul.selectByValue("MULTIPLICATION");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys(a2);
		driver.findElement(By.id("gobutton")).click();
		WebElement result = driver.findElementByTagName("h2");
		num=Integer.parseInt(a1)*Integer.parseInt(a2);
		String res=String.valueOf(num);
		wait.until(ExpectedConditions.textToBePresentInElement(result, res));
				//(result.getText().toString().compareToIgnoreCase("100")));
		
		System.out.println(result.getText());
				
	}

}
