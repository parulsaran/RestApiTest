package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.qa.util.ReadWriteToJson;

public class TestBaseClass {
	public Properties prop;
	
	protected String apiUrl;
	protected String serviceUrl,accountUrl,createDbUrl,deleteUrl,createTableUrl;
	protected String url;

	protected String apiKey;
	ReadWriteToJson readWriteUtil;
	JSONObject jsonObject;
	
	public TestBaseClass() {
		
		readWriteUtil = new ReadWriteToJson();
		
		    jsonObject = readWriteUtil.readFromJsonFile("database_input.json");
			
		    apiUrl = (String) jsonObject.get("URL");
			apiKey = (String) jsonObject.get("ApiKey");
			serviceUrl = (String) jsonObject.get("serviceURL");
			accountUrl = (String) jsonObject.get("accountURL");
			createDbUrl = (String) jsonObject.get("createDbURL");
			deleteUrl = (String) jsonObject.get("deleteURL");
			createTableUrl =(String) jsonObject.get("createTableURL");
		
		 
			
	}
}

