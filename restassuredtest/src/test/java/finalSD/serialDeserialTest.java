package finalSD;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class serialDeserialTest {
	private static final ObjectMapper MAPPER = new ObjectMapper();
@Test
public void DeserTest() throws JsonProcessingException{
	dataPojo dp = new dataPojo();
	dp.setId(1010);
	dp.setName("Morpheus");
	dp.setEmail("morpheus@matrix.com");
	dp.setGender("Male");
	dp.setStatus("active");
	String url = "https://gorest.co.in/public/v1/users/";
	String json = MAPPER.writeValueAsString(dp);
	
	Response response = RestAssured.given().header("Authorization","Bearer 22165c6cdaa5a062950d4559b5824ad289743ef9eb6d8ef5d156691aaf876f70").contentType("application/json").log().all().body(dp).when().post(url).andReturn();
	int statusCode = response.getStatusCode();
	assertEquals(201, statusCode);
	Response response2 = RestAssured.get("https://gorest.co.in/public/v1/users?email=morpheus@matrix.com");
	JsonPath js = new JsonPath(response2.asString());                                                     
	String id = js.getString("data[0].id");
	System.out.println("****************************");
    System.out.println(id);
    System.out.println("****************************");
	RestAssured.baseURI = "https://gorest.co.in/public/v1/users/"+id;
	 
	 firstPojo fp = RestAssured.given().when().get().as(firstPojo.class);
	System.out.println(fp.toString());
}
}
