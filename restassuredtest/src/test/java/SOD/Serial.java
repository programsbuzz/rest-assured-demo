package SOD;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Serial {
private static final ObjectMapper MAPPER = new ObjectMapper();

public static void main(String[] args) throws JsonProcessingException {
	Pojo bp1 = new Pojo();

	bp1.setId(2929);
	bp1.setName("Madara");
	bp1.setEmail("neji@mbn.com");
	bp1.setGender("Male");
	bp1.setStatus("active");
	
	String url = "https://gorest.co.in/public/v1/users/";
	String json = MAPPER.writeValueAsString(bp1);
	
	Response response = RestAssured.given().header("Authorization","Bearer 22165c6cdaa5a062950d4559b5824ad289743ef9eb6d8ef5d156691aaf876f70").contentType("application/json").log().all().body(json).when().post(url).andReturn();
	int statusCode = response.getStatusCode();
	assertEquals(201, statusCode);
	Response response2 = RestAssured.get("https://gorest.co.in/public/v1/users?email=neji@mbn.com");
	JsonPath js = new JsonPath(response2.asString());                                                     
	String id = js.getString("data[0].id");
	System.out.println("****************************");
    System.out.println(id);
    System.out.println("****************************");
    Pojo bp2 = RestAssured.given().get("https://gorest.co.in/public/v1/users/"+id).as(Pojo.class);
    
    System.out.println(bp2.toString());
}



}
