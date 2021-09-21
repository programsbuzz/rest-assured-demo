package serial;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Deserialization {
@Test
private void deSerializatinTest() {
	SerializationPOJO spo2 = RestAssured.get("https://gorest.co.in/public/v1/users/1685").as(SerializationPOJO.class);
	System.out.println(spo2.toString());

}
}
