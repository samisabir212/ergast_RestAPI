package com.PostCalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoQA_Customer_PostCall {
	
	
	@Test(groups= {"postcall"})
	public void createNewCustomer() {
		
		//1.) define base url end point
		 RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		 //2.) define the http request
		 RequestSpecification httpRequest = RestAssured.given();

		 //3.) create a json object with all the fields 
		  org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		  requestJson.put("FirstName", "sami");
		  requestJson.put("LastName", "sabir");
		  requestJson.put("UserName", "samisabir002");
		  requestJson.put("Password", "peterparkerSPiderMan");
		  requestJson.put("Email", "avenger212@gmail.com");


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
		 String faultCode = jspath.get("FaultId");

		 //validate the fault code
		 Assert.assertEquals(faultCode, "User already exists");
		 System.out.println("assertion passed (faultCode)");

		 //8.) get the status code and validate it
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);

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
