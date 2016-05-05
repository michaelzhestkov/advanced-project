/**
 *   File Name: AutoBasics.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.util.helpers;

import org.openqa.selenium.*;

public class AutoBasics {
	public static WebDriver driver;

	public static boolean isElementPresent(By locator) throws AutoBasicsNotInitializedException {
		if (driver == null) {
			throw new AutoBasicsNotInitializedException();
		}
		try {

			driver.findElement(locator);
		} catch (Exception e) {

		}
		return false;
	}

	public static boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public AutoBasics(WebDriver driver) {
		AutoBasics.driver = driver;
	}

}
