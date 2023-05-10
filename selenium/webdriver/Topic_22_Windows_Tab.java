package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Windows_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html"); // Trang cha
		
		String githubID = driver.getWindowHandle();
		
		// Lấy ID của trang hiện tại mà driver đang đứng
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		// khi click thì mở ra tab mới -> mỗi tab có 1 cái tittle riêng -> muốn switch tới thằng nào thì dùng title của nó
		// Lấy title: inspect -> tab console -> nhập lệnh: document.title -> enter
		
		switchToWindowByTitle("Google"); 
		
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		
		
		switchToWindowByTitle("Selenium WebDriver"); 
	
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Facebook – log in or sign up");
		
		driver.findElement(By.id("email")).sendKeys("quynh@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		sleepInSecond(3);
		
		switchToWindowByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh'﻿﻿﻿");
		sleepInSecond(3);
		
		closeAllWindowWithoutExpectedID(githubID);
		
			
	}

	//@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		String kynaID = driver.getWindowHandle();
		
		//scroll xuống cuối trang
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kyna.vn']")).isDisplayed());
		
		
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Kyna.vn - YouTube");
		
		Assert.assertTrue(driver.findElement(By.xpath("//yt-formatted-string[@id='text' and text()='Kyna.vn']")).isDisplayed());
		

		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//img[contains(@src,'dathongbao')]")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Địa chỉ tên miền:']/parent::div/following-sibling::div/p[contains(text(),'kyna.vn')]")).isDisplayed());
		closeAllWindowWithoutExpectedID(kynaID);
		
	}

	@Test
	public void TC_03_TechPanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		// Click close button
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		
		switchToWindowByTitle("Mobile");
		
		driver.findElement(By.cssSelector("input#search")).sendKeys("LCD");
		sleepInSecond(3);
		
		
	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	// Hàm này chỉ dùng khi có duy nhất 2 window/tab
	public void switchToWindowByID(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
			
		}
		
	}
	
	// có thể chạy cho 2 hoặc nhiều hơn 2 window/tab
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
		
	}
	
	/// Xóa tất cả các tab ngoại trừ 1 tab muốn để lại
	public void closeAllWindowWithoutExpectedID(String expectedID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(expectedID);
		
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		
	}
	
	
	}

