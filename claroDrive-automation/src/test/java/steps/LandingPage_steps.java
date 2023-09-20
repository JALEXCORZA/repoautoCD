package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lib.ConsoleColors;
import pages.actions.LandingPageActions;
import pages.actions.NavFaqsActions;
import pages.utils.SeleniumDriver;
import utils.ConfigAttributes;
import utils.ConfigSingleton;


public class LandingPage_steps {

    private final ConfigSingleton config = ConfigSingleton.getInstance();
    LandingPageActions landingPageActions = new LandingPageActions();
    NavFaqsActions navFaqsActions = new NavFaqsActions();

    @Given("I want to navigate in Vc Claro")
    public void iWantToNavigateInVcClaro() {
        SeleniumDriver.openPage(config.getProperty(ConfigAttributes.Host));
    }

    @And("I want to see the {string} section")
    public void iWantToSeeTheSection(String arg0) {
        try {
            switch (arg0) {
                case "Administration":
                    landingPageActions.clickOnAdminLinkText();
                    break;
                case "Download":
                    landingPageActions.clickOnDownLinkText();
                    break;
                case "FAQs":
                    navFaqsActions.clickOnFAQsLinkText();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("I download the {string} version")
    public void iDownloadTheVersion(String arg0) {
        try {
            switch (arg0) {
                case "Mac":
                    landingPageActions.clickOnMacDownload();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("I click on {string} menu")
    public void iClickOnMenu(String arg0) {
        landingPageActions.clickOnRegDropBtn();
    }

    @And("I click on {string} country button")
    public void iClickOnCountryButton(String arg0) {
        landingPageActions.clickOnBrazilBtn();
    }

    @Then("I should see {string} section")
    public void iShouldSeeSection(String arg0) {
        try {
            switch (arg0) {
                case "Administração":
                    landingPageActions.seeAdminSectionBR();
                    break;
                case "Baixar":
                    landingPageActions.seeDownSectionBR();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la sección de " + arg0 + ConsoleColors.RESET);
    }
}






