package org.com.demo.pkalita.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.com.demo.pkalita.stepdefinitions"},
        //tags = "@AddPlace",
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"   //This report runs with Surefire plugin
        }
)
public class TestRunner {
        // mvn command
        // mvn test -Dcucumber.filter.tags="@AddPlace"

}
