/**
 *   File Name: Search.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.pageobjects;

import java.text.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Search {

	private WebDriver driver;
	private String invalidCheckInCheckOutRangeMessage = "Success: Proper message is present, Enter valid range";
	private String invalidCheckInMessage = "Success: Proper message is present";
	private Select locationSelect;
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

	public void enterCheckInCheckOutDays(int CheckInDateFromToday, int CheckOutDateFromToday) {
		String CheckInDateF = enterCheckIn(CheckInDateFromToday);
		String CheckOutDateF = enterCheckOut(CheckOutDateFromToday);

		this.driver.findElement(this.checkInDateField).clear();
		this.driver.findElement(this.checkInDateField).sendKeys(CheckInDateF);
		this.driver.findElement(this.checkOutDateField).clear();
		this.driver.findElement(this.checkOutDateField).sendKeys(CheckOutDateF);

	}

	private String enterCheckIn(int CheckInDateFromToday) {
		SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, CheckInDateFromToday);
		String CheckInDate = checkIn.format(c.getTime());
		System.out.println(CheckInDate);
		return CheckInDate;

	}

	private String enterCheckOut(int CheckOutDateFromToday) {
		SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, CheckOutDateFromToday);
		String CheckOutDate = checkOut.format(c.getTime());
		System.out.println(CheckOutDate);
		return CheckOutDate;

	}

	// public void selectOption(By locator, String value) {
	// Select fieldSelect = new Select(this.driver.findElement(locator));
	// System.out.println("Possible " + fieldSelect.getOptions());
	// fieldSelect.selectByValue(value);
	// }
	//
	// private void assertCheckOutCheckInValidRangeMessage() {
	// Assert.assertEquals("Check-In Date should be either Today or Later Date",
	// this.driver.findElement(this.invalidCheckInCheckOutRangeLocator).getText(),
	// this.invalidCheckInCheckOutRangeMessage);
	// System.out.println(this.invalidCheckInCheckOutRangeMessage);
	//
	// }
	//
	// private void assertCheckOutdateErrorMessage() {
	// Assert.assertEquals("Check-In Date shall be before than Check-Out Date",
	// this.driver.findElement(this.checkInMessageLocator).getText(),
	// this.invalidCheckInMessage);
	// System.out.println(this.invalidCheckInMessage);
	// }
	//
	// // private void enterCheckIn() {
	// // SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
	// // Calendar c = Calendar.getInstance();
	// // c.setTime(new Date()); // Now use today date.
	// // String dayToday = checkIn.format(c.getTime());
	// // System.out.println(dayToday);
	// // driver.findElement(this.checkInDateField).clear();
	// // driver.findElement(this.checkInDateField).sendKeys(dayToday);
	// //
	// // }
	//
	// private void clickSignInBtn() {
	// this.driver.findElement(this.loginButtonLocator).click();
	// Assert.assertTrue(isElementPresent(By.id("username_show")),
	// this.succesfullLoginMessage);
	// System.out.println(this.succesfullLoginMessage);
	// }
	//
	// // private void enterCheckOut() {
	// // SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
	// // Calendar c = Calendar.getInstance();
	// // c.setTime(new Date()); // Now use today date.
	// // String dayToday = checkOut.format(c.getTime());
	// // System.out.println(dayToday);
	// // driver.findElement(this.checkOutDateField).clear();
	// // driver.findElement(this.checkOutDateField).sendKeys(dayToday);
	// // // OR
	// // // enterCheckOut(0);
	// // }
	//
	// private int enterCheckIn(int daysFromToday) {
	// SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
	// Calendar c = Calendar.getInstance();
	// c.setTime(new Date()); // Now use today date.
	// c.add(Calendar.DATE, daysFromToday);
	// String dayToday = checkIn.format(c.getTime());
	// System.out.println(dayToday);
	// this.driver.findElement(this.checkInDateField).clear();
	// this.driver.findElement(this.checkInDateField).sendKeys(dayToday);
	// return daysFromToday;
	//
	// }
	//
	// private String enterCheckOut(int daysFromToday) {
	// SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
	// Calendar c = Calendar.getInstance();
	// c.setTime(new Date()); // Now use today date.
	// c.add(Calendar.DATE, daysFromToday);
	// String dayToday = checkOut.format(c.getTime());
	// System.out.println(dayToday);
	// this.driver.findElement(this.checkOutDateField).clear();
	// this.driver.findElement(this.checkOutDateField).sendKeys(dayToday);
	// return dayToday;
	//
	// }
	//
	// private void enterCredentialsAndLogIn(String username, String password) {
	// this.driver.findElement(this.usernameField).sendKeys(username);
	// this.driver.findElement(this.passwordField).sendKeys(password);
	//
	// }
	//
	// private boolean isElementPresent(By by) {
	// try {
	// this.driver.findElement(by);
	// return true;
	// } catch (NoSuchElementException e) {
	// return false;
	// }
	// }
	//
	// private void selectElementsOnSearchPage() {
	// selectOption(this.locationField, "Sydney");
	// selectOption(this.hotelsField, "Hotel Creek");
	// selectOption(this.roomTypeField, "Standard");
	// selectOption(this.numberOfRoomsField, "1");
	//
	// }
	//
}
