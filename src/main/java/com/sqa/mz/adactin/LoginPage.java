
package com.sqa.mz.adactin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage extends DefaultPage {

	@FindBy(id = "login")
	private WebElement loginBtn;

	@FindBy(id = "password")
	private WebElement passwordFld;

	@FindBy(id = "username")
	private WebElement usernameFld;

	@FindBy(how = How.CSS, using = "td.welcome_menu")
	private WebElement welcomeElement;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public LoginPage enterPassword(String password) {
		this.passwordFld.sendKeys(password);
		return this;
	}

	public LoginPage enterUsername(String username) {
		this.usernameFld.sendKeys(username);
		return this;
	}

	public SearchPage login() {
		this.loginBtn.click();
		return new SearchPage();
	}
}
