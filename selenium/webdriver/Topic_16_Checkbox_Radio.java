package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor)driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Default_Checkbox() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        
//      WebElement scroll = driver.findElement(By.xpath("//div[@id='example']"));
//       //Scroll để tránh cookies
//      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViews(true);", scroll);
//	sleepInSecond(8);
		
        // Click chọn checkbox
		
		if (!driver.findElement(dualZoneCheckbox).isSelected());
		driver.findElement(dualZoneCheckbox).click();
		sleepInSecond(2);

		// Verify checkbox này đã đc chọn
		Assert.assertTrue(
				driver.findElement(dualZoneCheckbox).isSelected());

		// Click bỏ chọn checkbox
		driver.findElement(dualZoneCheckbox).click();
		sleepInSecond(2);

		// Verify checkbox này đã đc bỏ chọn chọn
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

	}

	@Test
	public void TC_02_Default_RadioButton() {
	driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
    
	By petrolTwoDotZeroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
	By dieselTwoDotZeroRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");

	// Click vào radio
	if (!driver.findElement(petrolTwoDotZeroRadio).isSelected()) {
	 driver.findElement(petrolTwoDotZeroRadio).click();
	 sleepInSecond(2);
	}	
		
	//Verify radio đc chọn hay ko
	
	Assert.assertTrue(driver.findElement(petrolTwoDotZeroRadio).isSelected());
	
	//Bỏ chọn cái radio
	
	if (driver.findElement(petrolTwoDotZeroRadio).isSelected());{
	driver.findElement(dieselTwoDotZeroRadio).click();
	 sleepInSecond(2);
	}
	// Verify petrol radio đc bỏ chọn thành công + diesel được chọn thành công
	
	Assert.assertFalse(driver.findElement(petrolTwoDotZeroRadio).isSelected());
	Assert.assertTrue(driver.findElement(dieselTwoDotZeroRadio).isSelected());


    }
	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Dùng 1 list element để chứa hết tất cả checkbox
		
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
		
		// Click hết toàn bộ -> dùng vòng lặp for
		
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
			checkbox.click();
		
			}
		}

		// Verify toàn bộ được chọn
		
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
			
		}
		
	
	}

	@Test
	public void TC_04_Select_Checkbox_Radio_By_Condition() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Dùng 1 list element để chứa hết tất cả checkbox
		
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
		
		// Checkbox tên là Gallstones thì mới click
        for (WebElement checkbox : allCheckboxes) {
			if(!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gallstones")) {
				checkbox.click();
				sleepInSecond(2);
			}
		}
		
        //Verify chỉ có checkbox là Gallstones được chọn
        for (WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").equals("Gallstones")) {
			Assert.assertTrue(checkbox.isSelected());
			}
		}
		
        
        // Lưu hết các Radio của Exercise về
        
        List<WebElement> exerciseRadio = driver.findElements(By.xpath("//label[contains(text(), ' Exercise ')]/following-sibling::div//input[@type='radio']"));
        
        // Click chọn radio là 3-4 days
        for (WebElement radio : exerciseRadio) {
			if (!radio.isSelected() && radio.getAttribute("value").equals("3-4 days")) {
			radio.click();
			}
		}
        
        // Verify chỉ có radio 3-4 days đc chọn
        for (WebElement radio : exerciseRadio) {
			if (radio.getAttribute("value").equals("3-4 days")) {
				Assert.assertTrue(radio.isSelected());
			}
		}
		
	}
	@Test
	public void TC_05_Custom() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		By registerRadio_withoutInput = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']");
		
		// CLick vào radio Đăng ký cho người thân
		// Case 1: Nếu như dùng thẻ input thì ko click đc nhưng lại dùng verify đc
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());
		
		// Case 2: Dùng thẻ khác hiện thị để click nhưng lại ko verify đc ( vì nếu ko phải thẻ input thì ko có trang thái selected nên ko verify đc)

		driver.findElement(registerRadio_withoutInput).click();
		Assert.assertTrue(driver.findElement(registerRadio_withoutInput).isSelected());
		
		// Case 3: dùng thẻ khác input để click + dùng input để verify
		
		driver.findElement(registerRadio_withoutInput);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());
		
		// Case4: Vẫn dùng input để click + verify luôn -> Dùng thư viện JS click
		// JS nó ko quan tâm element có bị che hay ko nó vẫn click đc
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());
		
	
	}
	@Test
	public void TC_06_Google_Checkbox_Radio() {
		
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	
	By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
	By quangnamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
	
		// Verify HCM radio chưa đc chọn
	    Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "false");
		
		
		// Click vào HCm radio
		driver.findElement(hcmRadio).click();
		driver.findElement(quangnamCheckbox).click();
		
		// verify đc chọn thành công
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "true");
		
		
		// Muốn chọn hết checkbox =>> Tạo list element chứa hết tất cả các checkbox
		
		List<WebElement> allcheckboxes = driver.findElements(By.xpath("//div[@role='checkbox'] and @aria-label"));
		
		for (WebElement checkbox : allcheckboxes) {
			if (checkbox.getAttribute("aria-checked").equals("false")) {
				checkbox.click();
				sleepInSecond(1);
			}
			
		}
		for (WebElement checkbox : allcheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
			
			
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
		//driver.quit();

	}

}
