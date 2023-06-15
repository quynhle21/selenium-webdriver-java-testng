package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Priority {
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("Before Class");
	}
	@Test(priority = 1)
	  public void Create_New_User() {
		System.out.println("TC 01");
	  }
	  
	  @Test(priority = 2)
	  public void View_Existing_User() {
		  System.out.println("TC 02");
	  }
	  @Test(priority = 3)
	  public void Update_User() {
		  System.out.println("TC 03");
	  }
	  @Test(priority = 4)
	  public void Move_user() {
		  System.out.println("TC 04");
		  
	  }
	  @Test(priority = 5)
	  public void Delete_user() {
		  System.out.println("TC 05");
		  
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void afterClass() {
		  System.out.println("After class");
	  }
}


