package parameter;

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

public class MultipleBrowser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String userName =  "selenium_11_01@gmail.com";
	String password = "111111";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		} else if (browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			
		} else {
			throw new RuntimeException("Browser name is not valid");
		}
		
//		// Cách 2: viết bằng switch case: nó sẽ phân biệt hoa thường. nếu case ko trùng vs class bên multipleBrowswer thì sẽ ko chạy đc
//		switch (browserName) {
//		case "Firefox":
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//
//		   break;
//		   
//		case "Chrome":
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//			break;
//			
//		case "Edge":
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
//			break;
//		default:
//			throw new RuntimeException("Browser name is not valid");
//		
//		}
//		
//		
		
		
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
    @Parameters("url")
	@Test
	public void TC_01_LoginToSystem(String urlValue) {
		driver.get(urlValue + "index.php/customer/account/login/");

		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
		// ....
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
