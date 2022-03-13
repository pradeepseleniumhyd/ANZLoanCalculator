package com.au.anz.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features= {"src/test/java/com/au/anz/features/LoanBorrowingCalculator.feature"},
glue= {"com.au.anz.stepDefinitions"},
plugin = { "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
monochrome=true,
dryRun=false,
//publish=true,
tags="@All"
)
public class LoanBorrowingCalculatorRunner extends AbstractTestNGCucumberTests{
	
	
	

}
