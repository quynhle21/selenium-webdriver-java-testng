package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Exercise {
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
	public void TC_01_Displayed() { 
	driver.get("https://automationfc.github.io/basic-form/index.html");
	if (driver.findElement(By.id("Email")).isDisplayed()) {
		System.out.println("Email textbox is displayed");
		driver.findElement(By.id("Email")).sendKeys("Automation Testing");
	} else {
		System.out.println("Email textbox is not displayed");
	}
	
	if (driver.findElement(By.id("under_18")).isDisplayed()) {
		System.out.println("Age under 18 is displayed");
		driver.findElement(By.id("under_18")).click();
	} else {
		System.out.println("Age under 18 is not displayed");

	}
    
	if (driver.findElement(By.id("edu")).isDisplayed()) {
		System.out.println("Education textarea is displayed");
		driver.findElement(By.id("edu")).sendKeys("Automation Testing");
		
	} else {
		System.out.println("Education textarea is not displayed");

	}
	
	if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
		System.out.println("Name User 5 is Displayed");
	} else {
		System.out.println("Name User 5 is not Displayed");
	}
	
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.id("Disable_password")).isEnabled()) {
			System.out.println("Password textbox is enable");
		} else {
			System.out.println("Password textbox is not displayed");
		}
        
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println("mail textbox is enable");
		} else {
			System.out.println("mail textbox is not displayed");
		}
		
		
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("development is enable");
		} else {
			System.out.println("development is not displayed");
		}
		
	
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());/// hàm assert dùng để kiểm tra element được chọn thành công
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());/// hàm assert true dùng để kiểm tra element được chọn thành công
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected()); /// hàm assert false dùng để kiểm tra element chuađược chọn thành công
		

		 
	
	}
	
	@Test
	public void TC_04_Page_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		
		//Verify Signup button is enabled
		Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
		
		//Verify error message is displayed
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "An email address must contain a single @.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(), "Please enter a value");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
        driver.findElement(By.id("email")).sendKeys("quynhle@gmail.com");

        
       // Case 1: Enter number only
        driver.findElement(By.id("new_password")).sendKeys("211199");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        
        // Case 2: Enter lowercase only
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("abcd");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        
        
        // Case 3: Enter uppercase only
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("ABCD");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        
        // Case 4: Enter special charactor
        
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("!@$#$");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        
        
        // Case 5: enter more than 7 character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("12345678");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        
        // Case 6: valid 
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("Quynhle@21");
        driver.findElement(By.id("create-account-enabled"));
        sleepInSecond(1);
        
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        driver.findElement(By.id("marketing_newsletter")).click();
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
        



		

		
		
		
		
		
	}
	
	private void sleepInSecond(long timeout) {
		
	}

	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}
}
