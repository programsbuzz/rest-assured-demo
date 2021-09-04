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
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";
	}
	
	@Test
	public void postMethodExample()
	{
		given()
			.body("{" + 
					"\"name\":\"Tarun Goswami\",\n" + 
					"\"job\":\"QA\",\n" + 
					"}")
		.when()
			.post("/users")
		.then()
		    .log()
//		    .cookies()
//		    .status()
//		    .headers()
//		    .body()
		    .all()
			.statusCode(201);
		
//		System.out.println(res.body().prettyPrint());
			
	}
}
