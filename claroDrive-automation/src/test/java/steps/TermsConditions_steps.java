package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lib.ConsoleColors;
import pages.actions.LandingPageActions;
import pages.actions.TermsConditionsActions;
import pages.utils.SeleniumDriver;
import utils.ConfigAttributes;
import utils.ConfigSingleton;

import static java.lang.Thread.sleep;


public class TermsConditions_steps {

    TermsConditionsActions termsConditionsActions = new TermsConditionsActions();
    LandingPageActions landingPageActions = new LandingPageActions();
    private final ConfigSingleton config = ConfigSingleton.getInstance();

    @Given("I want to see Terms and Conditions")
    public void iWantToSeeTermsAndConditions() throws InterruptedException {
        SeleniumDriver.openPage(config.getProperty(ConfigAttributes.Host));
        landingPageActions.seeLandingPage();
    }

    @And("I click on {string} button")
    public void clickOnBtn(String arg0) {
        termsConditionsActions.clickOnTermsBtn();
        System.out.println(ConsoleColors.YELLOW_BRIGHT + "Clic en Términos y condiciones" + ConsoleColors.RESET);

    }

    @Then("I should see TermsConditions page")
    public void iShouldSeeTermsConditionsPage() {
        try {
            termsConditionsActions.seeTermsPage();
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la sección de Términos y Condiciones " + ConsoleColors.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
