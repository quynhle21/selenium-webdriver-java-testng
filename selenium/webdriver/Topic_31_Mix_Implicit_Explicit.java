package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_31_Mix_Implicit_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	}
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Element_Found() {
		// Element có xuất hiện và ko cần chờ hết timeout
		// Dù có set cả 2 loại wait thì ko ảnh hưởng
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		
		// Implicit Wait: Chỉ apply cho việc findElement/ finElements -> Luôn đc ưu tiên trước rồi mới apply điều kiện
		// Explicit Wait: Cho các điều kiện của element
		driver.get("https://vi-vn.facebook.com/");
		
		//Explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());

		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());

		driver.findElement(By.cssSelector("input#email"));
		
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
	}

	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://vi-vn.facebook.com/");
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());

		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		}
		//Cơ chế tìm lại sau mỗi 0.5s
		//Khi hết timeout thì sẽ đánh fail testcase
		// Throw ra exception: NoSuchElement	
	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
	
		// 3.1 - Implicit = Explicit	
		// 3.2 - Implicit < Explicit	
	    // 3.3 - Implicit > Explicit	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://vi-vn.facebook.com/");

		// Implicit
				System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());

				try {
					driver.findElement(By.cssSelector("input#selenium"));
				} catch (Exception e) {
					System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());
					
				}
		//Explicit		
				System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
				} catch (Exception e) {
					System.out.println("Thời gian kết thúc của explicit- " + getTimeStamp());	

				}
		
		
	}
	
	@Test
	public void TC_04_Element_Not_Found_Implicit_Explicit() {
		
		// 3.2 - Implicit < Explicit/ áp dụng cả 3 wait cho testcase này
		// 5s của implicit
		// 8s của explicit
	 	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		driver.get("https://vi-vn.facebook.com/");

		// Implicit
				System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());

				try {
					driver.findElement(By.cssSelector("input#selenium"));
				} catch (Exception e) {
					System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());
					
				}
		//Explicit		
				System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Thời gian kết thúc của explicit- " + getTimeStamp());	

				}
		}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit() {
		explicitWait = new WebDriverWait(driver, 8);
		driver.get("https://vi-vn.facebook.com/");

		//Explicit - By là tham số nhận vào của hàm explicit - 	visibilityOfElementLocated
		// Implicit = 0
		// Tổng time = Explicit
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit- " + getTimeStamp());	

		}
	}
	
	@Test
	public void TC_06_Element_Not_Found_Explicit_Element() {
		explicitWait = new WebDriverWait(driver, 8);
		driver.get("https://vi-vn.facebook.com/");
	

		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf((driver.findElement(By.cssSelector("input#selenium")))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit- " + getTimeStamp());	

		}
	
	
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	
	//Show ra time-stamp tại thời điểm gọi hàm này
	// time-stamp = ngày giờ phút giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}
	
	}

