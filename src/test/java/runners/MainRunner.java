package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "junit:target/cucumber-reports/report.xml",
                "html:target/cucumber-reports/report.html",
        },
        features = "src/test/resources/feature_files",
        glue = "step_definitions",
        tags = "@regression"
)
public class MainRunner {


}
