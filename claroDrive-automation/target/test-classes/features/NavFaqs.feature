# CCO-3770 Acceder y navegar por la página de FAQs de VC Claro

Feature: Navigating in FAQs page
  @Landing @NavFaqs
    Scenario:
    Given I want to navigate in Vc Claro
    When I should see the "Landing" page
    And I want to see the "FAQs" section
    Then I should see the "Faqs" page
    And I open a Claro page
    | ClaroVideo  |
    | ClaroMusica |
    | ClaroShop   |
    | ClaroDrive  |
    Then I should see Claro new page
    When I should see the "Faqs" page
    And I want to see another section
    | home           |
    | administration |
    | functions      |
    | download       |
    | login          |
    Then I should see new section

