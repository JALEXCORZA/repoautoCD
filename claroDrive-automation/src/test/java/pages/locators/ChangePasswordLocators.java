package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePasswordLocators {

    @FindBy(how= How.CSS,using=".tab-option:nth-child(2)")
    public WebElement securityButton;

    @FindBy(how= How.CSS,using=".change-password")
    public WebElement changePassButton;

    @FindBy(how= How.CSS,using="div:nth-child(1) > .div-pwd > .input-pwd")
    public WebElement currentPassTextField;

    @FindBy(how= How.CSS,using=".ic-eye")
    public WebElement currentEye;

    @FindBy(how= How.CSS,using="div:nth-child(2) > .div-pwd > .input-pwd")
    public WebElement newPassTextField;

    @FindBy(how= How.CSS,using="label:nth-child(2) > .ic-eye")
    public WebElement newPassEye;

    @FindBy(how= How.CSS,using="div:nth-child(3) > .div-pwd > .input-pwd")
    public WebElement confirmPassTextField;

    @FindBy(how= How.CSS,using="label:nth-child(3) > .ic-eye")
    public WebElement confirmPassEye;

    @FindBy(how= How.CSS,using=".update-pwd")
    public WebElement confirmChangeButton ;

    @FindBy(how= How.CSS,using=".cancel-update-pwd")
    public WebElement cancelChangeButton;

}

