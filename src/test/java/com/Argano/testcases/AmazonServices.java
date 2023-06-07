package com.Argano.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Argano.pages.CartPage;
import com.Argano.pages.HomePage;
import com.Argano.pages.LoginPage;
import com.Argano.reports.ExtentLogger;
import com.github.javafaker.Faker;


public class AmazonServices extends BaseTest{
	
	LoginPage loginpage;
	HomePage homepage;
	CartPage cartpage;
	Faker faker = new Faker();
	
	@Test
	public void SearchProductAndAddtoCart(Map<String, String> data) {
		ExtentLogger.info("Executing in browser : " + data.get("Browser"));

		loginpage = new LoginPage();
		homepage = new HomePage();
		homepage =loginpage.loginToAmazon(data.get("MobileNumber"), data.get("Password"));
		Assert.assertFalse(loginpage.getWelcomeTxt().contains("sign in"));
		homepage.SearchForItem(data.get("SearchKeyword"));
		Assert.assertTrue(homepage.getSearchResultsCount()>=1);
		homepage.clickFirstSearchResult().navigateToOtherWindow();
		int cartitemsCount = homepage.getCartItemsCount();
		cartpage =homepage.clickAddToCart();
		cartpage.clickProceedToCartbutton();
		homepage.getCartItemsCount();
		Assert.assertTrue(homepage.getCartItemsCount()==cartitemsCount+1);
}
	
	@Test
	public void ErrorTextvalidation(Map<String, String> data)
	{
		ExtentLogger.info("Executing in browser : " + data.get("Browser"));
	loginpage = new LoginPage();
	String erroText =loginpage.loginwithoutMobileNumber().getErrorText();
	Assert.assertEquals(erroText, data.get("loginerror"));
	}
}
