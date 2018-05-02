package com.PostCalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoQA_Customer_PostCall {
	
	
	@Test(enabled = false,groups= {"postcall"})
	public void createNewCustomer() {
		
		//1.) define base url end point
		 RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		 //2.) define the http request
		 RequestSpecification httpRequest = RestAssured.given();

		 //3.) create a json object with all the fields 
		  org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		  requestJson.put("FirstName", "sambo");
		  requestJson.put("LastName", "yappo");
		  requestJson.put("UserName", "samisabir003");
		  requestJson.put("Password", "peterparkerSPiderMan1");
		  requestJson.put("Email", "avenger12@gmail.com");


		  System.out.println("executing group postcall");

		  //4.) add header ...define the type of header you are sending (JSON)
		  httpRequest.header("Content-Type", "application/json");

		  //5.) add the json payload to the body of the request::
		  httpRequest.body(requestJson.toJSONString());

		  //6.) post the request and get the response:
		  Response response = httpRequest.post("/register");

		  //7.)now get the response body and print the result
		 String responseBody = response.getBody().asString();
		  System.out.println("Response Body is:: "+ responseBody);

		  JsonPath jspath = response.jsonPath();
		  String faultCode = jspath.get("Faultid");
		  String SuccessCode = jspath.getString("SuccessCode");
 
		  //validate the fault code
		 //  Assert.assertEquals(faultCode, "User already exists");
		 //  System.out.println("assertion passed (faultCode)");
		  Assert.assertEquals(SuccessCode, "OPERATION_SUCCESS");
		  System.out.println("assertion passed (SuccessCode)");
 
		  //8.) get the status code and validate it
		 int statusCode = response.getStatusCode();
		 System.out.println(statusCode);
		 Assert.assertEquals(statusCode, 201);
 
		  //9.)get the headers
		  System.out.println(response.getHeaders());
 
		  //assert the content type
		  String contentType = response.getContentType();
		  Assert.assertEquals(contentType, "application/json");
 
		  //more better way of getting individual header content
		  String contentLength = response.getHeader("Content-Length");
		  System.out.println(contentLength);
 



		

		
	}

}
