package com.pb.rest.presentation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class ReqRes 
{
	public static String id = "2";
	public int a;
	//Hello
	
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api";
	}

	
	@Test(enabled=true)
	public void conditionalLogic()
	{
		Response res = given()
			.queryParam("page", "2")
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
	
	@Test(enabled=false)
	public void ArrayIterate()
	{
		Response res = given()
			.queryParam("page", "2")
		.when()
			.get("/users/");
			
		JsonPath js = new JsonPath(res.asString());
		
		int size = js.getInt("data.size()");
		
		for(int i = 0; i < size; i++)
		{
			System.out.println(js.getString("data["+i+"].email"));
		}	
	}
	
	
	@Test(enabled=false)
	public void ArraySizeAndElements()
	{
		Response res = given()
			.queryParam("page", "2")
		.when()
			.get("/users/");
			
		JsonPath js = new JsonPath(res.asString());
		
		int size = js.getInt("data.size()");
		System.out.println("size is: "+size);
		String firstUserEmail = js.getString("data[5].email");
		System.out.println("user email: "+firstUserEmail);
	}
	
	@Test(enabled=false)
	public void TestPathParametersExample()
	{
		Response res = given()
			.queryParam("page", "2")
		.when()
			.get("/users/");
			
		JsonPath js = new JsonPath(res.asString());
		
		Assert.assertEquals(js.getString("total_pages"), "2");
		Assert.assertEquals(res.getHeader("server"), "cloudflare1");
	
	}
	
	@Test(enabled=false)
	public void pathParametersExample()
	{
		given()
			.queryParam("page", "2")
		.when()
			.get("/users/")
		.then()
			.statusCode(200)
			.body("total_pages", equalTo(2))
			.header("server", equalTo("cloudflare"));
		
	}
	
	
	@Test(enabled=false)
	public void postRequestExample()
	{
		String res = 
		given()
			.body("{" + 
					"    \"name\": \"Tarun\",\n" + 
					"    \"job\": \"Goswami\"\n" + 
					"}")
			  
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.extract().response().asString();
		
		JsonPath js = new JsonPath(res);
		id = js.getString("id");
		
		System.out.println("id is"+id);
			
	}
	
	@Test(enabled=false)
	public void reusedResponse()
	{
		Response res = 
				given()
					.pathParam("id", id)
				.when()
					.get("/users/{id}");
				
				System.out.println("res is:"+res.body().prettyPrint());
	}
	
	@Test(enabled=false)
	public void statusCodeVerification()
	{
		given()
			.queryParam("page","2")
		.when()
			.get("/users/")
		.then()
			.statusCode(200);
//			.body(arguments, responseAwareMatcher);
	}
}
