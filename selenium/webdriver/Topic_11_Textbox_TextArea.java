package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	
	String emailAddress = "automation" + rand.nextInt(999999) + "@gmail.com";
	String firstname = "Quynhhhhlltt";
	String lastname = "Lee";
	String fullName = firstname + " " + lastname;
	String password = "1234567888899";
	
	

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
	public void TC_01_TechPanda_Register() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector(" a[title='Create an Account'")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.cssSelector(" button[title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfor);
		
		Assert.assertTrue(contactInfor.contains(fullName));
		Assert.assertTrue(contactInfor.contains(emailAddress));
		
		driver.findElement(By.xpath("//a[text()='Account Information']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);
	    
		driver.findElement(By.cssSelector("a.skip-account>span.label")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		
		driver.findElement(By.cssSelector("div.page-title img")).isDisplayed(); // verify có về màn home hay ko thì chỉ cần verify 1 cái thuộc màn home
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed());

		// .isDisplayed cũng là 1 cách wait hay
		// Ảnh hưởng bởi impliciwait là 30s
		// Giây đầu tiên ko tìm thấy sẽ tiếp tục chờ và tìm lại
		// 0,5s tìm lại 1 lần
		// 5s đầu tiên nó tìm 10 lần vẫn chưa thấy
		// 5.5 giây tiếp theo thì element image xuất hiện -> thấy -> trả về kết quả là True (isDisplayed)
		// assertTrue
		
		
		// moi lan run lai testcase phair thay firstname & lastname
	}
	

	@Test
	public void TC_02_Invalid_Email() {

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
		// driver.quit();
	}
}
