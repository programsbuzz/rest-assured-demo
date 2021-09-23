package finalSD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class serialDeserialTest {
@Test
public void DeserTest(){
	RestAssured.baseURI = "https://gorest.co.in/public/v1/users/1546";
	 
	 firstPojo fp = RestAssured.given().when().get().as(firstPojo.class);
	System.out.println(fp.toString());
}
}
