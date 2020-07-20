package com.pb.rest.specs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RequestSpecificationTest 
{
	RequestSpecBuilder reqSpecBuilder;
	static RequestSpecification reqSpec;
	
	@BeforeClass
	public void setup()
	{
		reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setBaseUri("https://reqres.in/");
		reqSpecBuilder.setBasePath("api");
		reqSpecBuilder.addQueryParam("page", "2");
//		AuthenticationScheme  authScheme = RestAssured.oauth(consumerKey, consumerSecret, accessToken, secretToken);
//		reqSpecBuilder.setAuth(authScheme);
		reqSpec = reqSpecBuilder.build();
	}

	
	@Test(enabled=true)
	public void conditionalLogic()
	{
		Response res = 
		given()
//			.queryParam("page", "2")
		    .spec(reqSpec)
		.when()
			.get("/users/");
			
		JsonPath js = new JsonPath(res.asString());
		
		int size = js.getInt("data.size()");
		String email = "michael.lawson@reqres.in";
		String temp = "";
		
		for(int i = 0; i < size; i++)
		{
			temp = js.getString("data["+i+"].email");
			if(temp.equals(email))
			{
				System.out.println(js.getString("data["+i+"]"));
				break;
			}
		}	
	}
	
	@Test(enabled=true)
	public void ArrayIterate()
	{
		Response res = 
		given()
//			.queryParam("page", "2")
	    .spec(reqSpec)

		.when()
			.get("/users/");
			
		JsonPath js = new JsonPath(res.asString());
		
		int size = js.getInt("data.size()");
		
		for(int i = 0; i < size; i++)
		{
			System.out.println(js.getString("data["+i+"].email"));
		}	
	}
	

}
