package pages.actions;

import lib.ConsoleColors;
import org.openqa.selenium.support.PageFactory;
import pages.locators.FaqsLocators;
import pages.utils.SeleniumDriver;

import static java.lang.Thread.sleep;

public class FaqsActions {

    FaqsLocators faqsLocators;

    public FaqsActions() {
        this.faqsLocators = new FaqsLocators();
        PageFactory.initElements(SeleniumDriver.getDriver(), faqsLocators);
    }

    public void clickOnGeneralBtn() {
        faqsLocators.generalBtn.click();
    }

    public void clickOnVidBtn() {
        faqsLocators.videoconferenceBtn.click();
    }

    public void clickOnChatsbtn() {
        faqsLocators.chatsBtn.click();
    }

    public void clickOnContactsBtn() {
        faqsLocators.contactsBtn.click();
    }

    public void clickOnProfileBtn() {
        faqsLocators.profileBtn.click();
    }

    public void seeGeneralFilter() throws InterruptedException {
        SeleniumDriver.explicitWait(faqsLocators.faqArrowBtn1);
        faqsLocators.faqArrowBtn1.click();
        assert(faqsLocators.answerText).getText().contains("es la plataforma que te mantiene en contacto");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeVideoconferenceFilter() throws InterruptedException {
        SeleniumDriver.explicitWait(faqsLocators.faqArrowBtn1);
        faqsLocators.faqArrowBtn1.click();
        assert(faqsLocators.answerText).getText().contains("puedes encender tu cámara y tu micrófono");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeChatsFilter() throws InterruptedException {
        SeleniumDriver.explicitWait(faqsLocators.faqArrowBtn1);
        faqsLocators.faqArrowBtn1.click();
        assert(faqsLocators.answerText).getText().contains("sistema de chats");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeContactsFilter() throws InterruptedException {
        sleep(5000);
        faqsLocators.faqArrowBtn1.click();
        assert(faqsLocators.answerText).getText().contains("Puedes enviarles una invitación");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

    public void seeProfileFilter() throws InterruptedException {
        sleep(5000);
        faqsLocators.faqArrowBtn1.click();
        assert(faqsLocators.answerText).getText().contains("El nombre");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

}
