package org.company;

import cucumber.api.CucumberOptions;
import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "json:target/"},
        features = {"src/main/org.company.Features"}
//        glue = {"."}
)

public class CucumberRunner {
}
