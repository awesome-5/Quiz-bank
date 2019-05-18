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

public class SignUpTest {
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
	public void SignUpCancelButton() 
	{

		driver.get("http://localhost:8080/#!signup");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(3) > .v-button")));
		driver.findElement(By.cssSelector(".v-slot:nth-child(3) > .v-button")).click();
		Assert.assertTrue("Cancel didn't work",new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("http://localhost:8080/#!login")));	

	}

	@Test
	public void SignUpPasswordDontMatch() {

		driver.get("http://localhost:8080/#!signup");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(1) > .v-button")));

		driver.findElement(By.id("gwt-uid-3")).click();
		driver.findElement(By.id("gwt-uid-3")).sendKeys("A");
		driver.findElement(By.id("gwt-uid-5")).click();
		driver.findElement(By.id("gwt-uid-5")).sendKeys("A");
		driver.findElement(By.id("gwt-uid-7")).click();
		driver.findElement(By.id("gwt-uid-7")).sendKeys("A");
		driver.findElement(By.id("gwt-uid-9")).click();
		driver.findElement(By.id("gwt-uid-9")).sendKeys("A");
		driver.findElement(By.id("gwt-uid-11")).click();
		driver.findElement(By.id("gwt-uid-11")).sendKeys("a");
		driver.findElement(By.id("gwt-uid-13")).click();
		driver.findElement(By.id("gwt-uid-13")).sendKeys("b");
		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();

		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, 5);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-errorindicator")));
	}

	@Test
	public void SignUpUsernameTaken() {

		driver.get("http://localhost:8080/#!signup");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(1) > .v-button")));

		driver.findElement(By.id("gwt-uid-3")).click();
		driver.findElement(By.id("gwt-uid-3")).sendKeys("a");
		driver.findElement(By.id("gwt-uid-5")).sendKeys("a");
		driver.findElement(By.id("gwt-uid-7")).click();
		driver.findElement(By.id("gwt-uid-7")).sendKeys("a");
		driver.findElement(By.id("gwt-uid-9")).click();
		driver.findElement(By.id("gwt-uid-9")).sendKeys("nikola");
		driver.findElement(By.id("gwt-uid-11")).click();
		driver.findElement(By.id("gwt-uid-11")).sendKeys("a");
		driver.findElement(By.id("gwt-uid-13")).click();
		driver.findElement(By.id("gwt-uid-13")).sendKeys("a");
		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-errorindicator")));
	}

	@Test
	public void SignUpTextViewErrors() {

		driver.get("http://localhost:8080/#!signup");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-slot:nth-child(1) > .v-button")));

		driver.findElement(By.cssSelector(".v-slot:nth-child(1) > .v-button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, 5);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-10 > .v-errorindicator")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-2 > .v-errorindicator")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-6 > .v-errorindicator")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-8 > .v-errorindicator")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-4 > .v-errorindicator")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gwt-uid-12 > .v-errorindicator")));
	}

}
