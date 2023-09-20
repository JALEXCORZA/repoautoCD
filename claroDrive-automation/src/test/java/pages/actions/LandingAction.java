package pages.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lib.ConsoleColors;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import pages.locators.LandingLocators;
import pages.locators.LandingLocators.CountryElements;
import pages.locators.LandingLocators.CountryElementsNegocio;
import pages.utils.SeleniumDriver;

public class LandingAction {
	
	LandingLocators landingLocators;
	
	private CountryElements countrySelected;
	
	private String langService;
	private String apaMetadataService;
	
	private JSONObject jsonTranslation;
	private JSONObject jsonApaMetadata;
	
	
	
	
	/**
	 * Constructor de clase
	 */
	public LandingAction(){
		landingLocators = new LandingLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), landingLocators);
		countrySelected = CountryElements.MEXICO;
	}
	
	/**
	 * Realiza el cambio entre paises en la plataforma de Claro Drive
	 * @param country
	 * @throws InterruptedException
	 */
	public void changeCountry( CountryElements country ) throws InterruptedException {
		SeleniumDriver.explicitWait(landingLocators.countrieList);
		landingLocators.countrieList.click();
		
		switch ( country ) {
		case MEXICO: 		landingLocators.buttonMexico.click();  			break;
		case COLOMBIA: 		landingLocators.buttonColombia.click();  		break;
		case BRAZIL: 		landingLocators.buttonBrazil.click();  			break;
		case GUATEMALA: 	landingLocators.buttonGuatemala.click(); 		break;
		case HONDURAS: 		landingLocators.buttonHonduras.click(); 		break;
		case NICARAGUA: 	landingLocators.buttonNicaragua.click(); 		break;
		case EL_SALVADOR: 	landingLocators.buttonElSalvador.click(); 		break;
		case COSTA_RICA: 	landingLocators.buttonCostaRica.click(); 		break;
		case PERU: 			landingLocators.buttonPeru.click(); 			break;
		case ARGENTINA: 	landingLocators.buttonArgentina.click(); 		break;
		case CHILE: 		landingLocators.buttonChile.click(); 			break;
		case ECUADOR: 		landingLocators.buttonEcuador.click(); 			break;
		case PUERTO_RICO: 	landingLocators.buttonPuertoRico.click(); 		break;
		case DOMINICANA: 	landingLocators.buttonDominicana.click(); 		break;
		case URUGUAY: 		landingLocators.buttonUruguay.click(); 			break;
		case PARAGUAY: 		landingLocators.buttonParaguay.click(); 		break;
		default:break;
		}	
		
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Realiza el cambio entre paises en la plataforma de Claro Drive en Negocio
	 * @param country
	 * @throws InterruptedException
	 */
	public void changeCountryNegocio( CountryElementsNegocio country ) throws InterruptedException {
		SeleniumDriver.explicitWait(landingLocators.countrieListNegocio);
		landingLocators.countrieListNegocio.click();
		
		switch ( country ) {
		case MEXICO: 		landingLocators.buttonMexicoNegocio.click();  			break;
		case COLOMBIA: 		landingLocators.buttonColombiaNegocio.click();  		break;
		case BRAZIL: 		landingLocators.buttonBrazilNegocio.click();  			break;
		case GUATEMALA: 	landingLocators.buttonGuatemalaNegocio.click(); 		break;
		case HONDURAS: 		landingLocators.buttonHondurasNegocio.click(); 			break;
		case NICARAGUA: 	landingLocators.buttonNicaraguaNegocio.click(); 		break;
		case EL_SALVADOR: 	landingLocators.buttonElSalvadorNegocio.click(); 		break;
		case COSTA_RICA: 	landingLocators.buttonCostaRicaNegocio.click(); 		break;
		case PERU: 			landingLocators.buttonPeruNegocio.click(); 				break;
		case ARGENTINA: 	landingLocators.buttonArgentinaNegocio.click(); 		break;
		case CHILE: 		landingLocators.buttonChileNegocio.click(); 			break;
		case ECUADOR: 		landingLocators.buttonEcuadorNegocio.click(); 			break;
		case PUERTO_RICO: 	landingLocators.buttonPuertoRicoNegocio.click(); 		break;
		case DOMINICANA: 	landingLocators.buttonDominicanaNegocio.click(); 		break;
		default:break;
		}	
		
		SeleniumDriver.waitForPageToLoad();
	}

	/**
	 * Realizar un login en el ambiente productivo
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void loginNormal( String user, String password ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonIniciarSesion);
		landingLocators.buttonIniciarSesion.click();
		
		landingLocators.textFieldLoginNormal.click();
		landingLocators.textFieldLoginNormal.sendKeys( user );
		
		landingLocators.buttonSiguiente.click();
		
		SeleniumDriver.explicitWait( landingLocators.textFieldPassword );
		landingLocators.textFieldPassword.click();
		
		//Se añade una segunda busqueda del elemento porque al hacer click en la linea anterior aparece un error en el que ya no se encuentra asociado al elemento el Xpath
		//La dirección se corresponde a 'landingLocators.textFieldPassword' pero no se ha encontrado solución sintacticamente mejor para que llene el password sin problema
		WebElement txtFld = SeleniumDriver.getDriver().findElement( By.xpath("/html/body/app-root/app-landing-wrapper/div/app-onboarding/div/app-password-send/form/div[3]/div[2]/app-input-password/input"));
		txtFld.sendKeys( password );
		
		landingLocators.buttonSiguiente.click();
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Da click en el botón iniciar sesión para Claro Drive Video
	 */
	public void startSesionVideo(  ) {
		landingLocators.buttonIniciarSesionVideo.click();
	}
	
	/**
	 * Da click en el botón iniciar sesión para Claro Drive Negocio
	 */
	public void startSesionNegocio(  ) {
		landingLocators.buttonIniciarSesionNegocio.click();
	}
	
	/**
	 * Realizar el llenado de campos de texto para hacer un login normal sin iniciar sesión
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void putUserLoginNormal( String user, String password ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonIniciarSesion);
		landingLocators.buttonIniciarSesion.click();
		
		landingLocators.textFieldLoginNormal.click();
		landingLocators.textFieldLoginNormal.sendKeys( user );
		
		landingLocators.buttonSiguiente.click();
		
		SeleniumDriver.explicitWait( landingLocators.textFieldPassword );
		landingLocators.textFieldPassword.click();
		
		//Se añade una segunda busqueda del elemento porque al hacer click en la linea anterior aparece un error en el que ya no se encuentra asociado al elemento el Xpath
		//La dirección se corresponde a 'landingLocators.textFieldPassword' pero no se ha encontrado solución sintacticamente mejor para que llene el password sin problema
		WebElement txtFld = SeleniumDriver.getDriver().findElement( By.xpath("/html/body/app-root/app-landing-wrapper/div/app-onboarding/div/app-password-send/form/div[3]/div[2]/app-input-password/input"));
		txtFld.sendKeys( password );
		
	}
	
	/**
	 * Realizar el llenado de campos de texto para hacer un login en video sin iniciar sesión
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void putUserLoginVideo( String user, String password ) throws InterruptedException{
		loginClaroDriveVideo();
		
		landingLocators.textFieldLoginVideo.click();
		landingLocators.textFieldLoginVideo.sendKeys( user );
		
		landingLocators.textFieldPasswordVideo.click();
		landingLocators.textFieldPasswordVideo.sendKeys( password );
		
	}
	
	/**
	 * Realizar el llenado de campos de texto para hacer un login en musica sin iniciar sesión
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void putUserLoginMusica( String user, String password ) throws InterruptedException{
		loginClaroDriveMusica();
		
		landingLocators.textFieldLoginMusica.click();
		landingLocators.textFieldLoginMusica.sendKeys( user );
		
		landingLocators.textFieldPasswordMusica.click();
		landingLocators.textFieldPasswordMusica.sendKeys( password );
		
	}
	
	/**
	 * Realizar el llenado de campos de texto para hacer un login en negocio sin iniciar sesión
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void putUserLoginNegocio( String user, String password ) throws InterruptedException{
		loginClaroDriveNegocio();
		
		landingLocators.textFieldLoginNegocio.click();
		landingLocators.textFieldLoginNegocio.sendKeys( user );
		
		landingLocators.textFieldPasswordNegocio.click();
		landingLocators.textFieldPasswordNegocio.sendKeys( password );
	}
	
	/**
	 * Dirije al login por medio de Claro Video
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void loginClaroDriveVideo( ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonIniciarSesion);
		landingLocators.buttonIniciarSesion.click();
		landingLocators.footerClaroVideoMusicaRegistrate.findElement( By.linkText( "Claro video" ) ).click();
	}
	
	/**
	 * Dirije al login por medio de Claro Negocio
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void loginClaroDriveNegocio( ) throws InterruptedException{
		SeleniumDriver.explicitWait( landingLocators.buttonClaroNegocio );
		landingLocators.buttonClaroNegocio.click();
	}
	
	/**
	 * Dirije al login por medio de Claro Música
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void loginClaroDriveMusica( ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonIniciarSesion);
		landingLocators.buttonIniciarSesion.click();
		landingLocators.footerClaroVideoMusicaRegistrate.findElement( By.linkText( "Claro música" ) ).click();
	}
	
	/**
	 * Dirije al login de Claro Drive
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void loginClaroDrive( ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonIniciarSesion);
		landingLocators.buttonIniciarSesion.click();
	}
	/**
	 * Dirije al registro de Telcel
	 * @param user Usuario con el que se ingresará
	 * @param password Password del usuario
	 * @throws InterruptedException
	 */
	public void register( ) throws InterruptedException{
		landingLocators.footerClaroVideoMusicaRegistrate.findElement( By.linkText( "Regístrate" ) ).click();
	}
	
	/*
	 * Ingresa al registro
	*/
	public void registerLanding( ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.buttonRegister);
		landingLocators.buttonRegister.click();
	}
	
	/**
	 * Registro desde Claro Video.
	 * @param user Usuario para registrar
	 * @param password Password del usuario
	 * @throws InterruptedException 
	 */
	public void RegisterCV ( String user, String password ) throws InterruptedException{
		
		landingLocators.textFieldRegisterCVUser.click();
		landingLocators.textFieldRegisterCVUser.sendKeys( user );
		
		landingLocators.textFieldRegisterCVPassword.click();
		landingLocators.textFieldRegisterCVPassword.sendKeys( password );
		
		landingLocators.buttonRegisterCV.click();
		
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Valida el mensaje desplegado por el Hijack.
	 * @throws InterruptedException
	 */
	public void validateHijackMessage( ) throws InterruptedException{
		SeleniumDriver.explicitWait(landingLocators.hijackMessage);
		assertEquals( landingLocators.hijackMessage.getText(), "Esta cuenta ya fue registrada con tus datos de ClaroVideo, para continuar ingresa tus credenciales de ClaroVideo" );
		Thread.sleep(5000);
	}
	
	/**
	 * Selecciona el partner de registro de Telcel
	 */
	public void selectResgisterTelcelPartner() {
		landingLocators.buttonRegisterTelcel.click();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/register/mexico/telcel" ) );
		
	}
	
	/**
	 * Selecciona el partner de registro de Telmex
	 */
	public void selectResgisterTelmexPartner() {
		landingLocators.buttonRegisterTelmex.click();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/register/mexico/telmex" ) );
		
	}
	/**
	 * Selecciona el partner de registro de Claro Video
	 */
	public void selectResgisterClaroVideoPartner() {
		landingLocators.buttonRegisterClaroVideo.click();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/register/mexico/clarovideo" ) );
		
	}
	
	/**
	 * Compara que dos URL's sean iguales
	 * @param urlA Primer URL a comparar
	 * @param urlB Segungo URL a comparar
	 */
	public void compareEqualUrls( String urlA, String urlB ){ assertEquals( urlA, urlB ); }
	
	/**
	 * Hace click en el botoón de instagran y cambia de pestaña del navegador donde se abre.
	 * @throws InterruptedException
	 */
	public void clickOnInstagramButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonInstagram );
		landingLocators.buttonInstagram.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.instagram.com/clarodrive/" ) );	
	}
	
	public void clickOnContrata100TelcelMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telcel?price=0&storage=100" ) );
		
	}
	
	public void clickOnContrata200TelcelMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata200 );
		landingLocators.buttonContrata200.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telcel?price=19&storage=200" ) );
	}
	
	public void clickOnContrata300TelcelMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telcel?price=36&storage=300" ) );
	}
	
	public void clickOnContrata1024TelcelMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telcel?price=169&storage=1024" ) );
	}
	
	public void clickOnTelmexPlansMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonTelmexPlansMX );
		landingLocators.buttonTelmexPlansMX.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/" ) );
	}
	
	public void clickOnCreditCardPlansMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonCreditCardPlansMX );
		landingLocators.buttonCreditCardPlansMX.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/" ) );
	}
	
	public void clickOnClaroHogarPlansMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonClaroHogarPlansCO );
		landingLocators.buttonClaroHogarPlansCO.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/" ) );
	}
	
	public void clickOnNegocioPage() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonNegocioPage );
		landingLocators.buttonNegocioPage.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/microsite/mexico/claronegocio" ) );
	}
	
	public void clickOnContrata100TelmexMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telmex?price=0&storage=100" ) );
	}
	
	public void clickOnContrata200TelmexMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata200 );
		landingLocators.buttonContrata200.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telmex?price=19&storage=200" ) );
	}
	
	public void clickOnContrata300TelmexMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telmex?price=36&storage=300" ) );
	}
	
	public void clickOnContrata1024TelmexMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/telmex?price=169&storage=1024" ) );
	}
	
	public void clickOnContrata200CreditCardMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata200 );
		landingLocators.buttonContrata200.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/email?price=19&storage=200" ) );
	}
	
	public void clickOnContrata300CreditCardMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/email?price=36&storage=300" ) );
	}
	
	public void clickOnContrata1024CreditCardMXButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/mexico/email?price=169&storage=1024" ) );
	}
	
	public void clickOnContrata100ClaroMovilCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata200ClaroMovilCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata200 );
		landingLocators.buttonContrata200.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/claromovil?price=3500&storage=75" ) );
	}
	
	public void clickOnContrata400ClaroMovilCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata400 );
		landingLocators.buttonContrata400.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/claromovil?price=6000&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/claromovil?price=28000&storage=1024" ) );
	}
	
	public void clickOnContrata2048ClaroMovilCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata2048 );
		landingLocators.buttonContrata2048.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/claromovil?price=28000&storage=1024" ) );
	}
	
	public void clickOnContrata100ClaroHogarCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/fijaclaro?price=0&storage=25" ) );
	}
	
	public void clickOnContrata200ClaroHogarCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata200 );
		landingLocators.buttonContrata200.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/fijaclaro?price=3500&storage=75" ) );
	}
	
	public void clickOnContrata400ClaroHogarCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata400 );
		landingLocators.buttonContrata400.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/fijaclaro?price=6000&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroHogarCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/fijaclaro?price=28000&storage=1024" ) );
	}
	
	//Nuevo
	public void clickOnContrata2048ClaroHogarCOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata2048 );
		landingLocators.buttonContrata2048.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/colombia/fijaclaro?price=28000&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroBRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/brasil/claro?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroBRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/brasil/claro?price=2.99&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroBRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/brasil/claro?price=5.99&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroBRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/brasil/claro?price=26.9&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilGTButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/guatemala/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilGTButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/guatemala/claromovil?price=7.45&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilGTButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/guatemala/claromovil?price=14.45&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilGTButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/guatemala/claromovil?price=63.3&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilHNButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/honduras/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilHNButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/honduras/claromovil?price=24.5&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilHNButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/honduras/claromovil?price=47.2&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilHNButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/honduras/claromovil?price=216.9&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilNIButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/nicaragua/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilNIButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/nicaragua/claromovil?price=1.05&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilNIButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/nicaragua/claromovil?price=2&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilNIButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/nicaragua/claromovil?price=9.2&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilSVButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/elsalvador/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilSVButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/elsalvador/claromovil?price=1.05&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilSVButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/elsalvador/claromovil?price=2&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilSVButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/elsalvador/claromovil?price=9.05&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilCRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/costarica/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilCRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/costarica/claromovil?price=580&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilCRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/costarica/claromovil?price=1120&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilCRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/costarica/claromovil?price=5150&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilPEButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/peru/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilPEButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/peru/claromovil?price=3&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilPEButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/peru/claromovil?price=5&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilPEButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/peru/claromovil?price=20&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=0&storage=25" ) );
	}
	
	public void clickOnContrata50ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata50 );
		landingLocators.buttonContrata50.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=50&storage=50" ) );
	}
	
	public void clickOnContrata75ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=50&storage=75" ) );
	}
	
	public void clickOnContrata100ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=95&storage=100" ) );
	}
	
	public void clickOnContrata300ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=280&storage=300" ) );
	}
	
	public void clickOnContrata1024ClaroARButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/argentina/claro?price=25&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilCLButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/chile/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilCLButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/chile/claromovil?price=750&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilCLButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/chile/claromovil?price=1490&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilCLButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/chile/claromovil?price=6490&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilECButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/ecuador/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilECButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/ecuador/claromovil?price=1.11&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilECButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/ecuador/claromovil?price=2.23&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilECButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/ecuador/claromovil?price=10.07&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilPRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/puertorico/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilPRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/puertorico/claromovil?price=1&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilPRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/puertorico/claromovil?price=2&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilPRButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/puertorico/claromovil?price=10.5&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilDOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/dominicana/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata75ClaroMovilDOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata75 );
		landingLocators.buttonContrata75.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/dominicana/claromovil?price=50&storage=75" ) );
	}
	
	public void clickOnContrata150ClaroMovilDOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata150 );
		landingLocators.buttonContrata150.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/dominicana/claromovil?price=115&storage=150" ) );
	}
	
	public void clickOnContrata1024ClaroMovilDOButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata1024 );
		landingLocators.buttonContrata1024.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/dominicana/claromovil?price=450&storage=1024" ) );
	}
	
	public void clickOnContrata25ClaroMovilURButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/uruguay/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata50ClaroMovilURButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata50 );
		landingLocators.buttonContrata50.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/uruguay/claromovil?price=30&storage=50" ) );
	}
	
	public void clickOnContrata100ClaroMovilURButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/uruguay/claromovil?price=50&storage=100" ) );
	}
	
	public void clickOnContrata300ClaroMovilURButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/uruguay/claromovil?price=120&storage=300" ) );
	}
	
	public void clickOnContrata25ClaroMovilPAButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata25 );
		landingLocators.buttonContrata25.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/paraguay/claromovil?price=0&storage=25" ) );
	}
	
	public void clickOnContrata50ClaroMovilPAButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata50 );
		landingLocators.buttonContrata50.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/paraguay/claromovil?price=3000&storage=50" ) );
	}
	
	public void clickOnContrata100ClaroMovilPAButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata100 );
		landingLocators.buttonContrata100.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/paraguay/claromovil?price=5000&storage=100" ) );
	}
	
	public void clickOnContrata300ClaroMovilPAButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonContrata300 );
		landingLocators.buttonContrata300.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.clarodrive.com/register/paraguay/claromovil?price=10000&storage=300" ) );
	}
	
	/**
	 * Hace click en el botoón de twiter y cambia de pestaña del navegador donde se abre.
	 * @throws InterruptedException
	 */
	public void clickOnTwitterButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonTwiter );
		landingLocators.buttonTwiter.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://twitter.com/clarodrivemx" ) );
		
	}
	
	/**
	 * Hace click en el botoón de Facebook y cambia de pestaña del navegador donde se abre.
	 * @throws InterruptedException
	 */
	public void clickOnFacebookButton() throws InterruptedException {
		SeleniumDriver.explicitWait( landingLocators.buttonFacebook );
		landingLocators.buttonFacebook.click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
			
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "https://www.facebook.com/Claro-Drive-199778734073924/" ) );
		
	}
	
	/**
	 * Validar que se encuentre presente un elemento en especifico propio de la plataforma de la red social y cierra su ventana.
	 * @param socialMedia Cadena para seleccionar la plataforma
	 * @throws InterruptedException
	 */
	public void validateSocialMediaPageElement( String socialMedia ) throws InterruptedException {
		switch ( socialMedia ) {
			case "facebook":
				SeleniumDriver.explicitWait( landingLocators.referenceElementFacebook );
				//Se ha puesto a false porque a la fecha (221110) el enlace de FB está caido
				assertTrue( false  );	
			
				break;
			case "instagram": 
				SeleniumDriver.explicitWait( landingLocators.referenceElementInstagram );
				assertEquals( landingLocators.referenceElementInstagram.getText(), "www.clarodrive.com" );
				break;
			case "twitter":  	
				SeleniumDriver.explicitWait( landingLocators.referenceElementTwitter );
				assertEquals(  landingLocators.referenceElementTwitter.getText(),  "Claro Drive MX" ); 
				break;
			default:  break;
		}
		
		SeleniumDriver.getDriver().close();	
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		String newwin = it.next(); //Seleccionar el primer elemento en iteración
		SeleniumDriver.getDriver().switchTo().window(newwin);
	}
	
	public void validateContrataElement() throws InterruptedException {  	
		SeleniumDriver.explicitWait( landingLocators.referenceElementContrataMX );
		//Esto es una prueba México
		WebElement textoHTML = SeleniumDriver.getDriver().findElement( By.xpath("/html/body/app-root/app-landing-wrapper/div/app-onboarding/div[2]"));
		String actual = textoHTML.getText();
//		WebElement textoHTMLprueba = SeleniumDriver.getDriver().findElement( By.xpath("//*[@id=\"cont6\"]/div/app-offer/div/app-payment-methods/app-plans/div[1]/div[1]"));
//		String actualprueba = textoHTMLprueba.getText();
//		WebElement textoHTMLprueba2 = SeleniumDriver.getDriver().findElement( By.xpath(landingLocators.buttonPagePrice9));
//		String actualprueba2 = textoHTMLprueba2.getText();
//		System.out.println(ConsoleColors.RED_BACKGROUND + "Plan:" + actualprueba + "Costo: " + actualprueba2 + ConsoleColors.RESET + "\n");
		assertEquals(  landingLocators.referenceElementContrataMX.getText(), actual ); 
	}
	
	/**
	 * Hace click en el logo de Claro Drive para regresar al Landing de inicio
	 */
	public void clickOnClaroDriveButton() {
		landingLocators.buttonClaroDrive.click();
	}
	
	/**
	 * Valida que se oculte y se muestre la contraseña introducida previamente
	 */
	public void validateShowPassword() {
		WebElement btnPswd = SeleniumDriver.getDriver().findElement( By.xpath("/html/body/app-root/app-landing-wrapper/div/app-onboarding/div/app-password-send/form/div[3]/div[2]/app-input-password/div"));
		btnPswd.click();
			
		WebElement type = SeleniumDriver.getDriver().findElement( By.xpath("/html/body/app-root/app-landing-wrapper/div/app-onboarding/div/app-password-send/form/div[3]/div[2]/app-input-password/input"));
		assertEquals( "text", type.getAttribute( "type" ) );
	}
	
	/**
	 * Valida que se oculte y se muestre la contraseña introducida previamente
	 */
	public void validateShowPasswordVideo() {
		landingLocators.buttonShowPasswordVideo.click();
		assertEquals( "text", landingLocators.textFieldPasswordVideo.getAttribute( "type" ) );
	}
	
	/**
	 * Valida que se oculte y se muestre la contraseña introducida previamente
	 */
	public void validateShowPasswordNegocio() {
		landingLocators.buttonShowPasswordNegocio.click();
		assertEquals( "text", landingLocators.textFieldPasswordNegocio.getAttribute( "type" ) );
	}
	
	/**
	 * Valida que se oculte y se muestre la contraseña introducida previamente
	 */
	public void validateShowPasswordMusica() {
		landingLocators.buttonShowPasswordMusica.click();
		assertEquals( "text", landingLocators.textFieldPasswordMusica.getAttribute( "type" ) );
	}
	
	/**
	 * Validar que se cancele el login. 
	 */
	public void validateCancelLoginVideo() {
		landingLocators.buttonCancelarLoginVideo.click();
	}

	/**
	 * Validar que se cancele el login. 
	 */
	public void validateCancelLoginMusica() {
		landingLocators.buttonCancelarLoginMusica.click();

	}
	
	/**
	 * Validar que se cancele el login. 
	 */
	public void validateCancelLoginNormal() {
		landingLocators.buttonCancelarLoginNormal.click();
	}
	
	/**
	 * Validar que se dirija a recuperar password
	 */
	public void validateRecoverPassword() {
		landingLocators.buttonRecoverPassword.click();
		landingLocators.buttonReturnToStartRecoverPassword.click();
	}
	
	/**
	 * Validar que se cancela el registro para partner de Telcel
	 */
	public void validateCancelRegisterTelcel() {
		landingLocators.buttonCancelRegisterTelcel.click();
	}
	
	/**
	 * Validar que se cancela el registro para partner de Telmex
	 */
	public void validateCancelRegisterTelmex() {
		landingLocators.buttonCancelRegisterTelmex.click();
	}
	
	/**
	 * Validar la redirección a los terminos y condiciones desde registro de Telcel
	 */
	public void validateTelcelRegisterTerms() {
		assertNotNull( jsonTranslation );
		landingLocators.footerRegisterTermsPolicyTelcel.findElement( By.linkText( jsonTranslation.get( "GENERAL_TERMS_AND_CONDITIONS" ).toString() ) ).click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/mexico/legals?option=terms&country=mexico" ) );	
		
		SeleniumDriver.getDriver().close();	
		
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		it = handles.iterator(); //Refrescar el iterador
		String newwin = it.next(); //Seleccionar el primer elemento en iteración
		SeleniumDriver.getDriver().switchTo().window(newwin);
	}
	
	/**
	 * Validar la redirección a los politicas desde registro de Telcel
	 */
	public void validateTelcelRegisterPrivacyPolicies() {
		assertNotNull( this.jsonTranslation );
		landingLocators.footerRegisterTermsPolicyTelcel.findElement( By.linkText( jsonTranslation.get( "GENERAL_PRIVACY_POLICY" ).toString() ) ).click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/mexico/legals?option=privacy&country=mexico" ) );
		
		SeleniumDriver.getDriver().close();	
		
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		it = handles.iterator();
		String newwin = it.next();
		SeleniumDriver.getDriver().switchTo().window(newwin);
		
	}
	
	/**
	 * Validar la redirección a los terminos y condiciones desde registro de Telmex
	 */
	public void validateTelmexRegisterTerms() {
		assertNotNull( jsonTranslation );
		landingLocators.footerRegisterTermsPolicyTelmex.findElement( By.linkText( jsonTranslation.get( "GENERAL_TERMS_AND_CONDITIONS" ).toString() ) ).click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/mexico/legals?option=terms" ) );	
		
		SeleniumDriver.getDriver().close();	
		
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		it = handles.iterator(); //Refrescar el iterador
		String newwin = it.next(); //Seleccionar el primer elemento en iteración
		SeleniumDriver.getDriver().switchTo().window(newwin);
	}
	
	/**
	 * Validar la redirección a los politicas desde registro de Telmex
	 */
	public void validateTelmexRegisterPrivacyPolicies() {
		assertNotNull( this.jsonTranslation );
		landingLocators.footerRegisterTermsPolicyTelmex.findElement( By.linkText( jsonTranslation.get( "GENERAL_PRIVACY_POLICY" ).toString() ) ).click();
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
		
		assertTrue( SeleniumDriver.getDriver().getCurrentUrl().contains( "/mexico/legals?option=privacy" ) );
		
		SeleniumDriver.getDriver().close();	
		
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		it = handles.iterator();
		String newwin = it.next();
		SeleniumDriver.getDriver().switchTo().window(newwin);
		
	}
	
	/**
	 * Valida los precios de los paquetes de Claro Drive segun el servicio APA para el nodo Country Microsite Negocio.
	 */
	public void validatePricesCountryMicrositeNegocio() {
		try {
			assertNotNull( jsonApaMetadata );
			JSONParser parser = new JSONParser();
			JSONObject jsonCountries = (JSONObject) parser.parse( jsonApaMetadata.getAsString("countries_microsite_negocio") ); //Se des escapa y se vuelve a parsear
			
			//System.out.println(ConsoleColors.YELLOW_BACKGROUND +"APA NEGOCIO: " + jsonCountries + ConsoleColors.RESET + "\n"); //Parsear sobre jsonCountries
			
			Iterator<String> keys = jsonCountries.keySet().iterator();
			
			changeCountry(CountryElements.MEXICO);
			clickOnNegocioPage();
			
			while(keys.hasNext()) {
				String key = keys.next();
			    JSONObject tmpJson = (JSONObject) jsonCountries.get( key );
			    tmpJson = (JSONObject)tmpJson.get("paymentMethods");
			    
			    String json = tmpJson.toString();
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(json);
				
			    switch ( key ) {
					case "MX": 
						changeCountryNegocio(CountryElementsNegocio.MEXICO); 
						
						//--------------------------PLAN UNO NEGOCIO (100GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAMX.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAMX.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAMX.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageMX.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (300GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAMX.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAMX.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPAMX.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAMX.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPAMX.add(rootNode.path("default").path("plans").path("300").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1024GB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAMX.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAMX.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPAMX.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAMX.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPAMX.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageMX.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageMX.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
			    		
					break;
					case "CO": 
						changeCountryNegocio(CountryElementsNegocio.COLOMBIA);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPACO.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPACO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPACO.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageCO.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPACO.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPACO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						//landingLocators.listPlanDosNegocioAPACO.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPACO.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPACO.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageCO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageCO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPACO.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPACO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		//landingLocators.listPlanTresNegocioAPACO.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPACO.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPACO.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageCO.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageCO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageCO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
					
					break;
					
					case "BR":  
						changeCountryNegocio(CountryElementsNegocio.BRAZIL);
						
						//--------------------------PLAN UNO NEGOCIO (30GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPABR.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPABR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPABR.add(rootNode.path("default").path("plans").path("30").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageBR.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPABR.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPABR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPABR.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPABR.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPABR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPABR.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
					
			    		//--------------------------PLAN CUATRO NEGOCIO (1.5TB)

			    		//Valores de APA
			    		landingLocators.listPlanCuatroNegocioAPABR.add(getPlansNode(rootNode, "default").get(3));
			    		landingLocators.listPlanCuatroNegocioAPABR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanCuatroNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanCuatroNegocioAPABR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanCuatroNegocioAPABR.add(rootNode.path("default").path("plans").path("1536").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroNegocioPageBR.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroNegocioPageBR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    		
					
					break;
					
					case "GT":
						changeCountryNegocio(CountryElementsNegocio.GUATEMALA);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAGT.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAGT.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAGT.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageGT.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (350GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAGT.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAGT.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						//landingLocators.listPlanDosNegocioAPAGT.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAGT.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPAGT.add(rootNode.path("default").path("plans").path("350").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						//landingLocators.listPlanDosNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAGT.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAGT.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		//landingLocators.listPlanTresNegocioAPAGT.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAGT.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPAGT.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageGT.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		//landingLocators.listPlanTresNegocioPageGT.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
					break;
					
					case "HN":
						changeCountryNegocio(CountryElementsNegocio.HONDURAS);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAHN.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAHN.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAHN.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageHN.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (350GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAHN.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAHN.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPAHN.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAHN.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPAHN.add(rootNode.path("default").path("plans").path("350").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAHN.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAHN.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPAHN.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAHN.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPAHN.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageHN.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageHN.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
					
					break;
					
					case "NI":
						changeCountryNegocio(CountryElementsNegocio.NICARAGUA);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)

			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPANI.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPANI.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPANI.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageNI.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (350GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPANI.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPANI.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPANI.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPANI.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPANI.add(rootNode.path("default").path("plans").path("350").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)

			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPANI.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPANI.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPANI.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPANI.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPANI.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageNI.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageNI.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
					break;
					
					case "SV": 
						changeCountryNegocio(CountryElementsNegocio.EL_SALVADOR);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPASV.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPASV.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPASV.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageSV.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (350GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPASV.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPASV.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPASV.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPASV.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPASV.add(rootNode.path("default").path("plans").path("350").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPASV.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPASV.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPASV.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPASV.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPASV.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageSV.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageSV.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
		    		
					case "CR":
						changeCountryNegocio(CountryElementsNegocio.COSTA_RICA);
						
						//--------------------------PLAN UNO NEGOCIO (100GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPACR.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPACR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPACR.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageCR.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (350GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPACR.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPACR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPACR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPACR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosNegocioAPACR.add(rootNode.path("default").path("plans").path("350").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPACR.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPACR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPACR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPACR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1));
			    		landingLocators.listPlanTresNegocioAPACR.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageCR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageCR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
						
					case "PE": 
			    		changeCountryNegocio(CountryElementsNegocio.PERU);
						
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAPE.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAPE.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAPE.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPagePE.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAPE.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAPE.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						//landingLocators.listPlanDosNegocioAPAPE.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAPE.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPAPE.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						//landingLocators.listPlanDosNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAPE.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAPE.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		//landingLocators.listPlanTresNegocioAPAPE.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAPE.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPAPE.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPagePE.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		//landingLocators.listPlanTresNegocioPagePE.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
	    		
					case "AR": 
						changeCountryNegocio(CountryElementsNegocio.ARGENTINA);
						
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAAR.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAAR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAAR.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageAR.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAAR.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAAR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPAAR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAAR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPAAR.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
						landingLocators.listPlanDosNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAAR.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAAR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPAAR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAAR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPAAR.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageAR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
			    		landingLocators.listPlanTresNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageAR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
						
		    		break;
					
					case "CL": 
						changeCountryNegocio(CountryElementsNegocio.CHILE);
					
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPACL.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPACL.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPACL.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageCL.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPACL.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPACL.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPACL.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPACL.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPACL.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPACL.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPACL.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPACL.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPACL.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPACL.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageCL.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageCL.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
	    		
					case "EC": 
						changeCountryNegocio(CountryElementsNegocio.ECUADOR);
						
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAEC.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAEC.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAEC.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageEC.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAEC.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAEC.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPAEC.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAEC.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPAEC.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAEC.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAEC.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPAEC.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAEC.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPAEC.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageEC.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageEC.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
		    		
					case "PR": 
						changeCountryNegocio(CountryElementsNegocio.PUERTO_RICO);
						
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPAPR.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPAPR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPAPR.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPagePR.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPAPR.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPAPR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPAPR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPAPR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPAPR.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPAPR.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPAPR.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPAPR.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPAPR.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPAPR.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPagePR.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPagePR.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break;
						
					case "DO":
						changeCountryNegocio(CountryElementsNegocio.PUERTO_RICO);
						
						//--------------------------PLAN UNO NEGOCIO (25GB)
	
			    		//Valores de APA
						landingLocators.listPlanUnoNegocioAPADO.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoNegocioAPADO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoNegocioAPADO.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoNegocioPageDO.add(getPageStringElement(landingLocators.buttonPagePrice).toUpperCase());
					
			            //--------------------------PLAN DOS NEGOCIO (250GB)
			    		
			    		//Valores de APA
						landingLocators.listPlanDosNegocioAPADO.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosNegocioAPADO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosNegocioAPADO.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
						landingLocators.listPlanDosNegocioAPADO.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).substring(0, 1));
						landingLocators.listPlanDosNegocioAPADO.add(rootNode.path("default").path("plans").path("250").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanDosNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0));
						landingLocators.listPlanDosNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1));
						landingLocators.listPlanDosNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
						landingLocators.listPlanDosNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
						landingLocators.listPlanDosNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    		
			    	    //--------------------------PLAN TRES NEGOCIO (1TB)
	
			    		//Valores de APA
			    		landingLocators.listPlanTresNegocioAPADO.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresNegocioAPADO.add(getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresNegocioAPADO.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(0));
			    		landingLocators.listPlanTresNegocioAPADO.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(1).substring(0, 1));
			    		landingLocators.listPlanTresNegocioAPADO.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanTresNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0));
			    		landingLocators.listPlanTresNegocioPageDO.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1));
			    		landingLocators.listPlanTresNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    		landingLocators.listPlanTresNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    		landingLocators.listPlanTresNegocioPageDO.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));
						
		    		break; 
		    		
					default: break;
				}
			}
			
			System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- M A S I V O -------------------------------------" + ConsoleColors.RESET);
    		//MX
			System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- M E X I C O -------------------------------------" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELCEL100: " + landingLocators.listPlanUnoAPAMXUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELCEL100: " + landingLocators.listPlanUnoPageMXUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAMXUno, landingLocators.listPlanUnoPageMXUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELCEL200: " + landingLocators.listPlanDosAPAMXUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELCEL200: " + landingLocators.listPlanDosPageMXUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAMXUno, landingLocators.listPlanDosPageMXUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELCEL300: " + landingLocators.listPlanTresAPAMXUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELCEL300: " + landingLocators.listPlanTresPageMXUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAMXUno, landingLocators.listPlanTresPageMXUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELCEL1024: " + landingLocators.listPlanCuatroAPAMXUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELCEL1024: " + landingLocators.listPlanCuatroPageMXUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAMXUno, landingLocators.listPlanCuatroPageMXUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELMEX100: " + landingLocators.listPlanUnoAPAMXDos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELMEX100: " + landingLocators.listPlanUnoPageMXDos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAMXDos, landingLocators.listPlanUnoPageMXDos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELMEX200: " + landingLocators.listPlanDosAPAMXDos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELMEX200: " + landingLocators.listPlanDosPageMXDos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAMXDos, landingLocators.listPlanDosPageMXDos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELMEX300: " + landingLocators.listPlanTresAPAMXDos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELMEX300: " + landingLocators.listPlanTresPageMXDos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAMXDos, landingLocators.listPlanTresPageMXDos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX TELMEX1024: " + landingLocators.listPlanCuatroAPAMXDos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX TELMEX1024: " + landingLocators.listPlanCuatroPageMXDos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAMXDos, landingLocators.listPlanCuatroPageMXDos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX CREDITCARD200: " + landingLocators.listPlanUnoAPAMXTres + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX CREDITCARD200: " + landingLocators.listPlanUnoPageMXTres + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAMXTres, landingLocators.listPlanUnoPageMXTres);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX CREDITCARD300: " + landingLocators.listPlanDosAPAMXTres + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX CREDITCARD300: " + landingLocators.listPlanDosPageMXTres + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAMXTres, landingLocators.listPlanDosPageMXTres);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MX CREDITCARD1024: " + landingLocators.listPlanTresAPAMXTres + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MX CREDITCARD1024: " + landingLocators.listPlanTresPageMXTres + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAMXTres, landingLocators.listPlanTresPageMXTres);
    		
    		//COL
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- C O L O M B I A ---------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROMOVIL100: " + landingLocators.listPlanUnoAPACOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROMOVIL100: " + landingLocators.listPlanUnoPageCOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPACOUno, landingLocators.listPlanUnoPageCOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROMOVIL200: " + landingLocators.listPlanDosAPACOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROMOVIL200: " + landingLocators.listPlanDosPageCOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPACOUno, landingLocators.listPlanDosPageCOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROMOVIL400: " + landingLocators.listPlanTresAPACOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROMOVIL400: " + landingLocators.listPlanTresPageCOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPACOUno, landingLocators.listPlanTresPageCOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROMOVIL1024: " + landingLocators.listPlanCuatroAPACOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROMOVIL1024: " + landingLocators.listPlanCuatroPageCOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPACOUno, landingLocators.listPlanCuatroPageCOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROMOVIL2048: " + landingLocators.listPlanCincoAPACOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROMOVIL2048: " + landingLocators.listPlanCincoPageCOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCincoAPACOUno, landingLocators.listPlanCincoPageCOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROHOGAR100: " + landingLocators.listPlanUnoAPACODos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROHOGAR100: " + landingLocators.listPlanUnoPageCODos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPACODos, landingLocators.listPlanUnoPageCODos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROHOGAR200: " + landingLocators.listPlanDosAPACODos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROHOGAR200: " + landingLocators.listPlanDosPageCODos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPACODos, landingLocators.listPlanDosPageCODos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROHOGAR400: " + landingLocators.listPlanTresAPACODos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROHOGAR400: " + landingLocators.listPlanTresPageCODos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPACODos, landingLocators.listPlanTresPageCODos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROHOGAR1024: " + landingLocators.listPlanCuatroAPACODos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROHOGAR1024: " + landingLocators.listPlanCuatroPageCODos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPACODos, landingLocators.listPlanCuatroPageCODos);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CO CLAROHOGAR2048: " + landingLocators.listPlanCincoAPACODos + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CO CLAROHOGAR2048: " + landingLocators.listPlanCincoPageCODos + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCincoAPACODos, landingLocators.listPlanCincoPageCODos);
    		
    		//BR
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- B R A S I L -------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BR CLAROH25: " + landingLocators.listPlanUnoAPABRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BR CLARO25: " + landingLocators.listPlanUnoPageBRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPABRUno, landingLocators.listPlanUnoPageBRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BR CLARO75: " + landingLocators.listPlanDosAPABRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BR CLARO75: " + landingLocators.listPlanDosPageBRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPABRUno, landingLocators.listPlanDosPageBRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BR CLARO150: " + landingLocators.listPlanTresAPABRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BR CLARO150: " + landingLocators.listPlanTresPageBRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPABRUno, landingLocators.listPlanTresPageBRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BR CLARO1TB: " + landingLocators.listPlanCuatroAPABRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BR CLARO1TB: " + landingLocators.listPlanCuatroPageBRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPABRUno, landingLocators.listPlanCuatroPageBRUno);
    		
    		//GT
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- G U A T E M A L A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GT CLAROMOVIL25: " + landingLocators.listPlanUnoAPAGTUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GT CLAROMOVIL25: " + landingLocators.listPlanUnoPageGTUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAGTUno, landingLocators.listPlanUnoPageGTUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GT CLAROMOVIL75: " + landingLocators.listPlanDosAPAGTUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GT CLAROMOVIL75: " + landingLocators.listPlanDosPageGTUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAGTUno, landingLocators.listPlanDosPageGTUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GT CLAROMOVIL150: " + landingLocators.listPlanTresAPAGTUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GT CLAROMOVIL150: " + landingLocators.listPlanTresPageGTUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAGTUno, landingLocators.listPlanTresPageGTUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GT CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPAGTUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GT CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageGTUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAGTUno, landingLocators.listPlanCuatroPageGTUno);
    		
    		//HN
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- H O N D U R A S ---------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HN CLAROMOVIL25: " + landingLocators.listPlanUnoAPAHNUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HN CLAROMOVIL25: " + landingLocators.listPlanUnoPageHNUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAHNUno, landingLocators.listPlanUnoPageHNUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HN CLAROMOVIL75: " + landingLocators.listPlanDosAPAHNUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HN CLAROMOVIL75: " + landingLocators.listPlanDosPageHNUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAHNUno, landingLocators.listPlanDosPageHNUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HN CLAROMOVIL150: " + landingLocators.listPlanTresAPAHNUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HN CLAROMOVIL150: " + landingLocators.listPlanTresPageHNUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAHNUno, landingLocators.listPlanTresPageHNUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HN CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPAHNUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HN CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageHNUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAHNUno, landingLocators.listPlanCuatroPageHNUno);
    		
    		//NI
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- N I C A R A G U A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NI CLAROMOVIL25: " + landingLocators.listPlanUnoAPANIUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NI CLAROMOVIL25: " + landingLocators.listPlanUnoPageNIUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPANIUno, landingLocators.listPlanUnoPageNIUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NI CLAROMOVIL75: " + landingLocators.listPlanDosAPANIUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NI CLAROMOVIL75: " + landingLocators.listPlanDosPageNIUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPANIUno, landingLocators.listPlanDosPageNIUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NI CLAROMOVIL150: " + landingLocators.listPlanTresAPANIUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NI CLAROMOVIL150: " + landingLocators.listPlanTresPageNIUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPANIUno, landingLocators.listPlanTresPageNIUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NI CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPANIUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NI CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageNIUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPANIUno, landingLocators.listPlanCuatroPageNIUno);
    		
    		//SV
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- E L  S A L V A D O R ----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SV CLAROMOVIL25: " + landingLocators.listPlanUnoAPASVUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SV CLAROMOVIL25: " + landingLocators.listPlanUnoPageSVUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPASVUno, landingLocators.listPlanUnoPageSVUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SV CLAROMOVIL75: " + landingLocators.listPlanDosAPASVUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SV CLAROMOVIL75: " + landingLocators.listPlanDosPageSVUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPASVUno, landingLocators.listPlanDosPageSVUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SV CLAROMOVIL150: " + landingLocators.listPlanTresAPASVUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SV CLAROMOVIL150: " + landingLocators.listPlanTresPageSVUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPASVUno, landingLocators.listPlanTresPageSVUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SV CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPASVUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SV CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageSVUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPASVUno, landingLocators.listPlanCuatroPageSVUno);
    		
    		//CR
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- C O S T A  R I C A ------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CR CLAROMOVIL25: " + landingLocators.listPlanUnoAPACRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CR CLAROMOVIL25: " + landingLocators.listPlanUnoPageCRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPACRUno, landingLocators.listPlanUnoPageCRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CR CLAROMOVIL75: " + landingLocators.listPlanDosAPACRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CR CLAROMOVIL75: " + landingLocators.listPlanDosPageCRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPACRUno, landingLocators.listPlanDosPageCRUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CR CLAROMOVIL150: " + landingLocators.listPlanTresAPACRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CR CLAROMOVIL150: " + landingLocators.listPlanTresPageCRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPACRUno, landingLocators.listPlanTresPageCRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CR CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPACRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CR CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageCRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPACRUno, landingLocators.listPlanCuatroPageCRUno);
    		
    		//PE
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- P E R U -----------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PE CLAROMOVIL25: " + landingLocators.listPlanUnoAPAPEUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PE CLAROMOVIL25: " + landingLocators.listPlanUnoPagePEUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAPEUno, landingLocators.listPlanUnoPagePEUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PE CLAROMOVIL75: " + landingLocators.listPlanDosAPAPEUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PE CLAROMOVIL75: " + landingLocators.listPlanDosPagePEUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAPEUno, landingLocators.listPlanDosPagePEUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PE CLAROMOVIL150: " + landingLocators.listPlanTresAPAPEUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PE CLAROMOVIL150: " + landingLocators.listPlanTresPagePEUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAPEUno, landingLocators.listPlanTresPagePEUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PE CLAROMOVIL1TB: " + landingLocators.listPlanCuatroAPAPEUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PE CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPagePEUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAPEUno, landingLocators.listPlanCuatroPagePEUno);
    		
    		//AR
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- A R G E N T I N A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list AR CLAROMOVIL25: " + landingLocators.listPlanUnoAPAARUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list AR CLAROMOVIL25: " + landingLocators.listPlanUnoPageARUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAARUno, landingLocators.listPlanUnoPageARUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list AR CLAROMOVIL50: " + landingLocators.listPlanDosAPAARUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list AR CLAROMOVIL50: " + landingLocators.listPlanDosPageARUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAARUno, landingLocators.listPlanDosPageARUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list AR CLAROMOVIL75: " + landingLocators.listPlanTresAPAARUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list AR CLAROMOVIL75: " + landingLocators.listPlanTresPageARUno + ConsoleColors.RESET);
    		compareAPAtoPagePromo(landingLocators.listPlanTresAPAARUno, landingLocators.listPlanTresPageARUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list AR CLAROMOVIL100: " + landingLocators.listPlanCuatroAPAARUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list AR CLAROMOVIL100: " + landingLocators.listPlanCuatroPageARUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAARUno, landingLocators.listPlanCuatroPageARUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list AR CLAROMOVIL300GB: " + landingLocators.listPlanCincoAPAARUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list AR CLAROMOVIL300GB: " + landingLocators.listPlanCincoPageARUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCincoAPAARUno, landingLocators.listPlanCincoPageARUno);
    		
    		//CL
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- C H I L E ---------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CL CLAROMOVIL25: " + landingLocators.listPlanUnoAPACLUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CL CLAROMOVIL25: " + landingLocators.listPlanUnoPageCLUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPACLUno, landingLocators.listPlanUnoPageCLUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CL CLAROMOVIL75: " + landingLocators.listPlanDosAPACLUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CL CLAROMOVIL75: " + landingLocators.listPlanDosPageCLUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPACLUno, landingLocators.listPlanDosPageCLUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CL CLAROMOVIL150: " + landingLocators.listPlanTresAPACLUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CL CLAROMOVIL150: " + landingLocators.listPlanTresPageCLUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPACLUno, landingLocators.listPlanTresPageCLUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CL CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroAPACLUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CL CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageCLUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPACLUno, landingLocators.listPlanCuatroPageCLUno);
    		
    		//EC
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- E C U A D O R -----------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list EC CLAROMOVIL25: " + landingLocators.listPlanUnoAPAECUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list EC CLAROMOVIL25: " + landingLocators.listPlanUnoPageECUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAECUno, landingLocators.listPlanUnoPageECUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list EC CLAROMOVIL75: " + landingLocators.listPlanDosAPAECUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list EC CLAROMOVIL75: " + landingLocators.listPlanDosPageECUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAECUno, landingLocators.listPlanDosPageECUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list EC CLAROMOVIL150: " + landingLocators.listPlanTresAPAECUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list EC CLAROMOVIL150: " + landingLocators.listPlanTresPageECUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAECUno, landingLocators.listPlanTresPageECUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list EC CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroAPAECUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list EC CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageECUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAECUno, landingLocators.listPlanCuatroPageECUno);
    		
    		//PR
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- P U E R T O  R I C O ----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PR CLAROMOVIL25: " + landingLocators.listPlanUnoAPAPRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PR CLAROMOVIL25: " + landingLocators.listPlanUnoPagePRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAPRUno, landingLocators.listPlanUnoPagePRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PR CLAROMOVIL75: " + landingLocators.listPlanDosAPAPRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PR CLAROMOVIL75: " + landingLocators.listPlanDosPagePRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAPRUno, landingLocators.listPlanDosPagePRUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PR CLAROMOVIL150: " + landingLocators.listPlanTresAPAPRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PR CLAROMOVIL150: " + landingLocators.listPlanTresPagePRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAPRUno, landingLocators.listPlanTresPagePRUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PR CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroAPAPRUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PR CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPagePRUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAPRUno, landingLocators.listPlanCuatroPagePRUno);
    		
    		//DO
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- D O M I N I C A N A -----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DO CLAROMOVIL25: " + landingLocators.listPlanUnoAPADOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DO CLAROMOVIL25: " + landingLocators.listPlanUnoPageDOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPADOUno, landingLocators.listPlanUnoPageDOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DO CLAROMOVIL75: " + landingLocators.listPlanDosAPADOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DO CLAROMOVIL75: " + landingLocators.listPlanDosPageDOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPADOUno, landingLocators.listPlanDosPageDOUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DO CLAROMOVIL150: " + landingLocators.listPlanTresPageDOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPADOUno, landingLocators.listPlanTresPageDOUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DO CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroAPADOUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DO CLAROMOVIL1024GB: " + landingLocators.listPlanCuatroPageDOUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPADOUno, landingLocators.listPlanCuatroPageDOUno);
    		
    		//UY
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- U R U G U A Y -----------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list UY CLAROMOVIL25: " + landingLocators.listPlanUnoAPAUYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list UY CLAROMOVIL25: " + landingLocators.listPlanUnoPageUYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAUYUno, landingLocators.listPlanUnoPageUYUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list UY CLAROMOVIL50: " + landingLocators.listPlanDosAPAUYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list UY CLAROMOVIL50: " + landingLocators.listPlanDosPageUYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAUYUno, landingLocators.listPlanDosPageUYUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list UY CLAROMOVIL100: " + landingLocators.listPlanTresAPAUYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list UY CLAROMOVIL100: " + landingLocators.listPlanTresPageUYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAUYUno, landingLocators.listPlanTresPageUYUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list UY CLAROMOVIL300GB: " + landingLocators.listPlanCuatroAPAUYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list UY CLAROMOVIL300GB: " + landingLocators.listPlanCuatroPageUYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAUYUno, landingLocators.listPlanCuatroPageUYUno);
    		
    		//PY
    		System.out.println(ConsoleColors.YELLOW_BACKGROUND + "\n--------------------------------- P A R A G U A Y ---------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PY CLAROMOVIL25: " + landingLocators.listPlanUnoAPAPYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PY CLAROMOVIL25: " + landingLocators.listPlanUnoPagePYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoAPAPYUno, landingLocators.listPlanUnoPagePYUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PY CLAROMOVIL50: " + landingLocators.listPlanDosAPAPYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PY CLAROMOVIL50: " + landingLocators.listPlanDosPagePYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosAPAPYUno, landingLocators.listPlanDosPagePYUno);

    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PY CLAROMOVIL100: " + landingLocators.listPlanTresAPAPYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PY CLAROMOVIL100: " + landingLocators.listPlanTresPagePYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresAPAPYUno, landingLocators.listPlanTresPagePYUno);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PY CLAROMOVIL300GB: " + landingLocators.listPlanCuatroAPAPYUno + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PY CLAROMOVIL300GB: " + landingLocators.listPlanCuatroPagePYUno + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroAPAPYUno, landingLocators.listPlanCuatroPagePYUno);
    		
    		
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- N E G O C I O -----------------------------------" + ConsoleColors.RESET);
			//MX
			System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- M E X I C O -------------------------------------" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MXNegocio100: " + landingLocators.listPlanUnoNegocioAPAMX + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MXNegocio100: " + landingLocators.listPlanUnoNegocioPageMX + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAMX, landingLocators.listPlanUnoNegocioPageMX);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MXNegocio300: " + landingLocators.listPlanDosNegocioAPAMX + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MXNegocio300: " + landingLocators.listPlanDosNegocioPageMX + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAMX, landingLocators.listPlanDosNegocioPageMX);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list MXNegocio1T: " + landingLocators.listPlanTresNegocioAPAMX + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list MXNegocio1T: " + landingLocators.listPlanTresNegocioPageMX + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAMX, landingLocators.listPlanTresNegocioPageMX);
    		
    		//COL
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- C O L O M B I A ---------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CONegocio100: " + landingLocators.listPlanUnoNegocioAPACO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CONegocio100: " + landingLocators.listPlanUnoNegocioPageCO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPACO, landingLocators.listPlanUnoNegocioPageCO);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CONegocio250: " + landingLocators.listPlanDosNegocioAPACO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CONegocio250: " + landingLocators.listPlanDosNegocioPageCO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPACO, landingLocators.listPlanDosNegocioPageCO);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CONegocio1tb: " + landingLocators.listPlanTresNegocioAPACO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CONegocio1tb: " + landingLocators.listPlanTresNegocioPageCO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPACO, landingLocators.listPlanTresNegocioPageCO);
    		
    		//BR
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- B R A S I L -------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BRNegocio30: " + landingLocators.listPlanUnoNegocioAPABR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BRNegocio30: " + landingLocators.listPlanUnoNegocioPageBR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPABR, landingLocators.listPlanUnoNegocioPageBR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BRNegocio250: " + landingLocators.listPlanDosNegocioAPABR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BRNegocio250: " + landingLocators.listPlanDosNegocioPageBR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPABR, landingLocators.listPlanDosNegocioPageBR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BRNegocio1tb: " + landingLocators.listPlanTresNegocioAPABR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BRNegocio1tb: " + landingLocators.listPlanTresNegocioPageBR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPABR, landingLocators.listPlanTresNegocioPageBR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list BRNegocio1.5tb: " + landingLocators.listPlanCuatroNegocioAPABR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BRNegocio1.5tb: " + landingLocators.listPlanCuatroNegocioPageBR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanCuatroNegocioAPABR, landingLocators.listPlanCuatroNegocioPageBR);
    		
    		//GT
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- G U A T E M A L A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GTNegocio100: " + landingLocators.listPlanUnoNegocioAPAGT + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GTNegocio100: " + landingLocators.listPlanUnoNegocioPageGT + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAGT, landingLocators.listPlanUnoNegocioPageGT);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GTNegocio350: " + landingLocators.listPlanDosNegocioAPAGT + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list BRNegocio350: " + landingLocators.listPlanDosNegocioPageGT + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAGT, landingLocators.listPlanDosNegocioPageGT);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list GTNegocio1tb: " + landingLocators.listPlanTresNegocioAPAGT + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list GTNegocio1tb: " + landingLocators.listPlanTresNegocioPageGT + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAGT, landingLocators.listPlanTresNegocioPageGT);
    		
    		//HN
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- H O N D U R A S ---------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HNNegocio100: " + landingLocators.listPlanUnoNegocioAPAHN + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HNNegocio100: " + landingLocators.listPlanUnoNegocioPageHN + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAHN, landingLocators.listPlanUnoNegocioPageHN);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HNNegocio350: " + landingLocators.listPlanDosNegocioAPAHN + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HNNegocio350: " + landingLocators.listPlanDosNegocioPageHN + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAHN, landingLocators.listPlanDosNegocioPageHN);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list HNNegocio1tb: " + landingLocators.listPlanTresNegocioAPAHN + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HNNegocio1tb: " + landingLocators.listPlanTresNegocioPageHN + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAHN, landingLocators.listPlanTresNegocioPageHN);
    		
    		//NI
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- N I C A R A G U A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NINegocio100: " + landingLocators.listPlanUnoNegocioAPANI + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list HNNegocio100: " + landingLocators.listPlanUnoNegocioPageNI + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPANI, landingLocators.listPlanUnoNegocioPageNI);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NINegocio350: " + landingLocators.listPlanDosNegocioAPANI + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NINegocio350: " + landingLocators.listPlanDosNegocioPageNI + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPANI, landingLocators.listPlanDosNegocioPageNI);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list NINegocio1tb: " + landingLocators.listPlanTresNegocioAPANI + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list NINegocio1tb: " + landingLocators.listPlanTresNegocioPageNI + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPANI, landingLocators.listPlanTresNegocioPageNI);
    		
    		//SV
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- E L  S A L V A D O R ----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SVNegocio100: " + landingLocators.listPlanUnoNegocioAPASV + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SVNegocio100: " + landingLocators.listPlanUnoNegocioPageSV + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPASV, landingLocators.listPlanUnoNegocioPageSV);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SVNegocio350: " + landingLocators.listPlanDosNegocioAPASV + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SVNegocio350: " + landingLocators.listPlanDosNegocioPageSV + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPASV, landingLocators.listPlanDosNegocioPageSV);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list SVNegocio1tb: " + landingLocators.listPlanTresNegocioAPASV + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list SVNegocio1tb: " + landingLocators.listPlanTresNegocioPageSV + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPASV, landingLocators.listPlanTresNegocioPageSV);
    		
    		//CR
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- C O S T A  R I C A ------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CRNegocio100: " + landingLocators.listPlanUnoNegocioAPACR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CRNegocio100: " + landingLocators.listPlanUnoNegocioPageCR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPACR, landingLocators.listPlanUnoNegocioPageCR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CRNegocio350: " + landingLocators.listPlanDosNegocioAPACR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CRNegocio350: " + landingLocators.listPlanDosNegocioPageCR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPACR, landingLocators.listPlanDosNegocioPageCR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CRNegocio1tb: " + landingLocators.listPlanTresNegocioAPACR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CRNegocio1tb: " + landingLocators.listPlanTresNegocioPageCR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPACR, landingLocators.listPlanTresNegocioPageCR);
    		
    		//PE
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- P E R U -----------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PENegocio25: " + landingLocators.listPlanUnoNegocioAPAPE + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PENegocio25: " + landingLocators.listPlanUnoNegocioPagePE + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAPE, landingLocators.listPlanUnoNegocioPagePE);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PENegocio250: " + landingLocators.listPlanDosNegocioAPAPE + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PENegocio250: " + landingLocators.listPlanDosNegocioPagePE + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAPE, landingLocators.listPlanDosNegocioPagePE);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PENegocio1tb: " + landingLocators.listPlanTresNegocioAPAPE + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PENegocio1tb: " + landingLocators.listPlanTresNegocioPagePE + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAPE, landingLocators.listPlanTresNegocioPagePE);
    		
    		//AR
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- A R G E N T I N A -------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ARNegocio25: " + landingLocators.listPlanUnoNegocioAPAAR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ARNegocio25: " + landingLocators.listPlanUnoNegocioPageAR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAAR, landingLocators.listPlanUnoNegocioPageAR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ARNegocio250: " + landingLocators.listPlanDosNegocioAPAAR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ARNegocio250: " + landingLocators.listPlanDosNegocioPageAR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAAR, landingLocators.listPlanDosNegocioPageAR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ARNegocio1tb: " + landingLocators.listPlanTresNegocioAPAAR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ARNegocio1tb: " + landingLocators.listPlanTresNegocioPageAR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAAR, landingLocators.listPlanTresNegocioPageAR);
    		
    		//CL
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- C H I L E ---------------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CLNegocio25: " + landingLocators.listPlanUnoNegocioAPACL + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CLNegocio25: " + landingLocators.listPlanUnoNegocioPageCL + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPACL, landingLocators.listPlanUnoNegocioPageCL);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CLNegocio250: " + landingLocators.listPlanDosNegocioAPACL + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CLNegocio250: " + landingLocators.listPlanDosNegocioPageCL + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPACL, landingLocators.listPlanDosNegocioPageCL);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list CLNegocio1tb: " + landingLocators.listPlanTresNegocioAPACL + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list CLNegocio1tb: " + landingLocators.listPlanTresNegocioPageCL + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPACL, landingLocators.listPlanTresNegocioPageCL);
    		
    		//EC
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- E C U A D O R -----------------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ECNegocio25: " + landingLocators.listPlanUnoNegocioAPAEC + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ECNegocio25: " + landingLocators.listPlanUnoNegocioPageEC + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAEC, landingLocators.listPlanUnoNegocioPageEC);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ECNegocio250: " + landingLocators.listPlanDosNegocioAPAEC + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ECNegocio250: " + landingLocators.listPlanDosNegocioPageEC + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAEC, landingLocators.listPlanDosNegocioPageEC);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list ECNegocio1tb: " + landingLocators.listPlanTresNegocioAPAEC + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list ECNegocio1tb: " + landingLocators.listPlanTresNegocioPageEC + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAEC, landingLocators.listPlanTresNegocioPageEC);
    		
    		//PR
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- P U E R T O  R I C O ----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PRNegocio25: " + landingLocators.listPlanUnoNegocioAPAPR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PRNegocio25: " + landingLocators.listPlanUnoNegocioPagePR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPAPR, landingLocators.listPlanUnoNegocioPagePR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PRNegocio250: " + landingLocators.listPlanDosNegocioAPAPR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PRNegocio250: " + landingLocators.listPlanDosNegocioPagePR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPAPR, landingLocators.listPlanDosNegocioPagePR);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list PRNegocio1tb: " + landingLocators.listPlanTresNegocioAPAPR + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list PRNegocio1tb: " + landingLocators.listPlanTresNegocioPagePR + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPAPR, landingLocators.listPlanTresNegocioPagePR);
    		
    		//DO
    		System.out.println(ConsoleColors.PURPLE_BACKGROUND + "\n--------------------------------- D O M I N I C A N A -----------------------------" + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DONegocio25: " + landingLocators.listPlanUnoNegocioAPADO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DONegocio25: " + landingLocators.listPlanUnoNegocioPageDO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanUnoNegocioAPADO, landingLocators.listPlanUnoNegocioPageDO);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DONegocio250: " + landingLocators.listPlanDosNegocioAPADO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DONegocio250: " + landingLocators.listPlanDosNegocioPageDO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanDosNegocioAPADO, landingLocators.listPlanDosNegocioPageDO);
    		
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "APA list DONegocio1tb: " + landingLocators.listPlanTresNegocioAPADO + ConsoleColors.RESET);
    		System.out.println(ConsoleColors.BLUE_BACKGROUND + "Page list DONegocio1tb: " + landingLocators.listPlanTresNegocioPageDO + ConsoleColors.RESET);
    		compareAPAtoPage(landingLocators.listPlanTresNegocioAPADO, landingLocators.listPlanTresNegocioPageDO);
    		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validatePromo() {
		try {
			assertNotNull( jsonApaMetadata );
			
			JSONParser parser = new JSONParser();
			JSONObject jsonCountriesProviders = (JSONObject) parser.parse( jsonApaMetadata.getAsString("country_providers") ); //Se des escapa y se vuelve a parsear
			
			JSONObject jsonCountries = (JSONObject)jsonCountriesProviders.get("COUNTRIES");
			System.out.println("APA: " + jsonCountries + "\n"); //Parsear sobre jsonCountries
				
			Iterator<String> keys = jsonCountries.keySet().iterator();
    		
			while(keys.hasNext()) {
			    String key = keys.next();
			    JSONObject tmpJson = (JSONObject) jsonCountries.get( key );
			    String json = tmpJson.toString();
			    int i = (json.lastIndexOf("motorolaPromo") + 15);
			    String s = json.substring(i, i + 5);
			    if(s.equals("false")) {
			    	return false;
			    }else {
			    	return true;
			    }
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Valida los precios de los paquetes de Claro Drive segun el servicio APA para el nodo Country Providers.
	 */
	public void validatePricesCountryProviders() {
		try {
			assertNotNull( jsonApaMetadata );
			
			JSONParser parser = new JSONParser();
			JSONObject jsonCountriesProviders = (JSONObject) parser.parse( jsonApaMetadata.getAsString("country_providers") ); //Se des escapa y se vuelve a parsear
			
			JSONObject jsonCountries = (JSONObject)jsonCountriesProviders.get("COUNTRIES");
			System.out.println(ConsoleColors.GREEN_BACKGROUND +"APA: " + jsonCountries + ConsoleColors.RESET + "\n"); //Parsear sobre jsonCountries
				
			Iterator<String> keys = jsonCountries.keySet().iterator();
    		
			while(keys.hasNext()) {
			    String key = keys.next();
			    JSONObject tmpJson = (JSONObject) jsonCountries.get( key );
			    tmpJson = (JSONObject)tmpJson.get("paymentMethods");
			    
			    String json = tmpJson.toString();
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(json);
				
			    switch ( key ) {
					case "MX": 				
						changeCountry(CountryElements.MEXICO);
						
						//--------------------------PLAN UNO PARTNER UNO (100GB TELCEL)

			    		//Valores de APA
			    		landingLocators.listPlanUnoAPAMXUno.add(getPlansNode(rootNode, "telcel").get(1));//100
			    		landingLocators.listPlanUnoAPAMXUno.add(this.getStorage(tmpJson, "telcel", "plans", "default", "quotaUnity"));//GB
			    		landingLocators.listPlanUnoAPAMXUno.add(rootNode.path("telcel").path("plans").path("100").path("price").asText());//0
			            
			            //Valores de la pagina
			    		landingLocators.listPlanUnoPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));//100
			    		landingLocators.listPlanUnoPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));//GB
			    		landingLocators.listPlanUnoPageMXUno.add(getPageStringElement(landingLocators.buttonPagePrice5));//0
			    		
			    		//--------------------------PLAN DOS PARTNER UNO (200GB TELCEL)

			    		//Valores de APA
			    		landingLocators.listPlanDosAPAMXUno.add(getPlansNode(rootNode, "telcel").get(2));//200
			    		landingLocators.listPlanDosAPAMXUno.add(this.getStorage(tmpJson, "telcel", "plans", "default", "quotaUnity"));//GB
			    		landingLocators.listPlanDosAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currencySymbol").get(0));//$
			    		landingLocators.listPlanDosAPAMXUno.add(rootNode.path("telcel").path("plans").path("200").path("price").asText());//19
			    		landingLocators.listPlanDosAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currency").get(1));//MXN
			            
			            //Valores de la pagina
			    		landingLocators.listPlanDosPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));//200
			    		landingLocators.listPlanDosPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));//GB
			    		landingLocators.listPlanDosPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));//$
			    		landingLocators.listPlanDosPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));//19
			    		landingLocators.listPlanDosPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));//MXN
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (300GB TELCEL)

			    		//Valores de APA
			    		landingLocators.listPlanTresAPAMXUno.add(getPlansNode(rootNode, "telcel").get(3));
			    		landingLocators.listPlanTresAPAMXUno.add(this.getStorage(tmpJson, "telcel", "plans", "default", "quotaUnity")); 
			    		landingLocators.listPlanTresAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currencySymbol").get(0)); 
			    		landingLocators.listPlanTresAPAMXUno.add(rootNode.path("telcel").path("plans").path("300").path("price").asText()); 
			    		landingLocators.listPlanTresAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currency").get(1)); 

			    		//Valores de la pagina
			    		landingLocators.listPlanTresPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0)); 
			    		landingLocators.listPlanTresPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1)); 
			    		landingLocators.listPlanTresPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
			    		landingLocators.listPlanTresPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
			    		landingLocators.listPlanTresPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO1024GB TELCEL

			    		//Valores de APA
			    		landingLocators.listPlanCuatroAPAMXUno.add(getPlansNode(rootNode, "telcel").get(4));
			    		landingLocators.listPlanCuatroAPAMXUno.add(this.getStorage(tmpJson, "telcel", "plans", "default", "quotaUnity")); 
			    		landingLocators.listPlanCuatroAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAMXUno.add(rootNode.path("telcel").path("plans").path("1024").path("price").asText()); 
			    		landingLocators.listPlanCuatroAPAMXUno.add(getSymbols(tmpJson, "telcel", "plans", "default", "currency").get(1));

			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0)); 
			    		landingLocators.listPlanCuatroPageMXUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1)); 
			    		landingLocators.listPlanCuatroPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageMXUno.add(getDataPageNegocio(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    		
			    	    clickOnTelmexPlansMXButton();
			    	    
						//--------------------------PLAN UNO PARTNER DOS (100GB TELMEX)

			    		//Valores de APA
			    	    landingLocators.listPlanUnoAPAMXDos.add(getPlansNode(rootNode, "default").get(0));
			    	    landingLocators.listPlanUnoAPAMXDos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    	    landingLocators.listPlanUnoAPAMXDos.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
			    	    landingLocators.listPlanUnoPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
			    	    landingLocators.listPlanUnoPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
			    	    landingLocators.listPlanUnoPageMXDos.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			    		//--------------------------PLAN DOS PARTNER DOS (200GB TELMEX)

			    		//Valores de APA
			    	    landingLocators.listPlanDosAPAMXDos.add(getPlansNode(rootNode, "default").get(1));
			    	    landingLocators.listPlanDosAPAMXDos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    	    landingLocators.listPlanDosAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    	    landingLocators.listPlanDosAPAMXDos.add(rootNode.path("default").path("plans").path("200").path("price").asText());
			    	    landingLocators.listPlanDosAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			            
			            //Valores de la pagina
			    	    landingLocators.listPlanDosPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
			    	    landingLocators.listPlanDosPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
			    	    landingLocators.listPlanDosPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
			    	    landingLocators.listPlanDosPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
			    	    landingLocators.listPlanDosPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));

			    		//--------------------------PLAN TRES PARTNER DOS (300GB TELMEX)

			    		//Valores de APA
			    	    landingLocators.listPlanTresAPAMXDos.add(getPlansNode(rootNode, "default").get(2));
			    	    landingLocators.listPlanTresAPAMXDos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity")); 
			    	    landingLocators.listPlanTresAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    	    landingLocators.listPlanTresAPAMXDos.add(rootNode.path("default").path("plans").path("300").path("price").asText()); 
			    	    landingLocators.listPlanTresAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1)); 

			    		//Valores de la pagina
			    	    landingLocators.listPlanTresPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0)); 
			    	    landingLocators.listPlanTresPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1)); 	    		
			    	    landingLocators.listPlanTresPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
			    	    landingLocators.listPlanTresPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
			    	    landingLocators.listPlanTresPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));

			    		//--------------------------PLAN CUATRO PARTNER DOS (1024GB TELMEX)

			    		//Valores de APA
			    	    landingLocators.listPlanCuatroAPAMXDos.add(getPlansNode(rootNode, "default").get(3));
			    	    landingLocators.listPlanCuatroAPAMXDos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity")); 
			    	    landingLocators.listPlanCuatroAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0)); 
			    	    landingLocators.listPlanCuatroAPAMXDos.add(rootNode.path("default").path("plans").path("1024").path("price").asText()); 
			    	    landingLocators.listPlanCuatroAPAMXDos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1)); 

			    		//Valores de la pagina
			    	    landingLocators.listPlanCuatroPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0)); 
			    	    landingLocators.listPlanCuatroPageMXDos.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1)); 		    		
			    	    landingLocators.listPlanCuatroPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    	    landingLocators.listPlanCuatroPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    	    landingLocators.listPlanCuatroPageMXDos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    	    
			    	    clickOnCreditCardPlansMXButton();
			    	    
			    	    //--------------------------PLAN UNO PARTNER TRES (200GB CREDIT CARD)

			    		//Valores de APA
			    	    landingLocators.listPlanUnoAPAMXTres.add(getPlansNode(rootNode, "creditcard").get(0));
			    	    landingLocators.listPlanUnoAPAMXTres.add(this.getStorage(tmpJson, "creditcard", "plans", "default", "quotaUnity"));
			    	    landingLocators.listPlanUnoAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currencySymbol").get(0));
			    	    landingLocators.listPlanUnoAPAMXTres.add(rootNode.path("creditcard").path("plans").path("200").path("price").asText());
			    	    landingLocators.listPlanUnoAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currency").get(1));
			            
			            //Valores de la pagina
			    	    landingLocators.listPlanUnoPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
			    	    landingLocators.listPlanUnoPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));			    	    
			    	    landingLocators.listPlanUnoPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice8)).get(0));
			    	    landingLocators.listPlanUnoPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice8)).get(1));
			    	    landingLocators.listPlanUnoPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice8)).get(2));
			    	    
			    	    //--------------------------PLAN DOS PARTNER TRES (300GB CREDIT CARD)

			    		//Valores de APA
			    	    landingLocators.listPlanDosAPAMXTres.add(getPlansNode(rootNode, "creditcard").get(1));
			    	    landingLocators.listPlanDosAPAMXTres.add(this.getStorage(tmpJson, "creditcard", "plans", "default", "quotaUnity")); 
			    	    landingLocators.listPlanDosAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currencySymbol").get(0)); 
			    	    landingLocators.listPlanDosAPAMXTres.add(rootNode.path("creditcard").path("plans").path("300").path("price").asText()); 
			    	    landingLocators.listPlanDosAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currency").get(1));

			    		//Valores de la pagina
			    	    landingLocators.listPlanDosPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(0)); 
			    	    landingLocators.listPlanDosPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage2).get(1)); 			    		
			    	    landingLocators.listPlanDosPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice2)).get(0));
			    	    landingLocators.listPlanDosPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice2)).get(1));
			    	    landingLocators.listPlanDosPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice2)).get(2));
			    	    
			    		//--------------------------PLAN TRES PARTNER TRES (1024GB CREDIT CARD)

			    		//Valores de APA
			    	    landingLocators.listPlanTresAPAMXTres.add(getPlansNode(rootNode, "creditcard").get(2));
			    	    landingLocators.listPlanTresAPAMXTres.add(this.getStorage(tmpJson, "creditcard", "plans", "default", "quotaUnity")); 
			    	    landingLocators.listPlanTresAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currencySymbol").get(0));
			    	    landingLocators.listPlanTresAPAMXTres.add(rootNode.path("creditcard").path("plans").path("1024").path("price").asText()); 
			    	    landingLocators.listPlanTresAPAMXTres.add(getSymbols(tmpJson, "creditcard", "plans", "default", "currency").get(1)); 

			    		//Valores de la pagina
			    	    landingLocators.listPlanTresPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(0)); 
			    	    landingLocators.listPlanTresPageMXTres.add(getDataPageStorage(landingLocators.buttonStoragePage3).get(1)); 
			    	    landingLocators.listPlanTresPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice3)).get(0));
			    	    landingLocators.listPlanTresPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice3)).get(1));
			    	    landingLocators.listPlanTresPageMXTres.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice3)).get(2));

			        break;
			        
					case "CO": 
						changeCountry(CountryElements.COLOMBIA);
						
						//--------------------------PLAN UNO PARTNER UNO (100GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPACOUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPACOUno.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageCOUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (200GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPACOUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPACOUno.add(rootNode.path("default").path("plans").path("200").path("price").asText());
						landingLocators.listPlanDosAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (400GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPACOUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPACOUno.add(rootNode.path("default").path("plans").path("400").path("price").asText());
						landingLocators.listPlanTresAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		landingLocators.listPlanCuatroAPACOUno.add(getPlansNode(rootNode, "default").get(3));
			    		landingLocators.listPlanCuatroAPACOUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPACOUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (2048GB CLARO MOVIL)

			    		//Valores de APA
			    		landingLocators.listPlanCincoAPACOUno.add(getPlansNode(rootNode, "default").get(4));
			    		landingLocators.listPlanCincoAPACOUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCincoAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCincoAPACOUno.add(rootNode.path("default").path("plans").path("2048").path("price").asText());
			    		landingLocators.listPlanCincoAPACOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCincoPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(0));
			    		landingLocators.listPlanCincoPageCOUno.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(1));
			    		landingLocators.listPlanCincoPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(0));
			    		landingLocators.listPlanCincoPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(1));
			    		landingLocators.listPlanCincoPageCOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(2));
			    	    
			    		clickOnClaroHogarPlansMXButton();
			    		
			    		//--------------------------PLAN UNO PARTNER DOS (100GB CLARO HOGAR)
						
			    		//Valores de APA
			    		landingLocators.listPlanUnoAPACODos.add(getPlansNode(rootNode, "default").get(0));
			    		landingLocators.listPlanUnoAPACODos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanUnoAPACODos.add(rootNode.path("default").path("plans").path("100").path("price").asText());
			            
			            //Valores de la pagina
			    		landingLocators.listPlanUnoPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
			    		landingLocators.listPlanUnoPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
			    		landingLocators.listPlanUnoPageCODos.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER DOS (200GB CLARO HOGAR)

			    		//Valores de APA
			    		landingLocators.listPlanDosAPACODos.add(getPlansNode(rootNode, "default").get(1));
			    		landingLocators.listPlanDosAPACODos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanDosAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanDosAPACODos.add(rootNode.path("default").path("plans").path("200").path("price").asText());
			    		landingLocators.listPlanDosAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanDosPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
			    		landingLocators.listPlanDosPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
			    		landingLocators.listPlanDosPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
			    		landingLocators.listPlanDosPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
			    		landingLocators.listPlanDosPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER DOS (400GB CLARO HOGAR)
			    		
			    		//Valores de APA
			    		landingLocators.listPlanTresAPACODos.add(getPlansNode(rootNode, "default").get(2));
			    		landingLocators.listPlanTresAPACODos.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
			    		landingLocators.listPlanTresAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanTresAPACODos.add(rootNode.path("default").path("plans").path("400").path("price").asText());
			    		landingLocators.listPlanTresAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanTresPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
			    		landingLocators.listPlanTresPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
			    		landingLocators.listPlanTresPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
			    		landingLocators.listPlanTresPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
			    		landingLocators.listPlanTresPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER DOS (1024GB CLARO HOGAR)

			    		//Valores de APA
			    		landingLocators.listPlanCuatroAPACODos.add(getPlansNode(rootNode, "default").get(3));
			    		landingLocators.listPlanCuatroAPACODos.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPACODos.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER DOS (2048GB CLARO HOGAR)

			    		//Valores de APA
			    		landingLocators.listPlanCincoAPACODos.add(getPlansNode(rootNode, "default").get(4));
			    		landingLocators.listPlanCincoAPACODos.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCincoAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCincoAPACODos.add(rootNode.path("default").path("plans").path("2048").path("price").asText());
			    		landingLocators.listPlanCincoAPACODos.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCincoPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(0));
			    		landingLocators.listPlanCincoPageCODos.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(1));
			    		landingLocators.listPlanCincoPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(0));
			    		landingLocators.listPlanCincoPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(1));
			    		landingLocators.listPlanCincoPageCODos.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(2));
			    		
					break;
					
					case "BR": 
						changeCountry(CountryElements.BRAZIL);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanUnoAPABRUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPABRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPABRUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageBRUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanDosAPABRUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPABRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPABRUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0).substring(1, 2));//$
						landingLocators.listPlanDosPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));//2.99
						landingLocators.listPlanDosPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0).substring(0, 1));//R
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanTresAPABRUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPABRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPABRUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0).substring(1, 2));
						landingLocators.listPlanTresPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0).substring(0, 1));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1TB CLARO)

			    		//Valores de APA
			    		String planCuatroAPABRUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPABRUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		//landingLocators.listPlanCuatroAPABRUno.add(planCuatroAPABRUno.substring(5));
			    		landingLocators.listPlanCuatroAPABRUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPABRUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPABRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageBRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0).substring(1, 2));
			    		landingLocators.listPlanCuatroPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageBRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0).substring(0, 1));
			    		
					break;
					
					case "GT": 
						changeCountry(CountryElements.GUATEMALA);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAGTUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAGTUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAGTUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageGTUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAGTUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAGTUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAGTUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAGTUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAGTUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAGTUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPAGTUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPAGTUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPAGTUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAGTUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPAGTUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageGTUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageGTUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "HN": 
						changeCountry(CountryElements.HONDURAS);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAHNUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAHNUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAHNUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageHNUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAHNUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAHNUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAHNUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAHNUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAHNUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAHNUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPAHNUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPAHNUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPACOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPAHNUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAHNUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPAHNUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageHNUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageHNUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "NI":  
						changeCountry(CountryElements.NICARAGUA);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPANIUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPANIUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPANIUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageNIUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPANIUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPANIUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPANIUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPANIUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPANIUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPANIUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPANIUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPANIUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPANIUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPANIUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPANIUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPANIUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageNIUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageNIUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "SV": 
						changeCountry(CountryElements.EL_SALVADOR);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPASVUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPASVUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPASVUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageSVUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPASVUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPASVUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPASVUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
		    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPASVUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPASVUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPASVUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPASVUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPASVUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPASVUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPASVUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPASVUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPASVUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageSVUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageSVUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "CR":  
						changeCountry(CountryElements.COSTA_RICA);
						
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPACRUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPACRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPACRUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageCRUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPACRUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPACRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPACRUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageCRUno.add("&#"+(int)getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0).charAt(0)+";");
						landingLocators.listPlanDosPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPACRUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPACRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPACRUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageCRUno.add("&#"+(int)getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0).charAt(0)+";");
						landingLocators.listPlanTresPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		landingLocators.listPlanCuatroAPACRUno.add(getPlansNode(rootNode, "default").get(3));
			    		landingLocators.listPlanCuatroAPACRUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		//landingLocators.listPlanCuatroAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPACRUno.add(new String(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0).getBytes("ISO-8859-1"), "UTF-8"));
			    		landingLocators.listPlanCuatroAPACRUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPACRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
			    		landingLocators.listPlanCuatroPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageCRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageCRUno.add("&#"+(int)getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0).charAt(0)+";");
			    		landingLocators.listPlanCuatroPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageCRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "PE": 
						changeCountry(CountryElements.PERU);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAPEUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAPEUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAPEUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPagePEUno.add(getPageStringElement(landingLocators.buttonPagePrice9));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAPEUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAPEUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAPEUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAPEUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAPEUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAPEUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPAPEUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPAPEUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPAPEUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPAPEUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAPEUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPAPEUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPagePEUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPagePEUno.add(getDataPagePricePE(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "AR": 
						changeCountry(CountryElements.ARGENTINA);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAARUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAARUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAARUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageARUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (50GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanDosAPAARUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAARUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAARUno.add(rootNode.path("default").path("plans").path("50").path("price").asText());
						landingLocators.listPlanDosAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));

						//--------------------------PLAN DOS PARTNER UNO (75GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanTresAPAARUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAARUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAARUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanTresAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));

			    		//--------------------------PLAN TRES PARTNER UNO (100GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanCuatroAPAARUno.add(getPlansNode(rootNode, "default").get(3));
						landingLocators.listPlanCuatroAPAARUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanCuatroAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanCuatroAPAARUno.add(rootNode.path("default").path("plans").path("100").path("price").asText());
						landingLocators.listPlanCuatroAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanCuatroPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
						landingLocators.listPlanCuatroPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
						landingLocators.listPlanCuatroPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
						landingLocators.listPlanCuatroPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
						landingLocators.listPlanCuatroPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (300GB CLARO)

			    		//Valores de APA
						landingLocators.listPlanCincoAPAARUno.add(getPlansNode(rootNode, "default").get(4));
						landingLocators.listPlanCincoAPAARUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanCincoAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanCincoAPAARUno.add(rootNode.path("default").path("plans").path("300").path("price").asText());
						landingLocators.listPlanCincoAPAARUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
						landingLocators.listPlanCincoPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(0));
						landingLocators.listPlanCincoPageARUno.add(getDataPageStorage(landingLocators.buttonStoragePage7).get(1));
						landingLocators.listPlanCincoPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(0));
						landingLocators.listPlanCincoPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(1));
						landingLocators.listPlanCincoPageARUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice10)).get(2));
					
					break;
					
					case "CL": 
						changeCountry(CountryElements.CHILE);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPACLUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPACLUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPACLUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageCLUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPACLUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPACLUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPACLUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPACLUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPACLUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPACLUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPACLUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPACLUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPACLUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPACLUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPACLUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPACLUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageCLUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageCLUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));

					break;
					
					case "EC": 
						changeCountry(CountryElements.ECUADOR);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAECUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAECUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAECUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageECUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAECUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAECUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAECUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAECUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAECUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAECUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPAECUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPAECUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPAECUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPAECUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAECUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPAECUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageECUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageECUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
					
					break;
					
					case "PR": 
						changeCountry(CountryElements.PUERTO_RICO);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAPRUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAPRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAPRUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPagePRUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAPRUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAPRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAPRUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAPRUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAPRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAPRUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPAPRUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPAPRUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPAPRUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPAPRUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPAPRUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPAPRUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPagePRUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPagePRUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
					
					break;
					
					case "DO": 
						changeCountry(CountryElements.DOMINICANA);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPADOUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPADOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPADOUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageDOUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (75GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPADOUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPADOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPADOUno.add(rootNode.path("default").path("plans").path("75").path("price").asText());
						landingLocators.listPlanDosAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (150GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPADOUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPADOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPADOUno.add(rootNode.path("default").path("plans").path("150").path("price").asText());
						landingLocators.listPlanTresAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (1024GB CLARO MOVIL)

			    		//Valores de APA
			    		String planCuatroAPADOUno = rootNode.path("default").path("plans").path("1024").path("smsKey").asText();
			    		landingLocators.listPlanCuatroAPADOUno.add(getPlansNode(rootNode, "default").get(3));
			    		//listPlanCuatroAPADOUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));//
			    		landingLocators.listPlanCuatroAPADOUno.add(rootNode.path("default").path("plans").path("default").path("quotaUnity").asText().replaceAll("QUOTA_UNITIES:::", "").toUpperCase());
			    		landingLocators.listPlanCuatroAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
			    		landingLocators.listPlanCuatroAPADOUno.add(rootNode.path("default").path("plans").path("1024").path("price").asText());
			    		landingLocators.listPlanCuatroAPADOUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
			    		landingLocators.listPlanCuatroPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
			    		landingLocators.listPlanCuatroPageDOUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
			    		landingLocators.listPlanCuatroPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
			    		landingLocators.listPlanCuatroPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
			    		landingLocators.listPlanCuatroPageDOUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
						
					break;
					
					case "UY": 
						changeCountry(CountryElements.URUGUAY);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanUnoAPAUYUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAUYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAUYUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPageUYUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (50GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanDosAPAUYUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAUYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAUYUno.add(rootNode.path("default").path("plans").path("50").path("price").asText());
						landingLocators.listPlanDosAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "compoundPrice").get(1).replaceAll("\\$U\\$\\{v\\} ", ""));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (100GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAUYUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAUYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAUYUno.add(rootNode.path("default").path("plans").path("100").path("price").asText());
						landingLocators.listPlanTresAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "compoundPrice").get(1).replaceAll("\\$U\\$\\{v\\} ", ""));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (300GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanCuatroAPAUYUno.add(getPlansNode(rootNode, "default").get(3));
						landingLocators.listPlanCuatroAPAUYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanCuatroAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanCuatroAPAUYUno.add(rootNode.path("default").path("plans").path("300").path("price").asText());
						landingLocators.listPlanCuatroAPAUYUno.add(getSymbols(tmpJson, "default", "plans", "default", "compoundPrice").get(1).replaceAll("\\$U\\$\\{v\\} ", ""));
			    		
			    		//Valores de la pagina
						landingLocators.listPlanCuatroPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
						landingLocators.listPlanCuatroPageUYUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
						landingLocators.listPlanCuatroPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
						landingLocators.listPlanCuatroPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
						landingLocators.listPlanCuatroPageUYUno.add(getDataPagePrice(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
						
					break;
					
					case "PY": 
						changeCountry(CountryElements.PARAGUAY);
						//--------------------------PLAN UNO PARTNER UNO (25GB CLARO MOVIL)

			    		//Valores de APA						
						landingLocators.listPlanUnoAPAPYUno.add(getPlansNode(rootNode, "default").get(0));
						landingLocators.listPlanUnoAPAPYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanUnoAPAPYUno.add(rootNode.path("default").path("plans").path("25").path("price").asText());
			            
			            //Valores de la pagina
						landingLocators.listPlanUnoPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(0));
						landingLocators.listPlanUnoPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage).get(1));
						landingLocators.listPlanUnoPagePYUno.add(getPageStringElement(landingLocators.buttonPagePrice5));
			    		
			            //--------------------------PLAN DOS PARTNER UNO (50GB CLARO MOVIL)

			    		//Valores de APA   
						landingLocators.listPlanDosAPAPYUno.add(getPlansNode(rootNode, "default").get(1));
						landingLocators.listPlanDosAPAPYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanDosAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanDosAPAPYUno.add(rootNode.path("default").path("plans").path("50").path("price").asText());
						landingLocators.listPlanDosAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanDosPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(0));
						landingLocators.listPlanDosPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage5).get(1));
						landingLocators.listPlanDosPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice6)).get(0));
						landingLocators.listPlanDosPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice6)).get(1));
						landingLocators.listPlanDosPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice6)).get(2));
			    		
			    		//--------------------------PLAN TRES PARTNER UNO (100GB CLARO MOVIL)

			    		//Valores de APA
						landingLocators.listPlanTresAPAPYUno.add(getPlansNode(rootNode, "default").get(2));
						landingLocators.listPlanTresAPAPYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanTresAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanTresAPAPYUno.add(rootNode.path("default").path("plans").path("100").path("price").asText());
						landingLocators.listPlanTresAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			            //Valores de la pagina
						landingLocators.listPlanTresPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(0));
						landingLocators.listPlanTresPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage6).get(1));
						landingLocators.listPlanTresPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice7)).get(0));
						landingLocators.listPlanTresPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice7)).get(1));
						landingLocators.listPlanTresPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice7)).get(2));
			    		
			    		//--------------------------PLAN CUATRO PARTNER UNO (300GB CLARO MOVIL)
						
						//Valores de APA
						landingLocators.listPlanCuatroAPAPYUno.add(getPlansNode(rootNode, "default").get(3));
						landingLocators.listPlanCuatroAPAPYUno.add(this.getStorage(tmpJson, "default", "plans", "default", "quotaUnity"));
						landingLocators.listPlanCuatroAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currencySymbol").get(0));
						landingLocators.listPlanCuatroAPAPYUno.add(rootNode.path("default").path("plans").path("300").path("price").asText());
						landingLocators.listPlanCuatroAPAPYUno.add(getSymbols(tmpJson, "default", "plans", "default", "currency").get(1));
			    		
			    		//Valores de la pagina
						landingLocators.listPlanCuatroPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(0));
						landingLocators.listPlanCuatroPagePYUno.add(getDataPageStorage(landingLocators.buttonStoragePage4).get(1));
						landingLocators.listPlanCuatroPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice4)).get(0));
						landingLocators.listPlanCuatroPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice4)).get(1));
						landingLocators.listPlanCuatroPagePYUno.add(getDataPagePricePY(getPageStringElement(landingLocators.buttonPagePrice4)).get(2));
						
					break;
					
					default: break;
				}
			}    		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Obtiene los datos del precio de cada plan para los paises, por ejemplo $19MXN, devuelve [$, 19, MXN]
	public static List<String> getDataPagePrice(String xpath) {
        List<String> result = new ArrayList<>();
        String regex = "^([A-Za-z\\$₡₲Gs]*)([\\d\\,\\.]+)\\s?([A-Za-z]{2,3})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xpath);

        if (matcher.find()) {
            result.add(matcher.group(1));
            result.add(matcher.group(2).replaceAll(",", "."));
            result.add(matcher.group(3));
        }

        return result;
    }
	
	// Obtiene los datos del precio de cada plan para PERU
	public static List<String> getDataPagePricePE(String xpath) {
		List<String> result = new ArrayList<>();
	    String regex = "^(S/\\.)(\\d+)(\\.\\d{2})(\\s+\\w+)$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(xpath);

	    if (matcher.find()) {
	        result.add(matcher.group(1));
	        result.add(matcher.group(2).replaceAll(",", ""));
	        result.add(matcher.group(4).replaceAll("\\s+", ""));
	    }

	    return result;
    }
	
	// Obtiene los datos del precio de cada plan para PARAGUAY
	public static List<String> getDataPagePricePY(String xpath) {
		List<String> result = new ArrayList<>();
	    String regex = "^(Gs)\\s+(\\d+\\.\\d{2})$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(xpath);

	    if (matcher.find()) {
	        result.add(matcher.group(1));
	        result.add(matcher.group(2));
	    }
	    
	    result.add("N/A");

	    return result;
	}

	
    // Obtiene el valor de todos los planes del pais, por ejemplo [25, 50, 100, 300, default] para el caso de paraguay
    public List<String> getPlansNode(JsonNode rootNode, String nodeSearch) throws IOException{
    	JsonNode plansNode = rootNode.get(nodeSearch).get("plans");
        Iterator<String> planKeys = plansNode.fieldNames();
        List<Integer> planListInt = new ArrayList<>();

        while (planKeys.hasNext()) {
            String planKey = planKeys.next();
            if (!planKey.equals("default")) { // excluye el valor "default" de la lista
                int planInt = Integer.parseInt(planKey);
                planListInt.add(planInt);
            }
        }
        
        // Ordena la lista de enteros de menor a mayor
        Collections.sort(planListInt);
        
        // Convierte la lista de enteros ordenados en una lista de strings
        List<String> planList = new ArrayList<>();
        for (int i = 0; i < planListInt.size(); i++) {
            String planString = Integer.toString(planListInt.get(i));
            planList.add(planString);
        }
        
        // Agrega el valor "default" al final de la lista
        planList.add("default");
        
        System.out.println(planList);
        return planList;
    }
	
	//Obtenemos los simbolos de cada plan en APA, por ejemplo [$, MXN]
	public ArrayList<String> getSymbols(JSONObject tmpJson, String default1, String plans, String default2, String currency) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
    	String json = tmpJson.toString();
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode rootNode = objectMapper.readTree(json);
    	
    	String currencySymbol = rootNode.path(default1).path(plans).path(default2).path(currency).asText();
    	String currencyAPA = rootNode.path(default1).path(plans).path(default2).path(currency).asText();
    	
    	list.add(currencySymbol);
    	list.add(currencyAPA);
        
        return list;
    }
    
	// Obtiene los datos del almacenamiento de cada plan, por ejemplo 100GB, devuelve [100, GB]
	public ArrayList<String> getDataPageStorage(WebElement xpathStorage){
		ArrayList<String> list = new ArrayList<String>();
		String planPage = xpathStorage.getText();
        list.add(planPage.substring(0, planPage.length() - 2));
        list.add(planPage.substring(planPage.length() - 2).toUpperCase());
        
        return list;
    }
    
    // Obtiene los datos del precio de cada plan, por ejemplo $19MXN, devuelve [$, 19, MXN]
    public ArrayList<String> getDataPagePriceBR(String xpathPrice){
    	ArrayList<String> list = new ArrayList<String>();
    	
    	String inputString = getPageStringElementAux(xpathPrice);

        String[] parts = inputString.split("[\\d,]+");

        String firstChar = inputString.substring(0, 1);
        String secondChar = parts[0].substring(1);
        String numberString = parts[1];
        
        list.add(firstChar);
        list.add(secondChar);
        list.add(numberString);
        
        return list;
    }
    
    // Obtiene los datos del precio de cada plan en negocio, por ejemplo MXN $59, devuelve [MXN, $, 59]    
    public static List<String> getDataPageNegocio(String input) {
        List<String> separatedValues = new ArrayList<>();

        // Definir el patrón regex para separar la cadena
        String regex = "([A-Za-z]+)|([$€£¥]|\\bR\\b)|([0-9]+(?:\\.[0-9]+)?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Buscar las coincidencias y agregarlas a la lista
        while (matcher.find()) {
            String value = matcher.group();
            separatedValues.add(value);
        }

        return separatedValues;
    }
    
	/* 
	 * Metodo para obtener las iniciales del almacenamiento
	 * @param tmpJson El json de los planes de APA
	 * @return El almacenamiento de cada plan, por ejemplo GB
	 */
    public String getStorage(JSONObject tmpJson, String default1, String plans, String default2, String quotaUnity) throws IOException { //agregar los parametros de default, plans, default, quotaunity para ponerlos abaj
    	String json = tmpJson.toString();
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode rootNode = objectMapper.readTree(json);
    	
    	String storageAPA = rootNode.path(default1).path(plans).path(default2).path(quotaUnity).asText(); //leer los casos particulares
        String storageValueAPA = storageAPA.substring(storageAPA.lastIndexOf(":::") + 3);
        String storageValueUpper = storageValueAPA.toUpperCase();
        
        return storageValueUpper;
    }
	
	public boolean compareAPAtoPage(ArrayList<String> ListAPA, ArrayList<String> ListPage) {
		if (ListAPA.size() != ListPage.size()) {
			 System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	         return false;
	    }
		
		for (int i = 0; i < ListAPA.size(); i++) {
	         String elementoLista1 = ListAPA.get(i);
	         String elementoLista2 = ListPage.get(i);
	         // Validamos los elementos que son equivalentes
	         if (!elementoLista1.equals(elementoLista2) &&
	         !(elementoLista1.equals("0") && elementoLista2.equals("SIN COSTO *")) &&
			 !(elementoLista1.equals("0") && elementoLista2.equals("SIN COSTO")) &&
			 !(elementoLista1.equals("0") && elementoLista2.equals("GRATUITO")) &&
       		 !(elementoLista1.equals("0") && elementoLista2.equals("GRATIS")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("1024")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("TB")) &&
			 !(elementoLista1.equals("1") && elementoLista2.equals("1024")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("GB")) &&
			 !(elementoLista1.equals("$") && elementoLista2.equals("RD$")) &&
			 !(elementoLista1.equals("PYG") && elementoLista2.equals("N/A")) &&
			 !(elementoLista1.equals("1024") && elementoLista2.equals("1")) &&
			 !(elementoLista1.equals("1536") && elementoLista2.equals("1.5")) &&
			 !(elementoLista1.equals("GB") && elementoLista2.equals("TB"))) {
	             // Si los elementos son precios, comparar su equivalencia numérica, por ejemplo, 22.00 y 22 son equivalentes
	             if (elementoLista1.matches("^\\d+(\\.\\d+)?$") && elementoLista2.matches("^\\d+(\\.\\d+)?$")) {
	                 double precio1 = Double.parseDouble(elementoLista1);
	                 double precio2 = Double.parseDouble(elementoLista2);
	                 if (precio1 != precio2) {
	                     System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	                     return false;
	                 }
	             } else {
	                 System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	                 return false;
	             }
	         }
	    }
		System.out.println(ConsoleColors.GREEN_BACKGROUND + "El servicio APA y la pagina es correcta" + ConsoleColors.RESET + "\n");
		
		return true;
	}
	
	
	public boolean compareAPAtoPagePromo(ArrayList<String> ListAPA, ArrayList<String> ListPage) {
		if (ListAPA.size() != ListPage.size()) {
			 System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	         return false;
	    }
		
		if(validatePromo()) {
			System.out.println(ConsoleColors.YELLOW_BACKGROUND + "La Promoción motorola está activa" + ConsoleColors.RESET + "\n");
		}
		
		for (int i = 0; i < ListAPA.size(); i++) {
	         String elementoLista1 = ListAPA.get(i);
	         String elementoLista2 = ListPage.get(i);
	         // Validamos los elementos que son equivalentes
	         if (!elementoLista1.equals(elementoLista2) &&
	         !(elementoLista1.equals("0") && elementoLista2.equals("SIN COSTO *")) &&
			 !(elementoLista1.equals("0") && elementoLista2.equals("SIN COSTO")) &&
			 !(elementoLista1.equals("0") && elementoLista2.equals("GRATUITO")) &&
       		 !(elementoLista1.equals("0") && elementoLista2.equals("GRATIS")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("1024")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("TB")) &&
			 !(elementoLista1.equals("1") && elementoLista2.equals("1024")) &&
			 !(elementoLista1.equals("1T") && elementoLista2.equals("GB")) &&
			 !(elementoLista1.equals("$") && elementoLista2.equals("RD$")) &&
			 !(elementoLista1.equals("PYG") && elementoLista2.equals("N/A")) &&
			 !(elementoLista1.equals("1024") && elementoLista2.equals("1")) &&
			 !(elementoLista1.equals("1536") && elementoLista2.equals("1.5")) &&
			 !(elementoLista1.equals("GB") && elementoLista2.equals("TB"))) {
	             // Si los elementos son precios, comparar su equivalencia numérica, por ejemplo, 22.00 y 22 son equivalentes
	             if (elementoLista1.matches("^\\d+(\\.\\d+)?$") && elementoLista2.matches("^\\d+(\\.\\d+)?$")) {
	                 double precio1 = Double.parseDouble(elementoLista1);
	                 double precio2 = Double.parseDouble(elementoLista2);
	                 if (precio1 != precio2) {
	                     System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	                     return false;
	                 }
	             } else {
	                 System.out.println(ConsoleColors.RED_BACKGROUND + "El servicio APA y la pagina es incorrecta" + ConsoleColors.RESET + "\n");
	                 return false;
	             }
	         }
	    }
		System.out.println(ConsoleColors.GREEN_BACKGROUND + "El servicio APA y la pagina es correcta" + ConsoleColors.RESET + "\n");
		
		return true;
	}
	
	
	public String getPageStringElementAux(String xpath) {
		WebElement elementHTML = SeleniumDriver.getDriver().findElement( By.xpath(xpath));
		String stringElement = elementHTML.getText();
		return stringElement;
	}
	
	public String getPageStringElement(WebElement xpath) {
		return xpath.getText();
	}
	
	public static void getJsonFront( ){

		System.setProperty("webdriver.chrome.driver", "D:\\AMX java\\Pruebas Automatizadas\\dlaargentina-clarodrive-qa-automation-web-e7589b35b422\\dlaargentina-clarodrive-qa-automation-web-e7589b35b422\\claroDrive-automation\\Drivers\\chromedriver.exe");
		// Crear una instancia del driver de Chrome

		WebDriver driver = new ChromeDriver();
		// Navegar a la página de Claro Drive

		driver.get("https://www.clarodrive.com/");
		// Esperar a que se cargue la página

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement pricingElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prices")));
		// Extraer el contenido del elemento de precios y planes

		String pricingJson = pricingElement.getAttribute("data-offers");
		// Parsear el contenido como un objeto JSON

		Gson gson = new Gson();

		Map<String, Object> pricingMap = gson.fromJson(pricingJson, Map.class);
		// Parcelar los datos según los requerimientos

		Map<String, Object> defaultPlan = (Map<String, Object>) pricingMap.get("default");

		Map<String, Object> plans = (Map<String, Object>) pricingMap.get("plans");

		Map<String, Object> paymentMethods = (Map<String, Object>) pricingMap.get("paymentMethods");
		// Imprimir los resultados

		System.out.println("Default Plan:");

		System.out.println(ConsoleColors.BLUE_BACKGROUND + defaultPlan);

		System.out.println("Plans:");

		System.out.println(ConsoleColors.BLUE_BACKGROUND + plans);

		System.out.println("Payment Methods:");

		System.out.println(ConsoleColors.BLUE_BACKGROUND + paymentMethods);

		// Cerrar el navegador

		driver.quit();

		}
	
	
	/**
	 * Verifica paquete por paquete el precio
	 * @param countriePrice Objeto {@link JSONObject} con precios de un país.
	 * @param comparation Cadena Json de referencia para comparar.
	 */
	private void validateIndividualPrices( JSONObject countriePrice, String comparation ) {
		Iterator<String> keys = countriePrice.keySet().iterator();
		JSONObject jsonComparation;
		
		try {
			JSONParser parser = new JSONParser();
			jsonComparation = (JSONObject) parser.parse( comparation );
			
			while(keys.hasNext()) {
			    String key = keys.next();
			    assertEquals( countriePrice.get(key), jsonComparation.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Validar la redirección a GooglePlay
	 */
	public void clickGooglePlayDownload() {
		landingLocators.buttonGooglePlay.click();
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Validar la redirección al AppStore
	 */
	public void clickAppStoreDownload() {
		landingLocators.buttonAppStore.click();
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Validar la redirección a Huawei
	 */
	public void clickWinMacDownload() {
		landingLocators.buttonWinMac.click();
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Validar la redirección a Huawei
	 */
	public void clickAppGAlleryDownload() {
		landingLocators.buttonHuawei.click();
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()){
			String newwin = it.next();
			SeleniumDriver.getDriver().switchTo().window(newwin);
		}
		SeleniumDriver.waitForPageToLoad();
	}
	
	/**
	 * Validar que existan los botones de descarga windows y mac
	 */
	public void clickWinMacButtonDownload () {
		landingLocators.buttonDonwloadWindows.click();
		landingLocators.buttonDonwloadMac.click();
	}
	
	/**
	 * Cirera una nueva ventana abierta y selecciona la anterior
	 */
	public void switchPreviousWindow() {
		SeleniumDriver.getDriver().close();	
		
		Set <String> handles = SeleniumDriver.getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();
		//Vovler a seleccionar la ventana anterior, en caso de no hacerlo no funcionaran las siguientes acciones
		String newwin = it.next(); //Seleccionar el primer elemento en iteración
		SeleniumDriver.getDriver().switchTo().window(newwin);
	}
	
	/**
	 * Obtiene el servicio lang en formato Json y lo limpia para obtener las traducciones del landing.
	 * @return Un objeto {@link JSONObject}
	 */
	private JSONObject getLangService() {
		try {
			JSONObject tmpJson = (JSONObject) SeleniumDriver.getJsonOfService( langService ).get( "translations" );
			assertNotNull(tmpJson); 
			return tmpJson;
		} catch (Exception e) {
			System.out.println( ConsoleColors.RED_BACKGROUND + "Falla al pedir servicio: " + langService + ConsoleColors.RESET );
		}
		return null;
	}
	
	/**
	 * Obtiene el servicio apa en formato Json y lo limpia para obtener los datos de paises
	 * @return Un objeto {@link JSONObject}
	 */
	private  JSONObject getApaService() {
		try {
			return (JSONObject) SeleniumDriver.getJsonOfService( apaMetadataService );
		} catch (Exception e) {
			System.out.println( ConsoleColors.RED_BACKGROUND + "Falla al pedir servicio: " + apaMetadataService + ConsoleColors.RESET );
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Configura los parametros necesarios para la petición de traducción para un país ne específico en el landings.
	 * @param hostUrl Url del ambiente configurado en el archivo env.
	 * @param serviceUrl Servicio lang configurado en el archivo env.
	 * @param country País para el que se configurará la traducción.
	 */
	public void setCountryTranslation( String hostUrl, String serviceUrl, CountryElements country ) { 
		String tmpString = "";
		
		switch ( countrySelected ) {
		case MEXICO: 		tmpString = serviceUrl.replaceAll("@", "es-MX"); 			break;
		case COLOMBIA: 		tmpString = serviceUrl.replaceAll("@", "es-CO"); 			break;
		case BRAZIL: 		tmpString = serviceUrl.replaceAll("@", "pt-BR"); 			break;
		case GUATEMALA: 	tmpString = serviceUrl.replaceAll("@", "es-GT"); 			break;
		case HONDURAS: 		tmpString = serviceUrl.replaceAll("@", "es-HN"); 			break;
		case NICARAGUA: 	tmpString = serviceUrl.replaceAll("@", "es-NI"); 			break;
		case EL_SALVADOR: 	tmpString = serviceUrl.replaceAll("@", "es-SV"); 			break;
		case COSTA_RICA: 	tmpString = serviceUrl.replaceAll("@", "es-AR"); 			break;
		case PERU: 			tmpString = serviceUrl.replaceAll("@", "es-NI"); 			break;
		case ARGENTINA: 	tmpString = serviceUrl.replaceAll("@", "es-AR"); 			break;
		case CHILE: 		tmpString = serviceUrl.replaceAll("@", "es-CL"); 			break;
		case ECUADOR: 		tmpString = serviceUrl.replaceAll("@", "es-EC"); 			break;
		case PUERTO_RICO: 	tmpString = serviceUrl.replaceAll("@", "es-PR"); 			break;
		case DOMINICANA: 	tmpString = serviceUrl.replaceAll("@", "es-DO"); 			break;
		case URUGUAY: 		tmpString = serviceUrl.replaceAll("@", "es-UY"); 			break;
		case PARAGUAY: 		tmpString = serviceUrl.replaceAll("@", "es-PY"); 			break;
		default: 			tmpString = serviceUrl.replaceAll("@", "es-MX"); 			break;
		}
		
		langService = hostUrl + tmpString;
		this.countrySelected = country;
		this.jsonTranslation = getLangService();
	}
	
	/**
	 * Configurar el archivo JSON de la respuesta del servicio APA
	 * @param url Servicio APA
	 */
	public void setApaService( String url ) {
		apaMetadataService = url;
		jsonApaMetadata = getApaService(); 
	}
}