
package com.sqa.mz.adactin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import com.sqa.mz.util.helpers.*;

/**
 * SearchPage //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Nepton, Jean-francois
 * @version 1.0.0
 * @since 1.0
 */
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
	private WebElement errorMesChekIn;

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

	public SearchPage chooseNumOfChildren(String numOfChildren) {
		Select selectNumOfChildren = new Select(this.childPerRoom);
		selectNumOfChildren.selectByValue(numOfChildren);
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

	public boolean hasWelcomeMsg() {
		// System.out.println("Driver:" + getDriver());
		return AutoBasics.isElementPresent(getDriver(), By.cssSelector("td.welcome_menu"));
	}
}
