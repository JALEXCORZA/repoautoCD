package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * 
 * @author ctin
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
        ,glue = {"steps"}
        ,monochrome=true
        ,plugin = { "pretty", "junit:target/cucumber-reports/Cucumber.xml",
        "html:target/cucumber-reports"}
        ,tags = "@VirtualBackground"
        )

public class CucumberRunnerTest {

}
