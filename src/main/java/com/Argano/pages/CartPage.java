package com.Argano.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Argano.driver.DriverManager;
import com.Argano.enums.JSAction;
import com.Argano.enums.WaitStrategy;
import com.Argano.factory.ExplicitWaitFactory;
import com.Argano.reports.ExtentLogger;

public class CartPage extends ApplicationMethods{
	
	private By bttnProceedToCheckout = By.xpath("//span[contains(text(),'Proceed to checkout')]");
	private By iconCart = By.id("nav-cart-text-container");
	private By collectionCartItems = By.xpath("//div[@data-name ='Active Items']//input[@value='Delete']");
	private By collectionCartItemTitle = By.xpath("//div[@data-name ='Active Items']//span[@class='a-truncate-full a-offscreen']");
	
	
	
	public CartPage clickProceedToCheckoutbutton()
	{
		
		ExplicitWaitFactory.performExplicitWait(bttnProceedToCheckout, WaitStrategy.VISIBLE, 15);
		jsOperation(JSAction.CLICK ,bttnProceedToCheckout);
		return this;
		
}
	public CartPage clickProceedToCartbutton()
	{
		DriverManager.getDriver().navigate().refresh();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExplicitWaitFactory.performExplicitWait(iconCart, WaitStrategy.VISIBLE, 5);
		jsOperation(JSAction.CLICK ,iconCart);
		return this;
		
}
	

}
