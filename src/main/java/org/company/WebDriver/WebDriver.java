package org.company.WebDriver;


import org.company.AutomationType;
import org.company.Automator;

public class WebDriver {

    public void quitBrowser(){
        Automator automator = new Automator(AutomationType.CHROME);
        automator.quit();
    }
}
