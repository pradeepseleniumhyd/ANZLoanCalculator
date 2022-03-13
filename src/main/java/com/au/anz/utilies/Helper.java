package com.au.anz.utilies;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

public class Helper {
	Properties pro;

	public WebDriver driver;
	public org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	private static final long time = 30000;
	private static final int SHORT_WAIT = 2000;
	private JavascriptExecutor jse;
	DriverExtensions de;

	public Helper(WebDriver driver) {
		this.driver = driver;
		de = new DriverExtensions(driver);
		jse = (JavascriptExecutor) driver;
	}

//this method is created for for click on element
	public void clickOnElement(By locator) {
		driver.findElement(locator).click();
	}

//this method is created for click on element using java script executor
	public void clickOnElement_JS(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

//this method is used to get the title of window
	public String getwindowTitle() {
		return driver.getTitle();
	}

//this method to launch the URL on the browser
	public void launchapplication(String url) {
		driver.get(url);
	}

//this method is used to send the data into input box
	public void inputData(By locator, String data) {
		de.waitUntilVisible(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(data);
	}

//this method is used to get the Text of an element
	public String getText(By locator) {
		de.waitUntilVisible(locator);
		return driver.findElement(locator).getText();
	}

//THIS METHOD IS USED TO HANDLE THE DROPDOWN USING SELECT CLASS WITH INDEX OR VALUE OR VISIBLE TEXT
	public void DropDownHandling(By locator, String DropDownType, String input) {
		Select sel = new Select(driver.findElement(locator));
		if (DropDownType.equalsIgnoreCase("index")) {
			sel.selectByIndex(Integer.parseInt(input));
		} else if (DropDownType.equalsIgnoreCase("value")) {
			sel.selectByValue(input);
		} else {
			sel.selectByVisibleText(input);
		}

	}

//this method is used to handle stale element reference exception
	public WebElement waitUntilVisible(By locator, int time) throws InterruptedException {
		long startTime;
		long currentTime = startTime = System.currentTimeMillis();
		int attempts = 1;
		while (currentTime - startTime <= time) {
			try {
				restWait1();
				return driver.findElement(locator);

			} catch (StaleElementReferenceException e) {
				currentTime = System.currentTimeMillis();
				++attempts;
				continue;
			}
		}
		throw new NoSuchElementException("Element not found:\"%s\" after %s seconds of trying and %s attempts");
	}

	private void restWait1() {
		// TODO Auto-generated method stub

	}

//method to get the value from the web element attribute(ex: Inner Text and mousehover)
	public String getAttributeText(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute);
	}

//Method to read the test data from the properties file...
	public Properties getTestData() {
		Properties prop = new Properties();
		try {
			URL res = getClass().getClassLoader().getResource("testdata.properties");
			File f = new File(res.getFile());
			log.info("Loading Test data file....." + f.getAbsolutePath());
			prop.load(res.openStream());
			log.info("Test Data file loaded.....");
		} catch (IOException e) {
			log.error("Error while loading the test data values into the test parameters!!" + e.getMessage());
			// e.printStackTrace();
		}
		return pro;
	}

//method to get the current URL the new tab or window
	public String getCurrentURL() throws IOException {
		return driver.getCurrentUrl();
	}

//method to return the web element based on a parent element and a child locator
	public WebElement findElement(WebElement element, By locator) {
		WebElement elem = element.findElement(locator);
		return elem;
	}
//method to click the web element...
	/*
	 * public void click(By locator){ driver.findElement(locator).click(); }
	 */

//method to find the element
	public void findElement(By locator) {
		driver.findElement(locator);
	}

//method to find the element
	public WebElement findAnElement(By locator) {
		return driver.findElement(locator);
	}

//method to get the current URLof the Lumiere2 Dashboard
	public String getExtendedURL() throws IOException {
		String currentURL = driver.getCurrentUrl();
//split the currentURL
		String tab[] = currentURL.split(".com/");
		return tab[1];
	}

//method to set the Time in milliseconds
	public void waitForMilliSeconds(int milliSec) throws InterruptedException {
		Thread.sleep(milliSec);
	}

//method to get list of the web elements
	public List<WebElement> findElements(By locator) {
		List<WebElement> elems = driver.findElements(locator);
		return elems;

	}

//method to set value by keyboard keys(Example: Hitting enter key)....
	public void setValue(By locator, Keys key) {
		driver.findElement(locator).sendKeys(key);
	}

//method to disappears of text
	public boolean waitInvisibilityOfElement(WebDriver driver, By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
	}

//this method is used to locate an element in the web page
	public WebElement getElement(By locator) {
		WebElement elem = driver.findElement(locator);
		return elem;
	}

//this method is used to check if an element is enabled in the web page.
	public boolean isElementEnabled(By locator) {
		boolean isElemEnabled = false;
		isElemEnabled = driver.findElement(locator).isEnabled();
		return isElemEnabled;
	}

//this method is used to check if an element is displayed in the web page
	public boolean isElementDisplayed(By locator) {
		boolean isElemDisplayed = false;
		isElemDisplayed = driver.findElement(locator).isDisplayed();
		return isElemDisplayed;
	}

//method to switch the window handle
	public void switchtoWindow(String ParentWindow) {
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(ParentWindow))
				driver.switchTo().window(windowHandle);
		}
	}

