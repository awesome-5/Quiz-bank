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


public class ChangeToForgotPasswordView {
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
	public void ChangeToForgotPasswordView() {
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(3) > .v-button")));
		driver.findElement(By.cssSelector(".link")).click();
	    Assert.assertTrue("Forgot Password Didn't Load",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!forgotPassword")));	
		
	}
	
	

}
