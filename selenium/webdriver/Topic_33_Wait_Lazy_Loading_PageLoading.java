package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_Wait_Lazy_Loading_PageLoading {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Actions action;
	
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
		
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
	}

	@Test
	public void TC_01_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(waitForAjaxIconInvisible());
	    //Cách 2:Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//ul[role='menu']/li/a/p[contains(text(),'Customers')]")).click();
		
		driver.findElement(By.xpath("//li/a/p[text()='Customers']")).click();
		
		Assert.assertTrue(waitForAjaxIconInvisible());
		// Cách 2: Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'content-header')]/h1[contains(text(),'Customers')]")).isDisplayed());
	}

	@Test
	public void TC_02_Blog_TestProject() {
		driver.get("https://blog.testproject.io/");
		Assert.assertTrue(isPageLoadedSuccess());
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.className("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("div.post-content>h3>a"));
		
		for (WebElement title : postTitles) {
			System.out.println(title.getText());
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
	}
	

	
	
	@Test
	public void TC_03_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	
	//Cách 1
	public boolean waitForAjaxIconInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}
	
	// Cách 2
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	}

