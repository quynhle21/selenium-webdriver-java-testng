package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropdown {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// khởi tạo driver
		driver = new FirefoxDriver();

		// Nhưng biến cần khởi tạo trong Before Class: Là nhưng biến khi khởi tạo cần
		// biến driver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Facebook() {
		driver.get("https://vi-vn.facebook.com/reg/");  // select date time only
		
		select = new Select(driver.findElement(By.cssSelector("select#day")));
		
		// select.selectByIndex(3): Ko dùng index vì khi thay đổi sửa xóa thì index sẽ bị cập nhập lại. Hoặc khi cần reproduce lỗi lại
		
		// select.selectByIndex: Ko dùng vì ko biết value đó là cái gì. Khi cần reproduce lại lỗi. Và value ko phải là tham số bắt buộc
		
		// select.selectByVisibleText: Dùng cái này vì text luôn bắt buộc, ko bị cập nhật lại. Khi tái hiện lỗi thì mình lấy cái text đó xem lại
	
		
		// Chọn item 
		select.deselectByVisibleText("thành phố Đà Nẵng");
		
		// Kiểm tra 1 dropdown là 1 single/multiple ( hầu hết dropdown là single)
		
		Assert.assertTrue(select.isMultiple()); // kiểm tra dropdown single
		Assert.assertFalse(select.isMultiple());// kiểm tra dropdown multiple
		
		// kiểm tra xem chọn item đã đúng chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"thành phố Đà Nẵng"); 
		
		
		// Lấy ra tất cả các item (option) ví dụ: 66
		List<WebElement> city = select.getOptions();
		
		// ktra xem có đủ các item ko
		Assert.assertEquals(city.size(), 66);
		
		for (WebElement text : city) {
			System.out.println(text.getText());
		}
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
