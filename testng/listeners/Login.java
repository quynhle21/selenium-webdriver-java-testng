package listeners;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ReportListener.class)
public class Login {
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
   
	@Test
	public void TC_01_LoginToSystem() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
		// ....
		
	}
	@Test
	public void TC_02_Verify() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
	
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
