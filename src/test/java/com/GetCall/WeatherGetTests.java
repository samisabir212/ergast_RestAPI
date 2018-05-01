package com.GetCall;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;


public class WeatherGetTests {


    @Test(groups= {"getcall"})
    public void getWeatherDetailsTest(){

        //1.define the base url
        RestAssured.baseURI ="http://restapi.demoqa.com/utilities/weather/city";

        //2. define the http request object
        RequestSpecification httprequest = RestAssured.given();

        /*
        make a request/execute the request in the same time
        put it inside a response object and validate that response 
        with an assertion
        */
        Response response = httprequest.request(Method.GET,"/Pune");
        
        System.out.println(response);

        //System.out.println(response.getBody().asString());

        //get response body as string
        String responseBody = response.getBody().asString();

        //assert that the body has something your looking for
        Assert.assertEquals(responseBody.contains("Pune"), true);

        System.out.println("Response body is :: "+responseBody);

       int statuscode = response.getStatusCode();

       System.out.println("Status code is :: "+statuscode);

       System.out.println("status session ID is :: " + response.getSessionId() );
       System.out.println("status line of code is :: "+ response.getStatusLine());
       System.out.println("this is pretty print :: "+ response.prettyPrint());
       System.out.println("this is getting the cookies "+response.getCookies());
       System.out.println("this is getting the headers "+ response.getHeaders());
       System.out.println("this is getting the jsonPath "+ response.jsonPath());
       System.out.println("this is getting the content type "+ response.contentType());
       System.out.println("this is getting the time "+ response.getTime());

       String connection = response.getHeader("Connection");
       System.out.println("This is getting the connection "+connection);


       Assert.assertEquals(statuscode, 200);

       JsonPath jspath=response.jsonPath();

       //get the key value by using jsonpath
       //pass the key as the parameter to get the value
       String city = jspath.get("City");
       System.out.println("City is :: "+city);

       String temperature = jspath.getString("Temperature");
       System.out.println(temperature);

        //softAssert.assertEquals(200, 200);







    }

}