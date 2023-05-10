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

public class Topic_21_Ifame {
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

	//@Test
	public void TC_01_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		WebElement facebookIframe = driver.findElement(By.cssSelector("div.fanpage iframe"));
		
		// Verify iframe có hiển thị
		
		Assert.assertTrue(facebookIframe.isDisplayed());
		
		
		// Cần phải switch qua frame/ iframe thì mới truy cập đc
		// có 3 cách vs hàm switch to
		
//		driver.switchTo().frame(0);
//		driver.switchTo().frame("");
//		driver.switchTo().frame(driver.findElement(By.cssSelector(""))); ( chỉ dùng cái này thôi!)
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		String facebookLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(facebookLike);
		
		//Switch về page trước đó
		driver.switchTo().defaultContent();
		
		//Switch vào cái frame chat
		driver.switchTo().frame("cs_chat_iframe");
		
	    // Click vào chat\
	
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Hi");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		
		driver.findElement(By.name("message")).sendKeys("bình luận");
		sleepInSecond(3);
		
		
		//Note: Nếu như chưa switch về trang default lại thì ko có tương tác vs element của page khác được
		// Switch về page trc đó: 
		driver.switchTo().defaultContent();
	
		String keyword = "Excel";
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		// verify course number 
		Assert.assertEquals(courseName.size(), 9);
		
		//Verify course name
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}

	@Test
	public void TC_02_Frame_HDFCbank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//switch vào frame
		driver.switchTo().frame("login_page");
		
		// Nhập vào user ID
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Adele");
		
		// CLick continue
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		//Switch về page trc đó
	    driver.switchTo().defaultContent();
	    
		// Verify password hiển thị
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		
		
		
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

