package org.company.StepDefinitions;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.company.APIObjects.LoginAPI;
import org.company.Utilities.JSONParser;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class SampleAPISd {
    LoginAPI loginAPI;

    @When("^I login via API$")
    public void loginAPI() {
        loginAPI = new LoginAPI();
        loginAPI.submitLoginViaAPI();
    }

    @Then("^I verify a failed login code")
    public void assertFailedLoginCode() {
        assertEquals(loginAPI.getResponseCode(), 404);
    }

    @Then("^I verify a failed login message")
    public void assertFailedLoginMessage() {
        JSONParser jsonParser = new JSONParser(LoginAPI.getOutputJson());
        assertEquals("Unauthorized: Username not found", jsonParser.getValue("message"));
    }
}

