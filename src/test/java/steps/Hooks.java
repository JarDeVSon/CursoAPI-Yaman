package steps;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ApiUtils;
import utils.ScenarioUtils;


public class Hooks extends ApiUtils {
    @Before
    public void Before(Scenario scenario) {
        ScenarioUtils.add(scenario);

    }

    @After
    public void AfterTest() {
        ScenarioUtils.remove();
    }

}