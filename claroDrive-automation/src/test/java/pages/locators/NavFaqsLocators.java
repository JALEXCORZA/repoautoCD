package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavFaqsLocators {

    @FindBy(how= How.CSS,using="a:nth-child(5)")
    public WebElement faqsLinkText;

    // Links Claro pages
    @FindBy(how= How.CSS,using=".elinks li:nth-child(1) > a")
    public WebElement claroVideoLinkText;

    @FindBy(how= How.CSS,using=".elinks li:nth-child(2) > a")
    public WebElement claroMusicaLinkText;

    @FindBy(how= How.CSS,using="li:nth-child(3) > a")
    public WebElement claroShopLinkText;

    @FindBy(how= How.CSS,using="li:nth-child(4) > a")
    public WebElement claroDriveLinkText;

    // Sections in FAQs
    @FindBy(how= How.CSS,using=".anchor-link")
    public WebElement homeLinkText;

    @FindBy(how= How.CSS,using="a:nth-child(2)")
    public WebElement adminLinkText;

    @FindBy(how= How.CSS,using="a:nth-child(3)")
    public WebElement functionsLinkText;

    @FindBy(how= How.CSS,using="a:nth-child(4)")
    public WebElement downloadLinkText;

    @FindBy(how= How.CSS,using="a:nth-child(5)")
    public WebElement loginLinkText;

    // Administration Section elements
    @FindBy(how= How.CSS,using=".info:nth-child(1) > .title")
    public WebElement manageText;

    // Functions Section elements
    @FindBy(how= How.CSS,using=".use-claro-connect > .title")
    public WebElement functionsText;

    // Download Section elements
    @FindBy(how= How.CSS,using=".black-button:nth-child(3) > h3:nth-child(2)")
    public WebElement downloadBtn;

}

