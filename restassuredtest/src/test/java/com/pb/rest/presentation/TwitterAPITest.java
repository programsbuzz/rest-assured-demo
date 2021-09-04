package com.pb.rest.presentation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import static io.restassured.RestAssured.withArgs;

public class TwitterAPITest 
{
	String consumerKey = "9wFbhfyCGAPwRq23DCfGnMG3y";
	String consumerSecret = "lmPQorJtvj3hp2mKdlOzA2Ay2rQsAj3491DfcLi1sAPpiUUR5i";
	String accessToken = "1441253365-jT2CXezyCWGjaKxu0FTSzmYYR4owfQDxPs9cKRt";
	String secretToken = "BEhXn6TPRszR3XvAudjUmqxPDU5NbhOJd78AxiWEMlK2D";
	
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
//		RestAssured.rootPath = "[0].user";
	}
	
	@Test(enabled = true)
	public void getExample()
	{
				given()
					.auth()
					.oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.queryParam("screen_name", "tgoswami013")
					.queryParam("count", "1")
					.header("Content-type","application/json")
				.when()
					.get("user_timeline.json")
				.then()
					.rootPath("[0].user.test")
//					.appendRootPath("%s.%s",withArgs("user","test"))
					.detachRootPath("test")
					.body("name", equalTo("Tarun"), "screen_name", equalTo("tgoswami013"),"followers_count", equalTo(10));			

//					.body("[0].user.name", equalTo("Tarun"), "[0].user.screen_name", equalTo("tgoswami013"),"[0].user.followers_count", equalTo(10));			
	}
	
	
	@Test(enabled = false)
	public void postExample()
	{
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, secretToken)
			.queryParam("status", "Hello testing rest assured twitter api")
			.header("Content-Type", "application/json")
		.when()
			.post("update.json")
		.then()
			.statusCode(200);
	}
	
//	.config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Set-Cookie"))).when()

}
