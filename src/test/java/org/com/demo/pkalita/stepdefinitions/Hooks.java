package org.com.demo.pkalita.stepdefinitions;

import io.cucumber.java.Before;

;import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws FileNotFoundException {
        System.out.println("*************************************************************************");
        StepDefinitions stepDefinitions = new StepDefinitions();
        if(StepDefinitions.placeID==null) {
            stepDefinitions.iHaveAPayloadToAddPlaceWith("StandaloneTEST", "HINDI", "GANDHI NAGAR", "9840998949");
            stepDefinitions.iMakeACallsToTheWithPostHttpRequest("AddPlaceAPI", "POST");
            stepDefinitions.theInResponseBodyIs("scope", "APP");
        }
    }

}
