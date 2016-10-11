package org.company.StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.company.Automator;
import org.company.Navigation.NavigateToUrl;
import org.company.ScreenObjects.HomepageScreen;
import org.company.Utilities.ReadJSONFile;
import org.company.Utilities.WriteJSONFile;

import static org.junit.Assert.assertEquals;


public class SampleGUISd {

    private Automator automator;

    @When("^I navigate to the lightbox website$")
    public void navigateLightbox() {
        NavigateToUrl navigateToUrl = new NavigateToUrl();
        navigateToUrl.toLightboxWebsite();
    }

    @Then("^I verify I can see the sign up button$")
    public void verifySignUpButton() {
        HomepageScreen homepageScreen = new HomepageScreen();
        assertEquals("SIGN UP", homepageScreen.getSignUpButton());
    }

    @Then("^I close the browser$")
    public void closeBrowser() {
        automator.quitBrowser();
    }

    @Given("^I write data to JSON file:([^\\\"]*) key:([^\\\"]*) and value:([^\\\"]*)$")
    public void writeJsonFile(String jsonFile, String key, String value) {
        WriteJSONFile writeJSONFile = new WriteJSONFile();
        writeJSONFile.writeValueJsonFile(jsonFile+".json", key, value);
    }

    @When("^I read data from JSON file$")
    public void readJsonFile() {
        ReadJSONFile readJSONFile = new ReadJSONFile();
        assertEquals("a@a.com", readJSONFile.getJsonValue("users.json", "test"));
    }
}