//method to switch the window handle and Title
	public void switchtoWindowBasedOnTitle(String parentWindow, String Title) {
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow) && Title.equals(getTestData().getProperty("Switchwindow_Title")))
				driver.switchTo().window(windowHandle);
		}
	}

//method to close the driver driver...
	public void close() {
		driver.close();
	}

//method to scroll the page till element gets into view
	public void windowScroll(By locator) {
		WebElement element = driver.findElement(locator);
		jse.executeScript("argument[0].scrollIntoView(true);", element);
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

//method to check if element exists
	public boolean isElementExist(By locator) {
		boolean isElemExists = false;
		List<WebElement> ele = driver.findElements(locator);
		if (ele.size() != 0)
			isElemExists = true;
		return isElemExists;
	}

//method to wait for element to disappear
	public void waitForDisappear(By locator) throws Exception {
		int timeout = 5;
		int i = 0;
		boolean isDisplay = true;
		while (i < timeout && isDisplay) {
			waitForMilliSeconds(1000);
			isDisplay = isElementExist(locator);
			i++;
		}
	}

//method to close all windows in instance
	public void closeallWindows() {
		driver.close();

	}
//method to scroll the page till element gets into view....

	public void windowScrollView(By locator) {
		WebElement element = driver.findElement(locator);
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

//this method is created to refresh the page
	public void refreshWebPage() {
		driver.navigate().refresh();
	}

	/*
	 * public void restWait1() { restWait(getShortWaitTime()); }
	 */

	public static int getShortWaitTime() {
		return SHORT_WAIT;
	}

	public void restWait(long time) {
		long startTime;
		long currentTime = startTime = System.currentTimeMillis();
		while (currentTime - startTime <= time) {
			currentTime = System.currentTimeMillis();
		}
	}

	/*
	 * Existing Code for click Method public WebElement click(By locator) throws
	 * InterruptedException { int time = getLongWaitTime(); int attempts =1; long
	 * startTime; long currentTime = startTime =System.currentTimeMillis();
	 * while(currentTime -startTime <= time) { try { WebElemenet clickable
	 * =waitUntilVisible(locator,attempts) clickable.click(); return clickable; }
	 * catch (WebDriver Exception e){ currentTime =System.currentTimeMillis();
	 * ++attempts; continue: } } return null; }
	 */
	public int getLongWaitTime() throws InterruptedException {
		Thread.sleep(5000);
		return 0;
	}

	public void restWait() throws InterruptedException {
		Thread.sleep(4000);
	}

//method to set the text in the web element
	public void setValue(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

//method to refresh the URl
	public void getRefresh(String URL) {
		driver.navigate().to(URL);
	}

//method to get the value from the web element input box based on locator
	public void waitForPageToLoad() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//this method used to accept the alert box
	public void handleWebBasedAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

//this method used to get the text the alert box
	public String getStringTextofAlert() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

//this method used to send the data to alert box
	public void filldatatoAlert(String data) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(data);
	}

//Right click on Element using mouse
	public void rightClickonElement(By locator) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(locator);
		actions.contextClick(element).build().perform();
	}

//method is created to upload the file
	public void uploadFile(String fileLocation) {
		try {
			StringSelection stringSelection = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

//this method is created to select the element based on x axis and y axis
	public void moveOffset(By locator, int x, int y) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(locator);
		action.clickAndHold(element).moveByOffset(x, y).release().build().perform();
	}

//This method is used to verify list of all elements text for a dropdown
	public List<String> getAllElementsFromDropdown(By locator) {
		List<String> alloptionstext = new ArrayList<String>();
		List<WebElement> alloptions = driver.findElements(locator);
		for (WebElement element : alloptions) {
			alloptionstext.add(element.getText().trim());
		}
		return alloptionstext;

	}
}
