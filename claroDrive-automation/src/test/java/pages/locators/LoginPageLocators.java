package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageLocators {


    @FindBy(how= How.CSS,using=".brand-logo > img")
    public WebElement vcClaroImage;

    @FindBy( how= How.ID, using ="email")
    public static WebElement adminEmailTextField;

    @FindBy(id ="password")
    public WebElement adminPassTextField;

    @FindBy(how= How.CSS,using=".btn:nth-child(1)")
    public WebElement loginButton;

    @FindBy(how= How.CSS,using=".btn:nth-child(4)")
    public WebElement cancelButton;

    @FindBy(how= How.CSS,using=".btn:nth-child(5)")
    public WebElement recoverPassButton;

    @FindBy(how= How.CSS,using=".underline")
    public WebElement registerButton;

    //@FindBy(how = How.ID, using = "startConference")
    @FindBy(how= How.CSS,using=".HR_Btn")
    public WebElement startConference;
}
