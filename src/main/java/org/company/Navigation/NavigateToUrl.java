package org.company.Navigation;


import org.company.AutomationType;
import org.company.Automator;

import javax.print.attribute.standard.MediaSize;

public class NavigateToUrl {

    private Automator automator;

    public NavigateToUrl(){

    }

    public void toLightboxWebsite(){
        automator = new Automator(AutomationType.CHROME);
        Automator.driver.navigate().to("http://www.lightbox.co.nz");
    }
}
