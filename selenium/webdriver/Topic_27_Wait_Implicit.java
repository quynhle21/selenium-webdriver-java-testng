package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_Implicit {
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
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_No_Set_Implicit() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>butotn")).click();

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_02_Implicit_Not_EnoughTime() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>butotn")).click();

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_03_Implicit_Equal() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>butotn")).click();

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");

	
	}
	@Test
	public void TC_04_Implicit_Greater() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>butotn")).click();

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();

	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
