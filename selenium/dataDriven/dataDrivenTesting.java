package dataDriven;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class dataDrivenTesting {
	
  @Test(dataProvider = "user")
  public void TC_01(String firstParam, String secondParam) {
	  System.out.println(firstParam);
      System.out.println(secondParam);
  }
  @Test(dataProvider = "admin")
  public void TC_02(String firstParam, String secondParam) {
	  System.out.println(firstParam);
      System.out.println(secondParam);
  }

  @DataProvider(name = "user")
  public Object[][] getUserData() {
    return new Object[][] {
      new Object[] { "name", "Automation FC" },
      new Object[] { "address", "1 Long Bien" },
      new Object[] { "city", "Ha Noi" },
    };
  }
  @DataProvider(name = "admin")
  public Object[][] getAdminData() {
    return new Object[][] {
      new Object[] { "name", "Automation FC" },
      new Object[] { "address", "1 Ha Noi" },
      new Object[] { "city", "Ha Noi" },
    };
  }
}
