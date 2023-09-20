package pages.actions;

import lib.ConsoleColors;
import org.openqa.selenium.support.PageFactory;
import pages.locators.TermsConditionsLocators;
import pages.utils.SeleniumDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TermsConditionsActions {

    TermsConditionsLocators termsConditionsLocators;

    public TermsConditionsActions() {
        this.termsConditionsLocators = new TermsConditionsLocators();
        PageFactory.initElements(SeleniumDriver.getDriver(), termsConditionsLocators);
    }

    public void clickOnTermsBtn() {
        termsConditionsLocators.termsBtn.click();
    }

    public void seeTermsPage() throws InterruptedException {
       SeleniumDriver.explicitWait(termsConditionsLocators.termsTitleText);
        termsConditionsLocators.termsTitleText.getText().contains("TÉRMINOS Y CONDICIONES");
        System.out.println(ConsoleColors.GREEN_BRIGHT + "Elemento encontrado" + ConsoleColors.RESET);
    }

}
