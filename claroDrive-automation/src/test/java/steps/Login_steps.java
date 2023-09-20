package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lib.ConsoleColors;
import pages.actions.*;
import pages.utils.SeleniumDriver;
import utils.ConfigAttributes;
import utils.ConfigSingleton;

import java.io.File;

public class Login_steps {
    LoginPageActions loginPageActions = new LoginPageActions();
    LandingPageActions landingPageActions = new LandingPageActions();
    NavFaqsActions navFaqsActions = new NavFaqsActions();

    private final ConfigSingleton config = ConfigSingleton.getInstance();

    @Given("I want to login on Vc Claro")
    public void iWantToLoginOnVcClaro() {
        SeleniumDriver.openPage(config.getProperty(ConfigAttributes.Host) + "/iam/login");
    }

    @When("I should see the {string} page")
    public void iShouldSeeThePage(String arg0) throws InterruptedException {
        try {
            switch (arg0) {
                case "Login":
                    loginPageActions.seeLoginPage();
                    break;
                case "Home":
                    loginPageActions.seeHomePage();
                    break;
                case "Landing":
                    landingPageActions.seeLandingPage();
                    break;
                case "Faqs":
                    navFaqsActions.seeFaqsPage();
                    break;
                case "BrazilLanding":
                    landingPageActions.seeBrazilLandingPage();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //loginPageActions.seeLoginPage();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la vista de " + arg0 + " " + ConsoleColors.RESET);
    }

    @Then("I send my {string} and {string} input")
    public void iSendMyInput(String arg1, String arg2) throws InterruptedException {
        SeleniumDriver.openPage(config.getProperty(ConfigAttributes.Host) + "/iam/login");
        loginPageActions.sendEmailUser(arg1);
        loginPageActions.sendPwdUser(arg2);
        System.out.println("Ingreso mi usuario: " + arg1 + " y contraseña: " + arg2 + " ");
    }

    @And("Click on {string} button")
    public void clickOnButton(String arg0) {
        try {
            switch (arg0) {
                case "LOGIN":
                    loginPageActions.clickOnLoginBtn();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ConsoleColors.YELLOW + "Click en botón de " + arg0 + ConsoleColors.RESET);
    }
}
