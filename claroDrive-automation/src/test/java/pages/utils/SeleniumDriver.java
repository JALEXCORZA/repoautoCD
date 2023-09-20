package pages.utils;

import config.WebDriverFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lib.ConsoleColors;
import net.minidev.json.JSONObject;

import javax.management.ValueExp;

/**
 * Maneja la instancia de un web driver
 * @author ctin
 *
 */
public class SeleniumDriver extends WebDriverFactory {
    private static SeleniumDriver seleniumDriver;

    //initialize webdriver
    private static WebDriver driver;

    //initialize timeouts
    private static WebDriverWait waitDriver;
    public final static int TIMEOUT = 30;
    public final static int PAGE_LOAD_TIMEOUT = 50;

    // Test number
    public static int TEST_NUMBER = 0;
    
    /**
     * Crea una nueva instancia de un web driver
     * @throws IOException
     */
    private  SeleniumDriver() throws IOException {
        driver = createWebDriver();
        //waitDriver = new WebDriverWait(driver, TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        

        String window=driver.getWindowHandle();
        System.out.println("Window ->"+window);
    }

    public static void openPage(String url) {
        System.out.println(url);
        System.out.println(driver);
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() throws IOException {
        if (seleniumDriver == null)
            seleniumDriver = new SeleniumDriver();
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        seleniumDriver = null;
    }
    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void switchTo() {
        String winHandleBefore = driver.getWindowHandle();

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    public static String save() throws IOException, UnsupportedFlavorException {
        String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // guarda el texto copiado
        return myText;
    }

    public static String getUrl(){
        driver.getCurrentUrl();
        String strUrl = driver.getCurrentUrl();
        System.out.println(ConsoleColors.CYAN + "Current Url is:"+ strUrl + ConsoleColors.RESET);
        return strUrl;
    }

    public static void copyURL() {
        String str = driver.getCurrentUrl();
        String str2 = "/iam/c/";
        String newUrl = str.replace("/video/",str2);
        System.out.println(ConsoleColors.CYAN + "New URl:" + newUrl + ConsoleColors.RESET);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(newUrl);
        clipboard.setContents(strSel, null);
    }


    public static void validateURL(String expURL) {
        try{
            if (driver.getCurrentUrl() == expURL) {
                System.out.println(ConsoleColors.YELLOW_BRIGHT + "Redirección exitosa" + ConsoleColors.RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void explicitWait(WebElement element) throws InterruptedException {
        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000, 1));
        // Usamos la espera explícita bajo una condiciones esperada, en este caso esperamos hasta que el elemento sea clicable
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public static void explicitWaitClickable(WebElement element) throws InterruptedException {
        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120, 1));
        // Usamos la espera explícita bajo una condiciones esperada, en este caso esperamos hasta que el elemento sea clicable
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void explicitWaitAlert(  ) {
    	 // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120, 1));
        // Usamos la espera explícita bajo una condiciones esperada, en este caso esperamos hasta que el elemento sea clicable
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void clear(WebElement element) {
        String OS = System.getProperty("os.name");
        if (OS.contains("Windows"))
            element.sendKeys(Keys.CONTROL + "a");
        else
            element.sendKeys(Keys.COMMAND + "a");
        element.sendKeys(Keys.DELETE);
    }

    public static void selectText(WebElement element) {
        String OS = System.getProperty("os.name");
        if (OS.contains("Windows"))
            element.sendKeys(Keys.CONTROL + "a");
        else
            element.sendKeys(Keys.COMMAND + "a");
    }

    public static void updateTestNumber () {
        TEST_NUMBER++;
    }

    public static int getTestNumber () {
        return TEST_NUMBER;
    }
    
    /**
     * Genera un nombre que sirve para nombrar archivos o carpetas en un formato único e irrepetible
     */
    public static String generateName () {
         SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssZ");
         Date date = new Date();
         return formatter.format(date);
    }
    
    /**
     * Genera un mapa para textos de servicios.
     * @param serivicecUrl Url del servicio que se quiere obtener el mapa.
     * @return Mapa con textos del servicio.
     */
    public static JSONObject getJsonOfService( String serivicecUrl ){
    	return Services.getJsonOfService( serivicecUrl );
    }

}
