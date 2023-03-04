 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.name("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	
	
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		//Action
				driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
				driver.findElement(By.name("txtEmail")).sendKeys("123@456@999");
				driver.findElement(By.id("txtCEmail")).sendKeys("123@456@999");
				driver.findElement(By.id("txtPassword")).sendKeys("123456");
				driver.findElement(By.id("txtCPassword")).sendKeys("123456");
				driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
				driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			
			
	   // Verify
				Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
				Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
	}
				

	@Test
	public void TC_03_Register_Incorrect_Email() {
					//Action
					driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
					driver.findElement(By.name("txtEmail")).sendKeys("john.wich@gmail.com");
					driver.findElement(By.id("txtCEmail")).sendKeys("john.wich@gmail.vn");
					driver.findElement(By.id("txtPassword")).sendKeys("123456");
					driver.findElement(By.id("txtCPassword")).sendKeys("123456");
					driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
					driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
				
		   // Verify
					Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	@Test
	public void TC_04_Register_Password_Less_Than_6_Chars() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.name("txtEmail")).sendKeys("john.wich@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("john.wich@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	
	
// Verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Register_Incorrect_Password() {
		//Action
				driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
				driver.findElement(By.name("txtEmail")).sendKeys("john.wich@gmail.com");
				driver.findElement(By.id("txtCEmail")).sendKeys("john.wich@gmail.com");
				driver.findElement(By.id("txtPassword")).sendKeys("123456");
				driver.findElement(By.id("txtCPassword")).sendKeys("1234578");
				driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
				driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			
			
		// Verify
				
				Assert.assertEquals(driver.findElement(By.id("txtCPassword")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Invalid_Phone() {
		//Action
				driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
				driver.findElement(By.name("txtEmail")).sendKeys("john.wich@gmail.com");
				driver.findElement(By.id("txtCEmail")).sendKeys("john.wich@gmail.com");
				driver.findElement(By.id("txtPassword")).sendKeys("123456");
				driver.findElement(By.id("txtCPassword")).sendKeys("123456");
				
				// Nhỏ hơn 10 ký tự
				driver.findElement(By.id("txtPhone")).sendKeys("09876543");
				driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		       // Verify
				Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
				

				// Lớn hơn 11 ký tự ( nếu không reload lại trang thì phải có hàm Clear)
				driver.findElement(By.id("txtPhone")).clear();
				driver.findElement(By.id("txtPhone")).sendKeys("09876543210111");
				driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='Đăng Ký']")).click();
				
				Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
				
				
				// Không bắt đầu bằng các số của nhà mạng
				driver.findElement(By.id("txtPhone")).clear();
				driver.findElement(By.id("txtPhone")).sendKeys("00987654321");
				driver.findElement(By.xpath("//form[id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
				Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
