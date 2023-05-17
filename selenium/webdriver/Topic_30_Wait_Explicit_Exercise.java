package webdriver;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Wait_Implicit_Exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String hagiang = "hagiang.jpg";
	String halong = "halong.jpg";
	String hanoi = "hanoi.jpg";
	String hoian = "hoian.jpg";

	String hagiangPath = projectPath + File.separator + "uploadfiles" + File.separator + hagiang;
	String halongPath = projectPath + File.separator + "uploadfiles" + File.separator + halong;
	String hanoiPath = projectPath + File.separator + "uploadfiles" + File.separator + hanoi;
	String hoianPath = projectPath + File.separator + "uploadfiles" + File.separator + hoian;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
 
		explicitWait = new WebDriverWait(driver,30);
	}
	
	//@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();
 
		//Wait for loading icon invisible (biến mất)
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#start>button")));
		
	
		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
		Assert.assertEquals(helloText, "Hello World!");

	
	}
	//@Test
	public void TC_02_InVisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to start button
		driver.findElement(By.cssSelector("div#start>button")).click();
 
		// Chờ cho thanh loading biến mất thì text xuất hiện
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
		Assert.assertEquals(helloText, "Hello World!");

	}
	
	//@Test
	public void TC_03_Text() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to start button
		driver.findElement(By.cssSelector("div#start>button")).click();
 
		// Chờ cho text xuất hiện
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!")));

		//Get text
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		//Verify
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	@Test
	public void TC_04_Calender_Telerik() {
	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
	// Wait cho calender xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
	
	// Wait cho locator chứa text
	 Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));
	
	 
	 // Wait cho 1 ngày đc ckick
	 
	 explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']/parent::td"))).click(); 
	 
	 // Wait cho cái loading icon biết mất
	 
	 explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));
	 
	// Wait cho locator chứa text
		 Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Thursday, May 11, 2023")));
		 
	}
	@Test
	public void TC_05_Upload_file() {
	driver.get("https://gofile.io/welcome");
	
	// wait cho tất cả icon loading biến mats
	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
	
	
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']"))).click();
	
	// wait cho tất cả icon loading biến mats
	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

	// Upload file lên
	driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(hoianPath + "\n" + halongPath + "\n" + hanoiPath);
	
	// wait cho tất cả uploading icon biến mats
	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
	
	// wait for text
	Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success"), "Your files have been successfully uploaded")));
	
	driver.get(driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).getAttribute("href"));
	
	// wait cho tất cả icon loading biến mats
	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
	
	//Wait cho tên ảnh xuất hiện
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + hoian + "']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + halong + "']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + hanoi + "']"))).isDisplayed());
	
	
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
}
