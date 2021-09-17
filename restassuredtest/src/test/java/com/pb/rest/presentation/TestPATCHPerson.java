package com.pb.rest.presentation;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pb.rest.helper.PersonServiceHelper;

public class TestPATCHPerson {
	private PersonServiceHelper personServiceHelper;


	@BeforeClass
	public void init(){
		personServiceHelper = new PersonServiceHelper();
	}
	
	@Test
	public void testPatchPerson(){
		String id = personServiceHelper.updatePerson(3).jsonPath().getString("id");
		System.out.println(id);
		assertNotNull(id, "Updated");
	}
	
	
	
	
}
