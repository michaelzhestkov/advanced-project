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
import org.testng.*;
import org.testng.annotations.*;

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

	private String message = "Successful login";

	String actualSignIn;

	By checkInDateField = By.cssSelector("#datepick_in");
	By checkInMessageLocator = By.cssSelector("#checkin_span");
	By checkOutDateField = By.cssSelector("#datepick_out");
	By hotelsField = By.cssSelector("#hotels > option:nth-of-type(2)");
	By locationField = By.cssSelector("#location > option:nth-of-type(2)");
	By loginButtonLocator = By.cssSelector("#login");
	By numberOfRoomsField = By.cssSelector("#room_nos > option:nth-of-type(2)");
	By passwordField = By.cssSelector("#password");
	By roomTypeField = By.cssSelector("#room_type > option:nth-of-type(2)");
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
		System.out.println("Check-Out Test: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL2"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		selectElementsOnSearchPage();
		enterInvalidCheckIn();

	}

	private void clickSignInBtn() {
		driver.findElement(this.loginButtonLocator).click();
		Assert.assertTrue(isElementPresent(By.id("username_show")), this.message);
		System.out.println(this.message);
	}

	private void enterCredentialsAndLogIn(String username, String password) {
		driver.findElement(this.usernameField).sendKeys(username);
		driver.findElement(this.passwordField).sendKeys(password);

	}

	private void enterInvalidCheckIn() {
		SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String dayToday = checkOut.format(c1.getTime());
		c.add(Calendar.DATE, 7); // Adding 7 days
		String todayPlusSeven = checkIn.format(c.getTime());
		System.out.println(dayToday);
		System.out.println(todayPlusSeven);

		driver.findElement(this.checkInDateField).clear();
		driver.findElement(this.checkInDateField).sendKeys(todayPlusSeven);

		driver.findElement(this.checkOutDateField).clear();
		driver.findElement(this.checkOutDateField).sendKeys(dayToday);

		driver.findElement(this.submitButtonLocator).click();

		Assert.assertEquals("Check-In Date shall be before than Check-Out Date",
				driver.findElement(this.checkInMessageLocator).getText());

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
		driver.findElement(this.locationField).click();
		driver.findElement(this.hotelsField).click();
		driver.findElement(this.roomTypeField).click();
		driver.findElement(this.numberOfRoomsField).click();

	}

}
