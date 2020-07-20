package com.pb.rest.presentation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Temp 
{
String id = "1";
	
	@BeforeClass
	public void setup() 
	{
		RestAssured.baseURI = "https://reqres.in/";		
		RestAssured.basePath = "/api/users/";
	}
			
	
	@Test(enabled=false)
	public void queryParametersExample() 
	{
		given()
			.queryParam("page", "2")
		.when()
		
			.get("/users/")
		
		.then()
			.statusCode(200);
	}
	
	@Test
	public void pathParametersExample()
	{
//		Response res = 
		given()
			.pathParam("id", id)
		.when()
			.get("{id}")
		.then()
			.statusCode(200);
			
//		System.out.println(res.body().prettyPrint());
	}
	

}
