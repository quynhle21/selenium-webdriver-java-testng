package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_Commands {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_() {
	 // Element: textbox/ textarea/ dropdown/ checkbox/ radio/ link/ text..
		
	// 1- chỉ tương tác trên element này 1 lần thôi ( ko khai báo biến) 
		
		driver.findElement(By.id("send2")).click();
		
	// 2- Element dùng lại nhiều lần ở trong trang hiện tại -> thì khai báo biến ( nếu qua trang khác thì ko dùng lại biến này vì 1 element chỉ dùng ở 1 trang chứ ko ở nhiều trang) 
		
		WebElement LoginButton = driver.findElement(By.id("send2"));
		
		
		By loginButton = By.id("send2"); // biến này lại có thể dùng ở nhiều trang khác nhau, vì nó ko tìm element mà chỉ là 1 biến bất kì
		
		// MÌnh khai báo biến khi dùng loginButton nhiều lần 
		loginButton.getClass();
		
		
		
		List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
		
		
		//Xóa dữ liệu trong 1 textbox/ textarea/ dropdown ( editable) 
		
		driver.findElement(By.id("")).clear();
		
		// Nhập dữ liệu vào 1 textbox/ textarea/ dropdown ( editable)
		driver.findElement(By.id("")).sendKeys("quynhle@gmail.com");
		
		
		driver.findElement(By.xpath("//div[@class='footer']")).findElement(By.xpath("//a[text()='My Account']"));
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account'"));
		
		driver.findElement(By.id("Search")).getAttribute("placeholder"); 
		
		
		
        // GUI: Font/size/color/poisition/location.. (ưu tiên thấp, ít apply làm auto)
		driver.findElement(By.id("Search")).getCssValue("background-color");
		
		//Kích thước của element: chiều cao/rộng
		
		Dimension loginButtonSize = driver.findElement(By.id("search")).getSize();
		loginButtonSize.getHeight();
		loginButtonSize.getWidth();
		
		// set tọa độ của element so với độ phân giải màn hình là bao nhiêu
		Point loginButtonLocation = driver.findElement(By.id("search")).getLocation(); 
		
		Rectangle loginButtonRect = driver.findElement(By.id("search")).getRect();
		loginButtonSize = loginButtonRect.getDimension();
		loginButtonLocation = loginButtonRect.getPoint();
		
		// Report HTML + Take Screenshot
		File screenshotfile = driver.findElement(By.id("Search")).getScreenshotAs(OutputType.FILE);
		String screenshotBase64 = driver.findElement(By.id("Search")).getScreenshotAs(OutputType.BASE64);
		
		
		// Lấy ra tên thẻ khi dùng các loại locator mà ko biết trước tên thẻ là gì
		String searchTextboxTagname = driver.findElement(By.cssSelector("#search")).getTagName(); 
		
		// Đầu ra của 1 element trên sẽ là đầu vào của 1 element dưới
	    driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email'"));
		
	    
	    // Lấy ra text của chính nó và các thẻ con của nó
		String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		
		
		// Áp dụng cho tất cả các element
		// 1 element có hiển thị trên màn hình hay ko.
		// Element đó nhìn thấy đc và thao tác được/ có kích thước cụ thể
		
		driver.findElement(By.cssSelector("ul.benefit")).isDisplayed();
		
		// Áp dụng cho tất cả element 
		// 1 element có thể thao tác lên được không (ko bị disable hay chỉ read only)
		driver.findElement(By.cssSelector("ul.benefit")).isEnabled();
		
		// Áp dụng cho 3 loại element: checkbox/ radio button/ dropdown 
		// 1 element đã được chọn được hay chưa
		driver.findElement(By.cssSelector("ul.benefit")).isSelected();
		
		//Chỉ apply cho form/ element trong form
		// thay thế cho hành vi click vào button Login/ Register/ Search..
		driver.findElement(By.cssSelector("ul.benefit")).submit();
		
		
		
		
		
		
	}

	
	

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
