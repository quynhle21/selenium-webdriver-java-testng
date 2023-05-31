package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String autoIT = projectPath + "\\autoIT\\authen_firefox.exe";
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	}
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
	}

    @Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		// Những cái mình cần switch qua: Alert/Iframe(frame)/Window
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent()); 
	
		// Verify the tittle 
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept alert
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// Wait thấy rồi switch qua
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent()); 
		
		Assert.assertEquals(alert.getText(), "Click for JS Confirm");
		
		alert.dismiss();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
		
	}

	@Test
	public void TC_03_Prompt_Alert() {
	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// Wait thấy rồi switch qua
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent()); 
		Assert.assertEquals(alert.getText(), "Click for JS Prompt");
		
		String keyword = "Selenium Java";
		alert.sendKeys(keyword);
		sleepInSecond(2);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered:" + keyword);

		
	}
	@Test
	public void TC_04_Authentication_Alert() {
		// Authen alert ko dùng thư viện Alert đc
		// Selenium support: truyền thẳng username vs password
		
		// http://username:password@domain
		
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

		Assert.assertEquals(driver.findElements(By.cssSelector("div.example>p")), "Congratulations! You must have the proper credentials.");
				
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("https://the-internet.herokuapp.com");
      
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		
		driver.get(getUrlByUserPass(basicAuthenLink, "admin", "admin"));
		
	Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");
		
	}
	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException { 
		Runtime.getRuntime().exec(new String[] { autoIT, "admin", "admin"});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.example>p")), "Congratulations! You must have the proper credentials.");

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
	public String getUrlByUserPass(String url, String username, String password) {
		
		String[] newUrl = url.split("//");
		url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		return url;
		
	}
	
	}

