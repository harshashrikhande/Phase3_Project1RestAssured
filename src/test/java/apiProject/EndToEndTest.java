package apiProject;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EndToEndTest {
	String emp_id;

	@Test
	public void TC01_GetAllEmployee() {
		RestAssured.given()
		.baseUri("http://localhost:3000/employees")
		.when()
		.get()
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	@Test
	public void  TC02_GetSingleEmployee() {
		RestAssured.given()
		.baseUri("http://localhost:3000/employees/1")
		.when()
		.get()
		.then()
		.log()
		.all()
		.statusCode(200)
		.body("name", Matchers.equalTo("Pankaj"));
	}
	
	@Test
	public void TC03_CreateEmployee() {
		HashMap<String,String> obj = new HashMap<String,String>();
		
		obj.put("id", "100");
		obj.put("name", "Rakesh");
		obj.put("salary", "7000");
		
		Response response = RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(obj)
					.when()
					.post();
		
		int resCode = response.statusCode();
		Assert.assertEquals(resCode, 201);
		
		JsonPath json = response.jsonPath();
		emp_id = json.get("id");
		System.out.println(emp_id);			
	}
	
	@Test
	public void  TC04_DeleteEmployee() {
		RestAssured.given()
		.baseUri("http://localhost:3000/employees")
		.when()
		.delete(emp_id)
		.then()
		.log()
		.all()
		.statusCode(200);	
	}
	
	@Test
	public void  TC05_TestDeletedEmployee() {
		RestAssured.given()
		.baseUri("http://localhost:3000/employees/"+emp_id)
		.when()
		.get(emp_id)
		.then()
		.log()
		.all()
		.statusCode(404);	
	}
	
}
