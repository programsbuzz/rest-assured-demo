package com.pb.rest.presentation;

import org.awaitility.Awaitility;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import static org.awaitility.Awaitility.*;
//import org.awaitility.core.ConditionFactory.*;
import static org.hamcrest.Matchers.*;



import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WaitCall 
{
	@BeforeClass
	public void setup() 
	{
		RestAssured.baseURI = "https://reqres.in/";		
		RestAssured.basePath = "/api/users/";
	}
	

	@DataProvider(name = "userlogin")
	public String[][] createUser() 
	{			
	    return new String[][]
	    {
	        {"Tarun Goswami","QA"},
	        {"Ram Sharma","DEV"},
	        {"Mohan Verma","SM"}
	    };
	}


	/*POST Request Example*/
	@Test(enabled=true, dataProvider ="userlogin")
	public void postExample(String name, String profile)
	{
		Awaitility.await().atMost(30,TimeUnit.SECONDS).until(()->mymethod(name, profile),equalTo(201));
	}
	

	public int mymethod(String name, String profile)
	{
	    Response res = 
	    given()
	        .header("Content-Type","application/json")
	        .body("{\n" + 
	            "    \"name\": \""+name+"\",\n" + 
	            "    \"job\": \""+profile+"\"\n" + 
	        "}")
	    .when()
	        .post("/users")
	    .then()
	        .statusCode(201)
	        .extract().response();   
			
	    res.body().prettyPrint();
	    
	    return res.then().extract().statusCode();
	}
	

}
