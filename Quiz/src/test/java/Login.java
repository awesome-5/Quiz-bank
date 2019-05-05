import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Login {
	
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
	public void Login() {
		
		// Test name: LoginTest
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(1617, 1050));
		WebDriverWait wait = new WebDriverWait(driver, 1000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-3")));
		driver.findElement(By.id("gwt-uid-3")).sendKeys("JohnDoe");
		driver.findElement(By.id("gwt-uid-5")).sendKeys("enter");
		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!home"));	}
}

