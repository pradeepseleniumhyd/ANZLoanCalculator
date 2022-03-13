package com.au.anz.utilies;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserCode {
	
	WebDriver driver;
	
	public WebDriver initializeDriver(String browsername) {
		if(browsername.equalsIgnoreCase("Chrome")) {	
				WebDriverManager.chromedriver().setup();
				 driver =new ChromeDriver();
			}else if(browsername.equalsIgnoreCase("edge")) {	
				WebDriverManager.edgedriver().setup();
			 driver =new ChromeDriver();
			}else if(browsername.equalsIgnoreCase("ie")) {	
				WebDriverManager.iedriver().setup();
				driver =new ChromeDriver();
			}else
			{
				System.out.println(browsername + " is invalid");
				Assert.fail(browsername + " is invalid");
			}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
		}

}
