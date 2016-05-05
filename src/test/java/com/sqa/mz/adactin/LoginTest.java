package com.sqa.mz.adactin;

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

public class LoginTest {

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
		driver.get(devProps.getProperty("baseURL2"));
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

	private String succesfullLoginMessage = "Success: Login successfully";
	private String unsuccesfullLoginMessage = "Success: Login failed with invalid credentials";

	String actualSignIn;

	By loginButtonLocator = By.cssSelector("#login");
	By passwordLocator = By.cssSelector("#password");
	By successfulLoginLocator = By.cssSelector("#username_show");
	By usernameLocator = By.cssSelector("#username");

	@BeforeClass
	public void loadProperties() {
		sharedMapUI = ConProperties.loadProperties(this.devPropsLocation);
		devProps = ConProperties.loadProperties(this.devPropsLocation);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	@Test(dataProvider = "UserAccountInfo", priority = 1, enabled = true)
	public void testLogin(String username, String password) {
		System.out.println("Basic Test: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL2"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		successfulLogin();

	}

	@Test(dataProvider = "UserAccountInfo", priority = 2, enabled = true)
	public void testLogin2(String username, String password) {
		System.out.println("Basic Test 2: Username-" + username + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL1"));
		enterCredentialsAndLogIn(username, password);
		clickSignInBtn();
		successfulLogin();

	}

	@Test(dataProvider = "UserAccountInfo", priority = 3, enabled = true)
	public void testLoginInvalidUsername(String username, String password) {
		System.out.println(
				"Basic Test 2: Username-" + devProps.getProperty("invalidUsaername") + "/Password-" + password);
		driver.get(devProps.getProperty("baseURL1"));
		enterCredentialsAndLogIn(devProps.getProperty("invalidUsaername"), password);
		clickSignInBtn();
		unsuccessfulLogin();

	}

	private void clickSignInBtn() {
		driver.findElement(this.loginButtonLocator).click();

	}

	private void enterCredentialsAndLogIn(String username, String password) {
		driver.findElement(this.usernameLocator).sendKeys(username);
		driver.findElement(this.passwordLocator).sendKeys(password);

	}

	private boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void successfulLogin() {
		Assert.assertTrue(isElementPresent(By.id("username_show")), this.succesfullLoginMessage);
		System.out.println(this.succesfullLoginMessage);

	}

	private void unsuccessfulLogin() {
		Assert.assertTrue(isElementPresent(By.cssSelector(".auth_error>b")), this.unsuccesfullLoginMessage);
		System.out.println(this.unsuccesfullLoginMessage);

	}

}
