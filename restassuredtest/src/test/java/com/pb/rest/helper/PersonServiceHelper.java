package com.pb.rest.helper;

import java.lang.reflect.Type;
import java.util.List;

import javax.xml.ws.Endpoint;

import com.pb.rest.model.Person;
import com.pb.rest.util.ConfigUtil;

import groovyjarjarasm.asm.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PersonServiceHelper {
//we need to read the config variables
//Rest Assured about the url,port
//Make a GET request on this url and send the data to TestGETPerson
	
	
private static final String BASE_URL = ConfigUtil.getInstance().getString("baseURL");

public PersonServiceHelper(){
	RestAssured.baseURI = BASE_URL;
}
public List<Person> getAllPerson(){
	Response response = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.get(Constants.GET_ALL_PERSON)
			.andReturn();
	Type type = new com.fasterxml.jackson.core.type.TypeReference<List<Person>>() {}.getType();
	List<Person> personList = response.as(type);
	return personList;
	
}

}
