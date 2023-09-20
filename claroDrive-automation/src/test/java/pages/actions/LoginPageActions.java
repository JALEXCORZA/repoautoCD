package pages.actions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.locators.LoginPageLocators;
import pages.utils.SeleniumDriver;
import lib.ConsoleColors;

import static java.lang.Thread.sleep;

public class LoginPageActions {

    WebDriver driver=null;
    LoginPageLocators loginPageLocators;

    public LoginPageActions(){

        this.driver=driver;
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(SeleniumDriver.getDriver(), loginPageLocators);
    }

    public void seeLoginPage() throws InterruptedException {
        SeleniumDriver.waitForPageToLoad();
        assert(loginPageLocators.adminEmailTextField).isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
        loginPageLocators.adminPassTextField.click();
    }

    public void sendEmailUser(String email) throws InterruptedException {
        loginPageLocators.adminEmailTextField.sendKeys(email);
    }


    public void sendPwdUser(String psw) throws InterruptedException {
        loginPageLocators.adminPassTextField.sendKeys(psw);
    }


    public void clickOnLoginBtn() {
        loginPageLocators.loginButton.click();
    }

    public void seeHomePage() throws InterruptedException {
        SeleniumDriver.waitForPageToLoad();
        SeleniumDriver.explicitWait(loginPageLocators.startConference);
        assert(loginPageLocators.startConference).isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }
}
