import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginViewTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	@Before
	public void setUp() {
		//System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void LoginSuccess() {
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-3")));
		driver.findElement(By.id("gwt-uid-3")).sendKeys("JohnDoe");
		driver.findElement(By.id("gwt-uid-5")).sendKeys("enter");
		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		Assert.assertTrue("Home didn't load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!home")));	
		
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
	
	@Test
	public void ChangeToSignUpView() {
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(3) > .v-button")));
		driver.findElement(By.cssSelector(".v-slot:nth-child(3) > .v-button")).click();
	    Assert.assertTrue("SignUp Didn't Load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!signup")));	
		
	}
	
	@Test
	public void ChangeToForgotPasswordView() {
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(3) > .v-button")));
		driver.findElement(By.cssSelector(".link")).click();
	    Assert.assertTrue("Forgot Password Didn't Load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!forgotPassword")));	
		
	}
}
