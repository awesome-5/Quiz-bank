//package com.example.Quiz;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class ResetPasswordViewTestSel {
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
//		driver.get("http://localhost:8080/#!resetPassword");
//	}
//	@After
//	public void tearDown() {
//		driver.quit();
//	}
//
//	@Test
//	public void resetFieldErrors() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-button")));
//		driver.findElement(By.cssSelector(".v-button")).click();
//		WebDriverWait wait2 = new WebDriverWait(driver, 5);
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-formlayout-firstrow .v-errorindicator")));
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-formlayout-row:nth-child(2) .v-errorindicator")));
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-formlayout-row:nth-child(3) .v-errorindicator")));
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-formlayout-row:nth-child(4) .v-errorindicator")));
//		System.out.println("Success");
//	}
//
//	@Test
//	public void passwordsDontMatch() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-button")));
//		driver.findElement(By.id("gwt-uid-7")).click();
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("a");
//		driver.findElement(By.id("gwt-uid-9")).click();
//		driver.findElement(By.id("gwt-uid-9")).sendKeys("b");
//		driver.findElement(By.cssSelector(".v-button")).click();
//		WebDriverWait wait2 = new WebDriverWait(driver, 5);
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-formlayout-row:nth-child(4) .v-errorindicator")));
//		System.out.println("Success");
//	}
//
//
//	@Test
//	public void TokendoesntExist() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-button")));
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("a");
//		driver.findElement(By.id("gwt-uid-9")).sendKeys("a");
//		driver.findElement(By.id("gwt-uid-11")).sendKeys("a");
//		driver.findElement(By.id("gwt-uid-13")).sendKeys("a");
//		driver.findElement(By.cssSelector(".v-button")).click();
//		wait.until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"We couldn't find your account"));
//		System.out.println("Success");
//	}
//
//}
