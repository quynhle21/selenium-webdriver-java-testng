package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Exercise_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Login_01_Empty_Email_Password() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),
				"This is a required field.");

	}

	@Test
	public void Login_02_Invalid_Email() {

		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("1234546@2455");
		driver.findElement(By.id("pass")).sendKeys("23455");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
		
		

	}

	@Test
	public void Login_03_Password_Less_Than_6_Characters() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("quynh@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1245");
		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Login_04_Incorrect_email() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("quynh211199@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("211199");
		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),
				"Invalid login or password.");
		
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
