
package com.sqa.mz.adactin;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class TC102 {

	private static String baseURL = "http://adactin.com/HotelAppBuild2";

	private static WebDriver driver;

	@BeforeClass
	public void setUp() {
		this.driver = new FirefoxDriver();
		this.driver.get(this.baseURL);
	}

	@Test
	public void testCheckInOut() {
		System.out.println("Test");
		SearchPage searchPage = new LoginPage(driver).enterUsername("michaeltest").enterPassword("qqqqqq").login();
		searchPage = searchPage.chooseLocation("Sydney").chooseHotel("Hotel Creek").chooseRoomType("Standard")
				.chooseNumOfRooms("1 - One");
	}
}
