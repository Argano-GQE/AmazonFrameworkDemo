package com.Argano.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.Argano.driver.DriverManager;
import com.Argano.enums.JSAction;
import com.Argano.enums.WaitStrategy;


public class LoginPage extends ApplicationMethods{

	private final By bttnSign = By.xpath("(//span[text()='Sign in'])[1]");
	private final By menuSignIn = By.xpath("//a[@id='nav-link-accountList']");
	private final By txtMobileNumber = By.id("ap_email");
	private final By txtLoginPswd = By.id("ap_password");
	private final By bttnSubmit = By.id("signInSubmit");
	private final By bttnContinue = By.id("continue");
	private final By errorMobileNumber = By.cssSelector("div#auth-email-missing-alert div.a-alert-content");
	private final By welcomeTxt = By.id("nav-link-accountList-nav-line-1");
	
	
	public LoginPage navigateToSignIn()
	{
		Actions action = new Actions(DriverManager.getDriver());
		WebElement menuSignInElement = DriverManager.getDriver().findElement(menuSignIn);
		action.moveToElement(menuSignInElement).build().perform();
		jsOperation(JSAction.CLICK ,bttnSign);
		//click(bttnSign, WaitStrategy.CLICKABLE, 15, "Clicked on Sign In button");
		return this;
		
	}

	public String getWelcomeTxt()
	{
		return getText(welcomeTxt, 5);
	}
	public LoginPage enterMobileNumber(String mobileNumber) {

		sendKeys(txtMobileNumber, mobileNumber, WaitStrategy.VISIBLE, 3);
		
		return this;
	}

	public LoginPage enterPassword(String password) {
		sendKeys(txtLoginPswd, password, WaitStrategy.VISIBLE, 3);
	
		return this;
	}
	
	public LoginPage clickContinuebutton()
	{
		click(bttnContinue, WaitStrategy.CLICKABLE, 3);
		return this;
		
	}
	public LoginPage clickSubmitbutton()
	{
		click(bttnSubmit, WaitStrategy.CLICKABLE, 2, "Entered credentails and clicked on submit button");
		return this;
		
	}
	
	public String  getErrorText()
	{
		return getText(errorMobileNumber, 3);
	}
	public HomePage loginToAmazon(String mobileNumber, String password)
	{
		navigateToSignIn().enterMobileNumber(mobileNumber).clickContinuebutton().enterPassword(password).clickSubmitbutton();
 return new HomePage();
}

	public LoginPage loginwithoutMobileNumber()
	{
		navigateToSignIn().clickContinuebutton();
		return this;
	}
}
