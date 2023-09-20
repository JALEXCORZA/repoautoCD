package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.utils.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterActions {
    private int counterStep = 1;

    @After
    public void tearDown(Scenario scenario) throws Exception{
        WebDriver driver=SeleniumDriver.getDriver();
        System.out.println(scenario.isFailed());
//        if (scenario.isFailed()) {
//            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(file, new File("./src/screenshots/error.png"));
//        }
        SeleniumDriver.tearDown();
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws Exception {
        WebDriver driver = SeleniumDriver.getDriver();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssZ");
        Date date = new Date();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("./src/screenshots/"+
                scenario.getName()+"/step"+counterStep+formatter.format(date)+".png"));
        counterStep += 1;
    }
}
