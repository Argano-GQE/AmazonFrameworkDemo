package com.Argano.pages;

import org.openqa.selenium.By;

import com.Argano.enums.WaitStrategy;
import com.Argano.reports.ExtentLogger;

public class CheckOutPage extends ApplicationMethods{
	
	private By bttnAddNewAddress = By.id("add-new-address-popover-link");
	private By txtUserName = By.id("address-ui-widgets-enterAddressFullName");
	private By txtpostalCode = By.id("address-ui-widgets-enterAddressPostalCode");
	private By txtMobileNumber = By.id("address-ui-widgets-enterAddressPhoneNumber");
	private By txtAddress = By.id("address-ui-widgets-enterAddressLine1");
	private By txtCity = By.id("address-ui-widgets-enterAddressCity");
	
	public CheckOutPage clickAddNewAddressbutton()
	{
		click(bttnAddNewAddress, WaitStrategy.CLICKABLE, 0, "Clicked on Add New Address Button");
		return this;
	}
	
	public CheckOutPage enterUserName(String username) {
		ExtentLogger.info("Entering details in shipping Adress section");
		sendKeys(txtUserName, username, WaitStrategy.VISIBLE, 5, "Username field ");
		return this;
	}
	
	public CheckOutPage enterMobileNumber(String number) {
		sendKeys(txtMobileNumber, number, WaitStrategy.VISIBLE, 3);
		return this;
	}
	
	public CheckOutPage enterPostalCode(String number) {
		clear(txtpostalCode,WaitStrategy.VISIBLE, 3);
		sendKeys(txtpostalCode, number, WaitStrategy.VISIBLE, 2);
		return this;
	}
	public CheckOutPage enterAddress(String address) {
		sendKeys(txtAddress, address, WaitStrategy.VISIBLE, 3);
		return this;
	}
	
	public CheckOutPage enterCity(String city) {
		clear(txtCity,WaitStrategy.VISIBLE, 3);
		sendKeys(txtCity, city, WaitStrategy.VISIBLE, 3);
		return this;
	}
	
}
