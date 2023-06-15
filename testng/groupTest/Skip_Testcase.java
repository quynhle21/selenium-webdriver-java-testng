package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Skip_Testcase {
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("Before Class");
	}
	//@Test
	  public void Create_New_User() {
		System.out.println("TC 01");
	  }
	  
	  @Test (enabled = false)
	  public void View_Existing_User() {
		  System.out.println("TC 02");
	  }
	 
	  
	  @AfterClass(alwaysRun = true)
	  public void afterClass() {
		  System.out.println("After class");
	  }
}


