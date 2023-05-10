package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();

	String emailAddress = "test" + rand.nextInt(999999) + "@gmail.com";

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


	public void TC_01_Java_Code_Geeks() {
		
		// Mới mở page ra thì popup chưa có trong html
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(10);
		
		// List findElements
		By firstStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks']:not([data-page='confirmation'])");
		By secondStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks'][data-page='confirmation']");

		List<WebElement> firstStepPopupElement = driver.findElements(firstStepPopup);

		// 1- Nếu có hiển thì thì nhập thông tin vào và click OK
		// xử lý tiếp các step tiếp theo cho đến khi đóng popup

		if (firstStepPopupElement.size() > 0 && firstStepPopupElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Your Email']")).sendKeys(emailAddress);
			sleepInSecond(3);
			driver.findElement(By.xpath("//span[text()='OK' or text()='Get the books']")).click();
			sleepInSecond(3);
			
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());
			sleepInSecond(10);

			Assert.assertFalse(driver.findElement(firstStepPopup).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());
		}

		// 2- Nếu như ko hiển thị thì qua step tiếp theo
		
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("form#search span.tie-icon-search")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.page-title>span")).getText(), "Agile Testing Explained");

	}


	public void TC_02_VNK_Always_In_HTML() {
    driver.get("https://vnk.edu.vn/");
    sleepInSecond(20);
    
    By popup = By.cssSelector("div#tve_editor");
    
    // Luôn có trong HTML -> dùng findElement để tìm
    
    if (driver.findElement(popup).isDisplayed()) {
    	driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
    	sleepInSecond(3);
    	Assert.assertFalse(driver.findElement(popup).isDisplayed());
    	
    }
    // step tiếp theo
    driver.findElement(By.cssSelector("button.btn-danger")).click();
	sleepInSecond(3);
	
	Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");

    
    
	}

	@Test
	public void TC_03_Popup_Not_In_HTML() {
      driver.get("https://dehieu.vn/");
      sleepInSecond(15);
      
      List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));
      
      // case 1: Có hiển thị popup
       if (popup.size() > 0 && popup.get(0).isDisplayed()) {
    	   // Close popup đi hoặc là nhập thông tin vào
    	   driver.findElement(By.cssSelector("button#close-popup")).click();
    	   sleepInSecond(3);
    	  
       }
       
       // case 2: ko hiển thị popup
       driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
       sleepInSecond(3);
       
       Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
       
       
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
		driver.quit();

	}

}
