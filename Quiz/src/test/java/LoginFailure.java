import org.junit.Test;
import org.junit.Before;
import org.apache.xalan.lib.Extensions;
import org.junit.After;
import org.junit.Assert;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class LoginFailure {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void LoginFailure() {
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-3")));
		driver.findElement(By.id("gwt-uid-3")).click();
		driver.findElement(By.id("gwt-uid-3")).sendKeys("nikola");
		driver.findElement(By.id("gwt-uid-5")).sendKeys("123");
		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, 3);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-label")));
	}
}
