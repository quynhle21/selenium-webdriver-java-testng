package dependTestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Depends {
	// chỉ apply cho những testcase chạy theo luồng/ có phụ thuộc dữ liệu của nhau
	
  @Test
  public void TC_01_Create_New_Product() {
	  System.out.println("Create new product");
	  Assert.assertTrue(false);
  }
 
  @Test(dependsOnMethods = "TC_01_Create_New_Product")
  public void TC_02_View_Existing_Product() {
	  System.out.println("View_Existing_Product");

  }
 
  @Test(dependsOnMethods = "TC_02_View_Existing_Product")
  public void TC_03_Move_Existing_Product_To_New_Category() {
	  System.out.println("Move_Existing_Product_To_New_Category");
  }
 
  @Test(dependsOnMethods = "TC_03_Move_Existing_Product_To_New_Category")
  public void TC_04_Edit_Existing_Product() {
	  System.out.println("Edit_Existing_Product");

  }
  
  @Test(dependsOnMethods = {"TC_04_Edit_Existing_Product", "TC_01_Create_New_Product"})
  public void TC_05_Delete_Existing_Product() {
	  System.out.println("Delete_Existing_Produc");

  }
  
  
}
