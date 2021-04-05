package org.com.demo.pkalita.stepdefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.com.demo.pkalita.util.ConfigReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Base {

    private static RequestSpecification requestSpecification;
    ConfigReader configReader = new ConfigReader();

    public RequestSpecification getRequestSpecification() throws FileNotFoundException {

        Properties properties = configReader.init_prop();
        if(requestSpecification==null) {
            PrintStream log = new PrintStream(new FileOutputStream("target/log.txt"));
            requestSpecification = new RequestSpecBuilder().setBaseUri(properties.getProperty("BASE_URL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log)) // Logging
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public ResponseSpecification getResponseSpecification(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }
}
