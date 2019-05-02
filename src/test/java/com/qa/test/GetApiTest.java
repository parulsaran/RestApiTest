package com.qa.test;


import org.junit.Before;
import org.junit.Test;

import com.qa.base.TestBaseClass;
import com.qa.client.RestClient;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetApiTest extends TestBaseClass{
	
	TestBaseClass testBase;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	
	@Before
	public void setUp() {
		testBase = new TestBaseClass();
		apiUrl = prop.getProperty("URL");
		serviceUrl = prop.getProperty("serviceURL");
		url = apiUrl + serviceUrl;
		

	}
	
	@Test
	public void getTest() {
	    restClient = new RestClient();
		restClient.get(url);
		
	}
	

	@Test
	public void test_numberOgCircuitsFor2017_Season() {
		given().
		when().
		get("http://ergast.com/api/f1/2017/circuits.json").
		then().
		assertThat().
		statusCode(200);
		
	}
}
