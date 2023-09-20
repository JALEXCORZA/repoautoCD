package steps;

import static org.junit.Assert.assertTrue;

import java.io.Console;
import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import lib.ConsoleColors;
import pages.actions.LandingAction;
import pages.actions.MailBoxActions;
import pages.locators.LandingLocators.CountryElements;
import pages.utils.SeleniumDriver;
import utils.ConfigAttributes;
import utils.ConfigSingleton;
import io.cucumber.datatable.DataTable;

public class LandingSteps {

	private final ConfigSingleton config = ConfigSingleton.getInstance();
	LandingAction landingAction = new LandingAction();
	MailBoxActions mailBoxActions = new MailBoxActions();
	
	
	@Before( order = 1 )
	public void setUp() {
		landingAction.setCountryTranslation( config.getProperty(ConfigAttributes.Host ) , config.getProperty(ConfigAttributes.Service_Portal_Translations), CountryElements.MEXICO );
		landingAction.setApaService( config.getProperty(ConfigAttributes.Service_APA_Metadata) );
	}
	
	@Given( "Navigate in Claro Drive" )
	public void navigateInClaroDrive() {
		SeleniumDriver.openPage(config.getProperty(ConfigAttributes.Host));
		SeleniumDriver.waitForPageToLoad();
	}
	
	@And( "I change betwen countries" )
	public void iChangeBetwenCountries ( DataTable table ) throws InterruptedException {
		List<List<String>> data = table.asLists( String.class );
		
		for ( int i = 1 ; i < data.size(); i++ ) {
			
			CountryElements tmpCountry = CountryElements.MEXICO ; // Se establece mexico por default para la configuración de la traducción
			
			switch ( data.get( i ).get( 0 ) ) {
				case "Mexico": 			tmpCountry = CountryElements.MEXICO; 		break;
				case "Colombia": 		tmpCountry = CountryElements.COLOMBIA;		break;
				case "Brazil": 			tmpCountry = CountryElements.BRAZIL;		break;
				case "Guatemala": 		tmpCountry = CountryElements.GUATEMALA;		break;
				case "Honduras": 		tmpCountry = CountryElements.HONDURAS;		break;
				case "Nicaragua": 		tmpCountry = CountryElements.NICARAGUA;		break;
				case "ElSalvador": 		tmpCountry = CountryElements.EL_SALVADOR;	break;
				case "CostaRica": 		tmpCountry = CountryElements.COSTA_RICA;	break;
				case "Peru": 			tmpCountry = CountryElements.PERU;			break;
				case "Argentina": 		tmpCountry = CountryElements.ARGENTINA;		break;
				case "Chile": 			tmpCountry = CountryElements.CHILE;			break;
				case "Ecuador": 		tmpCountry = CountryElements.ECUADOR;		break;
				case "PuertoRico": 		tmpCountry = CountryElements.PUERTO_RICO;	break;
				case "Dominicana": 		tmpCountry = CountryElements.DOMINICANA;	break;
				case "Uruguay": 		tmpCountry = CountryElements.URUGUAY;		break;
				case "Paraguay": 		tmpCountry = CountryElements.PARAGUAY;		break;
				default:  break;
			}
			
			//Cambiar la configuración de traducción
			landingAction.setCountryTranslation( config.getProperty(ConfigAttributes.Host) , config.getProperty(ConfigAttributes.Service_Portal_Translations), tmpCountry);
			//Cambiar al país seleccionado
			landingAction.changeCountry( tmpCountry ); 
			iShouldSeeTheUrl( data.get( i ).get( 1 ) );	
			
		}
	}
	
