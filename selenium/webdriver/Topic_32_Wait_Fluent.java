package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Wait_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	WebDriverWait webdriverWait;
	FluentWait<WebDriver> fluentWait;
	
	
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
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		

		fluentWait = new FluentWait<WebDriver>(driver);
		
		//Set timeout tổng tham bằng bao nhiêu
		fluentWait.withTimeout(Duration.ofSeconds(5))
		
		// Polling/interval time: lặp lại
		.pollingEvery(Duration.ofMillis(200))
		
		// Ignore exception nếu ko tìm thấy element
		.ignoring(NoSuchElementException.class);
		
		// Điều kiện để Wait
		// Wait cho element có locator này isDisplayed => trả về boolean
		// T là kiểu dữ liệu của fluent đã khởi tạo ban đầu( trùng nhau), là tham số của hàm fluentwait
		// V là hàm mong muốn đc trả về
		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver t) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
				
			}
		});
		
		Assert.assertTrue(textStatus);
		
	
		// Wait cho element có text -> getText => trả về String
		
		
		String hiText = fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver t) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}
		});
		Assert.assertEquals(hiText, "Hello World!");
	}

	@Test
	public void TC_02_Bai8() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentWait = new FluentWait<WebDriver>(driver);
		
		fluentWait.withTimeout(Duration.ofSeconds(12))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
			}
		});
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

