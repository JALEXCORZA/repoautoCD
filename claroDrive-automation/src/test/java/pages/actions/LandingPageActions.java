package pages.actions;

import io.cucumber.java.eo.Se;
import lib.ConsoleColors;
import org.openqa.selenium.support.PageFactory;
import pages.locators.ChangePasswordLocators;
import pages.locators.LandingPageLocators;
import pages.utils.SeleniumDriver;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LandingPageActions {

    LandingPageLocators landingPageLocators;

    public LandingPageActions() {
        this.landingPageLocators = new LandingPageLocators();
        PageFactory.initElements(SeleniumDriver.getDriver(), landingPageLocators);
    }

    public void seeLandingPage() throws InterruptedException {
        SeleniumDriver.explicitWait(landingPageLocators.administrationLinktext);
        landingPageLocators.administrationLinktext.isDisplayed();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void clickOnAdminLinkText() {
        landingPageLocators.administrationLinktext.click();
    }

    public  void clickOnDownLinkText() {
        landingPageLocators.downloadLinkText.click();
    }

    public void clickOnMacDownload() {
        landingPageLocators.macDownload.click();
    }

    public void clickOnRegDropBtn() {
        landingPageLocators.regDropBtn.click();
    }

    public void clickOnBrazilBtn() {
        landingPageLocators.brazilBtn.click();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Cambio de región a Brazil" + ConsoleColors.RESET);
    }

    public void seeBrazilLandingPage() throws InterruptedException {
        SeleniumDriver.explicitWait(landingPageLocators.landingTitleText);
        assert(landingPageLocators.landingTitleText.getText().contains("Agende reuniões"));
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeAdminSectionBR() throws InterruptedException {
        SeleniumDriver.explicitWait(landingPageLocators.landingTitleText);
        assert(landingPageLocators.infoAdminText.getText().contains("Gerencie suas reuniões mais facilmente"));
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeDownSectionBR() throws InterruptedException {
        SeleniumDriver.explicitWait(landingPageLocators.landingTitleText);
        assert(landingPageLocators.infoDownloadText.getText().contains("Videoconferência Claro para computador"));
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

}
