package steps;

import api.ApiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenarioUtils;


public class Hooks extends ApiUtils {

    @Before
    public void before(Scenario scenario) {
        ScenarioUtils.add(scenario);
    }

    @After
    public void after() {
        ScenarioUtils.remove();
    }
}

