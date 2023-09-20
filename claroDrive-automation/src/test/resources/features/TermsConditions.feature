# CCO-3771 Acceder a términos y condiciones

Feature: Accessing terms and conditions
  @Landing @TermsConditions
  Scenario:
    Given I want to see Terms and Conditions
    When I should see the "Landing" page
    And I want to see the "FAQs" section
    Then I should see the "Faqs" page
    And I click on "TermsConditions" button
    Then I should see TermsConditions page
    