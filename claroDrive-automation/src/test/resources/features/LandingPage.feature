Feature: Navegar por la landing page de VC Claro
  -Como usario quiero visualizar la landing page de la plataforma

  @Landing @Positive @LandingPage
  Scenario: Navigating in VC Claro landing page
    Given I want to navigate in Vc Claro
    When I should see the "Landing" page
    And I want to see the "Administration" section
    Then I want to see the "Download" section
    And I download the "Mac" version


