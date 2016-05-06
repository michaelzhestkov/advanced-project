
package com.sqa.mz.adactin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import com.sqa.mz.util.helpers.*;

public class SearchPage extends DefaultPage {

	@FindBy(id = "adult_room")
	private WebElement adultsPerRoom;

	@FindBy(id = "datepick_in")
	private WebElement checkIn;

	@FindBy(id = "datepick_out")
	private WebElement checkOut;

	@FindBy(id = "child_room")
	private WebElement childPerRoom;

	@FindBy(id = "checkout_span")
	private WebElement errorMEsCheckOut;

	@FindBy(id = "checkin_span")
	private WebElement errorMesCheckIn;

	@FindBy(id = "hotels")
	private WebElement hotels;

	@FindBy(id = "location")
	private WebElement location;

	@FindBy(id = "login")
	private WebElement loginBtn;

	@FindBy(id = "room_nos")
	private WebElement numOfRooms;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "Reset")
	private WebElement resetBtn;

	@FindBy(id = "room_type")
	private WebElement roomType;

	@FindBy(id = "Submit")
	private WebElement submitBtn;

	@FindBy(id = "username_show")
	private WebElement successLogin;

	@FindBy(id = "username")
	private WebElement username;

	public SearchPage() {
		PageFactory.initElements(getDriver(), SearchPage.class);
	}

	public SearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public SearchPage chooseCheckInDate(String date) {
		this.checkIn.clear();
		this.checkIn.sendKeys(date);
		return this;
	}

	public SearchPage chooseCheckOutDate(String date) {
		this.checkOut.clear();
		this.checkOut.sendKeys(date);
		return this;
	}

	public SearchPage chooseHotel(String hotelChoice) {
		Select selectHotels = new Select(this.hotels);
		selectHotels.selectByValue(hotelChoice);
		return this;
	}

	public SearchPage chooseLocation(String locationChoice) {
		Select selectLocation = new Select(this.location);
		selectLocation.selectByValue(locationChoice);
		return this;
	}

	public SearchPage chooseNumAdultsInRoom(String numInRoom) {
		Select selectAdultsPerRoom = new Select(this.adultsPerRoom);
		selectAdultsPerRoom.selectByValue(numInRoom);
		return this;
	}

	public SearchPage chooseNumChildrenInRoom(String numInRoom) {
		Select selectChildrenPerRoom = new Select(this.childPerRoom);
		selectChildrenPerRoom.selectByValue(numInRoom);
		return this;
	}

	public SearchPage chooseNumOfRooms(String numRooms) {
		Select selectNumRooms = new Select(this.numOfRooms);
		selectNumRooms.selectByVisibleText(numRooms);
		return this;
	}

	public SearchPage chooseRoomType(String roomType) {
		Select selectRoomType = new Select(this.roomType);
		selectRoomType.selectByValue(roomType);
		return this;
	}

	public String getCheckInErrorMessage() {
		if (hasCheckInErrorMessage()) {
			return this.errorMesCheckIn.getText();
		} else {
			return "";
		}
	}

	public String getCheckOutErrorMessage() {
		if (hasCheckOutErrorMessage()) {
			return this.errorMEsCheckOut.getText();
		} else {
			return "";
		}
	}

	public boolean hasCheckInErrorMessage() {
		boolean hasMessage = false;
		hasMessage = AutoBasics.isElementPresent(getDriver(), By.id("checkin_span"));
		if (hasMessage) {
			if (this.errorMesCheckIn.getText().length() > 1) {
				hasMessage = true;
				System.out.println("Message: " + this.errorMesCheckIn.getText());
			}
		}
		return hasMessage;
	}

	public boolean hasCheckOutErrorMessage() {
		boolean hasMessage = false;
		hasMessage = AutoBasics.isElementPresent(getDriver(), By.id("checkout_span"));
		if (hasMessage) {
			if (this.errorMesCheckIn.getText().length() > 1) {
				hasMessage = true;
				System.out.println("Message: " + this.errorMesCheckIn.getText());
			}
		}
		return hasMessage;
	}

	public boolean hasWelcomeMsg() {
		// System.out.println("Driver:" + getDriver());
		return AutoBasics.isElementPresent(getDriver(), By.cssSelector("td.welcome_menu"));
	}

	public SearchPage reset() {
		this.resetBtn.click();
		return this;

	}

	public SearchPage submit() {
		this.submitBtn.click();
		return this;

	}
}
