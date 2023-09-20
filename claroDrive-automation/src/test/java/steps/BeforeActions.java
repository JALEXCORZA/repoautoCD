package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lib.ConsoleColors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.utils.SeleniumDriver;

import java.io.File;

public class BeforeActions {

    @Before( order = 0 )
    public void SetUp(Scenario scenario) throws Exception {
        SeleniumDriver.setUpDriver();
        if (SeleniumDriver.getTestNumber() < 1) {
            try {
                FileUtils.cleanDirectory(new File("./src/screenshots/"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SeleniumDriver.updateTestNumber();
    }
}
