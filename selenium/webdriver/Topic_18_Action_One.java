package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Action_One {
	WebDriver driver;
	Actions action;
    JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String dragDropFile = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(5);

		// vào tab console nhập hàm: setTimeout(() => {debugger;}, 3000) để sau 3s tự
		// động bật debug. hover chuột vào thì sẽ hiện tooltip

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	
	public void TC_02_Hover_To_Element() {
		driver.get("https://www.healthkart.com/");

		// hover chuột vào Customer support link
		action.moveToElement(driver.findElement(By.cssSelector("div.container-item.support"))).perform();
		sleepInSecond(2);

		action.click(driver
				.findElement(By.xpath("//div[contains(@class,'container-item')]//div[text()='Terms & Conditions']")))
				.perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Terms and Conditions");

	}

	
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
    //Cách 1
		/*
		 * 1. Click chuột trái vào 1 số bắt đầu 
		 * 2. Vẫn giữ chuột trái 
		 * 3. Kéo chuột/move chuột tới số kết thúc 
		 * 4. Nhả chuột ra
		 *  5. Thực thi các hành động trên
		 */

		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']")))
				.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).release().perform();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='1' and contains(@class,'ui-selected')]")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='2' and contains(@class,'ui-selected')]")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='3' and contains(@class,'ui-selected')]")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='4' and contains(@class,'ui-selected')]")).isSelected());
	
		
	// Cách 2: Lưu hết 12 số này lại. Muốn thao tác số nào thì sẽ lấy số đó
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selected>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 4);
		
	}
	
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/"); 
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		Keys key = null;
		
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		
		
		//1- Nhấn phím Ctrl 
		// 2- Click chọn các số
		// 3- Nhả phím Ctrl

		action.keyDown(key).perform();
		action.click(allNumbers.get(0))
		.click(allNumbers.get(2))
		.click(allNumbers.get(5))
		.click(allNumbers.get(7))
		.click(allNumbers.get(9)).perform();
		
		action.keyUp(key).perform();
		sleepInSecond(3);
		
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selected>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 5);
		
	}

	
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Scroll tới element (chỉ firefox)
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#dome")).getText(), "Hello Automation Guys!");
		
	}
	
	
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		//Right click vào button
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		//Quit hiển thị
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		// Hover chuột vào
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		// 	Quit sẽ update thêm trạng thái hover
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
		
		// Click vào Quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		// Accept alert
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		
		// Quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	}
	@Test
	public void TC_07_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), driver.findElement(By.cssSelector("div#droptarget"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		String targetCircle = driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		Color targetCircleColor = Color.fromString(targetCircle);
		String targetCircleHexa = targetCircleColor.asHex().toUpperCase();
		Assert.assertEquals(targetCircleHexa, "#03A9F4");
	
	}
	
	
	public void TC_08_Drag_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String dragAndDropContent = getContentFile(dragDropFile);
		
	    // Drag from A to B
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		 // Drag from B to A
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
		
		
		
	}
	
	@Test
	public void TC_09_Drag_Drop_HTML5_Xpath() throws AWTException {
		// dùng chrome chạy đc nhưng firefox thì lỗi nha!
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		dragAndDropHTML5ByOffset("div#column-a", "div#column-b");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		 // Drag from B to A
		dragAndDropHTML5ByOffset("div#column-a", "div#column-b");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
		
		
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

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

public void dragAndDropHTML5ByOffset(String sourceLocator, String targetLocator) throws AWTException {

	WebElement source = driver.findElement(By.cssSelector(sourceLocator));
	WebElement target = driver.findElement(By.cssSelector(targetLocator));

	// Setup robot
	Robot robot = new Robot();
	robot.setAutoDelay(500);

	// Get size of elements
	Dimension sourceSize = source.getSize();
	Dimension targetSize = target.getSize();

	// Get center distance
	int xCentreSource = sourceSize.width / 2;
	int yCentreSource = sourceSize.height / 2;
	int xCentreTarget = targetSize.width / 2;
	int yCentreTarget = targetSize.height / 2;

	Point sourceLocation = source.getLocation();
	Point targetLocation = target.getLocation();

	// Make Mouse coordinate center of element
	sourceLocation.x += 20 + xCentreSource;
	sourceLocation.y += 110 + yCentreSource;
	targetLocation.x += 20 + xCentreTarget;
	targetLocation.y += 110 + yCentreTarget;

	// Move mouse to drag from location
	robot.mouseMove(sourceLocation.x, sourceLocation.y);

	// Click and drag
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

	// Move to final position
	robot.mouseMove(targetLocation.x, targetLocation.y);

	// Drop
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
}}