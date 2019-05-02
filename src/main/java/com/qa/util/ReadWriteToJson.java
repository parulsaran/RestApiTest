package com.qa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadWriteToJson {

	String writeLocation = "/Users/parulsaran/eclipse-workspace/restapi/src/main/java/com/qa/optput/";
	String readLocation = "/Users/parulsaran/eclipse-workspace/restapi/src/main/java/com/qa/input/";
	
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject;
	
	public void writeToJsonFile(String filename,String response) {
		try {
			FileWriter file = new FileWriter(writeLocation+filename);
			file.write(response);
			file.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public JSONObject readFromJsonFile(String filename) throws FileNotFoundException, IOException, ParseException {
	    try(FileReader reader = new FileReader(readLocation + filename)) {
		    Object obj = jsonParser.parse(reader);
		    jsonObject = (JSONObject)obj;
		    System.out.println(jsonObject.toString());
	       return jsonObject;
        }
  }
}
