package com.pb.rest.presentation;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testing
{
	 static final String JSON = "{\n" +
	            "\"count\": 4,\n" +
	            "\"cust\": [\n" +
	            "    {\n" +
	            "        \"id\": \"029384\",\n" +
	            "        \"type\": \"STATUS\",\n" +
	            "        \"name\": \"STATUS_ID\"\n" +
	            "     \n" +
	            "    },\n" +
	            "    {\n" +
	            "        \"id\": \"938736\",\n" +
	            "        \"type\": \"RENEWAL\",\n" +
	            "        \"name\": \"RENEWAL_ID\"\n" +
	            "\n" +
	            "    }\n" +
	            "]\n" +
	            "}";

	    public static void main(String[] args) 
	    {
	        JsonPath jsonPath = new JsonPath(JSON);
	        List results = new List();
	        results = (List) jsonPath.getList("cust.findAll{i -> i.name == 'STATUS_ID' || i.name == 'RENEWAL_ID'}.id");
	    } 
}
