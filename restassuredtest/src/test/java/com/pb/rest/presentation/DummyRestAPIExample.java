package com.pb.rest.presentation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DummyRestAPIExample 
{
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		RestAssured.basePath = "/api/v1";
	}
	
	@Test
	public void postMethodExample()
	{
		Response res = 
		given()
			.body("{" + 
					"\"name\":\"test\",\n" + 
					"\"salary\":\"123\",\n" + 
					"\"age\":\"23\"\n" + 
					"}")
		.when()
			.post("/create");
		
		System.out.println(res.body());
			
	}

}
