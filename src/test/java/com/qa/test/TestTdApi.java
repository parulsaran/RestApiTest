package com.qa.test;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.qa.base.TestBaseClass;
import com.qa.util.ReadWriteToJson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class TestTdApi extends TestBaseClass {
	
	
	ReadWriteToJson readWriteUtil;
	
	@Before
	public void setUp() {
		
	    readWriteUtil = new ReadWriteToJson();
	}
	//Test1.Test to verify the status code
    @Test
	public void testToVerifyStatus() {
		url = apiUrl + serviceUrl;
		int response = given()
		.header("Authorization",apiKey)
		.accept(ContentType.JSON)
		.when()
		.get(url)
		.getStatusCode();
		Assert.assertEquals(200, response);
	}
	
	//Test2.Test to print the Json response for database list
    @Test
	public void testGetForDatabaseList() {
		url = apiUrl + serviceUrl;
		String response = given()
		.header("Authorization",apiKey)
		.accept(ContentType.JSON)
		.when()
		.get(url)
		.prettyPrint().toString();
		
		//writing output to json file
		readWriteUtil.writeToJsonFile("databaseoutput.json",response);
	}
	
	//Test2.Test to print the Json response
    @Test
		public void testGetForAccountList() {
			url = apiUrl + accountUrl;
			String response = given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.when()
			.get(url)
			.prettyPrint().toString();
			
			//writing output to json file
			readWriteUtil.writeToJsonFile("accountOutput.json",response);
			
		}
		
  
		//Test to create a database (Post request)
		/*@SuppressWarnings("unchecked")
		@Test
		public void testCreateDatabase() {
			
			url = apiUrl + createDbUrl;
			
			String response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.queryParam("databsename", "test-parul")
					.when()
					.get(url)
					.prettyPrint();
					
			System.out.println(response);
			
			/*JSONObject jObject = new JSONObject();
			/*jObject.put("name","test-test");
			jObject.put("created_at"," ");
			jObject.put("updated_at"," ");
			jObject.put("count",0);
			jObject.put("organization",null);
			jObject.put("permission","administrator");
			jObject.put("delete_protected","false");
			
			Response response = given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.body(jObject.toJSONString())
			.post(url);
			int code = response.getStatusCode();
			Assert.assertEquals(201, code);
		}*/
		
		

}
