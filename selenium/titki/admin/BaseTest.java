package titki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	 WebDriver driver;
		String projectPath = System.getProperty("user.dir");

		@BeforeTest
		public void initBrowser() {
			System.out.println("Open browser and driver");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    
		}
		
		@AfterTest
		  public void cleanBrowser() {
				System.out.println("Open browser and driver");
	            driver.quit();
		  }
		
		public WebDriver getBrowserDriver() {
			return driver;
		}
}
