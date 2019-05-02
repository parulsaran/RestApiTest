package com.qa.test;

import static io.restassured.RestAssured.*;
//import org.junit.Before;
//import org.junit.Test;

import com.qa.base.TestBaseClass;
import com.qa.util.ReadWriteToJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestTdApi extends TestBaseClass {
	
	
	ReadWriteToJson readWriteUtil;
	String databaseName,tableName,ownerName;
	String createDatabase,deleteDatabase,verifyDatabase;
	String verifyTableName,databaseList,accountOwner;
	
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = baseUrl;
	    readWriteUtil = new ReadWriteToJson();
	    
	    readJsonInputFile();
	}

	
	//Test1.Test to verify the status code for get request
    @Test
	public void testToVerifyStatus() {
		
		int response = given()
		.header("Authorization",apiKey)
		.accept(ContentType.JSON)
		.when()
		.get(serviceUrl)
		.thenReturn()
		.getStatusCode();

		Assert.assertEquals(200, response);
	}
	
	//Test2.Test to verify the Json response for database list
    //verifies the status code is 200 and content Type is Json
    //verifies this particular database is in the list
    @Test
	public void testGetForDatabaseList() {
    	
		 given()
		.header("Authorization",apiKey)
		.accept(ContentType.JSON)
		.when()
		.get(serviceUrl)
		.then()
		.assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.body("databases[0].name", equalTo(databaseList));
		
		  
	}
	
	//Test3.Test to verify  the Json response for account list
    //verifies the status code is 200 and content Type is Json
    //verifies this particular account is in the list
    @Test
		public void testGetForAccountList() {
			
			given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.when()
			.get(accountUrl)
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.body("owner.name",equalTo(accountOwner));
			
		}
		
  
	   //Test4. to create a new database using Post request
       //Database name has to be unique or test fails
       //verify the status code is 200
		@Test
		public void testCreateDatabase() {
			
				
			 int response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.pathParam("database", createDatabase)
					.when()
					.post(createDbUrl)
					.thenReturn()
					.statusCode();
			 Assert.assertEquals(200, response);
			 
		}
		//Test5. To delete a database using Post request
		//Database name should exist or test fails
		//verify the delete is successful
		@Test
		public void testDeleteDatabase() {
			
			
			 int response = given()
					.header("Authorization",apiKey)
					.accept(ContentType.JSON)
					.pathParam("database", deleteDatabase)
					.when()
					.post(deleteUrl)
					.thenReturn()
					.statusCode();
			 Assert.assertEquals(200, response);
			 
		}
		
		//Test6. To verify the table exist in the specified database(Get request)
		//Verify the status code is 200 and contentType is Json
		//Verify that this table exist in the database
		@Test
		public void testGetTableInDatabase() {
			
			
			given()
			.header("Authorization",apiKey)
			.accept(ContentType.JSON)
			.pathParam("database", verifyDatabase)
			.when()
			.get(createTableUrl)
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.body("tables[0].name",equalTo(verifyTableName));
			
		}
		
		@AfterClass
		public void afterTestRun() {
			////delete resources created
		}
		//read Json test scenarios input file
		private void readJsonInputFile() {
			JSONObject jsonObject = readWriteUtil.readFromJsonFile("exampleTestScenario.json");
		    createDatabase = (String) jsonObject.get("CreateDatabase");
		    deleteDatabase = (String) jsonObject.get("DeleteDatabase");
		    verifyDatabase = (String) jsonObject.get("VerifyDatabase");
			verifyTableName = (String) jsonObject.get("VerifyTableName");
			databaseList = (String) jsonObject.get("DatabaseList");
			accountOwner =(String) jsonObject.get("AccountOwner");
		}
		
		

}
