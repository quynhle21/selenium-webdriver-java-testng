package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebBrowser_Exercise {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
	}

	@Test
	public void TC_01_Url() { 
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text() ='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		


	}

	@Test
	public void TC_02_Title() {
        driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text() ='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
	}

	@Test
	public void TC_03_Navigation() {
		 driver.get("http://live.techpanda.org/");
			
		 driver.findElement(By.xpath("//div[@class='footer']//a[text() ='My Account']")).click();
			sleepInSecond(3);
		 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

		driver.navigate().back();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        
		driver.navigate().forward();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

	}
	
	@Test
	public void TC_03_Page_Source() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text() ='My Account']")).click();
			sleepInSecond(3);
		
		// Do contains trả giá trị boolean nên dùng asset true/false
		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(3);
		
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

		
		
	}
	
	private void sleepInSecond(long timeout) {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
