
package com.sqa.mz.adactin;

import java.text.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.*;
import org.testng.log4testng.*;

import com.sqa.mz.adactin.exceptions.*;

public class DefaultPage extends PageFactory {

	private static String baseURL = "http://adactin.com/HotelAppBuild2";

	private static WebDriver driver;

	private static Logger logger;

	public static String changeDate(String date, int days) throws ParseException {
		String newDate;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat df = DateFormat.getDateInstance();
		Calendar calendar = Calendar.getInstance();
		Date inputDate = dateFormat.parse(date);
		calendar.setTime(inputDate);
		calendar.add(Calendar.DATE, days);
		newDate = dateFormat.format(calendar.getTime());
		return newDate;
	}

	/**
	 * @return the baseURL
	 */
	public static String getBaseURL() {
		return baseURL;
	}

	/**
	 * @param baseURL
	 *            the baseURL to set
	 */
	public static void setBaseURL(String baseURL) {
		DefaultPage.baseURL = baseURL;
	}

	public DefaultPage() {
		// try {
		// setDriver();
		// } catch (DriverAlreadyInitializedException e) {
		// getLogger().warn(
		// "Driver may have been initialized already, does not need
		// reinitilaization from Core Constructor.");
		// }
		PageFactory.initElements(getDriver(), this);
	}

	public DefaultPage(WebDriver driver) {
		setDriver(driver);
		PageFactory.initElements(getDriver(), this);
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		if (DefaultPage.driver == null) {
			try {
				throw new DriverNotInitializedException();
			} catch (DriverNotInitializedException e) {
				getLogger().warn("Driver was not initialized, setting Firefox as default WebDriver");
				DefaultPage.driver = new FirefoxDriver();
			}
		}
		return DefaultPage.driver;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		if (logger == null) {
			logger = logger.getLogger(this.getClass());
		}
		return logger;
	}

	/*
	 * @param driver the driver to set
	 *
	 * @throws DriverAlreadyInitializedException
	 */
	public void setDriver() throws DriverAlreadyInitializedException {
		if (DefaultPage.driver == null) {
			DefaultPage.driver = new FirefoxDriver();
			getLogger().warn("Using default Firefox Driver implementation");
		} else {
			throw new DriverAlreadyInitializedException();
		}
	}

	/**
	 * @param driver
	 *            the driver to set
	 * @throws DriverAlreadyInitializedException
	 */
	public void setDriver(WebDriver driver) {
		if (DefaultPage.driver == null) {
			DefaultPage.driver = driver;
		}
	}
}
