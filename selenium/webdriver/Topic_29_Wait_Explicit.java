package webdriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Wait_Explicit {
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
		driver.manage().window().maximize();
		expliciWait = new WebDriverWait(driver, 15);

	}

	@Test
	public void TC_01_() {
		driver.get("https://www.fahasa.com/");

		// 1- Chờ cho 1 alert xuất hiện trong HTML
		// Chờ + Switch vào luôn
		Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());       //**
		alert.accept();

		// 2 -Chờ cho 1 attribute có value
		// Dùng contains thì value có thể lấy 1 nửa text cũng đc
		expliciWait.until(
				ExpectedConditions.attributeContains(By.id("login_username"), "placeholder", "Nhập số điện thoại ")); 

		// Nhưng dùng attributeToBe thì phải có đầy đủ text mình mong đợi
		expliciWait.until(ExpectedConditions.attributeToBe(By.id("login_username"), "placeholder",
				"Nhập số điện thoại hoặc email"));      //**

		// 3- Dùng để chờ cho 1 element có thể đc click hay ko: button/ checkbox/ radio/
		// link/ image
		// Dùng trc hàm click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fhs-btn-login")));      //**

		// 4- Chờ cho 1 element đã đc chọn hay chưa: checkbox/radio
		// Dùng trc khi apply isSelected();
		expliciWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input[name='sex']")));

		// 5- Dùng để chờ cho Frame xuất hiện mà switch vào Frame đó
		expliciWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

		// 6- Chờ cho 1 element ko còn visible nữa
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

		// Chờ cho nhiều element ko còn visible nữa
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));

		// Var Arguments
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector(""))));
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")),
				driver.findElement(By.cssSelector(""))));
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")),
				driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));

		
		
		
		// 7- Chờ cho các element nó có tổng số lượng là bao nhiêu
		expliciWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input[name='sex']"), 3));
		
		// ít hơn 3 element thì pass
		expliciWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("input[name='sex']"), 3));
		// nhiều hơn 3 element thì pass
		expliciWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("input[name='sex']"), 3));

		// Lấy ra số lượng element bằng nhiêu
		driver.findElement(By.cssSelector("")).getSize();
		
		
		// Thao tác và nó bật ra các tab/window
		// 8- Chờ cho bao nhiêu tab/window đc xuất hiện
		boolean windowActive = expliciWait.until(ExpectedConditions.numberOfWindowsToBe(4));
		
		
		//Chờ cho element có trong HTML (ko quan tâm có trên UI hay ko)
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='sex']")));
		
		//Chờ cho element có trong HTML (ko quan tâm có trên UI hay ko): dropdown item
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='sex']")));
		
		WebElement loginUsername = expliciWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.popup-login-content"), By.cssSelector("input#login_username")));
		
		List<WebElement> loginTextbox = expliciWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.cssSelector("div.popup-login-content"), By.cssSelector("input#login_username")));
		
		
		// chờ cho element ko còn trong HTML
		expliciWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("input[name='sex']"))));
		//verify element ko còn trong HTML
		Assert.assertTrue(expliciWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("input[name='sex']")))));

		
		// Dùng trc hàm getText()
		expliciWait.until(ExpectedConditions.textToBe(By.cssSelector("span.strusted-rp-name"), ""));       //**
		
		// 2 cái này ít dùng
		expliciWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.strusted-rp-name"), ""));
		expliciWait.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector("h2.login-title"), "login_title"));

		// Dùng trc hàm getTitle()
		expliciWait.until(ExpectedConditions.titleContains("FAHASA.COM"));
		expliciWait.until(ExpectedConditions.titleIs("Nhà sách trực tuyến Fahasa.com - FAHASA.COM"));

		// Dùng trc hàm getCurrentUrl()
		expliciWait.until(ExpectedConditions.urlContains("fahasa.com"));
		expliciWait.until(ExpectedConditions.urlToBe("http://www.fahasa.com/"));

		// CHờ cho 1 element hiển thị
		expliciWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));            //**

		//Chờ cho nhiều element hiển thị
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector(""))));  //**
		// Chờ cho 1 hay nhiều element hiển thi
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector(""))));
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
		
		expliciWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector("div.popup-login-content"), By.cssSelector("input#login_username")));
		expliciWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(driver.findElement(By.cssSelector("div.popup-login-content")), By.cssSelector("input#login_username")));

		
		
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
