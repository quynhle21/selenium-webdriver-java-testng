package loopTestcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoopMultiple {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String userName =  "selenium_11_01@gmail.com";
	String password = "111111";
	
	
	@BeforeClass
	public void beforeClass() {
		
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
   
	@Test(invocationCount = 5)
	//chạy đi chạy lại testcase này 5 lần
	public void TC_01_LoginToSystem(String urlValue) {
		driver.get(urlValue + "index.php/customer/account/login/");

		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
