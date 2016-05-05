
package com.sqa.mz.adactin;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

public class TC101 {

	private static String baseURL = "http://adactin.com/HotelAppBuild2";

	private static WebDriver driver;

	@BeforeClass
	public void setUp() {
		this.driver = new FirefoxDriver();
		this.driver.get(this.baseURL);
	}

	@Test
	public void testLogin() {
		Assert.assertTrue(new LoginPage(this.driver).enterUsername("michaeltest").enterPassword("qqqqqq").login()
				.hasWelcomeMsg());
	}
}
