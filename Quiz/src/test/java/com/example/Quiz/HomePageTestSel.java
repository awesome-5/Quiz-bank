//package com.example.Quiz;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import org.junit.Assert;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.jcraft.jsch.JSchException;
//import com.vaadin.event.Action;
//
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
//import java.util.HashMap;
//import java.util.List;
//import java.io.File;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//public class HomePageTestSel {
//	private WebDriver driver;
//	private Map<String, Object> vars;
//	JavascriptExecutor js;
//	@Before
//	public void setUp() {
//
//		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/resources/geckodriver");
//		FirefoxOptions options = new FirefoxOptions();
//		options.setHeadless(true);
//		driver = new FirefoxDriver(options);
//		js = (JavascriptExecutor) driver;
//		vars = new HashMap<String, Object>();
//
//		driver.get("http://localhost:8080/");
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-3")));
//		driver.findElement(By.id("gwt-uid-3")).sendKeys("nikola");
//		driver.findElement(By.id("gwt-uid-5")).sendKeys("1234");
//		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!home"));
//
//	}
//	@After
//	public void tearDown() {
//		driver.quit();
//	}
//
//	@Test
//	public void createExistingCourse() {
//		driver.findElement(By.id("gwt-uid-7")).click();
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("Coms3003");
//		driver.findElement(By.id("gwt-uid-9")).click();
//		driver.findElement(By.id("gwt-uid-9")).sendKeys("Software Design");
//		driver.findElement(By.cssSelector(".v-slot:nth-child(5) > .v-button")).click();
//		new WebDriverWait(driver, 200).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Cannot add an existing course"));
//		System.out.println("Success");
//	}
//
//	@Test
//	public void createEmptyCourse() {
//		driver.findElement(By.cssSelector(".v-slot:nth-child(5) > .v-button")).click();
//		new WebDriverWait(driver, 200).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Cannot add an empty course"));
//		System.out.println("Success");
//
//	}
//
//	@Test
//	public void createNewCourse() throws ClassNotFoundException, JSchException, SQLException {
//		driver.findElement(By.id("gwt-uid-7")).click();
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("Test");
//		driver.findElement(By.id("gwt-uid-9")).click();
//		driver.findElement(By.id("gwt-uid-9")).sendKeys("Testing");
//		driver.findElement(By.cssSelector(".v-slot:nth-child(5) > .v-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Course added"));
//		System.out.println("Success");
//		driver.findElement(By.cssSelector(".v-grid-row:nth-child(6) > .v-grid-cell:nth-child(1)")).click();
//	    driver.findElement(By.cssSelector(".v-align-center:nth-child(9) > .v-button")).click();
//	    driver.findElement(By.id("confirmdialog-ok-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Course Deleted"));
//		
//	}
//	
//	@Test
//	public void deleteCourse() throws ClassNotFoundException, JSchException, SQLException {
//		driver.findElement(By.id("gwt-uid-7")).click();
//		driver.findElement(By.id("gwt-uid-7")).sendKeys("Test");
//		driver.findElement(By.id("gwt-uid-9")).click();
//		driver.findElement(By.id("gwt-uid-9")).sendKeys("Testing");
//		driver.findElement(By.cssSelector(".v-slot:nth-child(5) > .v-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Course added"));
//		driver.findElement(By.cssSelector(".v-grid-row:nth-child(6) > .v-grid-cell:nth-child(1)")).click();
//	    driver.findElement(By.cssSelector(".v-align-center:nth-child(9) > .v-button")).click();
//	    driver.findElement(By.id("confirmdialog-ok-button")).click();
//		new WebDriverWait(driver, 20).until(ExpectedConditions.textToBe(By.cssSelector(".v-label"),"Course Deleted"));
//		System.out.println("Success");
//
//	}
//	@Test
//	public void selectCourse() {
//		driver.findElement(By.cssSelector(".v-grid-row:nth-child(3) > .v-grid-cell:nth-child(1)")).click();
//		driver.findElement(By.cssSelector(".v-slot:nth-child(11) > .v-button")).click();
//		Assert.assertTrue("Question page didn't load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!questionGrid")));	
//		System.out.println("Success");
//
//	}
//
//}
