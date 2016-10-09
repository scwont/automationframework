package org.company.StepDefinitions;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.company.Automator;
import org.company.Navigation.NavigateToUrl;
import org.company.ScreenObjects.HomepageScreen;
import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

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
}
