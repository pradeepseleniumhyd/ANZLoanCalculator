package com.au.anz.utilies;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons {
	
	protected WebDriver driver;
	private final long timeOutInSeconds =50;

	
	
	public void dropdownSelection( WebElement wbElement, String value)
	{
		Select sel = new Select(wbElement);
		sel.selectByVisibleText(value);
	}

	
	public void click(WebDriver driver, WebElement wbElement)
	{
		wbElement.click();	
	}
	
	public void enterData(WebDriver driver, WebElement wbElement, String value)
	{
		click(driver, wbElement);
		wbElement.clear();
		wbElement.sendKeys(value);
	}
	public WebElement waitUntilVisible(By locator) {
		try {
			WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return driver.findElement(locator);
		}
		catch (TimeoutException e) {
			throw new IllegalStateException("Element not found|visible and Time out error is thrown: " + e.getMessage());
		}
		catch(WebDriverException e) {
		}
		return null;
		
	}
	public WebElement waitUntilPresence(By locator)
	{
	try {
	WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);	
	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	return driver.findElement(locator);
	}
	catch(TimeoutException e) {
	throw new IllegalStateException("Element not found|present and Time out error is thrown:" + e.getMessage());	
	}
	catch(WebDriverException e) {
	}
	return null;
	}


	public boolean waitUntilPageTitleFound(String title) {
		WebDriverWait wait =new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.titleIs(title));
		return true;
		}
	public boolean waitUntilPageLoaded(String url) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
			wait.until(ExpectedConditions.urlToBe(url));
			return true;
		}
		catch (TimeoutException e) {
	throw new IllegalStateException("Page not found|loaded and Time out error is thrown:" + e.getMessage());		
		}
		catch(WebDriverException e) {
			}
		return false;
		}
		
}
