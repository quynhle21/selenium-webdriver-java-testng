package webdriver;

import java.io.File;
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

public class Topic_24_Upload_File {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Upload_Single_file() {
		// do mỗi lần sendkey thì element sẽ update status mới, nên trc khi sendkey mình
		// phải tìm lại element
		// upload nhiều file 1 lúc thì sẽ sendkey file path và veryfy bằng cái tên file
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFiles = By.xpath("//input[@type='file']");

		driver.findElement(uploadFiles).sendKeys(hoianPath);
		sleepInSecond(3);

		driver.findElement(uploadFiles).sendKeys(halongPath);
		sleepInSecond(3);

		driver.findElement(uploadFiles).sendKeys(hanoiPath);
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoian + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + halong + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hanoi + "']")).isDisplayed());

		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hoian + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + halong + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hanoi + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFiles = By.xpath("//input[@type='file']");

		// để upload 1 lần nhiều file thì ở giữa các path có ký tự xuống dòng "\n"
		driver.findElement(uploadFiles).sendKeys(hoianPath + "\n" + halongPath + "\n" + hanoiPath);
		sleepInSecond(3);

		

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoian + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + halong + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hanoi + "']")).isDisplayed());

		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hoian + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + halong + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hanoi + "']")).isDisplayed());

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
