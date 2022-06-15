package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.ReportUtils;

import java.io.IOException;

import static utils.ReportUtils.gerar;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/report.html", "pretty","json:target/report/cucumber.json"},
        features = "src/test/resources/features/",
        glue = "steps",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        tags = "@regressivo"
)
public class RunnerTest {
    @AfterClass
    public static void report() throws IOException {

        if (System.getProperty("os.name").equals("Windows 10")) {
            Runtime.getRuntime().exec("cmd.exe /c mvn cluecumber-report:reporting");
        } else {
            Runtime.getRuntime().exec("sh -c mvn cluecumber-report:reporting");
        }
        gerar();
    }
}
