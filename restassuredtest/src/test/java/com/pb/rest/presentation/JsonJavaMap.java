package com.pb.rest.presentation;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonJavaMap
{
    @Test
    private void JsonBodyHashMap()
    {
    	Map<String, String> requestBody  = new HashMap<String, String>();
    	
    	requestBody.put("name", "Tarun Goswami");
    	requestBody.put("email", "tarun.goswami78@gmail.com");
    	requestBody.put("gender", "male");
    	requestBody.put("status","active");
    	
    	RestAssured
    		.given()
    			.header("Authorization", "Bearer c005c96081fd4f9205d53b5f0e8d0aab4eac0c63ad282de7aeda1f2b2fd0bdc6")
    			.baseUri("https://gorest.co.in/public/v1/users")
    			.contentType(ContentType.JSON)
    			.body(requestBody)
    			.log().body()
    		.when()
    			.post()
    		.then()
    			.assertThat().statusCode(201)
    			.log().body();
    }
}
