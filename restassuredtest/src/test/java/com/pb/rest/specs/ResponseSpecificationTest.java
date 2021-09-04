package com.pb.rest.specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationTest 
{
	ResponseSpecBuilder resSpecBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api";
		resSpecBuilder = new ResponseSpecBuilder();
		resSpecBuilder.expectStatusCode(200);
		resSpecBuilder.expectBody("ad.company", equalTo("StatusCode Weekly"));
		responseSpec = resSpecBuilder.build();
	}

	
	@Test(enabled=true)
	public void test1()
	{
		given()
			.queryParam("page", "2")
		.when()
			.get("/users/")
		.then()
			.spec(responseSpec)
			.body("total_pages", equalTo(2));	
	}
	
	@Test(enabled=true)
	public void test2()
	{
		given()
		.when()
			.get("/users/2")
		.then()
		.spec(responseSpec);
	}
}
