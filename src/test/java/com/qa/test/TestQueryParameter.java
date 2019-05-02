package com.qa.test;
import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.qa.base.TestBaseClass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

public class TestQueryParameter extends TestBaseClass {
	/*
	 * Given Accept the content in json format
	 * and Id is 1
	 * and first name is "George"
	 * then status code should be 200
	 * and response content should have id 1
	 */
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
	
	@Test
	public void testQueryParameter() {
		
		String response = given()
		.param("id", 1)
		.when()
		.get(url)
		.thenReturn()
		.asString();
		System.out.println(response);
		JsonPath json = new JsonPath(response);
	        
	        Assert.assertEquals(1, json.getString("data.id"));
		
		
		
	}

}
