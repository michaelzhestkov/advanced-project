
package com.sqa.mz.adactin;

import java.text.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

public class TC102 {

	private static String baseURL = "http://adactin.com/HotelAppBuild2";

	private static WebDriver driver;

	@DataProvider
	public static Object[][] tc102data() {
		return new Object[][] {
				{ "michaeltest", "qqqqqq", "Sydney", "Hotel Creek", "Standard", "1 - One", "01/06/2016", "2", "4",
						true },
				{ "michaeltest", "qqqqqq", "Paris", "Hotel Creek", "Deluxe", "1 - One", "02/06/2018", "2", "4", true }

		};
	}

	public SearchPage searchPage;

	@BeforeClass
	public void setUp() {
		this.driver = new FirefoxDriver();
		this.driver.get(this.baseURL);
	}

	@Test(dataProvider = "tc102data")
	public void testCheckInOut(String username, String password, String location, String hotel, String roomType,
			String numRooms, String checkIn, String adultsInRoom, String childrenInRoom, boolean expectedResults)
			throws ParseException {
		boolean actualResults;
		System.out.println("TC-102");
		// Eval CheckOut Date:
		String checkOut = DefaultPage.changeDate(checkIn, 7);
		System.out.println("Check-in: " + checkIn + "Check-out: " + checkOut);
		// Login:
		if (this.searchPage == null) {
			this.searchPage = new LoginPage(driver).enterUsername(username).enterPassword(password).login();
		} else {
			this.searchPage.getDriver().get(DefaultPage.getBaseURL() + "/SearchHotel.php");
		}
		System.out.println("Enter Information: ");
		// Enter Information
		this.searchPage.chooseLocation(location).chooseHotel(hotel).chooseRoomType(roomType).chooseNumOfRooms(numRooms)
				.chooseCheckInDate(checkIn).chooseCheckOutDate(checkOut).chooseNumAdultsInRoom(adultsInRoom)
				.chooseNumChildrenInRoom(childrenInRoom)
				// Submit
				.submit();
		// Checkout if actual is same as expected results
		actualResults = !this.searchPage.hasCheckInErrorMessage();
		Assert.assertEquals(actualResults, expectedResults);
	}
}