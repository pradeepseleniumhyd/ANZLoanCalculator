Feature: Validation of ANZ Home Loan Calculator
  verification of home loan borrowing power calculator based on user parameters

	Background: 
    Given user navigates to "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When user verifies the title of the page "Home loan borrowing power calculator | ANZ"

    @All
  Scenario Outline: Estimation of Loan based on required parameters
    When user enters "<ApplicationType>" "<NoofDependents>" and "<PropertyWouldlikeToBuy>" in Your details section   
    And user enters "<AnnualIncome>" and "<AnnualOtherIncome>" in Your earning section
    And user enters "<MonthlyLivingExpenses>" "<MonthlyHomeLoanRepayment>" "<MonthlyOtherLoanRepayment>" "<MonthlyOtherCommitments>" and "<TotalCreditcardLimits>"  in Your expenses section    
    When user clicks on work out how much i could borrow button
    Then user verifies the estimate amount could borrow as "<borrowAmount>"
    
    Examples:
    | ApplicationType    | NoofDependents | PropertyWouldlikeToBuy| AnnualIncome   | AnnualOtherIncome  |MonthlyLivingExpenses     | MonthlyHomeLoanRepayment  | MonthlyOtherLoanRepayment| MonthlyOtherCommitments| TotalCreditcardLimits|borrowAmount|
    | Single 							| 0 								|Home to live in								|   80000 						 		| 10000											|   500 						   					| 0 											 |100											 |0									|10000										|482000|
    
    
   @All
  Scenario: Clear all fields in the loan calculator
      When user enters "10000" and "50000" in Your earning section
          When user clicks on work out how much i could borrow button
    And user clicks on Start over button
    Then user verifies the estimate amount could borrow as "0"
    
    @All
  Scenario: Validation of the error message based on insufficient data
     When user enters data in Your expenses section
    | MonthlyLivingExpenses     | 
    | 1 						   					| 
     When user clicks on work out how much i could borrow button
     Then user verifies the error message "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500."
    
  
    
    
    
    
    
    
    


