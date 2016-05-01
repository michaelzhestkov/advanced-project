package com.sqa.mz.gmail;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BasicAutoTest {

	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	static WebDriver driver;

	static Logger logger = Logger.getLogger(BasicAutoTest.class);

	@BeforeClass(enabled = false, groups = "chrome")
	public static void setUpChrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		baseUrl = "https://mail.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = true, groups = "firefox")
	public static void setUpFirefox() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://mail.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = false, groups = "opera")
	public static void setUpOpera() throws Exception {
		System.setProperty("webdriver.chrome.driver", "drivers/operadriver");
		driver = new ChromeDriver();
		baseUrl = "https://mail.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = false, groups = "safari")
	public static void setUpSafari() throws Exception {
		driver = new SafariDriver();
		baseUrl = "https://mail.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}

	@AfterClass(alwaysRun = true)
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean acceptNextAlert = true;

	@DataProvider
	public Object[][] dp() {
		Object[][] data = { { "seleniummz@gmail.com", "seleniummz12345" },
				{ "seleniummzsqa@gmail.com", "seleniummz12345" }, };
		return data;
	}

	@Test(dataProvider = "dp")
	public void test(String username, String password) {
		System.out.println("Basic Test: U~" + username);
		this.driver.get(this.baseUrl + "/");
		// this.driver.findElement(By.id("gmail-sign-in")).click();
		this.driver.findElement(By.id("Email")).sendKeys(username);
		this.driver.findElement(By.id("next")).click();
		this.driver.findElement(By.id("Passwd")).sendKeys(password);
		this.driver.findElement(By.id("signIn")).click();
		System.out.println("User " + username + " is logged in");
		this.driver.findElement(By.cssSelector("span.gb_2a.gbii"));
		this.driver.findElement(By.id("gb_71"));
		System.out.println("User " + username + " is logged out");
		logger.info("My information");
		for (int i = 0; i < 1000; i++) {
			logger.debug("Debug message " + i);
			if (i % 12 == 0) {
				logger.fatal("Fatal message " + i);
			}

		}

	}

}
