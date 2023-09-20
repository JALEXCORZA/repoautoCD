package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FaqsLocators {

    // FAQs filters
    @FindBy(how= How.CSS,using=".filter:nth-child(1)")
    public WebElement generalBtn;

    @FindBy(how= How.CSS,using=".filter:nth-child(2)")
    public WebElement videoconferenceBtn;

    @FindBy(how= How.CSS,using=".filter:nth-child(3)")
    public WebElement chatsBtn;

    @FindBy(how= How.CSS,using=".filter:nth-child(4)")
    public WebElement contactsBtn;

    @FindBy(how= How.CSS,using=".filter:nth-child(5)")
    public WebElement profileBtn;

    // Filters FAQs elements
    @FindBy(how= How.CSS,using=".complete-question:nth-child(1) .arrow")
    public WebElement faqArrowBtn1;

    @FindBy(how= How.CSS,using=".complete-question:nth-child(2) .arrow")
    public WebElement faqArrowBtn2;

    @FindBy(how= How.CSS,using=".complete-question:nth-child(3) .arrow")
    public WebElement faqArrowBtn3;

    @FindBy(how= How.CSS,using=".complete-question:nth-child(4) .arrow")
    public WebElement faqArrowBtn4;

    @FindBy(how= How.CSS,using=".complete-question:nth-child(5) .arrow")
    public WebElement faqArrowBtn5;

    @FindBy(how= How.CSS,using=".complete-question:nth-child(6) .arrow")
    public WebElement faqArrowBtn6;

    // Filters answers elements
    @FindBy(how= How.CSS,using=".complete-question:nth-child(1) > .answer")
    public WebElement answerText;


}

