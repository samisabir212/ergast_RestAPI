package com.GetCall;


import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class WeatherGetTests {


    @Test
    public void getWeatherDetailsTest(){

        //1.define the base url
        RestAssured.baseURI =" http://restapi.demoqa.com/utilities/weather/city/";

        //2. define the http request object
        RequestSpecification httprequest = RestAssured.given();

        /*
        make a request/execute the request in the same time
        put it inside a response object and validate that response 
        with an assertion
        */
        Response response = httprequest.request(Method.GET,"/casablanca");

        



    }

}