package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_Static {
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
	public void TC_01_Not_EnoughTime() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to start button
		driver.findElement(By.cssSelector("div#start>button")).click();
 
		// Fail vì thiếu ít nhất 3s nữa thì text mới xuất hiện
		sleepInSecond(2);
		
		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_02_Equal() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to start button
		driver.findElement(By.cssSelector("div#start>button")).click();
 
		// Pass
		sleepInSecond(5);
		
		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
		Assert.assertEquals(helloText, "Hello World!");

	
	}
	@Test
	public void TC_03_Greater() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to start button
		driver.findElement(By.cssSelector("div#start>button")).click();
 
		// Pass nhưng dư 5s ko làm gì hết
		sleepInSecond(10);
		
		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
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
