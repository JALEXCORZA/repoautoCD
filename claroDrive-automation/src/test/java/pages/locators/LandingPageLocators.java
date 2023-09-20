package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LandingPageLocators {

    // Text
    @FindBy(how= How.CSS,using=".title-container")
    public WebElement landingTitleText;

    @FindBy(how= How.CSS,using=".info:nth-child(1) > .title")
    public WebElement infoAdminText;

    @FindBy(how= How.CSS,using=".info-content:nth-child(1) > .title")
    public WebElement infoDownloadText;

    // Sections
    @FindBy(how= How.CSS,using=".anchor-link:nth-child(2)")
    public WebElement administrationLinktext;

    @FindBy(how= How.CSS,using=".anchor-link:nth-child(4)")
    public WebElement downloadLinkText;

    @FindBy(how= How.CSS,using=".black-button:nth-child(4)")
    public WebElement macDownload;

    // Region drop button
    @FindBy(how= How.CSS,using=".dropBtn > span")
    public WebElement regDropBtn;

    // Country buttons
    @FindBy(how= How.CSS,using="li:nth-child(3) img")
    public WebElement brazilBtn;



}

