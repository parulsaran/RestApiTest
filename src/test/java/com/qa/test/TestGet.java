package com.qa.test;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.qa.base.TestBaseClass;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;


import java.util.HashMap;
import java.util.Map;

public class TestGet extends TestBaseClass{

	TestBaseClass testBase;
	String apiUrl;
	String serviceUrl;
	String url;
	
	@Before
	public void setUp() {
		testBase = new TestBaseClass();
		apiUrl = prop.getProperty("URL");
		serviceUrl = prop.getProperty("serviceURL");
		url = apiUrl + serviceUrl;
		

	}
	//Test to print the Json response
	@Test
	public void testGet() {
		Response response = given()
		.accept(ContentType.JSON)
		.when()
		.get(url);
		System.out.println(response.asString());
		
	}

	//Check the status code
	@Test
	public void testStatusCode() {
		int statusCode = given()
		.accept(ContentType.JSON)
		.when()
		.get(url)
		.thenReturn()
		.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(HttpStatus.SC_OK,statusCode);
		/*given()
		.accept(ContentType.JSON)
		.when()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);*/
	}
	
	//Get response with existing Id
	@Test
	public void testGetWithId() {
		String response = given()
				.accept(ContentType.JSON)
				.when()
				.get(url+"/1")
				.thenReturn()
				.body()
				.asString();
		System.out.println(response);
				//.statusCode();
				//System.out.println(statusCode);
				//Assert.assertEquals(HttpStatus.SC_OK,statusCode);
	}
	//Get response with non existing Id
	@Test
	public void testGetWithNonExistingId() {
			int statusCode = given()
			.accept(ContentType.JSON)
			.when()
			.get(url+"/0")
			.thenReturn()
			.statusCode();
			System.out.println(statusCode);
			Assert.assertEquals(HttpStatus.SC_NOT_FOUND,statusCode);
	}
	
	//testing get request with header
	@Test
	public void testGetWithIdWithHeader() {
		Map<String,String> headers = new HashMap<>();
		headers.put("Accept", "application/xml");
		
		String response = given()
				.headers(headers)
				.when()
				.get(url+"/1")
				.thenReturn()
				.body()
				.asString();
		System.out.println(response);
				
	}
	
	//Validating the response content
	@Test
	public void testResponseContent() {
		
		      String response =   given()
				.accept(ContentType.JSON)
				.when()
				.get(url+"/1")
				.thenReturn()
				.asString();
		      
		      JsonPath json = new JsonPath(response);
		        
		        Assert.assertEquals("George", json.getString("data.first_name"));
				
				
	}
	/*
	//Validating the xml response content
		@Test
		public void testResponseContentInXml() {
			
			         given()
					.accept(ContentType.XML)
					.when()
					.get(url+"/1")
					.then()
					.assertThat()
					.body("data.first_name",equalToIgnoringCase("George"),"data.last_name",equalToIgnoringCase("Bluth"))
					.and()
					.assertThat()
					.body("data", hasSize(3));
					
					
		}
	*/
	//Validating the response content
		@Test
		public void testResponseContentWithJsonPath() {
			
			        String s = given()
					.accept(ContentType.JSON)
					.when()
					.get(url+"/1")
					.thenReturn()
					.asString();
			        JsonPath json = new JsonPath(s);
			        
			        Assert.assertEquals("George", json.getString("data.firstName"));
		}
}
