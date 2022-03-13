package com.au.anz.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.au.anz.pageObjects.HowMuchBorrowPage;
import com.au.anz.utilies.AppConstants;
import com.au.anz.utilies.BrowserCode;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoanBorrowingCalStepDef extends BrowserCode{
	
	 public WebDriver driver;
	 HowMuchBorrowPage howMuchBorrowPage ;
	 Scenario scenario;
	 
		@Before
		public void browserOpen(Scenario scenario)
		{
		 	driver=	initializeDriver(AppConstants.browsername);  
		 	howMuchBorrowPage = new HowMuchBorrowPage(driver);
		 	 this.scenario = scenario;
		}
		
		 @Given("user opens {string} browser")
		 public void user_opens_browser(String browsername) {
			 	
		 }
		 @When("user navigates to {string}")
		 public void user_navigates_to(String url) {
			 driver.get(url);
		 	 }
		 @Then("user verifies the title of the page {string}")
		 public void user_verifies_the_title_of_the_page(String expectedTitle) {
			 Assert.assertEquals(expectedTitle, driver.getTitle());
			 
		  	 }
		 
		 
		 @After
		 public void closeDriver()
		 {
		 driver.quit();
		 }
	
	 @When("user enters {string} {string} and {string} in Your details section")
	 public void user_enters_and_in_your_details_section(String applicantType, String noOfDependent, String propertyLivetoBuy) {
		 howMuchBorrowPage.enterYourDetails(applicantType, noOfDependent, propertyLivetoBuy);
	   	 }
	 @When("user enters {string} and {string} in Your earning section")
	 public void user_enters_and_in_your_earning_section(String annualIncome, String otherIncome) {
		 howMuchBorrowPage.enterYourEarnings(annualIncome, otherIncome);
		 }
	 @When("user enters {string} {string} {string} {string} and {string}  in Your expenses section")
	 public void user_enters_and_in_your_expenses_section(String livingExpenses, String homeLoan, String otherLoan, String otherCommitments, String creditCard) {
		 howMuchBorrowPage.enterYourExpenses(livingExpenses, homeLoan, otherLoan, otherCommitments, creditCard);
	 }
	 @When("user clicks on work out how much i could borrow button")
	 public void user_clicks_on_button() {
		 howMuchBorrowPage.clickOnWorkOut();
	 }
	 @Then("user verifies the error message {string}")
	 public void user_verifies_the_error_message(String msg) throws Exception {
		 howMuchBorrowPage.verifyErrorMsg(msg);
		 scenario.log("Estimated Borrow Amount is " +msg);
		 }

		 @Then("user clicks on Start over button")
	 public void user_verifies_all_fields_are_clear_in_the_page() {
			 howMuchBorrowPage.clickOnStartOver();
		 }




@Then("user verifies the estimate amount could borrow as {string}")
public void user_verifies_the_estimate_amount_could_borrow_as(String amount) throws Exception {
	 howMuchBorrowPage.verifyBorrowAmount(amount);

  }


@When("user enters data in Your expenses section")
public void user_enters_data_in_your_expenses_section(DataTable dataTable) {
	List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
		howMuchBorrowPage.enterInsufficientExpenses(data);
	
  }





	 

}
