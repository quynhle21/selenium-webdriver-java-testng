package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Fixed_Popup {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_Fix_Popup_In_DOM_Ngoaingu() {
		driver.get("https://ngoaingu24h.vn/");

		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");

		// CLick Login button
		driver.findElement(By.cssSelector("button.login_")).click();

		// Verify login button is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input"))
				.sendKeys("automationfc"); // dùng dấu cách ở css trước input là nhảy tới nhiều note
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input"))
				.sendKeys("automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
		sleepInSecond(2);

		// Verify error message
		Assert.assertEquals(
				driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");

		// Close popup

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
		sleepInSecond(2);

		// Verify đã tắt popup: ko hiển thị nhưng vẫn còn trong HTML
		// isDisplayed(): Element hiển thị sẽ trả về true/ Element ko hiển thị nhưng vẫn
		// có trong HTML sẽ trả về false
		// element ko hiển thị nhưng ko có trong HTML thì ko dùng hàm isDisplayed()

		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}

	
	public void TC_02_Fix_Popup_In_DOM_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");
		
		//Click login button
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		// Verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Nhập thông tin
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		// Close popup
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}

	@Test
	public void TC_03_Fix_Popup_Not_In_HTML() {
		driver.get("https://tiki.vn/");
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		//Click button Login
		driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
		
		// Verify popup đã hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Click Đăng nhập bằng email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(1);
		// Ko điền gì và click Đăng nhập
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");
		
		// Close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		// Dùng findElements để verify
		// findElements: Tìm thấy element thì nó trả về 1 list các element đó. ko tìm thấy thì nó ko đánh fail, nó chỉ trả về rỗng
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		

	}
    
	@Test
	public void TC_04_Fix_Popup_Not_In_HTML_Facebook() {
     driver.get("https://www.facebook.com/");
     
     By signupPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
     
     driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']]")).click();
     
     // Verify sign up popup displayed
     Assert.assertTrue(driver.findElement(signupPopup).isDisplayed());
    
     driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
     sleepInSecond(2);
     
     // Verify sign up popup is not displayed
		Assert.assertEquals(driver.findElements(signupPopup).size(), 0);

	}

	public void TC_05_() {

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
