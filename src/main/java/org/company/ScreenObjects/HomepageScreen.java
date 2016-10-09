package org.company.ScreenObjects;

import org.company.Element;
import org.openqa.selenium.By;

public class HomepageScreen {

    private  Element signUpButton = new Element(By.xpath("//button[contains(@label, 'SIGN UP')]"));

    public HomepageScreen(){
        signUpButton.waitForElementToBeDisplayed(5);
    }

    public String getSignUpButton() {
        String signUpButtonText = signUpButton.getText();
        return signUpButtonText;
    }
}
