package titki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Seller_02_Manage_Cart {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest
	public void initBrowser() {
		System.out.println("Open browser and driver");
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    driver = new FirefoxDriver();
    
	}
	 @Test(groups = {"admin", "cart"})
	  public void Cart_01_Creat_Visa() {
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Cart_02_View_Visa() {
	  }
	  @Test(groups = {"admin", "cart"})
	  public void Cart_03_Update_Visa() {
	  }
	  @Test(groups = {"admin", "cart"})
	  public void Cart_04_Delete_Visa() {
	  }
	  @AfterTest
	  public void cleanBrowser() {
			System.out.println("Open browser and driver");
            driver.quit();
	  }
}
