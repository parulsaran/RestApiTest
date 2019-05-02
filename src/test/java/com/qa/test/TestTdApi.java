package com.qa.test;

import static io.restassured.RestAssured.*;
//import org.junit.Before;
//import org.junit.Test;

import com.qa.base.TestBaseClass;
import com.qa.util.ReadWriteToJson;

import io.restassured.http.ContentType;
import junit.framework.Assert;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestTdApi extends TestBaseClass {
	
	
	ReadWriteToJson readWriteUtil;
	String databaseName,tableName,ownerName;
	String createDatabase,deleteDatabase,verifyDatabase;
	String verifyTableName,databaseList,accountOwner;
	
	//@Before
	@BeforeClass
	public void setUp() {
		
	    readWriteUtil = new ReadWriteToJson();
	    
	    JSONObject jsonObject = readWriteUtil.readFromJsonFile("exampleTestScenario.json");
	    createDatabase = (String) jsonObject.get("CreateDatabase");
	    deleteDatabase = (String) jsonObject.get("DeleteDatabase");
	    verifyDatabase = (String) jsonObject.get("VerifyDatabase");
		verifyTableName = (String) jsonObject.get("VerifyTableName");
		databaseList = (String) jsonObject.get("DatabaseList");
		accountOwner =(String) jsonObject.get("AccountOwner");
	}
	
	//Test1.Test to verify the status code for get request
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
	
	//Test2.Test to verify the Json response for database list
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
		.body("databases[0].name", equalTo(databaseList));
		
	}
	
	//Test3.Test to verify  the Json response for account list
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
			.body("owner.name",equalTo(accountOwner));
			
		}
		
  
		//Test4. to create a new database Post request
       //Database name has to be unique or test fails
		@Test
		public void testCreateDatabase() {
			
			url = apiUrl + createDbUrl;
				
			 int response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.pathParam("database", createDatabase)
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
					.pathParam("database", deleteDatabase)
					.when()
					.post(url)
					.thenReturn()
					.statusCode();
			 Assert.assertEquals(200, response);
			 
		}
		
		//Test6. To get table in the specified database(get request )
		@Test
		public void testGetTableInDatabase() {
			
			url = apiUrl + createTableUrl;
			
			given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.pathParam("database", verifyDatabase)
			.when()
			.get(url)
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.body("tables[0].name",equalTo(verifyTableName));
			
		}
		
		@AfterClass
		public void afterTestRun() {
			////delete database created
		}
		
		
		
		

}
