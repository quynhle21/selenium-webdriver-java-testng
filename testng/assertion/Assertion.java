package assertion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {

	@Test
	public void TC_01() {
		// boolean: isDisplayed/ isEnabled/ isSelected/ isMultiple
		// Assert.assertTrue/False: tham số nhận vào là boolean
		Assert.assertTrue(isTextboxDisplayed());
		
		//int/String/ float
		// Assert.assertEquals: mong đợi và thực tế như nhau
		
		Assert.assertEquals(isTextboxDisplayed(), "Done");
		
		
	}
	public boolean isTextboxDisplayed() {
		return false;
	}
	
	public String getSuccessMesage() {
		return "Done";
		
	}
	
}
