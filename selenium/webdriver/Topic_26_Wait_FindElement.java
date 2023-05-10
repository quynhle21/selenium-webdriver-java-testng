package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Wait_FindElement {
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
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com");

		// tìm thấy đúng 1 Element
		// Nó sẽ ko cần phải chờ hết thời gian
		System.out.println("Start time" + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));

		System.out.println("End time" + getDateTimeNow());
		
		// Ko tìm thấy element nào hết
		// Nó sẽ có cơ chế tìm lại ( mỗi 0.5s tìm lại 1 lần)
		// 1- Tìm lại thấy Element thì trả lại Element đó và ko cần tìm tiếp nữa
		// 2- Tìm lại vẫn ko thấy và hết timeout -> đánh fail testcase tại chính step đó
		// -> Ném ra 1 ngoại lệ: NoSuchElementException ( ko tìm thấy element)
        
		System.out.println("Start time" + getDateTimeNow());
		driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		System.out.println("End time" + getDateTimeNow());

        
        
		
		// Tìm thấy nhiều hơn 1 element
		// Lấy ra element đầu tiên nếu có nhiều hơn 1 element
		// Khi mình thao tác vs 1 element nào đó thì nên tối ưu 1 locator
		
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("Testing");	
		
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("https://www.facebook.com");

		// Tạo ra 1 cái list tên là "elements" để đựng element: Ctrl shift O
		List<WebElement>  elements;
		
		// 1- Tìm thấy có đúng 1 Element -> có 1 element trong túi list elements
		// để kiểm tra có bao nhiêu element bên trong túi list thì dùng hàm .size()
		// Ko cần chờ hết timeout
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("Duy nhất element:" + elements.size());
		
		
		
		// 2- ko tìm thấy element nào hết
		// Nó sẽ có cơ chế tìm lại ( mỗi 0.5s tìm lại 1 lần)
		// 1- Tìm lại thấy Element thì trả lại Element đó và ko cần tìm tiếp nữa
		// 2- Tìm lại vẫn ko thấy và hết timeout -> ko đánh fail testcase -> vẫn chạy tiếp step tiếp theo
		// Trả về 1 list rỗng có 0 element
		elements = driver.findElements(By.cssSelector("input[name='reg_email_confirmation__']"));
		Assert.assertTrue(elements.isEmpty());
		System.out.println("ko có element:" + elements.size());
		
		
	
		
		// 3- Tìm thấy nhiều element
		// KO cần chờ hết timeout
		// Trả về túi list chứa tất cả các element tìm đc
		
		elements = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		Assert.assertFalse(elements.isEmpty());

		System.out.println("Nhiều element:" + elements.size());

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}
