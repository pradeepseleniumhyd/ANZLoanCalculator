package com.au.anz.pageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.au.anz.utilies.Commons;

public class HowMuchBorrowPage extends Commons {
	
	WebDriver driver;
	
	public HowMuchBorrowPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(how = How.XPATH, using = "//label[@for='application_type_single']") 
	private WebElement btn_ApplicationType_Single;
	
	@FindBy(how = How.XPATH, using = "//label[@for='application_type_joint']") 
	private WebElement btn_ApplicationType_Joint;
	
	@FindBy(how = How.XPATH, using = "//select[@title='Number of dependants']") 
	private WebElement drpdwn_NoOfDependents;
	
	@FindBy(how = How.XPATH, using = "//label[@for='borrow_type_home']") 
	private WebElement btn_BorrowTypeHome;
	
	@FindBy(how = How.XPATH, using = "//label[@for='borrow_type_investment']") 
	private WebElement btn_BorrowTypeInvestment;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'annual income')]/following::input[1]") 
	private WebElement txtBox_AnnualIncome;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'other income')]/following::input[1]") 
	private WebElement txtBox_OtherIncome;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'living')]/following::input[1]") 
	private WebElement txtBox_LivingExpenses;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'home loan')]/following::input[1]") 
	private WebElement txtBox_HomeLoanRepayment;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Other loan')]/following::input[1]") 
	private WebElement txtBox_OtherLoanRepayment;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Other monthly')]/following::input[1]") 
	private WebElement txtBox_OtherMonthlyCommitments;
	
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'credit card')]/following::input[1]") 
	private WebElement txtBox_Creditcard;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btnBorrowCalculater']") 
	private WebElement btn_WorkOut;
	
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'We estimate')]/following-sibling::span") 
	private WebElement lbl_estimateAmount;
	
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'borrow__error__text')]") 
	private WebElement lbl_ErrorMsg;
	
	
	@FindBy(how = How.XPATH, using = "(//button[@aria-label='Start over'])[1]") 
	private WebElement btn_StartOver;
	
	
	public void buttonSelection(String applicantType)
	{
		if(applicantType.equalsIgnoreCase("Joint"))
		{
			btn_ApplicationType_Joint.click();
		}else if(applicantType.equalsIgnoreCase("Single"))
		{
			 btn_ApplicationType_Single.click();
		}else if(applicantType.equalsIgnoreCase("Home to live in"))
		{
			 btn_BorrowTypeHome.click();
		}else if(applicantType.equalsIgnoreCase("Residential Investment"))
		{
			click(driver, btn_BorrowTypeInvestment);
		}
	}
	
	
	public void enterYourDetails(String applicantType,String noOfDependents, String propertyLiveToBuy)
	{
		buttonSelection(applicantType);
		dropdownSelection(drpdwn_NoOfDependents, noOfDependents);
		buttonSelection(propertyLiveToBuy);
	}

	public void enterYourEarnings(String annualIncome,String otherIncome)
	{
		txtBox_AnnualIncome.sendKeys(annualIncome);
		txtBox_OtherIncome.sendKeys(otherIncome);

	}
	
	public void enterYourExpenses(String monthlyExpenses,String homeLoan, String otherLoan, String otherCommitments, String creditCard)
	{
	
		txtBox_LivingExpenses.sendKeys(monthlyExpenses);
		txtBox_HomeLoanRepayment.sendKeys(homeLoan);
		txtBox_OtherLoanRepayment.sendKeys(otherLoan);
		txtBox_OtherMonthlyCommitments.sendKeys(otherCommitments);
		txtBox_Creditcard.sendKeys(creditCard);
		}
	
	public void clickOnWorkOut()
	{
		btn_WorkOut.click();
	}

	public void verifyBorrowAmount(String amount) throws Exception
	{
		Thread.sleep(3000);
		Assert.assertEquals(lbl_estimateAmount.getText(), "$"+String.format("%,d", Integer.parseInt(amount)));
	}

	public void clickOnStartOver()
	{
		btn_StartOver.click();
	}
	
	public void enterInsufficientExpenses(List<Map<String,String>>  data )
	{
	
		if(data.get(0).containsKey("MonthlyLivingExpenses"))
		{
			System.out.println(data.get(0).get("MonthlyLivingExpenses"));
			txtBox_LivingExpenses.sendKeys(data.get(0).get("MonthlyLivingExpenses"));
		}
		
}
	
	public void verifyErrorMsg(String msg) throws Exception
	{
		Thread.sleep(3000);
		Assert.assertEquals(lbl_ErrorMsg.getText(), msg);
	}
	
}
