package pages.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lib.ConsoleColors;
import pages.locators.MailBoxLocators;
import pages.utils.SeleniumDriver;

public class MailBoxActions {
	
	MailBoxLocators mailBoxLocators;
	String tmpName;
	
	public MailBoxActions() {
		mailBoxLocators = new MailBoxLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), mailBoxLocators);
	}
	
	/**
	 * Realiza un cierre de sesión dentro de la plataforma Claro Drive.
	 * @throws InterruptedException
	 */
	public void logout(  ) throws InterruptedException {
		SeleniumDriver.explicitWait( mailBoxLocators.buttonAvatar );
		mailBoxLocators.buttonAvatar.click();		
		mailBoxLocators.buttonLogout.click();
	}
	
	/**
	 * Verifica si el navegador esta viendo el buzón de Claro Drive.
	 * @throws InterruptedException 
	 */
	public void isOnTheMailBox( ) throws InterruptedException { 
		SeleniumDriver.explicitWait( mailBoxLocators.buttonLogo );
		assertTrue( mailBoxLocators.buttonLogo.isDisplayed() );
	}
	
	/**
	 * Verifica si un archivo se encuentra al buscarlo en la barra de busqueda (por tanto se encuentra en CD).
	 * @param name Nombre por el que se buscarà el archivo
	 * @return
	 * @throws InterruptedException
	 */
	public boolean isOnTheSearch( String name ) throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.boxSearch );
		mailBoxLocators.boxSearch.sendKeys( name );
		
		try {
			SeleniumDriver.explicitWaitClickable( mailBoxLocators.appListSearchFirstLonely );
			mailBoxLocators.appListSearchFirstLonely.click();
		} catch (Exception e) { 
			try {
				SeleniumDriver.explicitWaitClickable( mailBoxLocators.appListSearchFirstMultiple );
				mailBoxLocators.appListSearchFirstMultiple.click();
			} catch (Exception e2) { return false; }
		}
		return true;
	}
	
	/**
	 * Cambia a la sección de Archivos dentro del buzón.
	 * @throws InterruptedException
	 */
	public void changetToFileSection() throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonSectionFiles );
		mailBoxLocators.buttonSectionFiles.click();
	}
	
	/**
	 * Hace click en el botón vista lista/cuadrícula para cambiar de vista.
	 * @throws InterruptedException
	 */
	public void clickViewButton() throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonGridListView );
		mailBoxLocators.buttonGridListView.click();
	}
	
	/**
	 * Crea una nueva carpeta estandarizada con un nombre único.
	 * @throws InterruptedException
	 */
	public void createDirectory() throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonCrear );
		mailBoxLocators.buttonCrear.click();
		mailBoxLocators.buttonCrearCarpeta.click();
		mailBoxLocators.textFieldCrearCarpeta.sendKeys(SeleniumDriver.generateName());
		mailBoxLocators.buttonAceptarNombreCrearCarpeta.click();
		Thread.sleep(5000);
	}
	
	public void clickModifiedFilter() throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.modifiedFilter );
		mailBoxLocators.modifiedFilter.click();
	}
	
	public void closeDetails() throws InterruptedException {
		try {
			SeleniumDriver.explicitWaitClickable( mailBoxLocators.crossDetails );
			mailBoxLocators.crossDetails.click();
		} catch (Exception e) {		}
	}
	
	/*
	 * Añade la carpeta recién creada a destacados por medio del menú contextual
	 * */
	public void addToFeature() throws InterruptedException {
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonMC );
		mailBoxLocators.buttonMC.click();
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.addToFeature );
		mailBoxLocators.addToFeature.click();
		SeleniumDriver.explicitWait( mailBoxLocators.featureNotification );
		//assertTrue(SeleniumDriver.getDriver().findElement(By.linkText("Se destacó")).isDisplayed());
		assertTrue( mailBoxLocators.featureNotification.getText().contains("Se destacó"));
		assertTrue( mailBoxLocators.featureIcon.isDisplayed());
		//Thread.sleep(5000);
	}
	
	/**
	 * Flujo encargado de hacer la selección y carga de un archivo
	 * @param command Comando para la creación de un archivo (cambiar para cada SO).
	 * @param extension Extención del archivo que se creará.
	 * @throws InterruptedException
	 */
	public  void uploadFiles( String command, String extension ) throws InterruptedException {	
		String os = System.getProperty( "os", "macOS" );
		String uploadDir = System.getProperty( "dir", "/home/" );
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonCrear );
		mailBoxLocators.buttonCrear.click();
		mailBoxLocators.buttonCargarArchivo.click();
		
		tmpName = SeleniumDriver.generateName() + extension;
		
		try {
			command.concat( tmpName );
			Runtime.getRuntime().exec( command + tmpName ); 
		} catch (IOException ioe) {
			System.out.println (ioe);
		}	
		
		try {
			Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
			int h = sSize.height;
			int w = sSize.width;
					
			Robot r = new Robot();
			
			r.mouseMove( w/2, h/2 );
			r.mousePress( InputEvent.BUTTON1_DOWN_MASK );
			
			if( os == "macOS" ) {
				 
				//Se despliega ventana para busqueda por dirección (iOS) CTRL+SHIFT+G
				//Se presionanteclas en conjunto
				r.keyPress( KeyEvent.VK_META ); 
				r.keyPress( KeyEvent.VK_SHIFT );
				r.keyPress( KeyEvent.VK_G );
				
				//Se envia conjunto de teclas
				r.keyRelease( KeyEvent.VK_META );
				r.keyRelease( KeyEvent.VK_SHIFT );
				r.keyRelease( KeyEvent.VK_G ); 
			
				
				//Se introduce dirección de archivo en la ventana de búsqueda controlando teclado
				type( r, "./src/files/uploads/" + tmpName );
			}
			
			if( os.contentEquals( "linuxOS" ) ) {
				//Se despliega ventana para busqueda por dirección (iOS) CTRL+SHIFT+G
				//Se presionanteclas en conjunto
				r.keyPress( KeyEvent.VK_CONTROL ); 
				r.keyPress( KeyEvent.VK_L );
				
				//Se envia conjunto de teclas
				r.keyRelease( KeyEvent.VK_CONTROL );
				r.keyRelease( KeyEvent.VK_L ); 
			
				//Se introduce dirección de archivo en la ventana de búsqueda controlando teclado
				type( r, uploadDir + tmpName );
			}
			
			//Se presiona enter para confirmar la ruta del archivo
			r.keyPress( KeyEvent.VK_ENTER );
			r.keyRelease( KeyEvent.VK_ENTER );
			
			//Se presiona enter de nuevo para aceptar la carga
			r.keyPress( KeyEvent.VK_ENTER );
			r.keyRelease( KeyEvent.VK_ENTER );
			
			SeleniumDriver.explicitWait( mailBoxLocators.uploadIsDone );
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadDirectory( String command, String extension ) throws InterruptedException {
		String os = System.getProperty( "os", "macOS" );
		String uploadDir = System.getProperty( "dir", "/home/" );
		SeleniumDriver.explicitWaitClickable( mailBoxLocators.buttonCrear );
		mailBoxLocators.buttonCrear.click();
		mailBoxLocators.buttonCargarCarpeta.click();
		
		tmpName = SeleniumDriver.generateName() + extension;
		
		try {
			command.concat( tmpName );
			Runtime.getRuntime().exec( command + tmpName ); 
		} catch (IOException ioe) {
			System.out.println (ioe);
		}	
		
		try {
			Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
			int h = sSize.height;
			int w = sSize.width;
					
			Robot r = new Robot();
			
			r.mouseMove( w/2, h/2 );
			r.mousePress( InputEvent.BUTTON1_DOWN_MASK );
			
			if( os == "macOS" ) {
				 
				//Se despliega ventana para busqueda por dirección (iOS) CTRL+SHIFT+G
				//Se presionanteclas en conjunto
				r.keyPress( KeyEvent.VK_META ); 
				r.keyPress( KeyEvent.VK_SHIFT );
				r.keyPress( KeyEvent.VK_G );
				
				//Se envia conjunto de teclas
				r.keyRelease( KeyEvent.VK_META );
				r.keyRelease( KeyEvent.VK_SHIFT );
				r.keyRelease( KeyEvent.VK_G ); 
			
				
				//Se introduce dirección de archivo en la ventana de búsqueda controlando teclado
				type( r, "./src/files/uploads/" );
			}
			
			if( os.contentEquals( "linuxOS" ) ) {
				//Se despliega ventana para busqueda por dirección (iOS) CTRL+SHIFT+G
				//Se presionanteclas en conjunto
				r.keyPress( KeyEvent.VK_CONTROL ); 
				r.keyPress( KeyEvent.VK_L );
				
				//Se envia conjunto de teclas
				r.keyRelease( KeyEvent.VK_CONTROL );
				r.keyRelease( KeyEvent.VK_L ); 
			
				//Se introduce dirección de archivo en la ventana de búsqueda controlando teclado
				type( r, uploadDir );
			}
			
			//Se presiona enter para confirmar la ruta del archivo
			r.keyPress( KeyEvent.VK_ENTER );
			r.keyRelease( KeyEvent.VK_ENTER );
			
			//Se presiona enter de nuevo para aceptar la carga
			r.keyPress( KeyEvent.VK_ENTER );
			r.keyRelease( KeyEvent.VK_ENTER );
			
			//Hace falta solucionar la alerta para que funcione suerte a quien lo haga!
			//Que dios los acompañe
			
//			SeleniumDriver.explicitWaitAlert();
//			Alert alert = SeleniumDriver.getDriver().switchTo().alert();
//			System.out.println( ConsoleColors.CYAN_BACKGROUND + alert.getText() + ConsoleColors.RESET);
//			alert.accept();
			
			SeleniumDriver.explicitWait( mailBoxLocators.uploadIsDone );
		
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*********************************************************
	 *  	FUNCIONES PARA INTERACTUAR CON TECLADO 			 *
	 ********************************************************/
	/**
	 * Función principal para convertir una cadena de texto en una secuencia de telcas presionadas por @see Robot
	 * @param robot Elemento de la clase @see Robot para controlar teclado
	 * @param characters Secuencia de caracteres que seras presionados en teclado
	 */
	private static void type( Robot robot, CharSequence characters ) {
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type( robot, character);
        }
    }

	/**
	 * Función secundaria para seleccionar teclas a presionar según una cadena de texto en pisadas de teclado
	 * @param robot Elemento de la clase @see Robot para controlar teclado
	 * @param characters Secuencia de caracteres que seras presionados en teclado
	 */
    private static void type( Robot robot, char character ) {
        switch (character) {
        case 'a': doType( robot, KeyEvent.VK_A ); break;
        case 'b': doType( robot, KeyEvent.VK_B ); break;
        case 'c': doType( robot, KeyEvent.VK_C ); break;
        case 'd': doType( robot, KeyEvent.VK_D ); break;
        case 'e': doType( robot, KeyEvent.VK_E ); break;
        case 'f': doType( robot, KeyEvent.VK_F ); break;
        case 'g': doType( robot, KeyEvent.VK_G ); break;
        case 'h': doType( robot, KeyEvent.VK_H ); break;
        case 'i': doType( robot, KeyEvent.VK_I ); break;
        case 'j': doType( robot, KeyEvent.VK_J ); break;
        case 'k': doType( robot, KeyEvent.VK_K ); break;
        case 'l': doType( robot, KeyEvent.VK_L ); break;
        case 'm': doType( robot, KeyEvent.VK_M ); break;
        case 'n': doType( robot, KeyEvent.VK_N ); break;
        case 'o': doType( robot, KeyEvent.VK_O ); break;
        case 'p': doType( robot, KeyEvent.VK_P ); break;
        case 'q': doType( robot, KeyEvent.VK_Q ); break;
        case 'r': doType( robot, KeyEvent.VK_R ); break;
        case 's': doType( robot, KeyEvent.VK_S ); break;
        case 't': doType( robot, KeyEvent.VK_T ); break;
        case 'u': doType( robot, KeyEvent.VK_U ); break;
        case 'v': doType( robot, KeyEvent.VK_V ); break;
        case 'w': doType( robot, KeyEvent.VK_W ); break;
        case 'x': doType( robot, KeyEvent.VK_X ); break;
        case 'y': doType( robot, KeyEvent.VK_Y ); break;
        case 'z': doType( robot, KeyEvent.VK_Z ); break;
        case 'A': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_A ); break;
        case 'B': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_B); break;
        case 'C': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_C ); break;
        case 'D': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_D ); break;
        case 'E': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_E ); break;
        case 'F': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_F ); break;
        case 'G': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_G ); break;
        case 'H': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_H ); break;
        case 'I': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_I ); break;
        case 'J': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_J ); break;
        case 'K': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_K ); break;
        case 'L': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_L ); break;
        case 'M': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_M ); break;
        case 'N': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_N ); break;
        case 'O': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_O ); break;
        case 'P': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_P ); break;
        case 'Q': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_Q ); break;
        case 'R': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_R ); break;
        case 'S': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_S ); break;
        case 'T': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_T ); break;
        case 'U': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_U ); break;
        case 'V': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_V ); break;
        case 'W': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_W ); break;
        case 'X': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_X ); break;
        case 'Y': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_Y ); break;
        case 'Z': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_Z ); break;
        case '`': doType( robot, KeyEvent.VK_BACK_QUOTE ); break;
        case '0': doType( robot, KeyEvent.VK_0 ); break;
        case '1': doType( robot, KeyEvent.VK_1 ); break;
        case '2': doType( robot, KeyEvent.VK_2 ); break;
        case '3': doType( robot, KeyEvent.VK_3 ); break;
        case '4': doType( robot, KeyEvent.VK_4 ); break;
        case '5': doType( robot, KeyEvent.VK_5 ); break;
        case '6': doType( robot, KeyEvent.VK_6 ); break;
        case '7': doType( robot, KeyEvent.VK_7 ); break;
        case '8': doType( robot, KeyEvent.VK_8 ); break;
        case '9': doType( robot, KeyEvent.VK_9 ); break;
        case '-': doType( robot, KeyEvent.VK_MINUS ); break;
        case '=': doType( robot, KeyEvent.VK_EQUALS ); break;
        case '~': doType( robot, KeyEvent.VK_ALT_GRAPH, KeyEvent.VK_PLUS ); break;
        case '!': doType( robot, KeyEvent.VK_EXCLAMATION_MARK ); break;
        case '@': doType( robot, KeyEvent.VK_AT ); break;
        case '#': doType( robot, KeyEvent.VK_NUMBER_SIGN ); break;
        case '$': doType( robot, KeyEvent.VK_DOLLAR ); break;
        case '%': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_5 ); break;
        case '^': doType( robot, KeyEvent.VK_CIRCUMFLEX ); break;
        case '&': doType( robot, KeyEvent.VK_AMPERSAND ); break;
        case '*': doType( robot, KeyEvent.VK_ASTERISK ); break;
        case '(': doType( robot, KeyEvent.VK_LEFT_PARENTHESIS ); break;
        case ')': doType( robot, KeyEvent.VK_RIGHT_PARENTHESIS ); break;
        case '_': doType( robot, KeyEvent.VK_UNDERSCORE ); break;
        case '+': doType( robot, KeyEvent.VK_PLUS ); break;
        case '\t': doType( robot, KeyEvent.VK_TAB ); break;
        case '\n': doType( robot, KeyEvent.VK_ENTER ); break;
        case '[': doType( robot, KeyEvent.VK_OPEN_BRACKET ); break;
        case ']': doType( robot, KeyEvent.VK_CLOSE_BRACKET ); break;
        case '\\': doType( robot, KeyEvent.VK_BACK_SLASH ); break;
        case '{': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET ); break;
        case '}': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET ); break;
        case '|': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH ); break;
        case ';': doType( robot, KeyEvent.VK_SEMICOLON ); break;
        case ':': doType( robot, KeyEvent.VK_COLON ); break;
        case '\'': doType( robot, KeyEvent.VK_QUOTE ); break;
        case '"': doType( robot, KeyEvent.VK_QUOTEDBL ); break;
        case ',': doType( robot, KeyEvent.VK_COMMA); break;
        case '<': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA ); break;
        case '.': doType( robot, KeyEvent.VK_PERIOD ); break;
        case '>': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD ); break;
        case '/': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_7 ); break;
        case '?': doType( robot, KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH ); break;
        case ' ': doType( robot, KeyEvent.VK_SPACE ); break;
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
    }
    
    /**
     * Función primaria para recursividad que procesa cuantas teclas se pulsan a la vez
     * @param robot Elemento de la clase @see Robot para controlar teclado
	 * @param keyCodes Cantidad variable de eventos de teclas
     */
    private static void doType( Robot robot, int... keyCodes) {
        doType(robot, keyCodes, 0, keyCodes.length);
    }
    
    /**
     * Función secundaria para recursividad que procesa cuantas teclas se pulsan a la vez
     * @param robot Elemento de la clase @see Robot para controlar teclado
	 * @param keyCodes Arreglo de eventos de teclado
     */
    private static void doType( Robot robot, int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }
        robot.keyPress(keyCodes[offset]);
        doType(robot, keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }
    
    public String  getTmpName() {
    	return tmpName;
    }
}
