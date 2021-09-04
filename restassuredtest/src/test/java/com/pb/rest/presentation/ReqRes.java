package com.pb.rest.presentation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ReqRes 
{
	public static String id = "2";

	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api";
	}

	
	/* Reusing Response*/
	@Test(enabled=false)
	public void jsonschemavalid()
	{
				given()
					.pathParam("id", id)
				.when()
					.get("/users/{id}")
				.then()
					.assertThat()
					.body(matchesJsonSchemaInClasspath("com/pb/rest/presentation/testschema.json"));
			
	}
	
	/*Patch request example*/
	@Test(enabled=false)
	public void patchExample()
	{
		
				given()
					.header("Content-Type","application/json")
					.body("{\n" + 
							"    \"name\": \"Tarun Goswami1\",\n" + 
							"    \"job\": \"QA\"\n" + 
							"}")
				.when()
					.patch("/users/2")
				.then()
					.log().ifStatusCodeMatches(equalTo(200));

	}
	
	/*Put request example*/
	@Test(enabled=false)
	public void putExample()
	{
		Response res = 
				given()
					.header("Content-Type","application/json")
					.body("{\n" + 
							"    \"name\": \"Tarun Goswami1\",\n" + 
							"    \"job\": \"QA\"\n" + 
							"}")
				.when()	
					.put("/users/2")
				.then()
					.statusCode(200)
					.extract().response();
		
		System.out.println(res.body().prettyPrint());				
	}
	
	
	/*Delete request example*/
	@Test(enabled=false)
	public void deleteExample()
	{
		given()
		.when()
			.delete("/users/2")
		.then()
			.statusCode(204);
	}
	

	@DataProvider(name="userlogin")
	public String[][] createUser()
	{
		//Object[][] - 1: Size and  2: 
		// 3*2 objects
		return new String[][]
		{
			{"Tarun Goswami","QA"},
			{"Ram Sharma","DEV"},
			{"Mohan Verma","SM"}
		};
		
	}
	
	/*POST Request Example*/
	@Test(enabled=true, dataProvider="userlogin")
	public void postExample(String name, String profile)
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
	}
	

	
	
	/*Query Parameter and Logs Example*/
	@Test(enabled=false)
	public void test1()
	{
		given()
			.log()
//			.all()
			.headers()
			.queryParam("page", "2")
		.when()
			.get("/users/")
		.then()
			.statusCode(200)
			.body("ad.company", equalTo("StatusCode Weekly"))
			.body("total_pages", equalTo(2));	
	}
	
	
	/*Assertion in Body*/
	@Test(enabled=false)
	public void test2()
	{
		given()
		.when()
			.get("/users/2")
		.then()
			.statusCode(200)
			.body("ad.company", equalTo("StatusCode Weekly"));
	}
	
	
	/*JsonPath Conditional Logic*/
	@Test(enabled=false)
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
	
	/*JsonPath Iterations*/
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
	
	/*JsonPath Size and Retrieval*/
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
	
	/*Assert using TestNG*/
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
	
	/* Assert body and headers*/
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
	
	
	/*JSON Path Example*/
	@Test(enabled=true)
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
		
		System.out.println("id is: "+id);		
	}
	
	
	/* Reusing Response*/
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
	
	/*Status code verfication*/
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
