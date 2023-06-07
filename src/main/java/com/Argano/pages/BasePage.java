package com.Argano.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.Argano.driver.DriverManager;
import com.Argano.enums.JSAction;
import com.Argano.enums.WaitStrategy;
import com.Argano.factory.ExplicitWaitFactory;
import com.Argano.reports.ExtentLogger;
import com.google.common.util.concurrent.Uninterruptibles;


public class BasePage 
{
	
	protected void click(By by, WaitStrategy waitstragety, int waitTime, String elementname) {

	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).click();
	ExtentLogger.info(elementname + " is clicked");
}

protected void click(By by, WaitStrategy waitstragety, int waitTime) {

	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).click();
}

protected void sendKeys(By by, String value, WaitStrategy waitstragety, int waitTime, String elementname) {
	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).sendKeys(value);
	ExtentLogger.info(value + " is entered in  " + elementname);
}

protected void sendKeys(By by, String value, WaitStrategy waitstragety, int waitTime) {
	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).sendKeys(value);

}

protected void clickENTER(By by, WaitStrategy waitstragety, int waitTime) {
	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).sendKeys(Keys.BACK_SPACE);

}

protected void clear(By by, WaitStrategy waitstragety, int waitTime) {
	ExplicitWaitFactory.performExplicitWait(by, waitstragety, waitTime).clear();
}

protected String getPageTitle() {
	return DriverManager.getDriver().getTitle();
}

protected String getText(By by, int waitTime) {
	return ExplicitWaitFactory.performExplicitWait(by, WaitStrategy.VISIBLE, waitTime).getText();
}

protected List<WebElement> getElements(By by) {
	return DriverManager.getDriver().findElements(by);
}

protected List<String> getElementstext(By by) {
	List<String> textList = new ArrayList<String>();

	List<WebElement> elements = getElements(by);
	for (WebElement element : elements) {
		textList.add(element.getText().trim());
	}
	return textList;

}

protected static String getRandomString(int n) {
	String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
	StringBuilder sb = new StringBuilder(n);
	for (int i = 0; i < n; i++) {
		int index = (int) (AlphaString.length() * Math.random());
		sb.append(AlphaString.charAt(index));
	}
	return sb.toString();
}

protected static void jsOperation(JSAction jsaction, By by, String message) {
	WebElement element = DriverManager.getDriver().findElement(by);
	JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();

	if (jsaction == JSAction.CLICK) {
		executor.executeScript("arguments[0].click();", element);
		ExtentLogger.pass(message);
	} else if (jsaction == JSAction.SCROLLTOVIEW) {
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}

protected static void jsOperation(JSAction jsaction) {
	JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();

	if (jsaction == JSAction.SCROLLDOWN) {
		executor.executeScript("window.scrollBy(0,350)", "");
	}
}

protected static void jsOperation(JSAction jsaction, By by) {
	WebElement element = DriverManager.getDriver().findElement(by);
	JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();

	if (jsaction == JSAction.CLICK) {
		executor.executeScript("arguments[0].click();", element);

	} else if (jsaction == JSAction.SCROLLTOVIEW) {
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}

protected String getcurrentDateInFormat(String format) {

	Date date = new Date();
	SimpleDateFormat simpleformat = new SimpleDateFormat(format);
	return simpleformat.format(date);
}

protected String getCurrentDatePlusInFormat(String format, int dateplus) {

	Calendar c = Calendar.getInstance();
	c.add(Calendar.DATE, dateplus);
	Date date = c.getTime();
	SimpleDateFormat simpleformat = new SimpleDateFormat(format);
	return simpleformat.format(date);
}

protected void navigateToFrame(By by) {
	WebElement containerFrame = DriverManager.getDriver().findElement(by);
	DriverManager.getDriver().switchTo().frame(containerFrame);
}

protected List<String> removeAllOccuranceOfStringFromList(List<String> list, String valuetoberemoved) {
	int index;
	while ((index = list.indexOf(valuetoberemoved)) >= 0) {
		list.remove(index);
	}
	return list;
}

protected void refresh() {
	DriverManager.getDriver().navigate().refresh();
}

// JS Executer click

protected void jseClickByScript(String script, String elementname, int time) {
	Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
	JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
	WebElement element = (WebElement) jse.executeScript(script);
	jse.executeScript("arguments[0].click();", element);
	ExtentLogger.info(elementname + " is clicked");
}

public void navigateToOtherWindow()
{
	String mainWindowHandle = DriverManager.getDriver().getWindowHandle();
    Set<String> allWindowHandles = DriverManager.getDriver().getWindowHandles();
    Iterator<String> iterator = allWindowHandles.iterator();

    // Here we will check if child window has other child windows and will fetch the heading of the child window
    while (iterator.hasNext()) {
        String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
            	DriverManager.getDriver().switchTo().window(ChildWindow);
        }
    }
}
}
