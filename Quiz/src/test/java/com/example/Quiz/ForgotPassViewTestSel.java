//package com.example.Quiz;
//import java.util.HashMap;
//import java.util.Map;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//public class ForgotPassViewTestSel {
//
//	private WebDriver driver;
//	private Map<String, Object> vars;
//	JavascriptExecutor js;
//	@Before
//	public void setUp() {
//		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/resources/geckodriver");
//		FirefoxOptions options = new FirefoxOptions();
//		options.setHeadless(true);
//		driver = new FirefoxDriver(options);
//		js = (JavascriptExecutor) driver;
//		vars = new HashMap<String, Object>();
//		driver.get("http://localhost:8080/#!forgotPassword");
//
//	}
//	@After
//	public void tearDown() {
//		driver.quit();
//	}
//	@Test
//	public void forgotBackButton() {
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(".v-slot:nth-child(3) > .v-button")));
//		driver.findElement(By.cssSelector(".v-slot:nth-child(3) > .v-button")).click();
//		Assert.assertTrue("Home didn't load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!login")));	
//		System.out.println("Success");
//	}
//	@Test
//	public void sendEmailButton() {
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-7")));
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("natp4444@yahoo.com");
//		driver.findElement(By.cssSelector(".friendly")).click();
//		WebDriverWait wait2 = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Email Sent"));
//		System.out.println("Success");
//	}
//}
