/**
 *   File Name: Login.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.pageobjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.testng.*;

public class Login {

	private WebDriver driver;

	By loginButtonIsDisplayed = By.cssSelector("#login");
	By loginButtonLocator = By.cssSelector("#login");
	By passwordField = By.cssSelector("#password");
	By successfulLoginLocator = By.cssSelector("#username_show");
	By usernameField = By.cssSelector("#username");

	public void clickSignInBtn() {
		this.driver.findElement(this.loginButtonLocator).click();
		Assert.assertTrue(isElementPresent(this.successfulLoginLocator), "Successful login");
		System.out.println("Successful login");
	}

	public void enterCredentialsAndLogIn(String username, String password) {
		this.driver.findElement(this.usernameField).sendKeys(username);
		this.driver.findElement(this.passwordField).sendKeys(password);
		this.driver.findElement(this.loginButtonLocator).click();

	}

	private boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void logIn() {

	}
}
