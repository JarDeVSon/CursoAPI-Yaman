package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/report.html"},
        features = "src/test/resources/features",
        glue = "steps",
        publish = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = ""
)
public class RunnerTest {
}