	@Then( "I should see the URL {string}" )
	public void iShouldSeeTheUrl( String url ) {
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( url ) );
	}	
	
	@When( "I start with normal user" )
	public void iStartWithNormalUser() throws InterruptedException {
		landingAction.loginNormal( config.getProperty(ConfigAttributes.User_Normal), config.getProperty(ConfigAttributes.Password_Normal) );
	}
	
	@Then( "I click on the button iniciar sesion video" )
	public void iCLicOnTheButtonIniciarSesionVideo() {
		landingAction.startSesionVideo();
	}
	
	@Then( "I click on the button iniciar sesion negocio" )
	public void iCLicOnTheButtonIniciarSesionNegocio() {
		landingAction.startSesionNegocio();
	}
	
	@Then( "I should see the mail box" )
	public void iShouldSeeTheMailBox() throws InterruptedException {
		mailBoxActions.isOnTheMailBox();
	}
	
	@And( "I log out off the platform" )
	public void iLogOutOffThePlatform() throws InterruptedException {
		mailBoxActions.logout();
	}
	
	@When( "I click on the Instagram button" )
	public void iClickOnTheInstagramButton() throws InterruptedException {
		landingAction.clickOnInstagramButton();
	}
	
	@When( "I click on the Twitter button" )
	public void iClickOnTheTwitterButton() throws InterruptedException {
		landingAction.clickOnTwitterButton();
	}
	
	@When( "I click on the Facebook button" )
	public void iClickOnTheFacebookButton() throws InterruptedException {
		landingAction.clickOnFacebookButton();
	}
	
	@And( "I should see an element of Instagram page" )
	public void iShouldSeeAnElementOfInstagramPage() throws InterruptedException {
		landingAction.validateSocialMediaPageElement( "instagram" );
	}
	
	@And( "I should see an element of Facebook page" )
	public void iShouldSeeAnElementOfFacebookPage() throws InterruptedException {
		landingAction.validateSocialMediaPageElement( "facebook" );
	}
	
	@And( "I should see an element of Twitter page" )
	public void iShouldSeeAnElementOfTwitterPage() throws InterruptedException{
		landingAction.validateSocialMediaPageElement( "twitter" );
	}
	
	@When( "I fill in the text fields in normal login" )
	public void iFillInTheTextFieldsInNormalLogin( ) throws InterruptedException {
		landingAction.putUserLoginNormal(config.getProperty(ConfigAttributes.User_Normal), config.getProperty(ConfigAttributes.Password_Normal));
	}
	
	@When( "I fill in the text fields in video login" )
	public void iFillInTheTextFieldsInVideoLogin( ) throws InterruptedException {
		landingAction.putUserLoginVideo(config.getProperty(ConfigAttributes.User_Video), config.getProperty(ConfigAttributes.Password_Video));
	}
	
	@When( "I fill in the text fields in musica login" )
	public void iFillInTheTextFieldsInMusicaLogin() throws InterruptedException {
		landingAction.putUserLoginMusica( config.getProperty(ConfigAttributes.User_Musica), config.getProperty(ConfigAttributes.Password_Musica) );
	}
	
	@When( "I fill in the text fields in negocio login" )
	public void iFillInTheTextFieldsInNegocioLogin( ) throws InterruptedException {
		landingAction.putUserLoginNegocio( config.getProperty(ConfigAttributes.User_Negocio), config.getProperty(ConfigAttributes.Password_Negocio) );
	}
	
	@When( "I enter to claro video login" )
	public void  iEnterToClaroVideoLogin(  ) throws InterruptedException {
		landingAction.loginClaroDriveVideo();
	}
	
	@When( "I enter to claro musica login" )
	public void  iEnterToClaroMusicaLogin(  ) throws InterruptedException {
		landingAction.loginClaroDriveMusica();
	}
	
	@When( "I enter to claro drive login" )
	public void iEnterToClaroDriveLogin() throws InterruptedException {
		landingAction.loginClaroDrive();
	}
	
	@When( "I enter to claro negocio login" )
	public void iEnterToClaroNegocioLogin() throws InterruptedException {
		landingAction.loginClaroDriveNegocio();
	}
	
	@Then( "I show the password" )
	public void iShowThePassword() {
		landingAction.validateShowPassword();
	}
	
	@Then( "I show the password video" )
	public void iShowThePasswordVideo() {
		landingAction.validateShowPasswordVideo();
	}
	
	@Then( "I show the password negocio" )
	public void iShowThePasswordNegocio() {
		landingAction.validateShowPasswordNegocio();
	}
	
	@Then( "I show the password musica" )
	public void iShowThePasswordMusica() {
		landingAction.validateShowPasswordMusica();
	}
	
	@Then( "I cancel the login video" )
	public void iCancelTheLoginVideo() {
		landingAction.validateCancelLoginVideo();
	}
	
	@Then( "I cancel the login musica" )
	public void iCancelTheLoginMusica() {
		landingAction.validateCancelLoginMusica();
	}
	
	@Then( "I cancel the login normal" )
	public void iCancelTheLoginNormal() {
		landingAction.validateCancelLoginNormal();
	}
	
	@Then( "I go to recover my password" )
	public void iGoToRecoverMyPassword() {
		landingAction.validateRecoverPassword();
	}
	
	@Then( "I click on the text link Registrate" )
	public void iClickOnTheTextLinkRegistrate() throws InterruptedException {
		landingAction.register();
	}
	
	@And( "I cancel the registration telcel" )
	public void iCancelTheRegistrationTelcel() {
		landingAction.validateCancelRegisterTelcel();
	}
	
	@And( "I cancel the registration telmex" )
	public void iCancelTheRegistrationTelmex() {
		landingAction.validateCancelRegisterTelmex();
	}
	
	@And("I click on Register button")
	public void iClickOnRegisterButton() throws InterruptedException {
		landingAction.registerLanding();
	}
	
	@And( "I click on the Claro Drive button" )
	public void iClickOnTheClaroDriveButton() {
		landingAction.clickOnClaroDriveButton();
	}
	
	@And( "I click on the Telcel partner" )
	public void iClickOnTheTelcelPartner() {
		landingAction.selectResgisterTelcelPartner();
	}
	
	@And( "I click on the Telmex partner" )
	public void iClickOnTheTelmexPartner() {
		landingAction.selectResgisterTelmexPartner();
	}
	
	@And( "I click on the ClaroVideo partner" )
	public void iClickOnTheClaroVideoPartner() {
		landingAction.selectResgisterClaroVideoPartner();
	}
	
	@And( "I Register with ClaroVideo" )
	public void iRegisterWithClaroVideo() throws InterruptedException {
		landingAction.RegisterCV(config.getProperty(ConfigAttributes.User_Hijack), config.getProperty(ConfigAttributes.Password_Hijack));
	}
	
	@Then( "I should see the Hijack message" )
	public void iShouldSeeHijackMessage() throws InterruptedException {
		landingAction.validateHijackMessage();
	}
	
	@Then( "I click on the privacy policies telcel" )
	public void iClikcOnthePrivacyPoliciesTelcel() {
		landingAction.validateTelcelRegisterPrivacyPolicies();
	}
	
	@Then( "I click on the terms and conditions telcel" )
	public void iClickOnTheTermsAndConditionsTelcel() {
		landingAction.validateTelcelRegisterTerms();
	}
	
	@Then( "I click on the privacy policies telmex" )
	public void iClikcOnthePrivacyPoliciesTelmex() {
		landingAction.validateTelmexRegisterPrivacyPolicies();
	}
	
	@Then( "I click on the terms and conditions telmex" )
	public void iClickOnTheTermsAndConditionsTelmex() {
		landingAction.validateTelmexRegisterTerms();
	}
	
	@When( "I click on the Google Play button" )
	public void iClikOnTheGooglePlayButton() {
		landingAction.clickGooglePlayDownload();
	}
	
	@When( "I click on the App Store button" )
	public void iClikOnTheAppStoreButton() {
		landingAction.clickAppStoreDownload();
	}

	@When( "I click on the Win Mac button" )
	public void iClikOnTheWinMacButton() {
		landingAction.clickWinMacDownload();
	}
	
	@And("I click on the donwload windows and mac button")
	public void iClikOnTheDonwloadWindowsAndMacButton(){
		landingAction.clickWinMacButtonDownload();
	}
	
	@When( "I click on the App Gallery button" )
	public void iClikOnTheAppGalleryButton() {
		landingAction.clickAppGAlleryDownload();
	}
	
	@And( "Switch previous window" )
	public void switchPreviousWindow() {
		landingAction.switchPreviousWindow();
	}
	
	@And( "Validate prices" )
	public void validatePrices() {
		landingAction.validatePricesCountryProviders();
		landingAction.validatePricesCountryMicrositeNegocio();
	}
	
	@And( "Validate promo" )
	public void validatePromo() {
		landingAction.validatePromo();
	}
	
	@Given( "^following keywords are here$")
	public void ejemplo( DataTable table ) {
		List<List<String>> data = table.asLists( String.class );
	    
		for( int i = 0 ; i < 30 ; i ++ ) {
			System.out.println( "!!!!!!!!!!!!!!!!!!!!" + data.get(1) );
		}
	}
	
	@When( "Test services" )
	public void testServices() {
		landingAction.setCountryTranslation( config.getProperty(ConfigAttributes.Host) , config.getProperty(ConfigAttributes.Service_Portal_Translations), CountryElements.MEXICO );
		landingAction.setApaService( config.getProperty(ConfigAttributes.Service_APA_Metadata ));
	}
	
	@And( "Validate front" )
	public void validatefront() {
		landingAction.getJsonFront();
	}
	
	@When( "I click on the Contrata100 TelcelMX button" )
	public void iClickOnTheContrataButton() throws InterruptedException {
		landingAction.clickOnContrata100TelcelMXButton();
	}
	
	@And( "I should see an element of Contrata page" )
	public void iShouldSeeAnElementOfContrataPage() throws InterruptedException {
		landingAction.validateContrataElement( );
	}
	
	@When( "I click on the Contrata200 TelcelMX button" )
	public void iClickOnTheContrata200TelcelMXButton() throws InterruptedException {
		landingAction.clickOnContrata200TelcelMXButton();
	}
	
	@When( "I click on the Contrata300 TelcelMX button" )
	public void iClickOnTheContrata300TelcelMXButton() throws InterruptedException {
		landingAction.clickOnContrata300TelcelMXButton();
	}
	
	@When( "I click on the Contrata1024 TelcelMX button" )
	public void iClickOnTheContrata1024TelcelMXButton() throws InterruptedException {
		landingAction.clickOnContrata1024TelcelMXButton();
	}
	
	@When( "I click on the Telmex plans" )
	public void iClickOnTheTelmexPlansMXButton() throws InterruptedException {
		landingAction.clickOnTelmexPlansMXButton();
	}
	
	@When( "I click on the Contrata100 TelmexMX button" )
	public void iClickOnTheContrata100TelmexMXButton() throws InterruptedException {
		landingAction.clickOnContrata100TelmexMXButton();
	}
	
	@When( "I click on the Contrata200 TelmexMX button" )
	public void iClickOnTheContrata200TelmexMXButton() throws InterruptedException {
		landingAction.clickOnContrata200TelmexMXButton();
	}
	
	@When( "I click on the Contrata300 TelmexMX button" )
	public void iClickOnTheContrata300TelmexMXButton() throws InterruptedException {
		landingAction.clickOnContrata300TelmexMXButton();
	}
	
	@When( "I click on the Contrata1024 TelmexMX button" )
	public void iClickOnTheContrata1024TelmexMXButton() throws InterruptedException {
		landingAction.clickOnContrata1024TelmexMXButton();
	}
	
	@When( "I click on the Credit Card plans" )
	public void iClickOnTheCreditCardPlansMXButton() throws InterruptedException {
		landingAction.clickOnCreditCardPlansMXButton();
	}
	
	@When( "I click on the Contrata200 CreditCardMX button" )
	public void iClickOnTheContrata200CreditCardMXButton() throws InterruptedException {
		landingAction.clickOnContrata200CreditCardMXButton();
	}
	
	@When( "I click on the Contrata300 CreditCardMX button" )
	public void iClickOnTheContrata300CreditCardMXButton() throws InterruptedException {
		landingAction.clickOnContrata300CreditCardMXButton();
	}
	
	@When( "I click on the Contrata1024 CreditCardMX button" )
	public void iClickOnTheContrata1024CreditCardMXButton() throws InterruptedException {
		landingAction.clickOnContrata1024CreditCardMXButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata100 ClaroMovilCO button" )
	public void iClickOnTheContrata100ClaroMovilCOButton() throws InterruptedException {
		landingAction.clickOnContrata100ClaroMovilCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata200 ClaroMovilCO button" )
	public void iClickOnTheContrata200ClaroMovilCOButton() throws InterruptedException {
		landingAction.clickOnContrata200ClaroMovilCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata400 ClaroMovilCO button" )
	public void iClickOnTheContrata400ClaroMovilCOButton() throws InterruptedException {
		landingAction.clickOnContrata400ClaroMovilCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata1024 ClaroMovilCO button" )
	public void iClickOnTheContrata1024ClaroMovilCOButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata2048 ClaroMovilCO button" )
	public void iClickOnTheContrata2048ClaroMovilCOButton() throws InterruptedException {
		landingAction.clickOnContrata2048ClaroMovilCOButton();
	}
	
	@When( "I click on the Claro Hogar plans" )
	public void iClickOnTheClaroHogarPlansMXButton() throws InterruptedException {
		landingAction.clickOnClaroHogarPlansMXButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata100 ClaroHogarCO button" )
	public void iClickOnTheContrata100ClaroHogarCOButton() throws InterruptedException {
		landingAction.clickOnContrata100ClaroHogarCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata200 ClaroHogarCO button" )
	public void iClickOnTheContrata200ClaroHogarCOButton() throws InterruptedException {
		landingAction.clickOnContrata200ClaroHogarCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata400 ClaroHogarCO button" )
	public void iClickOnTheContrata400ClaroHogarCOButton() throws InterruptedException {
		landingAction.clickOnContrata400ClaroHogarCOButton();
	}
	
	//Cambiar
	@When( "I click on the Contrata1024 ClaroHogarCO button" )
	public void iClickOnTheContrata1024ClaroHogarCOButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroHogarCOButton();
	}
	
	//Nueva
	@When( "I click on the Contrata2048 ClaroHogarCO button" )
	public void iClickOnTheContrata2048ClaroHogarCOButton() throws InterruptedException {
		landingAction.clickOnContrata2048ClaroHogarCOButton();
	}
	
	@When( "I click on the Contrata25 ClaroBR button" )
	public void iClickOnTheContrata25ClaroBRButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroBRButton();
	}
	
	@When( "I click on the Contrata75 ClaroBR button" )
	public void iClickOnTheContrata75ClaroBRButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroBRButton();
	}
	
	@When( "I click on the Contrata150 ClaroBR button" )
	public void iClickOnTheContrata150ClaroBRButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroBRButton();
	}
	
	@When( "I click on the Contrata1TB ClaroBR button" )
	public void iClickOnTheContrata1024ClaroBRButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroBRButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilGT button" )
	public void iClickOnTheContrata25ClaroMovilGTButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilGTButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilGT button" )
	public void iClickOnTheContrata75ClaroMovilGTButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilGTButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilGT button" )
	public void iClickOnTheContrata150ClaroMovilGTButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilGTButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilGT button" )
	public void iClickOnTheContrata1024ClaroMovilGTButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilGTButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilHN button" )
	public void iClickOnTheContrata25ClaroMovilHNButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilHNButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilHN button" )
	public void iClickOnTheContrata75ClaroMovilHNButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilHNButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilHN button" )
	public void iClickOnTheContrata150ClaroMovilHNButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilHNButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilHN button" )
	public void iClickOnTheContrata1024ClaroMovilHNButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilHNButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilNI button" )
	public void iClickOnTheContrata25ClaroMovilNIButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilNIButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilNI button" )
	public void iClickOnTheContrata75ClaroMovilNIButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilNIButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilNI button" )
	public void iClickOnTheContrata150ClaroMovilNIButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilNIButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilNI button" )
	public void iClickOnTheContrata1024ClaroMovilNIButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilNIButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilSV button" )
	public void iClickOnTheContrata25ClaroMovilSVButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilSVButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilSV button" )
	public void iClickOnTheContrata75ClaroMovilSVButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilSVButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilSV button" )
	public void iClickOnTheContrata150ClaroMovilSVButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilSVButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilSV button" )
	public void iClickOnTheContrata1024ClaroMovilSVButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilSVButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilCR button" )
	public void iClickOnTheContrata25ClaroMovilCRButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilCRButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilCR button" )
	public void iClickOnTheContrata75ClaroMovilCRButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilCRButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilCR button" )
	public void iClickOnTheContrata150ClaroMovilCRButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilCRButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilCR button" )
	public void iClickOnTheContrata1024ClaroMovilCRButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilCRButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilPE button" )
	public void iClickOnTheContrata25ClaroMovilPEButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilPEButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilPE button" )
	public void iClickOnTheContrata75ClaroMovilPEButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilPEButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilPE button" )
	public void iClickOnTheContrata150ClaroMovilPEButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilPEButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilPE button" )
	public void iClickOnTheContrata1024ClaroMovilPEButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilPEButton();
	}
	
	@When( "I click on the Contrata25 ClaroAR button" )
	public void iClickOnTheContrata25ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroARButton();
	}
	
	@When( "I click on the Contrata50 ClaroAR button" )
	public void iClickOnTheContrata50ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata50ClaroARButton();
	}
	
	@When( "I click on the Contrata75 ClaroAR button" )
	public void iClickOnTheContrata75ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroARButton();
	}
	
	@When( "I click on the Contrata100 ClaroAR button" )
	public void iClickOnTheContrata100ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata100ClaroARButton();
	}
	
	@When( "I click on the Contrata300 ClaroAR button" )
	public void iClickOnTheContrata300ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata300ClaroARButton();
	}
	
	@When( "I click on the Contrata1024 ClaroAR button" )
	public void iClickOnTheContrata1024ClaroARButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroARButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilCL button" )
	public void iClickOnTheContrata25ClaroMovilCLButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilCLButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilCL button" )
	public void iClickOnTheContrata75ClaroMovilCLButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilCLButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilCL button" )
	public void iClickOnTheContrata150ClaroMovilCLButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilCLButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilCL button" )
	public void iClickOnTheContrata1024ClaroMovilCLButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilCLButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilEC button" )
	public void iClickOnTheContrata25ClaroMovilECButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilECButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilEC button" )
	public void iClickOnTheContrata75ClaroMovilECButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilECButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilEC button" )
	public void iClickOnTheContrata150ClaroMovilECButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilECButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilEC button" )
	public void iClickOnTheContrata1024ClaroMovilECButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilECButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilPR button" )
	public void iClickOnTheContrata25ClaroMovilPRButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilPRButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilPR button" )
	public void iClickOnTheContrata75ClaroMovilPRButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilPRButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilPR button" )
	public void iClickOnTheContrata150ClaroMovilPRButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilPRButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilPR button" )
	public void iClickOnTheContrata1024ClaroMovilPRButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilPRButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilDO button" )
	public void iClickOnTheContrata25ClaroMovilDOButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilDOButton();
	}
	
	@When( "I click on the Contrata75 ClaroMovilDO button" )
	public void iClickOnTheContrata75ClaroMovilDOButton() throws InterruptedException {
		landingAction.clickOnContrata75ClaroMovilDOButton();
	}
	
	@When( "I click on the Contrata150 ClaroMovilDO button" )
	public void iClickOnTheContrata150ClaroMovilDOButton() throws InterruptedException {
		landingAction.clickOnContrata150ClaroMovilDOButton();
	}
	
	@When( "I click on the Contrata1024 ClaroMovilDO button" )
	public void iClickOnTheContrata1024ClaroMovilDOButton() throws InterruptedException {
		landingAction.clickOnContrata1024ClaroMovilDOButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilUR button" )
	public void iClickOnTheContrata25ClaroMovilURButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilURButton();
	}
	
	@When( "I click on the Contrata50 ClaroMovilUR button" )
	public void iClickOnTheContrata50ClaroMovilURButton() throws InterruptedException {
		landingAction.clickOnContrata50ClaroMovilURButton();
	}
	
	@When( "I click on the Contrata100 ClaroMovilUR button" )
	public void iClickOnTheContrata100ClaroMovilURButton() throws InterruptedException {
		landingAction.clickOnContrata100ClaroMovilURButton();
	}
	
	@When( "I click on the Contrata300 ClaroMovilUR button" )
	public void iClickOnTheContrata300ClaroMovilURButton() throws InterruptedException {
		landingAction.clickOnContrata300ClaroMovilURButton();
	}
	
	@When( "I click on the Contrata25 ClaroMovilPA button" )
	public void iClickOnTheContrata25ClaroMovilPAButton() throws InterruptedException {
		landingAction.clickOnContrata25ClaroMovilPAButton();
	}
	
	@When( "I click on the Contrata50 ClaroMovilPA button" )
	public void iClickOnTheContrata50ClaroMovilPAButton() throws InterruptedException {
		landingAction.clickOnContrata50ClaroMovilPAButton();
	}
	
	@When( "I click on the Contrata100 ClaroMovilPA button" )
	public void iClickOnTheContrata100ClaroMovilPAButton() throws InterruptedException {
		landingAction.clickOnContrata100ClaroMovilPAButton();
	}
	
	@When( "I click on the Contrata300 ClaroMovilPA button" )
	public void iClickOnTheContrata300ClaroMovilPAButton() throws InterruptedException {
		landingAction.clickOnContrata300ClaroMovilPAButton();
	}
	
	@When( "I click on the Negocio page" )
	public void iClickOnTheNegocioPage() throws InterruptedException {
		landingAction.clickOnNegocioPage();
	}
	
}
