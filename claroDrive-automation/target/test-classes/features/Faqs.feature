# CCO-3770 Acceder y navegar por la página de FAQs de VC Claro

Feature: Navigating in FAQs page
  @Landing @Faqs
  Scenario:Navigate in VC Claro Faqs
    Given I want to navigate in Vc Claro
    When I should see the "Landing" page
    And I want to see the "FAQs" section
    Then I should see the "Faqs" page
    And Click on Faqs filter
    | general         |
    | videoconference |
    | chats           |
    | contacts        |
    | profile         |
    Then I should see related Faqs