package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_() {

		// Phân chia default Dropdown vs Custom Dropdown bằn HTML code

		// Default dropdown: Dùng thư viện Select của Selenium
		// thẻ select: cha
		// thẻ option: item con bên trong

		// Custom Dropdown: ko có thư viện hỗ trợ nên phải viết hàm dựa vào hành vi của
		// dropdown
		// thẻ div/span/ul/li/.. : Cha/con
		// dev tùy biến vào framework họ sử dụng

		// Hành vi:
		// Click vào 1 thẻ nào đó cho nó xổ hết các item ra
		// Chờ cho tất cả các item đc load ra hết
		// Nếu item mình cần chọn đã hiển thị thì chọn luôn được
		// Nếu item mình cần chọn ở dưới thì phải scroll xuống cho đến khi thấy >> Click
		// item

		

		/*
		 * // Foreach for (WebElement tempElement : allItems) { // tempElement là 1 biến
		 * tạm để duyện trong vòng lặp String itemText = tempElement.getText();
		 * 
		 * if (itemText.equals("10")) { tempElement.click(); break; }
		 * 
		 * }
		 * 
		 * }
		 */
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id= 'number-button']", "//ul[@id= 'number-menu']/li/div", "2");
         Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "2");
		
		selectItemInCustomDropdown("//span[@id= 'number-button']", "//ul[@id= 'number-menu']/li/div", "8");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "8");

		selectItemInCustomDropdown("//span[@id= 'number-button']", "//ul[@id= 'number-menu']/li/div", "15");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");

		selectItemInCustomDropdown("//span[@id= 'number-button']", "//ul[@id= 'number-menu']/li/div", "19");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");

        selectItemInCustomDropdown("//span[@id= 'speed-button']", "//ul[@id= 'speed-menu']/li/div", "Faster");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Faster");
        
        selectItemInCustomDropdown("//span[@id= 'salutation-button']", "//ul[@id= 'salutation-menu']/li/div", "Prof.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Prof.");
	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

		
	}
	
	//@Test
	public void VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class = 'dropdown-toggle']", "//ul[@class = 'dropdown-menu']/li/a", " Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class = 'dropdown-toggle']")).getText(), "Second Option");
	}
	
	@Test
	public void TC_04_Editable(){
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
			
			selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "American Samoa");
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "American Samoa");
			
			selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Belgium");
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

			
			
		}
		
	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {

		// click vào 1 thẻ nào đó để cho nó xổ hết các item ra
		driver.findElement(By.xpath(xpathParent)).click();
		sleepInSecond(1);

		// Chờ cho tất cả các item đc load ra hết -> trong vong 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	

		// Lấy ra hết tất cả các item trong dropdown và lưu vào list
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));

		// Duyệt qua từng item -> dùng vòng lặp For
		for (WebElement tempElement : allItems) {
			// Get text của từng item
			String itemText = tempElement.getText();

			// kiểm tra text đúng vs cái mình chọn
			if (itemText.equals(expectedText)) {

				// scroll to element
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(1);
				
				// Click vào
				tempElement.click();
				sleepInSecond(1);

				// Thoát khỏi vòng lặp
				break;
			}
		}
		
	}
	public void selectItemInEditableDropdown(String xpathTextbox, String xpathChild, String expectedText) {

		// click vào 1 thẻ nào đó để cho nó xổ hết các item ra
		driver.findElement(By.xpath(xpathTextbox)).clear();
		driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
		sleepInSecond(1);

		// Chờ cho tất cả các item đc load ra hết -> trong vong 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	

		// Lấy ra hết tất cả các item trong dropdown và lưu vào list
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));

		// Duyệt qua từng item -> dùng vòng lặp For
		for (WebElement tempElement : allItems) {
			// Get text của từng item
			String itemText = tempElement.getText();

			// kiểm tra text đúng vs cái mình chọn
			if (itemText.equals(expectedText)) {

				// scroll to element
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(1);
				
				// Click vào
				tempElement.click();
				sleepInSecond(1);

				// Thoát khỏi vòng lặp
				break;
			}
		}
		
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
