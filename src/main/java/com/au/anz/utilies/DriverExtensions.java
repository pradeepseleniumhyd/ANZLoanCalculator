package com.au.anz.utilies;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;


public class DriverExtensions {
	
private WebDriver driver;
org.slf4j.Logger log = LoggerFactory.getLogger(DriverExtensions.class);


private final long timeOutInSeconds =50;
public DriverExtensions(WebDriver driver) {
this.driver=driver;
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
	log.error("Element not found|visible and Time out in locating the element: " + e.getMessage());	
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
log.error("Element not found|visible and Time out in locating the element:" + e.getMessage());	
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
	log.error("Page not found|loaded and exception thrown:"+ e.getMessage());	
		}
	return false;
	}
	}


