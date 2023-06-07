package com.Argano.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.Argano.enums.WaitStrategy;
import com.Argano.reports.ExtentLogger;

public class HomePage extends ApplicationMethods{
	
	private By txtSearchBox = By.id("twotabsearchtextbox");
	private By iconSearch = By.id("nav-search-submit-button");
	private By collectionSearchResult = By.xpath("//div[@data-component-type ='s-search-result']");
	private By firstSearchResult = By.xpath("(//div[@data-component-type ='s-search-result'])[1]//h2/a/span");
	private By bttnAddToCart = By.id("add-to-cart-button");
	private By cartItemsCount = By.id("nav-cart-count");
	
	public HomePage enterSearchkeyword(String serachkeyword){
		sendKeys(txtSearchBox, serachkeyword, WaitStrategy.VISIBLE, 3);	
		return this;	
	}

	public int getCartItemsCount()
	{
		 
			int cartItemCount =	Integer.parseInt(getText(cartItemsCount, 3));
			ExtentLogger.info(cartItemCount + " items found in cart");
			return cartItemCount;
	/*	List<WebElement> cartItems =getElements(collectionCartItems);
		ExtentLogger.info(cartItems.size() + " items found in cart");
		return cartItems.size();
	*/
		}
	
	public HomePage clickiconSearch()
	{
		click(iconSearch, WaitStrategy.CLICKABLE, 2);
		return this;
	}
	public CartPage clickAddToCart()
	{
		click(bttnAddToCart, WaitStrategy.CLICKABLE, 6, "Added to Cart");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CartPage();
	}
	
	public HomePage SearchForItem(String serachkeyword)
	{
		enterSearchkeyword(serachkeyword).clickiconSearch();
		ExtentLogger.info("Searching for item : " +serachkeyword);
		return this;
	}
	
	public int getSearchResultsCount()
	{
		List<WebElement> searchResults =getElements(collectionSearchResult);
		ExtentLogger.info(searchResults.size() + "  Search result items found");
		return searchResults.size();
	}

	
	public HomePage clickFirstSearchResult()
	{
		click(firstSearchResult, WaitStrategy.CLICKABLE, 2);
		return this;
	}

}
