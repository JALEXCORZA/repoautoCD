package config;

//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import lib.ConsoleColors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigAttributes;
import utils.ConfigSingleton;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Se encarga de configurar la instancia de un nuevo @see {@link WebDriver}.
 * @author ctin
 *
 */
public class WebDriverFactory {
    public WebDriver driver;
    private final ConfigSingleton config = ConfigSingleton.getInstance();


    public WebDriver createWebDriver() throws IOException {
        String webdriver = System.getProperty( "browser", "chromeWithHead" );

            switch(webdriver) {
                case "firefox":
                    return firefoxD();
                case "chrome":
                    return chromeD();
                case "edge":
                    return edgeD();
                case "opera":
                    return operaDriver();
                case "chromeWithHead":
                    return chromeDriver();
                case "chromeDRemote":
                    return chromeDRemote();
                case "firefoxDRemote":
                    return firefoxDRemote();
                case "edgeDRemote":
                    return edgeDRemote();
                default:
                    throw new RuntimeException("Unsupported webdriver: " + webdriver);
            }
        }
    
    /**
     * Devuelve una instancia de WebDriver de Chrome para un testeo a nivel local 
     * @return 
     */
    private WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\AMX java\\Pruebas Automatizadas\\dlaargentina-clarodrive-qa-automation-web-e7589b35b422\\dlaargentina-clarodrive-qa-automation-web-e7589b35b422\\claroDrive-automation\\Drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=src/files/sample.mjpeg");

        // Local environment
        // Ignore ssl
        options.addArguments("--ignore-ssl-error=yes");
        options.addArguments("--ignore-certificate-errors");

        HashMap prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        //Full Screen
        driver.manage().window().maximize();
        return  driver;
    }
    
    
    private WebDriver chromeD(){
    	String headless = System.getProperty( "headless" , "inactive" );
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=src/files/sample.mjpeg");
        
        if( headless == "active" )
        	options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        	
        
        HashMap prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        //Full Screen
        driver.manage().window().maximize();
        return  driver;
    }
    
    /**
     * Devuelve una instancia de WebDriver de Chrome para un testeo a nivel remoto.
     * @return
     * @throws MalformedURLException
     */
    private WebDriver chromeDRemote() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=src/files/sample.mjpeg");
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        HashMap prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        options.setExperimentalOption("prefs", prefs);
        driver = new RemoteWebDriver(new URL(config.getProperty(ConfigAttributes.Host_URL)), options);
        //Full Screen
        driver.manage().window().maximize();
        return  driver;
    }
    
    /**
     * Devuelve una instancia de WebDriver de Firefox para un testeo a nivel local.
     * @return
     * @throws MalformedURLException
     */
    private WebDriver firefoxDriver() throws MalformedURLException {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        options.addPreference("permissions.default.microphone", 1);
        options.addPreference("permissions.default.camera", 1);
        driver = new FirefoxDriver(options);
        //Full Screen
        driver.manage().window().maximize();
        return  driver;
    }

    private WebDriver firefoxD(){
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("permissions.default.microphone", 1);
        options.addPreference("permissions.default.camera", 1);
        driver = new FirefoxDriver();
        //Full Screen
        //driver.manage().window().maximize();
        return  driver;
    }
    
    /**
     * Devuelve una instancia de WebDriver para un testeo a nivel remoto
     * @return
     * @throws MalformedURLException
     */
    private WebDriver firefoxDRemote() throws MalformedURLException {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        options.addPreference("permissions.default.microphone", 1);
        options.addPreference("permissions.default.camera", 1);
        driver = new RemoteWebDriver(new URL(config.getProperty(ConfigAttributes.Host_URL)), options);
        return  driver;
    }
    
    /**
     * Devuelve una instancia de WebDriver de Edge para un testeo a nivel local
     * @return
     * @throws MalformedURLException
     */
    private WebDriver edgeDriver() throws MalformedURLException {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        driver = new RemoteWebDriver(new URL(config.getProperty(ConfigAttributes.Host_URL)), options);
        return driver;
    }
    
    
    
    private WebDriver edgeD(){
        WebDriverManager.edgedriver().setup();
        //manager.config().setEdgeDriverVersion("91.0.864.41");
        EdgeOptions edgeOptions = new EdgeOptions();
        //manager.setup();
        //edgeoptions.AddAdditionalCapability("dom.webnotifications.enabled",1);
        //edgeOptions.AddAdditionalCapability("permissions.default.microphone", 1);
        //edgeOptions.AddAdditionalCapability("permissions.default.camera", 1);
        driver = new EdgeDriver();
        //driver = new RemoteWebDriver(new URL(Host_URL), options);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Devuelve una instancia de WebDriver de Edge para un testeo a nivel remoto
     * @return
     * @throws MalformedURLException
     */
    private WebDriver edgeDRemote() throws MalformedURLException {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        driver = new RemoteWebDriver(new URL(config.getProperty(ConfigAttributes.Host_URL)), edgeOptions);
        driver.manage().window().maximize();
        return driver;
    }
    
    /**
     * Devuelve una instancia de WebDriver de Opera para un testeo a nivel local
     * @return
     * @throws MalformedURLException
     */
    private  WebDriver operaDriver() throws MalformedURLException {
        OperaOptions options = new OperaOptions();
        HashMap prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        options.setExperimentalOption("prefs",prefs);
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
