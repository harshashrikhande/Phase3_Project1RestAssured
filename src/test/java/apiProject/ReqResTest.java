package apiProject;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ReqResTest {
	
	@Test
	public void getAllEmployee() {
		
		RestAssured.given()
		.baseUri("https://reqres.in/api/users")
		.when()
		.get()
		.then()
		.log()
		.all()
		.statusCode(200);
	
	}
	
	@Test
	public void getSingleEmployee() {
		
		RestAssured.given()
		.baseUri("https://reqres.in/api/users/3")
		.when()
		.get()
		.then()
		.log()
		.body()
		.statusCode(200);
	
	}
	
	@Test
	public void postEmployee() {
		
		HashMap<String,String> obj = new HashMap<String,String>();
		
		obj.put("name", "morpheus");
		obj.put("job", "leader");
		
		RestAssured.given()
		.baseUri("https://reqres.in/api/users")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(obj)
		.when()
		.post()
		.then()
		.log()
		.body()
		.statusCode(201);
	
	}
}
