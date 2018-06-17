package runners;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun=false,
		features="src/test/resources/features",
		glue = "stepdefs"
		)

public class CukesRunner {
	
}
