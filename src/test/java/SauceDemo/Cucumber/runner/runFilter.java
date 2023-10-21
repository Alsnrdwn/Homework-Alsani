package SauceDemo.Cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/SauceDemo/Cucumber/feature/Filter.feature",
        glue = "SauceDemo.Cucumber.stepDef",
        plugin = {"html:target/HTML_filter_report.html"}
)

public class runFilter {
}
