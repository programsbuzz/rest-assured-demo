package serial;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.omg.CORBA.Request;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SerialReqRes {
@Test
private void reqSerialize() {

Response response = null;
Response response2 = null;
SerializationPOJO spo = new SerializationPOJO("Sasuke", "sasuke@nana.com","male","active"); //change email address before post sir everytime.
response = RestAssured.given().header("Authorization","Bearer 22165c6cdaa5a062950d4559b5824ad289743ef9eb6d8ef5d156691aaf876f70").contentType("application/json").body(spo).when().post("https://gorest.co.in/public/v1/users");

response2 = RestAssured.get("https://gorest.co.in/public/v1/users?email=sasuke@nana.com");
JsonPath js = new JsonPath(response2.asString());                                                     //tried to filter via email and get the id sir.
String id = js.getString("data[0].id");
System.out.println("****************************");

System.out.println(id);

RequestSpecification request = RestAssured.given();
Response response3 = request.get("https://gorest.co.in/public/v1/users/"+id);
ResponseBody b = response3.getBody();
System.out.println("****************************");
System.out.println(b.asPrettyString());
SerializationPOJO spo2 = b.as(SerializationPOJO.class);
System.out.println("****************************");
System.out.println(spo2.email);
//System.out.println(res2);
int statusCode = response.getStatusCode();
assertEquals(201, statusCode);

//	RestAssured.baseURI = "https://bookstore.toolsqa.com/swagger/";
//	RequestSpecification request = RestAssured.given();
//String payload = "{\r\n"+
//  "\"userName\": \"Ashwin\",\r\n" +
//  " \"password\": \"ashwin@2R\"\r\n" +
//"}";
//request.header("Content-Type","application/json");
//Response responseFromGenerateToken = request.body(payload).post("/Account​/v1​/GenerateToken");
//responseFromGenerateToken.prettyPrint();
}
}
