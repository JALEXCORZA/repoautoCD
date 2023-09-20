@LandingTest
Feature: Test the landing portal

  ##############################################
  #							Countries 		   #
  ##############################################
  @Prueba
  Scenario: Prueba
    Given Navigate in Claro Drive
    And Validate prices
    #And I change betwen countries
      #| pais     | url               |
      #| Colombia | ?country=colombia |
    #And Validate prices
    
  @promo
  Scenario: promo
    Given Navigate in Claro Drive
    And Validate promo
    
  @PlanesMX
  Scenario: Validar planes de Mexico
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais   | url             |
      | Mexico | ?country=mexico |
    When I click on the Contrata100 TelcelMX button
    Then I should see an element of Contrata page
    #And I click on the Claro Drive button
    When I click on the Contrata200 TelcelMX button
    Then I should see an element of Contrata page
    #And I click on the Claro Drive button
    When I click on the Contrata300 TelcelMX button
    Then I should see an element of Contrata page
    #And I click on the Claro Drive button
    When I click on the Contrata1024 TelcelMX button
    Then I should see an element of Contrata page
    #WAnd I click on the Claro Drive button
    When I click on the Telmex plans
    When I click on the Contrata100 TelmexMX button
    Then I should see an element of Contrata page
    When I click on the Contrata200 TelmexMX button
    Then I should see an element of Contrata page
    When I click on the Contrata300 TelmexMX button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 TelmexMX button
    Then I should see an element of Contrata page
    When I click on the Credit Card plans
    When I click on the Contrata200 CreditCardMX button
    Then I should see an element of Contrata page
    When I click on the Contrata300 CreditCardMX button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 CreditCardMX button
    Then I should see an element of Contrata page
    
  @PlanesCO
  Scenario: Validar planes de Colombia
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais     | url               |
      | Colombia | ?country=colombia |
    When I click on the Contrata100 ClaroMovilCO button  
    Then I should see an element of Contrata page
    When I click on the Contrata200 ClaroMovilCO button  
    Then I should see an element of Contrata page
    When I click on the Contrata400 ClaroMovilCO button 
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilCO button
    Then I should see an element of Contrata page
    
    When I click on the Contrata2048 ClaroMovilCO button
    Then I should see an element of Contrata page
    
    When I click on the Claro Hogar plans
    When I click on the Contrata100 ClaroHogarCO button 
    Then I should see an element of Contrata page
    When I click on the Contrata200 ClaroHogarCO button
    Then I should see an element of Contrata page
    When I click on the Contrata400 ClaroHogarCO button 
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroHogarCO button
    Then I should see an element of Contrata page
    
    When I click on the Contrata2048 ClaroHogarCO button
    Then I should see an element of Contrata page
    
  @PlanesBR
  Scenario: Validar planes de Brasil
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais     | url               |
      | Brazil   | ?country=brasil   |
    When I click on the Contrata25 ClaroBR button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroBR button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroBR button
    Then I should see an element of Contrata page
    When I click on the Contrata1TB ClaroBR button
    Then I should see an element of Contrata page
    
  @PlanesGT
  Scenario: Validar planes de Guatemala
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais        | url                  |
      | Guatemala   | ?country=guatemala   |
    When I click on the Contrata25 ClaroMovilGT button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilGT button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilGT button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilGT button
    Then I should see an element of Contrata page
    
  @PlanesHN
  Scenario: Validar planes de Honduras
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                  |
      | Honduras   | ?country=honduras    |
    When I click on the Contrata25 ClaroMovilHN button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilHN button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilHN button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilHN button
    Then I should see an element of Contrata page
    
  @PlanesNI
  Scenario: Validar planes de Nicaragua
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                  |
      | Nicaragua  | ?country=nicaragua   |
    When I click on the Contrata25 ClaroMovilNI button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilNI button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilNI button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilNI button
    Then I should see an element of Contrata page
    
  @PlanesSV
  Scenario: Validar planes de Nicaragua
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais        | url                  |
      | ElSalvador  | ?country=elsalvador  |
    When I click on the Contrata25 ClaroMovilSV button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilSV button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilSV button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilSV button
    Then I should see an element of Contrata page
    
  @PlanesCR
  Scenario: Validar planes de Costa Rica
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais        | url                  |
      | CostaRica   | ?country=costarica   |
    When I click on the Contrata25 ClaroMovilCR button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilCR button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilCR button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilCR button
    Then I should see an element of Contrata page
    
  @PlanesPE
  Scenario: Validar planes de Peru
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais        | url                  |
      | Peru        | ?country=peru        |
    When I click on the Contrata25 ClaroMovilPE button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilPE button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilPE button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilPE button
    Then I should see an element of Contrata page
    
  @PlanesAR
  Scenario: Validar planes de Argentina
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais        | url                  |
      | Argentina   | ?country=argentina   |
    When I click on the Contrata25 ClaroAR button
    Then I should see an element of Contrata page
    When I click on the Contrata50 ClaroAR button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroAR button
    Then I should see an element of Contrata page
    When I click on the Contrata100 ClaroAR button
    Then I should see an element of Contrata page
    When I click on the Contrata300 ClaroAR button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroAR button
    Then I should see an element of Contrata page
  
  @PlanesCL
  Scenario: Validar planes de Chile
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Chile      | ?country=chile      |
    When I click on the Contrata25 ClaroMovilCL button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilCL button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilCL button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilCL button
    Then I should see an element of Contrata page
    
  @PlanesEC
  Scenario: Validar planes de Ecuador
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Ecuador    | ?country=ecuador    |
    When I click on the Contrata25 ClaroMovilEC button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilEC button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilEC button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilEC button
    Then I should see an element of Contrata page
    
  @PlanesPR
  Scenario: Validar planes de Puerto Rico
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | PuertoRico | ?country=puertorico |
    When I click on the Contrata25 ClaroMovilPR button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilPR button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilPR button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilPR button
    Then I should see an element of Contrata page
    
  @PlanesDO
  Scenario: Validar planes de Republica Dominicana
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Dominicana | ?country=dominicana |
    When I click on the Contrata25 ClaroMovilDO button
    Then I should see an element of Contrata page
    When I click on the Contrata75 ClaroMovilDO button
    Then I should see an element of Contrata page
    When I click on the Contrata150 ClaroMovilDO button
    Then I should see an element of Contrata page
    When I click on the Contrata1024 ClaroMovilDO button
    Then I should see an element of Contrata page
    
  @PlanesUR
  Scenario: Validar planes de Uruguay
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Uruguay    | ?country=uruguay    |
    When I click on the Contrata25 ClaroMovilUR button
    Then I should see an element of Contrata page
    When I click on the Contrata50 ClaroMovilUR button
    Then I should see an element of Contrata page
    When I click on the Contrata100 ClaroMovilUR button
    Then I should see an element of Contrata page
    When I click on the Contrata300 ClaroMovilUR button
    Then I should see an element of Contrata page
    
  @PlanesPA
  Scenario: Validar planes de Paraguay
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Paraguay   | ?country=paraguay   |
    When I click on the Contrata25 ClaroMovilPA button
    Then I should see an element of Contrata page
    When I click on the Contrata50 ClaroMovilPA button
    Then I should see an element of Contrata page
    When I click on the Contrata100 ClaroMovilPA button
    Then I should see an element of Contrata page
    When I click on the Contrata300 ClaroMovilPA button
    Then I should see an element of Contrata page
  

  @CDFS_7569 @Countries @All
  Scenario: Validar el pais de México
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais       | url                 |
      | Mexico     | ?country=mexico     |
      | Colombia   | ?country=colombia   |
      | Brazil     | ?country=brasil     |
      | Guatemala  | ?country=guatemala  |
      | Honduras   | ?country=honduras   |
      | Nicaragua  | ?country=nicaragua  |
      | ElSalvador | ?country=elsalvador |
      | CostaRica  | ?country=costarica  |
      | Peru       | ?country=peru       |
      | Argentina  | ?country=argentina  |
      | Chile      | ?country=chile      |
      | Ecuador    | ?country=ecuador    |
      | PuertoRico | ?country=puertorico |
      | Dominicana | ?country=dominicana |
      | Uruguay    | ?country=uruguay    |
      | Paraguay   | ?country=paraguay   |
    When I start with normal user
    Then I should see the mail box

  ##############################################
  #							Social Media 									#
  ##############################################
  #@SocialMedia
  @Instagram @CDFS-7338 @CDFS-7336 @CDFS-7339
  Scenario: Validate Instagram link
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais   | url             |
      | Mexico | ?country=mexico |
    When I click on the Instagram button
    Then I should see an element of Instagram page
    
	@Twitter
  Scenario: Validate Twitter link
    Given Navigate in Claro Drive
    And I change betwen countries
      | pais   | url             |
      | Mexico | ?country=mexico |
    When I click on the Twitter button
    Then I should see an element of Twitter page

  ##############################################
  #									Login 							  		#
  ##############################################
  #@Login
  @CDFS-7377 @CDFS-7376 @CDFS-7380 @Login @All
  Scenario: Validate show and hide password
    Given Navigate in Claro Drive
    When I fill in the text fields in normal login
    Then I show the password
    And I click on the Claro Drive button
    When I fill in the text fields in normal login
    Then I go to recover my password
    And I click on the Claro Drive button
    When I enter to claro musica login
    Then I cancel the login musica
    When I enter to claro video login
    Then I cancel the login video
    When I start with normal user
    Then I should see the mail box

  ##############################################
  #									Register 							  	#
  ##############################################
  #@Register
  @CDFS-7342 @CDFS-1910 @CDFS-1911 @TelcelRegister @Register @All
  Scenario: Cancel telcel registration
    Given Navigate in Claro Drive
    And I configure the translation for "Mexico"
    When I enter to claro drive login
    Then I click on the text link Registrate
    And I click on the Telcel partner
    And I cancel the registration telcel
    When I enter to claro drive login
    Then I click on the text link Registrate
    And I click on the Telcel partner
    And I click on the terms and conditions telcel
    And I click on the privacy policies telcel

  @CDFS-7366 @CDFS-7367 @CDFS-7368 @TelmexRegister @Register @All
  Scenario: Validate cancel login, privacy policies and termns and condition for Telmex login.
    Given Navigate in Claro Drive
    And I configure the translation for "Mexico"
    When I enter to claro drive login
    Then I click on the text link Registrate
    And I click on the Telmex partner
    And I cancel the registration telmex
    When I enter to claro drive login
    Then I click on the text link Registrate
    And I click on the Telmex partner
    And I click on the terms and conditions telmex
    And I click on the privacy policies telmex

  ##############################################
  #									Hijack  							  	#
  ##############################################
  #@Hijack
  @Hijack @All
  Scenario: Validate Hijack with Claro Video and Telmex partners
    Given Navigate in Claro Drive
    And I click on Register button
    And I click on the ClaroVideo partner
    And I Register with ClaroVideo
    Then I should see the Hijack message

  ##############################################
  #									Download 							  	#
  ##############################################
  #@Download
  @CDFS-2486 @CDFS-2487 @CDFS-2488 @CDFS-7801 @Download @All
  Scenario: Download movil aplications
    Given Navigate in Claro Drive
    When I click on the Google Play button
    Then I should see the URL "https://play.google.com/store/apps/details?id=com.clarodrive.android"
    And Switch previous window
    When I click on the App Store button
    Then I should see the URL "https://apps.apple.com/mx/app/claro-drive/id1250666367"
    And Switch previous window
    When I click on the App Gallery button
    Then I should see the URL "https://appgallery.huawei.com/#/app/C101156725?locale=en_US&source=appshare&subsource=C101156725"
    And Switch previous window
    When I click on the Win Mac button
    Then I should see the URL "/download"
    And I click on the donwload windows and mac button

  ###############################################
  #				Login video/musica/negocio					 #
  ###############################################
  #@Login
  @CDFS-7389 @CDFS-7388 @CDFS-7387 @LoginVideo @Login @All
  Scenario: Validate cancel login, show password and login in video mode
    Given Navigate in Claro Drive
    When I enter to claro video login
    Then I cancel the login video
    When I fill in the text fields in video login
    Then I show the password video
    Then I click on the button iniciar sesion video
    And I should see the mail box

  @CDFS-7391 @CDFS-7392 @CDFS-7390 @LoginNegocio @Login @All
  Scenario: Validate cancel login, show password and login in negocio mode
    Given Navigate in Claro Drive
    When I fill in the text fields in negocio login
    Then I show the password negocio
    Then I click on the button iniciar sesion negocio
    And I should see the mail box

  @CDFS-7389 @CDFS-7388 @CDFS-7387 @LoginMusica @Login @All
  Scenario: Validate cancel login, show password and login in musica mode
    Given Navigate in Claro Drive
    When I enter to claro musica login
    Then I cancel the login musica
    #Se pone cuenta de negocio pero no hay insumo de musica
    When I fill in the text fields in musica login
    Then I show the password musica
    
    
	@Pruebafront
	Scenario: Prueba
			And Validate front
