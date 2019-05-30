//package com.example.Quiz;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class QuestionGridViewTestSel {
//	private WebDriver driver;
//	private Map<String, Object> vars;
//	JavascriptExecutor js;
//	@Before
//	public void setUp() {
//
//		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/resources/geckodriver");
//		FirefoxOptions options = new FirefoxOptions();
//		//options.setHeadless(true);
//		driver = new FirefoxDriver(options);
//		js = (JavascriptExecutor) driver;
//		vars = new HashMap<String, Object>();
//
//		driver.get("http://localhost:8080/");
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-3")));
//		driver.findElement(By.id("gwt-uid-3")).sendKeys("nikola");
//		driver.findElement(By.id("gwt-uid-5")).sendKeys("1234");
//		driver.findElement(By.id("gwt-uid-5")).sendKeys(Keys.ENTER);
//		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!home"));
//		driver.findElement(By.cssSelector(".v-grid-row:nth-child(3) > .v-grid-cell:nth-child(1)")).click();
//		driver.findElement(By.cssSelector(".v-slot:nth-child(11) > .v-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!questionGrid"));	
//
//	}
//	@After
//	public void tearDown() {
//		driver.quit();
//	}
//	@Test
//	public void newQuestionTest() {
//		driver.findElement(By.cssSelector(".primary")).click();
//		System.out.println("Success");
//	}
//
//	@Test
//	public void filterQuestionTest() {
//		driver.findElement(By.cssSelector(".icon-align-top")).click();
//		driver.findElement(By.cssSelector(".primary")).click();	
//		System.out.println("Success");
//		}
//	
//	@Test
//	public void BackButtonTest() {
//		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!questionGrid"));	
//	    driver.findElement(By.cssSelector(".v-slot:nth-child(9) > .v-button")).click();
//		Assert.assertTrue("back button didn't work",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!home")));	
//		System.out.println("Success");
//		}
//	
//	@Test
//	public void createQuizButtonTest() {
//	    driver.findElement(By.cssSelector(".v-slot:nth-child(5) > .v-button")).click();
//		Assert.assertTrue("Drag page didn't load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!drag")));	
//		System.out.println("Success");
//		}
//	
//	@Test
//	public void viewQuizzesButtonTest() {
//	    driver.findElement(By.cssSelector(".v-slot:nth-child(7) > .v-button")).click();
//		Assert.assertTrue("Test page didn't load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!tests")));	
//		System.out.println("Success");
//		}
//}
//
