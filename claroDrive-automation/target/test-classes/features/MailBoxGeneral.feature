
@General
Feature: Test general the mail box general cases
  

  @CreateDirectory
  Scenario: Create a new directory 
    Given Navigate in Claro Drive
    And I start with normal user  
		When I upload a new directory

 	@AddToFeature
 	Scenario: Add to feature a directory
 		Given Navigate in Claro Drive
 		And I start with normal user
 		When I create a new directory
 		And I close the details tab
 		And I click in the Modified filter
 		Then I add to feature a directory