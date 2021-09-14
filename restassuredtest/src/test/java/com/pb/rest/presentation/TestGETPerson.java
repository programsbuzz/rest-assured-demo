package com.pb.rest.presentation;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pb.rest.helper.PersonServiceHelper;
import com.pb.rest.model.Person;

public class TestGETPerson {
private PersonServiceHelper personServiceHelper;


@BeforeClass
public void init(){
	personServiceHelper = new PersonServiceHelper();
}
@Test
public void testGetAllPerson(){
	List<Person> personList = personServiceHelper.getAllPerson();
	assertNotNull(personList, "Person List : is not empty");
	assertFalse(personList.isEmpty(), "Person list is not True");
}



}
