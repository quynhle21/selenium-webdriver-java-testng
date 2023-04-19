package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Button {
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
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		//click chuyển qua tap Đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled()); 
		
		// Verify login button with backround-color = rgba(224,224,224,1) ( ở firefox là rgba, Ở trình duyệt khác thì nó ra rgb)
		
		Assert.assertFalse(driver.findElement(loginButton).getCssValue("background").contains("rgb 224, 224, 224"));
		
		
		// Enter email/password
		
		driver.findElement(By.id("login_username")).sendKeys("test@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(2);
		
		// Verify login button is enabled
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Lấy ra mã màu của 1 element
		// Chrome/Edge/..: ra mã rgb
		// Firefox: ra mã rgba 
		// Lấy mã nào đều convert qua hexa để verify cho đồng bộ
		
		String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
		
		// Chuyển từ rgb or rgba qua color
		
		Color loginButtonColor = Color.fromString(loginButtonBackgroundColor);
		
		// Color có hàm chuyển qua hexa
		// hexa có các chữ viết thường -> dùng hàm toUppercase để viết hoa khi verify
		String loginButtonHexa = loginButtonColor.asHex().toUpperCase();
		
		
		// Verify login button with background color = rgb(201, 33, 39)
		 
		Assert.assertEquals(loginButtonHexa, "#C92127");  
		
		// Viết thu gọn lại là
		//Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
		
		
		
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

