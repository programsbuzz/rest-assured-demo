package com.pb.rest.helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.http.HttpStatus;

import com.pb.rest.model.Person;
import com.pb.rest.util.ConfigUtil;

import groovyjarjarasm.asm.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PersonServiceHelper {
//we need to read the config variables
//Rest Assured about the url,port
//Make a GET request on this url and send the data to TestGETPerson
	
	
private static final String BASE_URL = ConfigUtil.getInstance().getString("baseURL");

public PersonServiceHelper(){
	RestAssured.baseURI = BASE_URL;
//	RequestSpecification requestt = RestAssured.given();
//	requestt.urlEncodingEnabled(false);
}
public List<Person> getAllPerson(){
	Response response = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.get(Constants.GET_ALL_PERSON)
			.andReturn();
	Type type = new com.fasterxml.jackson.core.type.TypeReference<List<Person>>() {}.getType();
	assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"ok");

	List<Person> personList = response.as(type);
	return personList;
	
}
public Response createPerson(){
	
	Person person = new Person();
	person.setId(22);
	person.setEmail("neo@matrix.com");
	person.setFirstName("NEO");
	person.setLastName("The One");
	person.setAvatar("www.matrix.com");
	
	
	Response response = RestAssured.given().contentType(ContentType.JSON).when().body(person).post(Constants.CREATE_PERSON).andReturn();
	
	assertEquals(response.getStatusCode(),HttpStatus.SC_CREATED,"created");
	
	return response;
}

public Response updatePerson(Integer id){
	Person person = new Person();
	//person.setId(3);
	person.setEmail("morph@moof.com");
	person.setFirstName("neo");
	person.setLastName("blue");
	person.setAvatar("https://moose.in/");
	Response response = RestAssured.given().contentType(ContentType.JSON).when().body(person).patch(Constants.UPDATE_PERSON).andReturn();
	
	assertTrue(response.getStatusCode() == HttpStatus.SC_OK);
	return response;
}













}
