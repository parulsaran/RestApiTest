package com.qa.test;

import static io.restassured.RestAssured.*;
import org.junit.Before;
import org.junit.Test;

import com.qa.base.TestBaseClass;
import com.qa.util.ReadWriteToJson;

import io.restassured.http.ContentType;
import junit.framework.Assert;
import static org.hamcrest.Matchers.equalTo;

//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;


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
		.thenReturn()
		.getStatusCode();

		Assert.assertEquals(200, response);
	}
	
	//Test2.Test to print the Json response for database list
    @Test
	public void testGetForDatabaseList() {
		url = apiUrl + serviceUrl;
		 given()
		.header("Authorization",apiKey)
		.accept(ContentType.JSON)
		.when()
		.get(url)
		.then()
		.assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.body("databases[0].name", equalTo("no_access"));
		
		//.prettyPrint().toString();
		//writing output to json file
		//readWriteUtil.writeToJsonFile("databaseoutput.json",response);
	}
	
	//Test3.Test to print the Json response
    @Test
		public void testGetForAccountList() {
			url = apiUrl + accountUrl;
			given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.when()
			.get(url)
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.body("owner.name",equalTo("Kazuki Ota"));
			
			//writing output to json file
			//readWriteUtil.writeToJsonFile("accountOutput.json",response);
		}
		
  
		//Test4. to create a new database Post request
       //Database name has to be unique or test fails
		@Test
		public void testCreateDatabase() {
			
			url = apiUrl + createDbUrl;
			
			 int response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.pathParam("database", "hello_test"+ 11)
					.when()
					.post(url)
					.thenReturn()
					.statusCode();
			 Assert.assertEquals(200, response);
			 
		}
		//Test5. To delete a database (Post request)
		//Database name should exist or test fails
		@Test
		public void testDeleteDatabase() {
			
			url = apiUrl + deleteUrl;
			
			 int response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.pathParam("database", "hello_test10")
					.when()
					.post(url)
					.thenReturn()
					.statusCode();
			 Assert.assertEquals(200, response);
			 
		}
		
		

}
