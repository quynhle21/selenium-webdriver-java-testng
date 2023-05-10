package webdriver;


import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Command {
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
	public void TC_01_Browser() {
		// Các command/hàm để tương tác vs Browser thì nó thông qua biến driver
		//
		//// Dùng để đóng tab hiện tại hoặc Đóng luôn Browser nếu có 1 tab. Dùng để handle Windows/Tab
		driver.close(); 
		
		// ko đóng tab -> Đóng Browser
		driver.quit(); 
		
		// Tìm 1 element với 1 locator nào đó ( id/name/class/css/ xpath..)
		
		driver.findElement(By.id(""));  
		
		// Tìm nhiều element với 1 locator nào đó
		// VD: Tìm ra tất cả đường link của page hiện tại
		// Tìm ra all checkbox/radio
		
		driver.findElements(By.id("")); 
		driver.findElements(By.xpath("//a[@hrepf]"));
		driver.findElements(By.xpath("//input[@type='Checkbox"));

		// Dùng để mở ra 1 page url nào đó 
		driver.get("http://live.techpanda.org/index.php/");
		
		// Dùng để lấy ra url của page hiện tại
		// Đang đứng ở page nào thì nó lấy url của page đó
		
		driver.getCurrentUrl();
		
		//Cách 1: Dùng 1 step
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");
		
		// Cách 2: Dùng cho nhiều hơn 1 step thì mới khai báo biến
		// Code rườm rà, tốn bộ nhớ
		
		 String homeUrl = driver.getCurrentUrl(); 
		 Assert.assertEquals(homeUrl, "http://live.techpanda.org/index.php/");
	
		// Lấy ra code HTML/CSS/JS của page hiện tại => Để verify ( ko dùng Assert equal)
		 driver.getPageSource();
		 Assert.assertTrue(driver.getPageSource().contains("Please enter the following information to create your account"));
		 
		//  Lấy ra title của page hiện tại. Kiểm tra title đúng hay sai
		 driver.getTitle();
		 Assert.assertEquals(driver.getTitle(), "Mobile");
		 
		 // Windown/tab
		 //Lấy ra ID của tab window hiện tại
		 driver.getWindowHandle();
		 
		 //Lấy ra tất cả các ID của các tab/window
		 driver.getWindowHandles();
		 
		 // Cookies
		 
		 driver.manage().deleteAllCookies();
		 Set<Cookie> cookies = driver.manage().getCookies();
		 for (Cookie cookie : cookies) {
			 driver.manage().addCookie(cookie);
	     }
		 
		 // Log của browser
		 driver.manage().logs();
		 
		 // Define ra đơn vị thời gian để chờ cho element xuất hiện trong vòng bao lâu
		 // Apply cho 2 hàm findElement/ findelements
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		 
		 // Để chờ cho page được load xong vòng bao lâu
		 driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		 
		 // Để chờ cho 1 đoạn script được thực thi xong
		 // JavascriptExecutor
		 driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		 
		 // Windows
		 driver.manage().window().fullscreen();
		 driver.manage().window().maximize();
		 
		 // Lấy ra vị trí
		 Point point = driver.manage().window().getPosition();
		 point.getX(); // lấy ra vị trí
		 point.getX();
		 
		 // Set tại 1 vị trí nào đó -> dùng để test responsive ( màn hình ở góc bên trái thì lấy 0,0)
		 driver.manage().window().setPosition( new Point (1920, 1080)); 
         
		 Dimension dimension = driver.manage().window().getSize();
		 dimension.getWidth();
		 dimension.getHeight(); 
		 
		 // Set chiều rộng/cao cho browser
		 driver.manage().window().setSize(new Dimension(1366, 768));
		 
		 // Navigation dùng để chuyển hướng tab 
		 driver.navigate().back();
		 driver.navigate().forward();
		 driver.navigate().refresh();
		 driver.navigate().to("https://www.facebook.com/");
		 
		 // Switch vào Window or tab
		 // Frame or Iframe 
		 // Alert
		 driver.switchTo().alert();
		 driver.switchTo().frame(1);
		 driver.switchTo().window(""); 
		 
	}

	@Test
	public void TC_02_Element() {
		// Các command/ hàm để tương tác vs Elenment thì nó thông qua việc findElement
		//driver.findElement(By.xpath(""))
	}

	@Test
	public void TC_03_Tips() {
		// Chia ra gồm 3 nhóm chính 
		// Nhóm 1 - Hàm để tương tác/ action: Click/ sendkeys/ select.. =>> Hàm thể hiện rõ chức năng của nó và ko trả về dữ liệu
		
		// Nhóm 2 - lấy ra dữ liệu cho mục đích nào đó ( cho step tiếp theo/hiện tại) 
		// Nó sẽ bắt đầu bằng tiền tố là getxxx >> Trả về dữ liệu: String
		// Vd: getText/ getCurrentUrl/ getTitle/ getCssValue/ getAttribute/ getLocation/...
       // Dùng để kiểm tra dữ liệu thực tế (actual resutl) bằng vs dữ liệu mong muốn -> Hàm Assert 
	   // Assert có thư viện riêng ( Junit/ TestNG/ AssertJ/ Hamcresr/..)
		
		
		//Nhóm 3 - Kiểm tra dữ liệu ( ktra tính đúng đắn của dữ liệu (true/false) -> Hàm Assert
		// VD: isDisplayed/ isEnable/ isSelected/ isMultiple
		// Trả về dữ liệu -> boolean
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
