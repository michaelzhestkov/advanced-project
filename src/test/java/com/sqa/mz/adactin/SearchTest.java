/**
 *   File Name: SearchTest.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.adactin;

import java.text.*;
import java.util.*;
import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.opera.*;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import com.sqa.mz.pageobjects.*;
import com.sqa.mz.util.helpers.*;

public class SearchTest {

	private static Properties devProps;

	private static String devPropsLocation = "src/main/resources/dev.properties";

	private static Properties sharedMapUI;

	private static String sharedMapUILocation = "src/main/resources/shared-map-ui.properties";

	static WebDriver driver;

	@DataProvider(name = "UserAccountInfo")
	public static Object[][] getLoginData() {
		// Create a 2D object with only one level (for one test)
		Object[][] data = new Object[1][];
		{
			// Create test with two elements for username and password
			Object[] test = { devProps.get("username"), devProps.get("password") };
			// Set the array of parameters to be the first element and only for
			// the data
			data[0] = test;
			return data;
		}

	}

	@BeforeClass(enabled = false, groups = "chrome")
	public static void setupChrome() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://gmail.com");
		Thread.sleep(1000);
	}

	@BeforeClass(enabled = true, groups = "firefox", dependsOnMethods = "loadProperties")
	public static void setupFirefox() throws InterruptedException {
		driver = new FirefoxDriver();
		// driver.get(devProps.getProperty("baseURL2"));
		// Thread.sleep(1000);
	}

	@BeforeClass(enabled = false, groups = "chrome")
	public static void setupOpera() throws InterruptedException {
		System.setProperty("webdriver.opera.driver", "drivers/operadriver");
		driver = new OperaDriver();
		driver.get("http://gmail.com");
		Thread.sleep(1000);
	}

	@BeforeClass(enabled = false, groups = "safari")
	public static void setupSafari() throws InterruptedException {
		driver = new SafariDriver();
		driver.get("http://gmail.com");
		Thread.sleep(1000);
	}

	private String invalidCheckInCheckOutRangeMessage = "Success: Proper message is present, Enter valid range";

	private String invalidCheckInMessage = "Success: Proper message is present";

	private Select locationSelect;

	private Search search;

	private String succesfullLoginMessage = "Success: Login successfully";
	By checkInDateField = By.cssSelector("#datepick_in");
	By checkInMessageLocator = By.cssSelector("#checkin_span");
	By checkOutDateField = By.cssSelector("#datepick_out");
	By hotelsField = By.cssSelector("#hotels");
	By invalidCheckInCheckOutRangeLocator = By.cssSelector("#checkin_span");
	By locationField = By.cssSelector("#location");
	By loginButtonLocator = By.cssSelector("#login");
	By numberOfRoomsField = By.cssSelector("#room_nos");
	By passwordField = By.cssSelector("#password");
	By roomTypeField = By.cssSelector("#room_type");
	By submitButtonLocator = By.cssSelector("#Submit");
	By successfulLoginLocator = By.cssSelector("#username_show");

	By usernameField = By.cssSelector("#username");

	@BeforeClass
	public void loadProperties() {
		sharedMapUI = ConProperties.loadProperties(this.devPropsLocation);
		devProps = ConProperties.loadProperties(this.devPropsLocation);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	@Test(dataProvider = "UserAccountInfo", priority = 1)
	public void testCheckOut(String username, String password) {
		System.out.println("Check-Out Test with baseURL2: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL2"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		selectElementsOnSearchPage();
		enterCheckIn(7);
		enterCheckOut(0);
		driver.findElement(this.submitButtonLocator).click();
		assertCheckOutdateErrorMessage();

	}

	@Test(dataProvider = "UserAccountInfo", priority = 2)
	public void testCheckOut2(String username, String password) {
		System.out.println("Check-Out Test baseURL1: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL1"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		selectElementsOnSearchPage();
		enterCheckIn(7);
		enterCheckOut(0);
		driver.findElement(this.submitButtonLocator).click();
		assertCheckOutdateErrorMessage();

	}

	@Test(dataProvider = "UserAccountInfo", priority = 3)
	public void testCheckOutCheckInInvalidRange(String username, String password) {
		System.out.println("Check-Out Test with baseURL2: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL2"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		selectElementsOnSearchPage();
		// enterCheckIn(-5);
		// enterCheckOut(-3);
		// new Search().enterCheckInCheckOutDays(-3, -5);
		this.search.enterCheckInCheckOutDays(-3, -5);

		driver.findElement(this.submitButtonLocator).click();
		assertCheckOutCheckInValidRangeMessage();

	}

	private void assertCheckOutCheckInValidRangeMessage() {
		Assert.assertEquals("Check-In Date should be either Today or Later Date",
				driver.findElement(this.invalidCheckInCheckOutRangeLocator).getText(),
				this.invalidCheckInCheckOutRangeMessage);
		System.out.println(this.invalidCheckInCheckOutRangeMessage);

	}

	private void assertCheckOutdateErrorMessage() {
		Assert.assertEquals("Check-In Date shall be before than Check-Out Date",
				driver.findElement(this.checkInMessageLocator).getText(), this.invalidCheckInMessage);
		System.out.println(this.invalidCheckInMessage);
	}

	private void clickSignInBtn() {
		driver.findElement(this.loginButtonLocator).click();
		Assert.assertTrue(isElementPresent(By.id("username_show")), this.succesfullLoginMessage);
		System.out.println(this.succesfullLoginMessage);
	}

	// private void enterCheckIn() {
	// SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
	// Calendar c = Calendar.getInstance();
	// c.setTime(new Date()); // Now use today date.
	// String dayToday = checkIn.format(c.getTime());
	// System.out.println(dayToday);
	// driver.findElement(this.checkInDateField).clear();
	// driver.findElement(this.checkInDateField).sendKeys(dayToday);
	//
	// }

	private int enterCheckIn(int daysFromToday) {
		SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, daysFromToday);
		String dayToday = checkIn.format(c.getTime());
		System.out.println(dayToday);
		driver.findElement(this.checkInDateField).clear();
		driver.findElement(this.checkInDateField).sendKeys(dayToday);
		return daysFromToday;

	}

	// private void enterCheckOut() {
	// SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
	// Calendar c = Calendar.getInstance();
	// c.setTime(new Date()); // Now use today date.
	// String dayToday = checkOut.format(c.getTime());
	// System.out.println(dayToday);
	// driver.findElement(this.checkOutDateField).clear();
	// driver.findElement(this.checkOutDateField).sendKeys(dayToday);
	// // OR
	// // enterCheckOut(0);
	// }

	private String enterCheckOut(int daysFromToday) {
		SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, daysFromToday);
		String dayToday = checkOut.format(c.getTime());
		System.out.println(dayToday);
		driver.findElement(this.checkOutDateField).clear();
		driver.findElement(this.checkOutDateField).sendKeys(dayToday);
		return dayToday;

	}

	private void enterCredentialsAndLogIn(String username, String password) {
		driver.findElement(this.usernameField).sendKeys(username);
		driver.findElement(this.passwordField).sendKeys(password);

	}

	private boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void selectElementsOnSearchPage() {
		selectOption(this.locationField, "Sydney");
		selectOption(this.hotelsField, "Hotel Creek");
		selectOption(this.roomTypeField, "Standard");
		selectOption(this.numberOfRoomsField, "1");

	}

	private void selectOption(By locator, String value) {
		Select fieldSelect = new Select(driver.findElement(locator));
		System.out.println("Possible " + fieldSelect.getOptions());
		fieldSelect.selectByValue(value);
	}

}
