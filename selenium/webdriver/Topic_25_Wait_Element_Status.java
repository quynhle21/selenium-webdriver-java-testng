package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	}
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Displayed() {
		driver.get("https://www.facebook.com/");
		// Điều kiện 1: Element có trên giao diện (UI) và có trong HTML
		// Điều kiện bắt buộc của Visible: Element phải có trên UI
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='login']")));
		
		
	}

	//@Test
	public void TC_02_UnDisplayed_In_HTML() {
		driver.get("https://www.facebook.com/");
		
		// Điều kiện bắt buộc của Invisible: Element ko có trên UI
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Điều kiện 2: Element ko có trên UI nhưng vẫn có trong HTML
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
	}

	//@Test
	public void TC_03_UnDisplayed_Not_In_HTML() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Điều kiện 3: Element ko có trên UI nhưng ko có trong HTML => Case này chạy lâu
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
				
	}
	
	//@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");

	 // Điều kiện bắt buộc của Presence: Phải có trong HTML
		
	 // Điều kiện 1: Element có trên giao diện (UI) và có trong HTML => Điều thuộc Presence
		
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='login']")));
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='login']")));
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	
	// Điều kiện 2: Element ko có trên UI nhưng vẫn có trong HTML => Điều thuộc Presence
		
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
				
	}
	
	@Test
	public void TC_05_Staleness() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Tại thời điểm này đang có trog HTML
		WebElement confirmEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

	    driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
	
	    //Wait cho ConfirmEmailTextbox ko còn trong HTML nứa
	    
	    expliciWait.until(ExpectedConditions.invisibilityOf(confirmEmailTextbox));
		expliciWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
		
		
	
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

