package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lib.ConsoleColors;
import pages.actions.FaqsActions;

import java.util.List;
import java.util.Random;

public class Faqs_steps {

    String filter;
    FaqsActions faqsActions = new FaqsActions();

    @And("Click on Faqs filter")
    public void clickOnFaqsFilter(List<String> list) {
        try {
            String randFilter = list.get(new Random().nextInt(list.size()));
            this.filter = randFilter;
            switch (randFilter) {
                case "general":
                    faqsActions.clickOnGeneralBtn();
                    break;
                case "videoconference":
                    faqsActions.clickOnVidBtn();
                    break;
                case "chats":
                    faqsActions.clickOnChatsbtn();
                    break;
                case "contacts":
                    faqsActions.clickOnContactsBtn();
                    break;
                case "profile":
                    faqsActions.clickOnProfileBtn();
                    break;
            }
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Random Filter --> " + randFilter + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BRIGHT + "Clic en " + randFilter + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Then("I should see related Faqs")
    public void iShouldSeeRelatedFaqs() {
        try {
            switch (this.filter) {
                case "general":
                    faqsActions.seeGeneralFilter();
                    break;
                case "videoconference":
                    faqsActions.seeVideoconferenceFilter();
                    break;
                case "chats":
                    faqsActions.seeChatsFilter();
                    break;
                case "contacts":
                    faqsActions.seeContactsFilter();
                    break;
                case "profile":
                    faqsActions.seeProfileFilter();
                    break;
            }
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Estoy en la sección de " + this.filter + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
