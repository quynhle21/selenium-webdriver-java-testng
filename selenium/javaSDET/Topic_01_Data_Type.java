package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		
		// khai báo trước - khởi tạo sau
		String fullName;
		fullName = "Automation test";
		
		// Khai báo + khởi tạo cùng lúc
		String addressName = "123 Lê Lợi";
		
		// Kiểu dữ liệu nguyên thủy: primitive type
		// ký tự: char
		
		char c='A'; // duy nhất ký tự thì dùng nháy đơn
		
		//số nguyên: byte, short, int, long (tăng dần, dùng nhiều nhất là int và long)

		byte sNumber = 127;
		int bNumber = 3245;
		long cNumber = 3456657;
		
		//số thực: float double
		float fNumber = 15.8f;
		float dNumber = (float) 15.8d;
		
		//logic: boolean
		boolean status = true;
		status = false; // cập nhập lại giá trị mới
		
		// Kiểu dữ liệu tham chiếu: reference type
		// Chuỗi ký tự: String
		
		String cityName = "Hanoi";
		System.out.println(cityName);

		// Class
		FirefoxDriver driver;
		
		// Interface
		WebDriver fdriver;
		
		// Collection: set list queue
		
		// Object
		Object number;
		
		//Array
		String[] student = {"Nguyen Van A", "Nguyen Van B"};
		
		Integer[] point = {15,30,29};
		s
		

	}

}
