package pages.actions;

import org.openqa.selenium.support.PageFactory;
import pages.locators.NavFaqsLocators;
import pages.utils.SeleniumDriver;
import lib.ConsoleColors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class NavFaqsActions {

    NavFaqsLocators navFaqsLocators;

    public NavFaqsActions() {
        this.navFaqsLocators = new NavFaqsLocators();
        PageFactory.initElements(SeleniumDriver.getDriver(), navFaqsLocators);
    }

    public void clickOnFAQsLinkText() {
        navFaqsLocators.faqsLinkText.click();
    }

    public void seeFaqsPage() throws InterruptedException {
       SeleniumDriver.explicitWait(navFaqsLocators.faqsLinkText);
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void clickOnClaroVideoLinkText() {
        navFaqsLocators.claroVideoLinkText.click();
        SeleniumDriver.switchTo();
    }

    public void clickOnClaroMusicaLinkText() {
        navFaqsLocators.claroMusicaLinkText.click();
        SeleniumDriver.switchTo();
    }

    public void clickOnClaroShopLinkText() {
        navFaqsLocators.claroShopLinkText.click();
        SeleniumDriver.switchTo();
    }

    public void clickOnClaroDriveLinkText() {
        navFaqsLocators.claroDriveLinkText.click();
        SeleniumDriver.switchTo();
    }

    public void seeClaroVideoPage() {
        SeleniumDriver.waitForPageToLoad();
        SeleniumDriver.validateURL("https://www.clarovideo.com/mexico/landing");
    }

    public void seeClaroMusicaPage() {
        SeleniumDriver.waitForPageToLoad();
        SeleniumDriver.validateURL("https://www.claromusica.com/landing");
    }

    public void seeClaroShopPage() {
        SeleniumDriver.waitForPageToLoad();
        SeleniumDriver.validateURL("https://www.claroshop.com/");
    }

    public void seeClaroDrivePage() {
        SeleniumDriver.waitForPageToLoad();
        SeleniumDriver.validateURL("https://www.clarodrive.com/");
    }

    public void clickOnHomeLinkText() {
        navFaqsLocators.homeLinkText.click();
    }

    public void clickOnAdminLinkText() {
        navFaqsLocators.adminLinkText.click();
    }

    public void clickOnFunctionsLinkText() {
        navFaqsLocators.functionsLinkText.click();
    }

    public void clickOnDownLinkText() {
        navFaqsLocators.downloadLinkText.click();
    }

    public void clickOnLoginLinkText() {
        navFaqsLocators.loginLinkText.click();
    }

    public void seeAdminSection() throws InterruptedException {
        SeleniumDriver.explicitWait(navFaqsLocators.manageText);
        assert(navFaqsLocators.manageText).isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeFunctionsSection() throws InterruptedException {
        SeleniumDriver.explicitWait(navFaqsLocators.functionsLinkText);
        assert(navFaqsLocators.functionsText).isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeDownSection() throws InterruptedException {
        SeleniumDriver.explicitWait(navFaqsLocators.downloadBtn);
        assert(navFaqsLocators.downloadBtn).isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }
}
