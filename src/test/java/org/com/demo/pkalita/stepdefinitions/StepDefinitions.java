package org.com.demo.pkalita.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.demo.pkalita.Constants.APIResources;
import org.com.demo.pkalita.testdata.TestData;
import org.com.demo.pkalita.util.FileUtil;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Base {

    private RequestSpecification requestSpecification;
    private Response response;
    TestData testData = new TestData();
    public static String placeID;  // declared static to retain the value for next TC.

    @Given("I have a Payload to Add Place with {string} {string} {string} {string}")
    public void iHaveAPayloadToAddPlaceWith(String name, String language, String address, String phoneNumber) throws FileNotFoundException {
        requestSpecification = given().spec(getRequestSpecification())
                .body(testData.addPlacePayload(name, language, address, phoneNumber));
    }

    @When("I make a calls to the {string} with {string} http request")
    public void iMakeACallsToTheWithPostHttpRequest(String resource_url, String method) {
        APIResources apiResources = APIResources.valueOf(resource_url);

        if (method.equalsIgnoreCase("POST")) {
            response = requestSpecification
                    .when()
                    .post(apiResources.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = requestSpecification
                    .when()
                    .get(apiResources.getResource());
        }
    }

    @Then("I should gets the http status code as {int}")
    public void iShouldGetsTheHttpStatusCodeAs(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);
    }

    @And("the {string} in response body is {string}")
    public void theInResponseBodyIs(String field, String expectedValue) {

        String[] str = response.asString().split("/>"); //splitting the response string due to extra html fields
        System.out.println("ACTUAL RESPONSE : " + str[2]);
        JsonPath jsonPath = FileUtil.stringToJson(str[2]);
        System.out.println("Field Name : " + field);
        System.out.println("Expected Value : " + expectedValue);
        System.out.println("Actual Value : " + jsonPath.get(field).toString());
        assertEquals(jsonPath.get(field).toString(), expectedValue);
        placeID = jsonPath.get("place_id").toString();
    }

    @And("verify place_ID created maps to {string} using {string}")
    public void verifyPlace_IDCreatedMapsToUsing(String expectedName, String resource_url) throws FileNotFoundException {
        //APIResources apiResources = APIResources.valueOf(resource_url);
        requestSpecification = given().spec(getRequestSpecification()) // create the request object
                .queryParams("place_id", placeID);
        iMakeACallsToTheWithPostHttpRequest(resource_url, "GET"); // Calling the When statement to make the call.
        String actualName = FileUtil.getJsonPath(response, "name");
        assertEquals(actualName, expectedName);

    }

    @Given("I have a Payload to Delete Place")
    public void iHaveAPayloadToDeletePlace() throws FileNotFoundException {
        requestSpecification = given().spec(getRequestSpecification())
                .body(testData.deletePlacePayload(placeID));
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String field, String expectedValue) {
        String actualValue =FileUtil.getJsonPath(response, field);
        assertEquals(actualValue,expectedValue);
    }
}
