package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TermsConditionsLocators {

    // Terms and Conditions
    @FindBy(how= How.CSS,using=".footer-section:nth-child(2) > .list span")
    public WebElement termsBtn;

    // Header
    @FindBy(how= How.CSS,using="h1")
    public WebElement termsTitleText;

}

