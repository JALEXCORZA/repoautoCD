Feature: Navegar por la landing page de VC Claro en región Brasil
  -Como usario quiero visualizar la landing page de la plataforma

  @Landing @LandingPageBR
  Scenario: Navigating in VC Claro landing page Brazil
    Given I want to navigate in Vc Claro
    When I should see the "Landing" page
    And I click on "Region" menu
    * I click on "Brazil" country button
    Then I should see the "BrazilLanding" page
    And I want to see the "Administration" section
    Then I should see "Administração" section
    Then I want to see the "Download" section
    Then I should see "Baixar" section