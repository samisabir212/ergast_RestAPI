package com.PostCalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rest.objects.CustomerCreationRespnse_Success;
import com.qa.rest.objects.CustomerCreationResponse_Failure;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PostCallWithJavaObjects {


    @Test(enabled = true,groups= {"postcall"})
	public void createNewCustomerTest2() {
		
		//1.) define base url end point
		 RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		 //2.) define the http request
		 RequestSpecification httpRequest = RestAssured.given();

		 //3.) create a json object with all the fields 
		  org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		  requestJson.put("FirstName", "sami2w23");
		  requestJson.put("LastName", "sabir23w2");
		  requestJson.put("UserName", "samisabir00223w2");
		  requestJson.put("Password", "peterparkerSPiderManw322");
		  requestJson.put("Email", "avenger3212w22@gmail.com");


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

        
        /*
        what ever the response object is, 
        it will get mapped with what ever variables are available in the
        CustomerResponse.class
        There are 2 variables there "SuccessCode" && "Message"
        which is the reponse in json format... 
        its just getting checked by the basis of the keys
        variable key in CustomerResponse.class should  get
        mapped with keys in the JSON response body
        this is called 
        *****Deserializtion*****
        ---Deserializtion the response into CustomerResponse class:
        Positive Scenario
        */
        //write conditions of execution  :)

        if(response.statusCode()==201){
            CustomerCreationRespnse_Success customerResponse = response.as(CustomerCreationRespnse_Success.class); //customerResponse.class object got created
            System.out.println("Customer response message is ::  "+customerResponse.Message);
            System.out.println("Customer SuccessCode is :: " + customerResponse.SuccessCode);
    
    
            Assert.assertEquals("OPERATION_SUCCESS", customerResponse.SuccessCode);
            Assert.assertEquals("Operation completed successfully", customerResponse.Message);
            Assert.assertEquals(201, response.statusCode());
            Assert.assertNotEquals(200, response.statusCode());
        } else if(response.statusCode()==200){
           // Negative Scenario
            CustomerCreationResponse_Failure customerCreationResponse_Failure = response.as(CustomerCreationResponse_Failure.class);
            System.out.println("Customer Response faultid is " + customerCreationResponse_Failure.FaultId);
            System.out.println("Customer response fault is " + customerCreationResponse_Failure.fault);
    
            System.out.println(response.statusCode());
    

        }
    
    
       


		
	}



    

}
