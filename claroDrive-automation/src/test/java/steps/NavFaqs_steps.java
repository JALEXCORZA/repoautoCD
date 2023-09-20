package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lib.ConsoleColors;
import pages.actions.LandingPageActions;
import pages.actions.LoginPageActions;
import pages.actions.NavFaqsActions;

import java.util.List;
import java.util.Random;

public class NavFaqs_steps {

    String claroPage;
    String section;
    NavFaqsActions navFaqsActions = new NavFaqsActions();
    LandingPageActions landingPageActions = new LandingPageActions();
    LoginPageActions loginPageActions = new LoginPageActions();

    @And("I open a Claro page")
    public void iOpenAPage(List<String> list) {
        try {
            String randomClaro = list.get(new Random().nextInt(list.size()));
            this.claroPage = randomClaro;
            switch (claroPage) {
                case "ClaroVideo":
                    navFaqsActions.clickOnClaroVideoLinkText();
                    break;
                case "ClaroMusica":
                    navFaqsActions.clickOnClaroMusicaLinkText();
                    break;
                case "ClaroShop":
                    navFaqsActions.clickOnClaroShopLinkText();
                    break;
                case "ClaroDrive":
                    navFaqsActions.clickOnClaroDriveLinkText();
                    break;
            }
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Random Claro Page --> " + randomClaro + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Clic en " + randomClaro + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("I should see Claro new page")
    public void iShouldSeeClaroPage() {

        try{
            switch (this.claroPage){
                case "ClaroVideo":
                    navFaqsActions.seeClaroVideoPage();
                    break;
                case "ClaroMusica":
                    navFaqsActions.seeClaroMusicaPage();
                    break;
                case "ClaroShop":
                    navFaqsActions.seeClaroShopPage();
                    break;
                case "ClaroDrive":
                    navFaqsActions.seeClaroDrivePage();
                    break;
             }
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la página de " + this.claroPage + ConsoleColors.RESET);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("I want to see another section")
    public void iWantToSeeAnotherSection(List<String> list) {
        try {
            String randomSection = list.get(new Random().nextInt(list.size()));
            this.section = randomSection;
            switch (randomSection) {
                case "home":
                    navFaqsActions.clickOnHomeLinkText();
                    break;
                case "administration":
                    navFaqsActions.clickOnAdminLinkText();
                    break;
                case "functions":
                    navFaqsActions.clickOnFunctionsLinkText();
                    break;
                case "download":
                    navFaqsActions.clickOnDownLinkText();
                    break;
                case "login":
                    navFaqsActions.clickOnLoginLinkText();
                    break;
            }
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Random Section --> " + randomSection + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Clic en " + randomSection + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("I should see new section")
    public void iShouldSeeNewSection() {
        try{
            switch (this.section){
                case "home":
                    landingPageActions.seeLandingPage();
                    break;
                case "administration":
                    navFaqsActions.seeAdminSection();
                    break;
                case "functions":
                    navFaqsActions.seeFunctionsSection();
                    break;
                case "download":
                    navFaqsActions.seeDownSection();
                    break;
                case "login":
                    loginPageActions.seeLoginPage();
                    break;
            }
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la sección de " + this.section + ConsoleColors.RESET);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
