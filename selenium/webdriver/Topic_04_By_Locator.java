package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		}
	// Email Address Textbox
	// HTML Element 
	// <input type="email" autocapitalize="none" autocorrect="off" spellcheck="false"
	// name="login[username]" value="" id="email" class="input-text required-entry validate-email"
	// title="Email Address">
	
		
	// HTML
	// 1- Tên thẻ: Tag name - input
	// 2- Tên thuộc tính: Attribute name - type/ autocapitalize/ autocorrect/ spellcheck/
	// name/value/ id/class/title
	// 3- Giá trị thuộc tính: Attribute value - email/ login[username]/email

	// Xpath
	// Format: //tagname[@attribute_name='attribute_value']
	// VD: //input[@id='email'
	
	
	//Css
	// Format: tagname[attribute_name='attribute_value']
	


	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("test@automation.com");
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("search-button"));
		
	}
	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("login[username]"));
		
	}
	@Test
	public void TC_04_Tagname() {
		// Verify xem 1 page có bao nhiêu elenemt giống nhau: link/ radio/ button/ textbox
		driver.findElements(By.tagName("a"));  // dùng findEleement list
	
	
	}
	@Test
	public void TC_05_LinkText() {
		// chỉ dùng được với link
		// lấy tuyệt đối cả text
		driver.findElement(By.linkText("SEARCH TERMS"));
		driver.findElement(By.linkText("ADVANCED SEARCH"));
	}
	@Test
	public void TC_06_Partial_LinkText() {
		// chỉ dùng được với link
		// chỉ lấy tương đối - contain
		driver.findElement(By.partialLinkText("TERMS"));
		driver.findElement(By.partialLinkText("ADVANCED"));
	}
	@Test
	public void TC_07_Css() {
		// Css vs id
		driver.findElement(By.cssSelector("input#email"));
		
		// Css vs class
		driver.findElement(By.cssSelector("div.new-users")); // có dấu chấm là chỉ lấy 1 phần
		driver.findElement(By.cssSelector("div.skip-link"));
		driver.findElement(By.cssSelector("a.skip-cart"));
		driver.findElement(By.cssSelector("a[class='skip-link skip-cart no-count']")); // đây là theo format chuẩn của Css
		
		// Css vs name
		driver.findElement(By.cssSelector("input[name='login[password]'"));
		
		// Css vs tagname
		driver.findElement(By.cssSelector("a"));
		             
		// Css vs link 
		driver.findElement(By.cssSelector("a[title='Search Terms']"));
		
		// Css vs partial link
		driver.findElement(By.cssSelector("a[title='Search Terms']"));
		
		
		
		

	}
	@Test
	public void TC_08_XPath() {
		// Xpath vs id
				driver.findElement(By.xpath("//input[@id='email']"));
				
		// Xpath vs class
				driver.findElement(By.xpath("//div[@class='col-1 new-users']"));
				
		// Xpath vs name
				driver.findElement(By.xpath("//input[@name='login[password]']"));
				
		// Xpath vs tagname
				driver.findElement(By.xpath("//a"));
				             
		// Xpath vs link 
				driver.findElement(By.xpath("//a[@title='Search Terms']"));
				driver.findElement(By.xpath("//a[@text()='Search Terms']"));
				
				
		// Xpath vs partial link
				driver.findElement(By.xpath("//a[contains(@title,'Advanced']"));
				driver.findElement(By.xpath("//a[contains(text(),'Advanced']"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
